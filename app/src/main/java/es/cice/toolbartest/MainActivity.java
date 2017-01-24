package es.cice.toolbartest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import es.cice.toolbartest.adapters.CarAdapter;
import es.cice.toolbartest.model.Car;

public class MainActivity extends AppCompatActivity {
    private CarAdapter adapter;
    private ActionBar aBar;
    public static final String TAG= "MainActivity";
    private EditText searchET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar) findViewById(R.id.includedToolbar);
        setSupportActionBar(toolbar);
        aBar =getSupportActionBar();
        RecyclerView carRV=(RecyclerView) findViewById(R.id.carRV);
        adapter = new CarAdapter(this, getData());
        carRV.setAdapter(adapter);
        //le dice cómo tiene que mostrar las filas. con un linearlayout
        carRV.setLayoutManager(new LinearLayoutManager(this));
        //filter va a llamar a performFiltering(filtramos los datos) y publishResults
        //adapter.getFilter().filter("dkjf");

    }
    private List<Car> getData()
    {
        List<Car> list = new ArrayList<>();
        list.add(new Car("bla bla bla","Peugeot", R.drawable.vehiculo1, R.drawable.vehiculo1_thumb, "307"));
        list.add(new Car("bla bla bla","Renault", R.drawable.vehiculo2, R.drawable.vehiculo2_thumb, "357"));
        list.add(new Car("bla bla bla","Peugeot", R.drawable.vehiculo3, R.drawable.vehiculo3_thumb, "107"));
        list.add(new Car("bla bla bla","BMW", R.drawable.vehiculo4, R.drawable.vehiculo4_thumb, "207"));
        list.add(new Car("bla bla bla","Peugeot", R.drawable.vehiculo5, R.drawable.vehiculo5_thumb, "407"));
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //creamos el menú
        Log.d(TAG, "onCreateOpcionMenu");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.searchIT:
                Log.d(TAG, "Search item...");
                //para que aparezca una vista
                aBar.setDisplayShowCustomEnabled(true);
                //esta vista es la que se habilita
                aBar.setCustomView(R.layout.search_layout);
                //para q desaparezca el título
                aBar.setDisplayShowTitleEnabled(false);
                searchET=
                        (EditText) aBar.getCustomView().findViewById(R.id.searchET);

                searchET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    //el primer parámetro es el texto que estamos editando
                    //ESTE EVENTO se produce cada vez que pulsamos una tecla o acciones (iconos verdes, ej lupa)
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId== EditorInfo.IME_ACTION_SEARCH){
                          CharSequence searchText=searchET.getText();
                            //empezar la busqueda y hacer desaparecer el teclado
                            Log.d(TAG, "search text ...." + searchText);
                            //Hacer desaparecer el teclado, llamamos a un servicio del sistema q se encarga de hacer aparecer/desaparecer el teclado
                            InputMethodManager imn=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                            imn.hideSoftInputFromWindow(searchET.getWindowToken(),0);
                            //volvemos a poner el menú
                            aBar.setDisplayShowCustomEnabled(false);
                            //para q desaparezca el título
                            aBar.setDisplayShowTitleEnabled(true);
                            adapter.getFilter().filter(searchText);
                            //empezar la busqueda. Mostramos lista de vehículos

                            return true;
                        }
                        return false;

                    }
                });
                //mostamos el teclado
                InputMethodManager imn=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imn.showSoftInput(searchET, InputMethodManager.SHOW_IMPLICIT);
                searchET.requestFocus();
                break;
            case R.id.settingIT:
                Log.d(TAG, "Setting item...");
                break;
            case R.id.aboutIT:
                Log.d(TAG, "About...");
                //TextView txt = (TextView)findViewById(R.id.txtDesc);
                //txt.setText("Esta aplicación muestra una barra de herramientas.");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
