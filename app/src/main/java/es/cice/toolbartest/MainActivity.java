package es.cice.toolbartest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity {

    public static final String TAG= "MainActivity";
    private EditText searchET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar) findViewById(R.id.includedToolbar);
        setSupportActionBar(toolbar);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //creamos el menú
        Log.d(TAG, "onCreateOpcionMenu");
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ActionBar aBar =getSupportActionBar();
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
                            String searchText=searchET.getText().toString();
                            //empezar la busqueda y hacer desaparecer el teclado
                            Log.d(TAG, "search text ...." + searchText);
                            //Hacer desaparecer el teclado
                            InputMethodManager imn=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                            imn.hideSoftInputFromWindow(searchET.getWindowToken(),0);
                            return true;
                        }
                        return false;

                    }
                });

                break;
            case R.id.settingIT:
                Log.d(TAG, "Setting item...");
                break;
            case R.id.aboutIT:
                Log.d(TAG, "About...");
                TextView txt = (TextView)findViewById(R.id.txtDesc);
                txt.setText("Esta aplicación muestra una barra de herramientas.");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
