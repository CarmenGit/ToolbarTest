package es.cice.toolbartest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {
    public static final String EXTRA_KEY="key";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Intent intent = getIntent();
        //las claves se suelen dclarar como constantes
        String value=intent.getStringExtra("EXTRA_KEY");
        TextView tv=(TextView) findViewById(R.id.txtAbout);
        tv.setText(value);
    }
}
