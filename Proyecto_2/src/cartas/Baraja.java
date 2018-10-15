
package cartas;

import java.util.Random; //Libreria que utilizaremos para la distribucion de las cartas en la baraja

/**
 *
 * @author Manuel Arias & Justin Bogantes
 * @since 14/10/18
 */
public class Baraja {
    
    private Carta[] misCartas;
    private int numeroCartas;
    
    public Baraja(){
        //Llamaria al otro constructor indicando que no se desea mezclar la baraja
        this(false);
    }
    
    /**
     * 
     * @param mezclar para indicar si se desea mezclar la baraja
     */
    public Baraja(boolean mezclar){
        setNumeroCartas(52);
        setMisCartas(new Carta[getNumeroCartas()]);
        
        //Indice de la carta
        int i = 0;
        
        //Para cada categoria
        for (int c = 0; c < 4; c++){
            
            //Para cada numero
            for (int n = 1; n <= 13; n++){
                
                //agregar una nueva carta a la baraja
                misCartas[i] = new Carta(Categoria.values()[c], n);
                i++;
                
            }
        }
        
        //Mezclar si es necesario
        if (mezclar){
            this.mezclar();
        }
    }
    
    /**
     * Mezcla las cartas de la baraja
     */
    public void mezclar(){
        Random rng = new Random();
        Carta temp;
        int j;
        
        for (int i = 0; i < getNumeroCartas(); i++){
            
            //Escoge una carta al azar y se la asigna a j
            j = rng.nextInt(getNumeroCartas());
            
            //Realiza un intercambio en el orden de las cartas i y j
            temp = getMisCartas()[i];
            this.misCartas[i] = this.misCartas[j];
            this.misCartas[j] = temp;
        }
    }
    
    /**
     * 
     * @return La carta superior en la baraja
     */
    public Carta repartirSiguienteCarta(){ 
        Carta superior = getMisCartas()[0];
        
        //Corre todas las cartas una posicion hacia la izquierda en la baraja
        for (int c = 1; c < getNumeroCartas(); c++){
            this.misCartas[c-1] = this.misCartas[c];
        }
        this.misCartas[getNumeroCartas()-1] = null;
        setNumeroCartas(getNumeroCartas()-1);
        
        return superior;
    }
    
    /**
     * Imprime las cartas superiores de la baraja
     * 
     * @param numeroAImprimir Es el numero de cartas desde la parte superior de la baraja que se desea imprimir
     */
    public void imprimirBaraja(int numeroAImprimir){
        
        for (int c = 0; c < numeroAImprimir; c++){
            System.out.printf("% 3d/%d %s\n", c+1, getNumeroCartas(), getMisCartas()[c].toString());
        }
        System.out.printf("\t\t[%d otras]\n", getNumeroCartas()-numeroAImprimir);
        
    }

    /**
     * 
     * @return La lista de cartas que posee la baraja
     */
    public Carta[] getMisCartas() {
        return misCartas;
    }

    public void setMisCartas(Carta[] misCartas) {
        this.misCartas = misCartas;
    }

    /**
     * 
     * @return El numero de cartas que tiene la baraja
     */
    public int getNumeroCartas() {
        return numeroCartas;
    }

    public void setNumeroCartas(int numeroCartas) {
        this.numeroCartas = numeroCartas;
    }
}
