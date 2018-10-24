
package cartas;

import java.awt.List;
import java.util.Collections;
import java.util.Random; //Libreria que utilizaremos para la distribucion de las cartas en la baraja

/**
 *
 * @author Manuel Arias & Justin Bogantes
 * @since 14/10/18
 */
public class Baraja {
    
    private Carta[] misCartas;
    //private List<Carta> cartas;
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
    /*
    public void generarBarajaAleatoria(){
        for (int i = 1; i <= 52; i++) {
            misCartas.add(GenerarCarta(i));
        }
        Collections.shuffle(misCartas);
    }
    */
    
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
    
    public Carta GenerarCarta(int eID) {
        Carta salida = new Carta();
        switch (eID) {
            /*-----Corazones-----*/
            case 1:
                salida.setId("C1");
                salida.setValor(11);
                break;
            case 2:
                salida.setId("C2");
                salida.setValor(2);
                break;
            case 3:
                salida.setId("C3");
                salida.setValor(3);
                break;
            case 4:
                salida.setId("C4");
                salida.setValor(4);
                break;
            case 5:
                salida.setId("C5");
                salida.setValor(5);
                break;
            case 6:
                salida.setId("C6");
                salida.setValor(6);
                break;
            case 7:
                salida.setId("C7");
                salida.setValor(7);
                break;
            case 8:
                salida.setId("C8");
                salida.setValor(8);
                break;
            case 9:
                salida.setId("C9");
                salida.setValor(9);
                break;
            case 10:
                salida.setId("C10");
                salida.setValor(10);
                break;
            case 11:
                salida.setId("CJ");
                salida.setValor(10);
                break;
            case 12:
                salida.setId("CQ");
                salida.setValor(10);
                break;
            case 13:
                salida.setId("CK");
                salida.setValor(10);
                break;
            /*-----Diamantes-----*/
            case 14:
                salida.setId("D1");
                salida.setValor(11);
                break;
            case 15:
                salida.setId("D2");
                salida.setValor(2);
                break;
            case 16:
                salida.setId("D3");
                salida.setValor(3);
                break;
            case 17:
                salida.setId("D4");
                salida.setValor(4);
                break;
            case 18:
                salida.setId("D5");
                salida.setValor(5);
                break;
            case 19:
                salida.setId("D6");
                salida.setValor(6);
                break;
            case 20:
                salida.setId("D7");
                salida.setValor(7);
                break;
            case 21:
                salida.setId("D8");
                salida.setValor(8);
                break;
            case 22:
                salida.setId("D9");
                salida.setValor(9);
                break;
            case 23:
                salida.setId("D10");
                salida.setValor(10);
                break;
            case 24:
                salida.setId("DJ");
                salida.setValor(10);
                break;
            case 25:
                salida.setId("DQ");
                salida.setValor(10);
                break;
            case 26:
                salida.setId("DK");
                salida.setValor(10);
                break;
            /*-----Picas-----*/
            case 27:
                salida.setId("P1");
                salida.setValor(11);
                break;
            case 28:
                salida.setId("P2");
                salida.setValor(2);
                break;
            case 29:
                salida.setId("P3");
                salida.setValor(3);
                break;
            case 30:
                salida.setId("P4");
                salida.setValor(4);
                break;
            case 31:
                salida.setId("P5");
                salida.setValor(5);
                break;
            case 32:
                salida.setId("P6");
                salida.setValor(6);
                break;
            case 33:
                salida.setId("P7");
                salida.setValor(7);
                break;
            case 34:
                salida.setId("P8");
                salida.setValor(8);
                break;
            case 35:
                salida.setId("P9");
                salida.setValor(9);
                break;
            case 36:
                salida.setId("P10");
                salida.setValor(10);
                break;
            case 37:
                salida.setId("PJ");
                salida.setValor(10);
                break;
            case 38:
                salida.setId("PQ");
                salida.setValor(10);
                break;
            case 39:
                salida.setId("PK");
                salida.setValor(10);
                break;
            /*-----Treboles-----*/
            case 40:
                salida.setId("T1");
                salida.setValor(11);
                break;
            case 41:
                salida.setId("T2");
                salida.setValor(2);
                break;
            case 42:
                salida.setId("T3");
                salida.setValor(3);
                break;
            case 43:
                salida.setId("T4");
                salida.setValor(4);
                break;
            case 44:
                salida.setId("T5");
                salida.setValor(5);
                break;
            case 45:
                salida.setId("T6");
                salida.setValor(6);
                break;
            case 46:
                salida.setId("T7");
                salida.setValor(7);
                break;
            case 47:
                salida.setId("T8");
                salida.setValor(8);
                break;
            case 48:
                salida.setId("T9");
                salida.setValor(9);
                break;
            case 49:
                salida.setId("T10");
                salida.setValor(10);
                break;
            case 50:
                salida.setId("TJ");
                salida.setValor(10);
                break;
            case 51:
                salida.setId("TQ");
                salida.setValor(10);
                break;
            case 52:
                salida.setId("TK");
                salida.setValor(10);
                break;
        }

        return salida;
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
