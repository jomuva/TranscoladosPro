package Modelo;

//CLase principal del programa
import javax.swing.JButton;
import java.awt.event.*;
/**
 *	@ 
 *
 */
public class Main
{
   
    static Juego game; //Objeto del juego
    static Pantallas pcarga; //Objeto de las ventanas de carga
    
  

    public static void main(String args[]) throws Exception
    {
       //Inicializo la ventana de carga
        pcarga = new Pantallas();
        pcarga.carga();
        Thread.currentThread();
        Thread.sleep(1000); //tiempo de pausa entre carga de ventanas
        pcarga.setVisible(false);
        
        
       //Empieza a mostrar el menu Principal del juego
        pcarga.menu_Principal();
        
     
        //Inicia el juego al hacer clic en el boton de iniciar
        pcarga.Biniciar.addMouseListener(new MouseAdapter(){
           public void mouseClicked(MouseEvent e) {
               //inicio el juego, el contructor pide por parametro la dificultad del juego
               game = new Juego(pcarga.Cdifi.getSelectedIndex()+1*6); 
               try{
               game.empezar_juego();   //metodo para iniciar juego  
               }
               catch (Exception error){
                   
               }
               
           }
        }
   
);
    }

 
}
