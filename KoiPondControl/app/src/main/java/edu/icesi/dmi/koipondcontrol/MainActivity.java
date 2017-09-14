package edu.icesi.dmi.koipondcontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    private ComunicacionCliente cc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cc = new ComunicacionCliente();

        cc.addObserver(this);
        //cc.enviarMensaje("arriba");
    }

    @Override
    public void update(Observable observable, Object o) {

    }

    //----------FINAL DE LA CLASE--------//
}
