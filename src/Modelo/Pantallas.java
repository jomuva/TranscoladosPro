package Modelo;

//Clase para manejar las pantallas
import java.awt.*;
import javax.swing.*;

public class Pantallas extends Frame
{
    
    ImageIcon imagen; //Imagen a mostrar en el fondo
    int ancho_pantalla;
    int alto_pantalla;
    public JFrame Fmenu; //Menu
    //Botones
    public JButton Biniciar; 
    public JButton Bcargar;
    public JButton Binstrucciones;
    //Lista para escoger los niveles de dificultad
    public JComboBox Cdifi;
    
    
    public Pantallas()
    {
        //Aqui obtengo el ancho y alto de la resoluciÃ³n de la pantalla del usuario 
        ancho_pantalla = Toolkit.getDefaultToolkit().getScreenSize().width;
        alto_pantalla = Toolkit.getDefaultToolkit().getScreenSize().height;
    }

    //Metodo para crear una Ventana de inicio
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

    //Ventana del menu principal
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
    
    public void gameOver(String Mensaje){
    	JDialog dialogo = new JDialog();
        dialogo.getContentPane().setLayout(null);
        dialogo.setBounds(100, 200, 250, 100);
        dialogo.show(true);
        JOptionPane.showMessageDialog(dialogo, "GAME OVER \n  "+Mensaje);
        dialogo.show(false);	
    }
    
    public void MensajeConciencia(String Mensaje){
    	JDialog dialogo = new JDialog();
        dialogo.getContentPane().setLayout(null);
        dialogo.setBounds(100, 200, 250, 100);
        dialogo.show(true);
        JOptionPane.showMessageDialog(dialogo, " ¡"+Mensaje+"!");
        dialogo.show(false);
    }
    
    public void puntaje(int puntaje){
    	JDialog dialogo = new JDialog();
        dialogo.getContentPane().setLayout(null);
        dialogo.setBounds(100, 200, 250, 100);
        dialogo.show(true);
        JOptionPane.showMessageDialog(dialogo, "Tu puntaje total es: \n"+puntaje+" puntos");
        dialogo.show(false);
    }
    
    public void instrucciones(){
    	JDialog dialogo = new JDialog();
        dialogo.getContentPane().setLayout(null);
        dialogo.setTitle("Instrucciones del juego");
        dialogo.setBounds(100, 200, 250, 100);
        dialogo.show(true);
        JOptionPane.showMessageDialog(dialogo, " 1. Seleccione la difultad del juego \n 2. Con las flechas mueva el personaje animado \n 3. Intente llegar a la estación de Transmilenio que se encuentra en la parte más alta de la pantalla y colarse. \n 4. Tiene 3 vidas de las cuales puede perderlas en caso que lo estrellen los vehículos que hay en la calle. \n 5. Intente realizar el mayor puntaje posible", "Instrucciones de Juego",JOptionPane.INFORMATION_MESSAGE);
        dialogo.show(false);
    }

    //Metodo para retornar el nivel de dificultad escogido por el usuario
    public String obtener_nivel()
    {
        return Cdifi.getSelectedItem().toString();
    }

}
