
package sockets;

import cartas.Carta;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manuel Arias & Justin Bogantes
 * @since 23/10/18
 * @version 1.0
 */
public class HiloCliente extends Thread{
    
    private Cliente cliente;
    private int numJugadores;
    private boolean ejecutando = true;
    private DataOutputStream flujo_salida;
    private DataInputStream flujo_entrada;
    
    public HiloCliente(){
    }
    
    public HiloCliente(DataInputStream entrada, Cliente cliente, DataOutputStream salida){
        setFlujo_entrada(entrada);
        setCliente(cliente);
        setFlujo_salida(salida);
    }
    
    public void run() {
        int opcion = 0;

        while (ejecutando) {
            try {
                opcion = flujo_entrada.readInt();
                switch (opcion) {
                    case 0:
                        numJugadores = flujo_entrada.readInt();
                        cliente.getControlador().abrirVentanaDeEspera(numJugadores);
                        break;
   
                    case 1:
                        int temporal = flujo_entrada.readInt();
                        cliente.getControlador().actualizarVentanaDeEspera(temporal);
                        break;

                    case 2:
                         sleep(1000);
                         cliente.getControlador().abrirMesa();
                         break;
                    
                    case 3:
                        Carta nuevaCarta = new Carta();
                        nuevaCarta.setID(flujo_entrada.readUTF());
                        nuevaCarta.setValor(flujo_entrada.read());
                        System.out.println(nuevaCarta.toString());
                        cliente.getControlador().agregarCarta(nuevaCarta);
                        break;
                    
                    case 4:
                        cliente.getControlador().elegirRonda();
                        break;
                        
                    case 6:
                        cliente.verificarEstado();
                        break;
                    
                    case 7:
                        int finalJuego = flujo_entrada.readInt();
                        cliente.getControlador().abrirVentanaCierre(finalJuego);
                        sleep(5000);
                        cliente.getControlador().resetear();
                        break;      
                }
            } catch (IOException ex) {
                Logger.getLogger(HiloCliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getNumJugadores() {
        return numJugadores;
    }

    public void setNumJugadores(int numJugadores) {
        this.numJugadores = numJugadores;
    }

    public boolean isEjecutando() {
        return ejecutando;
    }

    public void setEjecutando(boolean ejecutando) {
        this.ejecutando = ejecutando;
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
}
