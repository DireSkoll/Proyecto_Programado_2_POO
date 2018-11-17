
package usuarios;

import cartas.Carta;

/**
 *
 * @author Manuel Arias & Justin Bogantes
 * @since 14/10/18
 */
public class Jugador {
    private String nombre;
    private Carta[] mano = new Carta[10];
    private int numeroCartasEnMano;
    
    /**
     * Constructor del jugador
     * @param pNombre Es el nombre o pseoudonimo del jugador
     */
    public Jugador(String pNombre){
        setNombre(pNombre);
        this.vaciarMano();
    }
    
    public void vaciarMano(){   
        for (int c = 0; c < 10; c++){
            this.mano[c] = null;
        }
        setNumeroCartasEnMano(0);
    }
    
    /**
     * Le da a la mano del jugador una nueva carta
     * 
     * @param pCarta La carta a ser agregada a la mano
     * @return Si la suma de las cartas en la mano del jugador equivale a 21
     */
    public boolean agregarCarta(Carta pCarta){
        if (getNumeroCartasEnMano() == 10){
            System.err.println("La mano de " + getNombre() + " ya posee el maximo numero de cartas: 10\n");
            System.exit(1);
        }
        
        this.mano[getNumeroCartasEnMano()] = pCarta;
        setNumeroCartasEnMano(getNumeroCartasEnMano()+1);
        
        return (this.conseguirSumaMano() <= 21);
    }
    
    /**
     * Consigue la suma de la mano del jugador
     * 
     * @return La suma de su mano
     */
    public int conseguirSumaMano(){
        int sumaMano = 0;
        int numeroCarta;
        int numeroAses = 0;
        
        //Calcula la contribucion de cada carta a la suma
        for (int c = 0; c < getNumeroCartasEnMano(); c++){
            //Consigue el numero de la carta actual
            numeroCarta = 1;//getMano()[c].getMiNumero();
            
            if (numeroCarta == 1) { //As
                numeroAses++;
                sumaMano += 11;
            } else if (numeroCarta > 10){ //Jack, Reina o Rey
                sumaMano += 10;
            } else {
                sumaMano += numeroCarta;
            }
        }
        
        //Si tenemos Ases y nuestra suma es mayor a 21 les asignamos valor de 1
        while (sumaMano > 21 && numeroAses > 0){
            sumaMano -= 10;
            numeroAses--;
        }
        
        return sumaMano;
    }
    
    /**
     * Imprime la mano del jugador
     * 
     * @param mostrarPrimeraCarta Determina si la primer carta se encuentra oculta
     */
    public void imprimirMano(boolean mostrarPrimeraCarta){
        System.out.println("La mano de " + getNombre() + ": ");
        for (int c = 0; c < getNumeroCartasEnMano(); c++){
            if (c == 0 && !mostrarPrimeraCarta){
                System.out.println("[oculta]");
            } else {
                System.out.println(getMano()[c].toString());
            }
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Carta[] getMano() {
        return mano;
    }

    public void setMano(Carta[] mano) {
        this.mano = mano;
    }

    public int getNumeroCartasEnMano() {
        return numeroCartasEnMano;
    }

    public void setNumeroCartasEnMano(int numeroCartasEnMano) {
        this.numeroCartasEnMano = numeroCartasEnMano;
    }
}
