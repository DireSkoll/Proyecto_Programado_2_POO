
package sockets;

import cartas.Baraja;
import usuarios.Jugador;

/**
 *
 * @author Manuel Arias & Justin Bogantes
 * @since 16/10/18
 */
public class GameState {
    public static Jugador[] listaJugadores = new Jugador[3];
    public static int contador = 0;
    public static int contadorJA = 1;
    public static int cantJugadores = 0;
    
    static Baraja baraja1 = new Baraja(true);
    
    static Jugador jugador1 = listaJugadores[0];
    static Jugador jugador2 = listaJugadores[1];
    static Jugador jugador3 = listaJugadores[2];
    
    public static void iniciarJuego(){
        jugador1.agregarCarta(baraja1.repartirSiguienteCarta());
        jugador2.agregarCarta(baraja1.repartirSiguienteCarta());
        jugador3.agregarCarta(baraja1.repartirSiguienteCarta());
        
        jugador1.agregarCarta(baraja1.repartirSiguienteCarta());
        jugador2.agregarCarta(baraja1.repartirSiguienteCarta());
        jugador3.agregarCarta(baraja1.repartirSiguienteCarta());    
    } 
}
