package Modelo;

import javax.swing.JButton;
import java.awt.event.*;

/**
 * Esta es la clase principal del juego, esta recibe el inicio del juego.
 * @author Jonathan Muñoz, Carlos Ortiz, Jefersson Guevara
 * @version 1.0.0.0
 */
public class Main
{
   
    /** Crea un objeto juego para inicializar los objetos de interfaz */
    static Juego game; //Objeto del juego
    
    /** Crea un objeto Pantallas para mostrar los elementos  */
    static Pantallas pcarga; //Objeto de las ventanas de carga
    
  

    /**
     * Este es el metodo principal
     *
     * @param args the arguments
     * @throws Exception the exception
     */
    public static void main(String args[]) throws Exception
    {
      /**
       * Inicializo la ventana de carga
       */
        pcarga = new Pantallas();
        pcarga.carga();
        Thread.currentThread();
        Thread.sleep(1000); //tiempo de pausa entre carga de ventanas
        pcarga.setVisible(false);
        
        
       /**
        * Empieza a mostrar el menu Principal del juego
        */
        pcarga.menu_Principal();
        
        
     
        /**
         * Inicia el juego al hacer clic en el boton de iniciar
         */
        pcarga.Biniciar.addMouseListener(new MouseAdapter(){
        
        	/**
        	 * inicio el juego, el contructor pide por parametro la dificultad del juego
        	 * 
        	 */
              public void mouseClicked(MouseEvent e) {
               game = new Juego(pcarga.Cdifi.getSelectedIndex()); 
               try{
               game.empezar_juego();   //metodo para iniciar juego  
               }
               catch (Exception error){
               }
           }
        }
   
);
      
        /**
         * Muestra instrucciones del juego
         */
         pcarga.Binstrucciones.addMouseListener(new MouseAdapter(){
           public void mouseClicked(MouseEvent e) {
               pcarga.instrucciones();
           }
        }
);
    }

 
}
