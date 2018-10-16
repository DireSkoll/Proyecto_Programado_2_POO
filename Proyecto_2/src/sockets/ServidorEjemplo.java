package sockets;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorEjemplo  {

	public static void main(String[] args) {
		MarcoServidorEjemplo mimarco=new MarcoServidorEjemplo();
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}
}

class MarcoServidorEjemplo extends JFrame implements Runnable{	
	public MarcoServidorEjemplo(){
		setBounds(1200,300,280,350);					
		JPanel milamina= new JPanel();
		milamina.setLayout(new BorderLayout());
		areatexto=new JTextArea();
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
                Socket miSocket = servidor.accept();
                DataInputStream flujo_entrada = new DataInputStream(miSocket.getInputStream());
                String mensaje = flujo_entrada.readUTF();
                areatexto.append("\n" + mensaje);
                miSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(MarcoServidor.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
            }
        }
}
