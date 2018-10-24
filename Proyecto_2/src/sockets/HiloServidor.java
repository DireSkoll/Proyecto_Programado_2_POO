
package sockets;

import cartas.Baraja;
import cartas.Carta;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manuel Arias & Justin Bogantes
 * @since 23/10/18
 * @version 1.0
 */
public class HiloServidor extends Thread{
    private Baraja baraja;

    private int estadoJ1 = 0;
    private int estadoJ2 = 0;
    private int estadoJ3 = 0;

    private Socket cliente = null;
    private Socket cliente2 = null;
    private Socket cliente3 = null;

    private DataInputStream entrada = null;
    private DataOutputStream salida = null;

    private DataInputStream entrada2 = null;
    private DataOutputStream salida2 = null;	

    private DataInputStream entrada3 = null;
    private DataOutputStream salida3 = null;	

    private boolean ejecucionHilo = true;

    private Servidor servidor;

    public HiloServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public HiloServidor(Socket cliente, Servidor serv) throws IOException {
        this.cliente = cliente;
        this.servidor = serv;
        this.salida = new DataOutputStream(cliente.getOutputStream());
        this.entrada = new DataInputStream(cliente.getInputStream());
    }

    public Socket getCliente() {
        return cliente;
    }

    public void setCliente(Socket cliente) {
        this.cliente = cliente;
    }

    public Socket getCliente2() {
        return cliente2;
    }

    public void setCliente2(Socket cliente2) {
        this.cliente2 = cliente2;
    }

    public Socket getCliente3() {
        return cliente3;
    }

    public void setCliente3(Socket cliente3) {
        this.cliente3 = cliente3;
    }

    public DataInputStream getEntrada() {
        return entrada;
    }

    public void setEntrada(DataInputStream entrada) {
        this.entrada = entrada;
    }

    public DataOutputStream getSalida() {
        return salida;
    }

    public void setSalida(DataOutputStream salida) {
        this.salida = salida;
    }

    public DataInputStream getEntrada2() {
        return entrada2;
    }

    public void setEntrada2(DataInputStream entrada2) {
        this.entrada2 = entrada2;
    }

    public DataOutputStream getSalida2() {
        return salida2;
    }

    public void setSalida2(DataOutputStream salida2) {
        this.salida2 = salida2;
    }

    public DataInputStream getEntrada3() {
        return entrada3;
    }

    public void setEntrada3(DataInputStream entrada3) {
        this.entrada3 = entrada3;
    }

    public DataOutputStream getSalida3() {
        return salida3;
    }

    public void setSalida3(DataOutputStream salida3) {
        this.salida3 = salida3;
    }

    public boolean isEjecucionHilo() {
        return ejecucionHilo;
    }

    public void setEjecucionHilo(boolean ejecucionHilo) {
        this.ejecucionHilo = ejecucionHilo;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public void asignacionCliente2(Socket eCliente2) throws IOException {
        this.cliente2 = eCliente2;
        this.salida2 = new DataOutputStream(cliente2.getOutputStream());
        this.entrada2 = new DataInputStream(cliente2.getInputStream());
    }

    public void asignacionCliente3(Socket eCliente3) throws IOException {
        this.cliente3 = eCliente3;
        this.salida3 = new DataOutputStream(cliente3.getOutputStream());//comunic
        this.entrada3 = new DataInputStream(cliente3.getInputStream());//comunic
    }

    @Override
    public void run() {

        baraja = new Baraja(true);

        EnviarCartasJugadoresInicio();
        EnviarCartasJugadoresInicio();

        int seleccion = 0;
        boolean ganador = false;

        while (ejecucionHilo) {
            try {
                if (estadoJ1 == 0) {
                    salida.writeInt(4);
                    seleccion = entrada.read();
                    Ejecucion(1, seleccion, salida);
                }

                salida.writeInt(6);
                estadoJ1 = entrada.read();

                if (estadoJ2 == 0) {
                    salida2.writeInt(4);
                    seleccion = entrada2.read();
                    Ejecucion(2, seleccion, salida2);
                }

                salida2.writeInt(6);
                estadoJ2 = entrada2.read();

                if (estadoJ3 == 0) {
                    salida3.writeInt(4);
                    seleccion = entrada3.read();
                    Ejecucion(3, seleccion, salida3);
                }

                salida3.writeInt(6);
                estadoJ3 = entrada3.read();

                if (RevisarEstados()) {
                    ejecucionHilo = false;
                }
            } catch (IOException ex) {
                System.out.println("Error con la informacion que se recibio");
            }
        }
        finalJuego();
    }

    public void Ejecucion(int Jugador, int seleccion, DataOutputStream salidaCliente) {
        switch (seleccion) {
            case 0:
                MensajeServidor("Jugador " + Jugador + " Paso su Turno");
                break;
            case 2:
                MensajeServidor("Jugador " + Jugador + " Pidio una carta");
                EnviarCarta(Jugador);
                break;
        }

    }

    public void EnviarCartasJugadoresInicio() {

        Carta temp;
        temp = baraja.repartirSiguienteCarta();
        EnviarCartaJugador1(temp);
        temp = baraja.repartirSiguienteCarta();
        EnviarCartaJugador2(temp);
        temp = baraja.repartirSiguienteCarta();
        EnviarCartaJugador3(temp);

        MensajeServidor("Se envio una carta a todos los Jugadores\n");
    }
    
    public void EnviarCartaJugador1(Carta entrada) {
        try {
            salida.writeInt(3);
            salida.writeUTF(entrada.getId());
            salida.write(entrada.getValor());
            MensajeServidor("Se envio una carta al Jugador 1\n");
        } catch (IOException ex) {
            Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void EnviarCartaJugador2(Carta entrada) {
        try {
            salida2.writeInt(3);
            salida2.writeUTF(entrada.getId());
            salida2.write(entrada.getValor());
            MensajeServidor("Se envio una carta al Jugador 2\n");

        } catch (IOException ex) {
            Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void EnviarCartaJugador3(Carta entrada) {
        try {
            salida3.writeInt(3);
            salida3.writeUTF(entrada.getId());
            salida3.write(entrada.getValor());
            MensajeServidor("Se envio una carta al Jugador 3\n");
        } catch (IOException ex) {
            Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void EnviarCarta(int Jugador) {
        Carta temp;
        temp = baraja.repartirSiguienteCarta();
        switch (Jugador) {
            case 1:
                EnviarCartaJugador1(temp);
                break;

            case 2:
                EnviarCartaJugador2(temp);
                break;

            case 3:
                EnviarCartaJugador3(temp);
                break;
        }
    }

    public boolean RevisarEstados() {

        if (estadoJ1 != 0 && estadoJ2 != 0 && estadoJ3 != 0) {
            return true;
        }
        return false;
    }

    public void MensajeServidor(String mensaje) {
        servidor.EscribirMensajeServidor(mensaje);
    }

    public void finalJuego() {
        try {
            salida.writeInt(7);
            salida2.writeInt(7);
            salida3.writeInt(7);

            if (estadoJ1 == 3) {
                salida.writeInt(0);
            } else {
                if (estadoJ1 == 1) {
                    if (estadoJ2 == 1 || estadoJ3 == 1) {
                        salida.writeInt(2);
                    } else {
                        salida.writeInt(1);
                    }
                } else {
                    if (estadoJ2 == 1 || estadoJ3 == 1) {
                        salida.writeInt(0);
                    } else {
                        if (estadoJ2 == 2 || estadoJ3 == 2) {
                            salida.writeInt(2);
                        } else {
                            salida.writeInt(1);
                        }
                    }
                }
            }

            if (estadoJ2 == 3) {
                salida2.writeInt(0);
            } else {
                if (estadoJ2 == 1) {
                    if (estadoJ1 == 1 || estadoJ3 == 1) {
                        salida2.writeInt(2);
                    } else {
                        salida2.writeInt(1);
                    }
                } else {
                    if (estadoJ1 == 1 || estadoJ3 == 1) {
                        salida2.writeInt(0);
                    } else {
                        if (estadoJ1 == 2 || estadoJ3 == 2) {
                            salida2.writeInt(2);
                        } else {
                            salida2.writeInt(1);
                        }
                    }
                }
            }
            if (estadoJ3 == 3) {
                salida3.writeInt(0);
            } else {
                if (estadoJ3 == 1) {
                    if (estadoJ1 == 1 || estadoJ2 == 1) {
                        salida3.writeInt(2);
                    } else {
                        salida3.writeInt(1);
                    }
                } else {
                    if (estadoJ1 == 1 || estadoJ2 == 1) {
                        salida3.writeInt(0);
                    } else {
                        if (estadoJ1 == 2 || estadoJ2 == 2) {
                            salida3.writeInt(2);
                        } else {
                            salida3.writeInt(1);
                        }
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
