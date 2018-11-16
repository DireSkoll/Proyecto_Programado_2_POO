/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartas;

/**
 *
 * @author Manuel Arias & Justin Bogantes
 */
public class Carta2 {
    
    private Categoria miCategoria;
    private int miNumero;
    private String id;
    private int valor;
    
    public Carta2(){
        
    }
    
    public Carta2(Categoria pCategoria, int pNumero){
        setMiCategoria(pCategoria);
        
        if (pNumero >= 1 && pNumero <= 13) {
            setMiNumero(pNumero);
        } else {
            System.err.println(pNumero + " no es un numero valido para una carta");
            System.exit(1);
        }
    }

    public Categoria getMiCategoria() {
        return miCategoria;
    }

    public void setMiCategoria(Categoria miCategoria) {
        this.miCategoria = miCategoria;
    }

    public int getMiNumero() {
        return miNumero;
    }

    public void setMiNumero(int miNumero) {
        this.miNumero = miNumero;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    /**
     * 
     * @return La informacion de la carta.
     */
     
    public String toString(){
        String msg = "";
        switch (getMiNumero()){     
            case 1:
                msg += "As";
                break;
                
            case 2:
                msg += "Dos";
                break;
            
            case 3:
                msg += "Tres";
                break;
            
            case 4:
                msg += "Cuatro";
                break;
                
            case 5:
                msg += "Cinco";
                break;
                
            case 6:
                msg += "Seis";
                break;
                
            case 7:
                msg += "Siete";
                break;
                
            case 8:
                msg += "Ocho";
                break;
                
            case 9:
                msg += "Nueve";
                break;
                
            case 10:
                msg += "Diez";
                break;
                
            case 11:
                msg += "Jack";
                break;
                
            case 12:
                msg += "Reina";
                break;
                
            case 13:
                msg += "Rey";
                break;       
        }
        return msg + " de " + getMiCategoria().toString();
    }
}
