
package sockets;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
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
 */
public class Servidor {
    
    public static void main(String[] args) {
		MarcoServidor mimarco=new MarcoServidor();
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}  
  
    public static void repartirCartasIniciales(){
            GameState.iniciarJuego();
            System.out.println("Se han repartido 2 cartas a las manos de cada jugador.");
            System.out.println("Las manos actuales de cada jugador son: ");
            System.out.println();
            GameState.jugador1.imprimirMano(true);
            System.out.println();
            GameState.jugador2.imprimirMano(true);
            System.out.println();
            GameState.jugador3.imprimirMano(true);
        }
}

class MarcoServidor extends JFrame implements Runnable{	
	public MarcoServidor(){
		setBounds(1200,300,280,350);					
		JPanel milamina= new JPanel();
		milamina.setLayout(new BorderLayout());
		areatexto=new JTextArea();
                areatexto.setEnabled(false);
		milamina.add(areatexto,BorderLayout.CENTER);
		add(milamina);
		setVisible(true);
		Thread miHilo = new Thread(this);
                miHilo.start();
	}
	
	private	JTextArea areatexto;
        
        public void run(){
            try {
                ServerSocket servidor = new ServerSocket(7777);
                while(true){
                Socket miSocket = servidor.accept();
                DataInputStream flujo_entrada = new DataInputStream(miSocket.getInputStream());
                String mensaje = flujo_entrada.readUTF();
                if (GameState.contador < 3){
                    Jugador jugador = new Jugador(mensaje);
                    areatexto.append("\nNuevo Jugador: " + jugador.getNombre());
                    System.out.println("Jugador Creado con exito!");
                    GameState.listaJugadores[GameState.contador] = jugador;
                    GameState.contador++;
                    GameState.cantJugadores++;
                } else {
                    System.out.println("Maxima cantidad de jugadores creados");
                }
                miSocket.close();   
                }
            } catch (IOException ex) {
                Logger.getLogger(MarcoServidor.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
            }
        }    
}
