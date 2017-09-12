package edu.icesi.dmi.restreposebastian_koipondcontrol;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Observable;

/**
 * Created by sebastianrestrepo on 9/09/17.
 */

public class ComunicacionCliente extends Observable implements Runnable {

    private Socket s;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    private boolean conectado;

//Hola
    public ComunicacionCliente(){
        conectado = false;

    }

    @Override
    public void run() {

        if(!conectado) {

            try {
                s = new Socket(InetAddress.getByName("172.30.159.213"), 8080);
                salida = new ObjectOutputStream(s.getOutputStream());
                entrada = new ObjectInputStream(s.getInputStream());
                conectado = true;
                setChanged();
                notifyObservers("conectado");
                clearChanged();
            } catch (IOException e) {
                e.printStackTrace();
            }


            while (conectado) {
                try {
                    recibirMensaje();
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }

    }

    public void recibirMensaje(){
        try {
            Object atraparObjeto = entrada.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void enviarMensaje(final Object obj){

        final Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //if (s.isConnected()) {
                while(conectado) {
                    try {
                        salida.writeObject(obj);
                        salida.flush();
                    } catch (IOException e) {
                        e.printStackTrace();

                    }
                }

                }
          //  }

        });
        t.start();


    }

    //------------FINAL DE LA CLASE COMUNICACIÓN CLIENTE-------//
}
