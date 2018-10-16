
package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

/**
 *
 * @author Manuel Arias & Justin Bogantes
 */
public class Cliente2 {
    
    public static void main(String args[]){
        String serverName = "192.168.56.1";//args[0];
        int port = 7874;//Integer.parseInt(args[1]);
        
        try {
            System.out.println("Conectandose a " + serverName + " al puerto " + port);
            Socket client = new Socket(serverName,port);
            
            System.out.println("Acaba de conectarse a " + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            
            out.writeUTF("Hola desde " + client.getLocalSocketAddress());
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            
            System.out.println("El servidor dice " + in.readUTF());
            client.close();
        }
        catch(UnknownHostException e) {
            System.out.println("No puedo encontrar " + e);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
