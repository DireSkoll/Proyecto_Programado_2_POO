
package aplicacion;

import cartas.Baraja;

/**
 *
 * @author Manuel Arias & Justin Bogantes
 * @since 14/10/18
 */
public class PruebaBaraja {
    
    public static void main(String args[]){
        Baraja miBaraja = new Baraja(false);
        miBaraja.imprimirBaraja(10);
        
        Baraja miBaraja2 = new Baraja(true);
        miBaraja2.imprimirBaraja(10);
    }
    
}
