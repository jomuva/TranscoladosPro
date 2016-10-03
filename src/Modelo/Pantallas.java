package Modelo;


import java.awt.*;
import javax.swing.*;


/**
 * Clase para manejar las pantallas
 * @author Jonathan Muñoz, Carlos Ortiz, Jefersson Guevara
 * @version 1.0.0.0
 */
public class Pantallas extends Frame
{
    
    /** Imagen a mostrar en el fondo */
    ImageIcon imagen; 
    
    /** El ancho de la pantalla. */
    int ancho_pantalla;
    
    /** El alto de pantalla. */
    int alto_pantalla;
    
    /** El Jframe para el menu inicial. */
    public JFrame Fmenu; //Menu
    
    /** El boton de iniciar */
    //Botones
    public JButton Biniciar; 
    
    /** 
     * El boton de cargar
     * */
    public JButton Bcargar;
    
    /** Boton para mostrar las instrucciones */
    public JButton Binstrucciones;
    
    /** 
     * 
     *   Lista para escoger los niveles de dificultad
     *   */
    
    public JComboBox Cdifi;
    
    
    /**
     * Constructor de los parametros de las  pantallas.
     */
    public Pantallas()
    {
        //Aqui obtengo el ancho y alto de la resoluciÃ³n de la pantalla del usuario 
        ancho_pantalla = Toolkit.getDefaultToolkit().getScreenSize().width;
        alto_pantalla = Toolkit.getDefaultToolkit().getScreenSize().height;
    }

    /**
     * Metodo para crear una Ventana de inicio.
     */
   
    public void carga()
    {
        //Le elimino bordes y que el controlador de ventanas no dibuje la barra de titulo de titulo
        setUndecorated(true);
        //Coloco de color blanco el fondo
        setBackground(Color.WHITE);
        //Le doy un tamaÃ±o proporcionado a la pantalla
        setBounds(ancho_pantalla / 2 - ancho_pantalla / 5, alto_pantalla / 2 - alto_pantalla / 5 - 5, 390, 380);
        //Imagen de fondo
        imagen = new ImageIcon(getClass().getResource("Imagenes/logo.png"));
        JLabel im = new JLabel(imagen);
        im.setBounds(0, 0, 400, 300);
        im.setVisible(true);
        add(im);
    }

    /**
     * Metodo para cargar el menu principal
     */
    
    public void menu_Principal()
    {
        //Propiedades del menu principal
        Fmenu = new JFrame("Transcolados Pro 2016");
        setVisible(false);
        Fmenu.setUndecorated(false);
        Fmenu.getContentPane().setLayout(null);
        Fmenu.setBounds(10, 10, 500, 110);
        Fmenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel Ldifi = new JLabel("Seleccione el nivel de dificultad:");
        Ldifi.setBounds(70, 5, 200, 15);
        Fmenu.getContentPane().add(Ldifi);
        //Tipo de niveles existentes que se agregaran a la lista de seleccion de dificultad
        String niveles[] = {
            "Baja", "Media", "Alta"
        };
        
        //Lista de seleccion de nivel
        Cdifi = new JComboBox(niveles);
        Cdifi.setBounds(50, 20, 200, 20);
        Fmenu.getContentPane().add(Cdifi);
        
        //Boton de iniciar partida y sus propiedades
        Biniciar = new JButton("Nueva Partida");
        Biniciar.setBounds(50, 40, 200, 20);
        Biniciar.setVisible(true);
        Fmenu.getContentPane().add(Biniciar);
        Fmenu.show();
        
        //boton Instrucciones
        Binstrucciones = new JButton("Instrucciones");
        Binstrucciones.setBounds(260, 30, 200, 20);
        Binstrucciones.setVisible(true);
        Fmenu.getContentPane().add(Binstrucciones);
        Fmenu.show();
    }
    
    /**
     * Game over: Este metodo muestra el mensaje cuando ya ha perdido todas las vidas
     *
     * @param Mensaje que se va a mostrar
     */
    public void gameOver(String Mensaje){
    	JDialog dialogo = new JDialog();
        dialogo.getContentPane().setLayout(null);
        dialogo.setBounds(100, 200, 250, 100);
        dialogo.show(true);
        JOptionPane.showMessageDialog(dialogo, "GAME OVER \n  "+Mensaje);
        dialogo.show(false);	
    }
    
    /**
     * Mensaje de Conciencia: Aqui se va a cargar los mensajes de concientizacion para ser mostrados
     *
     * @param Mensaje que recibe del juego
     */
    public void MensajeConciencia(String Mensaje){
    	JDialog dialogo = new JDialog();
        dialogo.getContentPane().setLayout(null);
        dialogo.setBounds(100, 200, 250, 100);
        dialogo.show(true);
        JOptionPane.showMessageDialog(dialogo, " ¡"+Mensaje+"!");
        dialogo.show(false);
    }
    
    /**
     * Mensaje de Puntaje final
     *
     * @param puntaje que recibe del juego, para se mostrado
     */
    public void puntaje(int puntaje){
    	JDialog dialogo = new JDialog();
        dialogo.getContentPane().setLayout(null);
        dialogo.setBounds(100, 200, 250, 100);
        dialogo.show(true);
        JOptionPane.showMessageDialog(dialogo, "Tu puntaje total es: \n"+puntaje+" puntos");
        dialogo.show(false);
    }
    
    /**
     * Instrucciones: Aqui se carga las instrucciones para ser mostradas
     */
    public void instrucciones(){
    	JDialog dialogo = new JDialog();
        dialogo.getContentPane().setLayout(null);
        dialogo.setTitle("Instrucciones del juego");
        dialogo.setBounds(100, 200, 250, 100);
        dialogo.show(true);
        JOptionPane.showMessageDialog(dialogo, " 1. Seleccione la difultad del juego \n 2. Con las flechas mueva el personaje animado \n 3. Intente llegar a la estación de Transmilenio que se encuentra en la parte más alta de la pantalla y colarse. \n 4. Tiene 3 vidas de las cuales puede perderlas en caso que lo estrellen los vehículos que hay en la calle. \n 5. Intente realizar el mayor puntaje posible", "Instrucciones de Juego",JOptionPane.INFORMATION_MESSAGE);
        dialogo.show(false);
    }

    /**
     * Obtener nivel.
     *
     * @return the string
     */
    //Metodo para retornar el nivel de dificultad escogido por el usuario
    public String obtener_nivel()
    {
        return Cdifi.getSelectedItem().toString();
    }

}
