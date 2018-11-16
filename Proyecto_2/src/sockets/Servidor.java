
package sockets;

import GUI.VentanaServidor;
import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import usuarios.Jugador;

/**
 *
 * @author Manuel Arias & Justin Bogantes
 * @since 16/10/18
 * @version 1.1
 */
public class Servidor {
    
    private DataInputStream entrada;
    private DataOutputStream salida;

    private Socket cliente1;
    private Socket cliente2;
    private Socket cliente3;

    private VentanaServidor ventana;

    private HiloServidor hiloServidor;
    private HiloServidor user2;
    private HiloServidor user3;

    public Servidor(VentanaServidor entradaVentana) {
        this.ventana = entradaVentana;
    }

    public void runServer() {
        ServerSocket ss;
        try {
            ss = new ServerSocket(7778);

            while (true) {
                cliente1 = ss.accept();
                System.out.println("Se ha conectado el primer jugador!");
                EscribirMensajeServidor("Se ha conectado el primer jugador!");

                hiloServidor = new HiloServidor(cliente1, this);
                hiloServidor.getSalida().writeInt(0);
                hiloServidor.getSalida().writeInt(1);

                cliente2 = ss.accept();
                System.out.println("Se ha conectado el segundo jugador!");
                EscribirMensajeServidor("Se ha conectado el segundo jugador!");

                hiloServidor.asignacionCliente2(cliente2);

                hiloServidor.getSalida2().writeInt(0);
                hiloServidor.getSalida2().writeInt(2);

                hiloServidor.getSalida().writeInt(1);

                hiloServidor.getSalida().writeInt(2);

                cliente3 = ss.accept();
                System.out.println("Se ha conectado el tercer jugador!");
                EscribirMensajeServidor("Se ha conectado el tercer jugador!");

                hiloServidor.asignacionCliente3(cliente3);

                hiloServidor.getSalida3().writeInt(0);

                hiloServidor.getSalida3().writeInt(3);

                hiloServidor.getSalida().writeInt(1);

                hiloServidor.getSalida().writeInt(3);

                hiloServidor.getSalida2().writeInt(1);

                hiloServidor.getSalida2().writeInt(3);

                hiloServidor.getSalida().writeInt(2);
                hiloServidor.getSalida2().writeInt(2);
                hiloServidor.getSalida3().writeInt(2);

                hiloServidor.start();

                EscribirMensajeServidor("------------------------");
                EscribirMensajeServidor("---------Inicio---------");
                EscribirMensajeServidor("------------------------");
            }
        } catch (IOException ex) {
            System.out.println("Hubo un error, revise el estado del servidor");
        }
    }

    public void EscribirMensajeServidor(String entrada) {
        ventana.agregarMensaje(entrada);
    }    
}
