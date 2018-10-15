
package aplicacion;

import cartas.Baraja;
import java.util.Scanner;
import usuarios.Jugador;

/**
 *
 * @author Manuel Arias & Justin Bogantes
 * @since 14/10/18
 */
public class PruebaJugador {
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        Baraja baraja1 = new Baraja(true);
        Jugador jugador1 = new Jugador("Manuel Arias");
        Jugador jugador2 = new Jugador("Justin Bogantes");
        Jugador jugador3 = new Jugador("Carlos Alvarado");
        
        jugador1.agregarCarta(baraja1.repartirSiguienteCarta());
        jugador2.agregarCarta(baraja1.repartirSiguienteCarta());
        jugador3.agregarCarta(baraja1.repartirSiguienteCarta());
        
        jugador1.agregarCarta(baraja1.repartirSiguienteCarta());
        jugador2.agregarCarta(baraja1.repartirSiguienteCarta());
        jugador3.agregarCarta(baraja1.repartirSiguienteCarta());
        
        System.out.println("Se han repartido las cartas iniciales");
        
        jugador1.imprimirMano(true);
        jugador2.imprimirMano(true);
        jugador3.imprimirMano(true);
        
        boolean jugador1Terminado = false;
        boolean jugador2Terminado = false;
        boolean jugador3Terminado = false;
        String respuesta;
        
        while (!jugador1Terminado || !jugador2Terminado || !jugador3Terminado){
            
            if (!jugador1Terminado){
                System.out.print("Pedir o quedarse? (Introduce P o Q)");
                respuesta = sc.next();
                
                //Si el jugador decide pedir
                if (respuesta.compareToIgnoreCase("P") == 0){
                    //Reparte la siguiente carta en el mazo
                    jugador1Terminado = !jugador1.agregarCarta(baraja1.repartirSiguienteCarta());
                } else{
                    jugador1Terminado = true;
                }
            }
            
            if (!jugador2Terminado){
                System.out.print("Pedir o quedarse? (Introduce P o Q)");
                respuesta = sc.next();
                
                //Si el jugador decide pedir
                if (respuesta.compareToIgnoreCase("P") == 0){
                    //Reparte la siguiente carta en el mazo
                    jugador2Terminado = !jugador2.agregarCarta(baraja1.repartirSiguienteCarta());
                } else{
                    jugador2Terminado = true;
                }
            }
            
            if (!jugador3Terminado){
                System.out.print("Pedir o quedarse? (Introduce P o Q)");
                respuesta = sc.next();
                
                //Si el jugador decide pedir
                if (respuesta.compareToIgnoreCase("P") == 0){
                    //Reparte la siguiente carta en el mazo
                    jugador3Terminado = !jugador3.agregarCarta(baraja1.repartirSiguienteCarta());
                } else{
                    jugador3Terminado = true;
                }
            }
        }
    }
    
}
