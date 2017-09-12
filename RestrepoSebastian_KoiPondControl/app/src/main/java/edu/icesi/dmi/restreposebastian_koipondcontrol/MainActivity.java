package edu.icesi.dmi.restreposebastian_koipondcontrol;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements /*View.OnClickListener,*/ Observer {

    Button botonArriba, botonIzq, botonDer, botonAbajo, botonConectar;
    ComunicacionCliente cc;
    String arriba, derecha, izquierda, abajo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("System.out","Hello!");
        System.out.print("Inició");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonArriba = (Button) findViewById(R.id.botonArriba);
        botonAbajo = (Button) findViewById(R.id.botonAbajo);
        botonDer = (Button) findViewById(R.id.botonDer);
        botonIzq = (Button) findViewById(R.id.botonIzq);
        botonConectar = (Button) findViewById(R.id.botonConectar);
        cc = new ComunicacionCliente();
        Thread t = new Thread(cc);
        t.start();

        arriba = "arriba";
        derecha = "derecha";
        izquierda = "izquierda";
        abajo = "abajo";

        //Arriba
        botonArriba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.print("Presionó botón arriba");
                Log.i("System.out","Presionó botón arriba");
                cc.enviarMensaje(arriba);
            }
        });
        //Derecha
        botonDer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.print("Presionó botón derecha");
                Log.i("System.out","Presionó botón derecha");
                cc.enviarMensaje(derecha);
            }
        });
        //Izquierda
        botonIzq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.print("Presionó botón abajo");
                Log.i("System.out","Presionó botón abajo");
                cc.enviarMensaje(izquierda);
            }
        });
        //Abajo
        botonIzq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.print("Presionó botón izquierda");
                Log.i("System.out","Presionó botón izquierda");
                cc.enviarMensaje(abajo);
            }
        });

    }

/*
    @Override
    public void onClick(View view) {
        Log.i("System.out","Presionó");
        switch (view.getId()){
            case R.id.botonArriba:
                System.out.print("Presionó botón arriba");
                Log.i("System.out","Presionó botón arriba");
                cc.enviarMensaje(arriba);
                break;
            case R.id.botonDer:
                break;
            case R.id.botonIzq:
                break;
            case R.id.botonAbajo:
                break;
        }
    }
    */

    @Override
    public void update(Observable observable, Object o) {

    }


    //------FINAL DE LA CLASE MAIN ACTIVITY-----//
}
