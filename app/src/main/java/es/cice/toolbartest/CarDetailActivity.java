package es.cice.toolbartest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class CarDetailActivity extends AppCompatActivity {
    public static final String IMAGEN_EXTRA ="image";
    public static final String MODELO_EXTRA ="model";
    public static final String FABRICANTE_EXTRA ="fabricante";
    public static final String DESCRIPCION_EXTRA ="descripcion";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);
        Intent intent=getIntent();
        //el segundo parámetro de geinextra es en caso de que no exista el extra
        int imageResource =intent.getIntExtra(IMAGEN_EXTRA, 0);
        String modelo= intent.getStringExtra(MODELO_EXTRA);
        String fabricante=intent.getStringExtra(FABRICANTE_EXTRA);
        String descripcion=intent.getStringExtra(DESCRIPCION_EXTRA);
        ImageView carImage=(ImageView) findViewById(R.id.carImage);
        TextView carDescription=(TextView) findViewById((R.id.carDetails));
        if (imageResource!=0)
            carImage.setImageResource(imageResource);
        carDescription.setText("Fabricante: " + fabricante + "\n" + "Modelo: " + modelo + "Descripcion: " + descripcion);
    }
}
