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
    
    //Lista para escoger los niveles de dificultad
    public JComboBox Cdifi;
    
    
    public Pantallas()
    {
        //Aqui obtengo el ancho y alto de la resolución de la pantalla del usuario 
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
        //Le doy un tamaño proporcionado a la pantalla
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
        Fmenu.setBounds(10, 10, 340, 110);
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
        JOptionPane.showMessageDialog(dialogo, " �"+Mensaje+"!");
        dialogo.show(false);
    }

    //Metodo para retornar el nivel de dificultad escogido por el usuario
    public String obtener_nivel()
    {
        return Cdifi.getSelectedItem().toString();
    }

}
