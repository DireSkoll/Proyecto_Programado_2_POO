
package cartas;

/**
 *
 * @author Manuel Arias & Justin Bogantes
 * @since 14/10/18
 */
public class Carta {
    
    private String ID;
    private int valor;

    public Carta() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "La identificacion de la carta es: " + ID + " y su valor es: " + valor;
    }
}
