
package sockets;

import GUI.ControladorPrincipal;
import aplicacion.Programa;
import static aplicacion.Programa.controladorPrincipal;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Manuel Arias & Justin Bogantes
 * @since 23/10/18
 * @version 1.0
 */
public class Cliente {
    
    private DataOutputStream flujo_salida = null;
    private DataInputStream flujo_entrada = null;
    private Socket cliente = null;
    private ControladorPrincipal controlador;
    
    public Cliente(){
    }
    
    public Cliente(ControladorPrincipal miControlador){
        this.setControlador(miControlador);
    }
    
    public void establecerConexion(){
        try{
            cliente = new Socket("localhost",7778);
            flujo_salida = new DataOutputStream(cliente.getOutputStream());
            flujo_entrada = new DataInputStream(cliente.getInputStream());
        }
        catch (IOException e){
            JOptionPane.showMessageDialog(null, "El servidor no se encuentra habilitado", "Problema", 0);
            System.out.println("El servidor no se encuentra habilitado");
        }
        new HiloCliente(flujo_entrada, this, flujo_salida).start();
    }
    
    public void solicitarCarta(){
        try{
            flujo_salida.write(2);
        }
        catch (IOException e){
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void quedarse(){
        try {
            flujo_salida.write(0);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviarEstado() {
           try {
               System.out.println(Programa.controladorPrincipal.getVentanaDeJuego().getEstado());
            if (Programa.controladorPrincipal.getVentanaDeJuego().getEstado() == 0) {
                flujo_salida.write(1);
            }
            else{
                flujo_salida.write(0);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void verificarEstado() {
        try {
            switch(controladorPrincipal.getVentanaDeJuego().getEstado()){
                case 0:
                    flujo_salida.write(0);
                    break;
                case 1:
                    flujo_salida.write(1);
                    break;
                case 2:
                    flujo_salida.write(2);
                    break;
                case 3:
                    flujo_salida.write(3);
                    break;
                 
            }
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DataOutputStream getFlujo_salida() {
        return flujo_salida;
    }

    public void setFlujo_salida(DataOutputStream flujo_salida) {
        this.flujo_salida = flujo_salida;
    }

    public DataInputStream getFlujo_entrada() {
        return flujo_entrada;
    }

    public void setFlujo_entrada(DataInputStream flujo_entrada) {
        this.flujo_entrada = flujo_entrada;
    }

    public Socket getCliente() {
        return cliente;
    }

    public void setCliente(Socket cliente) {
        this.cliente = cliente;
    }

    public ControladorPrincipal getControlador() {
        return controlador;
    }

    public void setControlador(ControladorPrincipal controlador) {
        this.controlador = controlador;
    }
}
