
package GUI;

import cartas.Carta;
import sockets.Cliente;

/**
 *
 * @author Manuel Arias & Justin Bogantes
 * @since 23/10/18
 * @version
 */
public class ControladorPrincipal {
    
    private Game menu;
    private String user;
    private Cliente clienteNuevo;
    private Espera ventanaDeEspera;
    private Mesa ventanaDeJuego;
    private VentanaCierre ventanaCierre;
    
    public ControladorPrincipal(){
        
    }
    
    public void empezarJuego(){
        menu = new Game();
        menu.setVisible(true);
    }
    
    public void iniciarConexion(String user){
        this.user = user;
        menu.dispose();
        System.out.println(user);
        clienteNuevo = new Cliente(this);
        clienteNuevo.establecerConexion();
    }
    
    public void abrirVentanaDeEspera(int jugador){
        ventanaDeEspera = new Espera();
        ventanaDeEspera.Load(jugador);
        ventanaDeEspera.setVisible(true);
    }
    
    public void actualizarVentanaDeEspera(int cantJugadores){
        ventanaDeEspera.Update(cantJugadores);
    }
    
    public void abrirVentanaCierre(int estado){
        ventanaDeJuego.dispose();
        ventanaCierre = new VentanaCierre();
        ventanaCierre.LoadVentana(estado);
        ventanaCierre.setVisible(true);
    }
    
    public void resetear(){
        ventanaCierre.dispose();
        menu = new Game();
        menu.setVisible(true);
    }
    
    public void abrirMesa(){
        ventanaDeEspera.dispose();
        ventanaDeJuego = new Mesa();
        ventanaDeJuego.LoadVentana(user);
        ventanaDeJuego.setVisible(true);
    }
    
    public void elegirRonda(){
        ventanaDeJuego.ronda();
    }
    
    public void agregarCarta(Carta carta){
        ventanaDeJuego.agregarCarta(carta);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Cliente getClienteNuevo() {
        return clienteNuevo;
    }

    public void setClienteNuevo(Cliente clienteNuevo) {
        this.clienteNuevo = clienteNuevo;
    }

    public Mesa getVentanaDeJuego() {
        return ventanaDeJuego;
    }

    public void setVentanaDeJuego(Mesa ventanaDeJuego) {
        this.ventanaDeJuego = ventanaDeJuego;
    }
}
