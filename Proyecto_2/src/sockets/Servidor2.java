
package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;



/**
 *
 * @author Manuel Arias & Justin Bogantes
 */
public class Servidor2 {
    private ServerSocket serverSocket;
    
    public Servidor2(int puerto, int tamanoCola) throws IOException {
        serverSocket = new ServerSocket(puerto, tamanoCola);
    }
    
    public void run(){
        Socket server;
        while (true){
            try {
                System.out.println("Esperando cliente en puerto: " + serverSocket.getLocalPort() + "...");
                server = serverSocket.accept();
                
                System.out.println("Se acaba de conectar: " + server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());
                
                System.out.println(in.readUTF());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("Gracias por conectarse a " + server.getLocalSocketAddress() + "\nAdios!");
                
                server.close();
            }
            
            catch (SocketTimeoutException s){
                System.out.println("Socket timed out!");
                break;
            }
            
            catch(IOException e){
                e.printStackTrace();
                break;
            }
        }
    }
    
    public static void main(String args[]){
        int puerto = 7874;//Integer.parseInt(args[0]);
        int cola = 1;//Integer.parseInt(args[1]);
        
        try {
            Servidor2 s = new Servidor2(puerto, cola);
            s.run();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    
}
