package Modelo;


import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Esta es la clase encargada de administrar los elementos del juego
 * @author Jonathan Muñoz, Carlos Ortiz, Jefersson Guevara
 * @version 1.0.0.0
 */
public class Juego {
    
    /** Ventana del juego */
   
    private JFrame frame;
    
    /** Boton de pausa. */
    //
    private JButton bpausa;
    
    /** Boton reiniciar. */
    private JButton breiniciar;
    
    /**  Boton salir. */
    private JButton bsalir;
    
    /** Hilo principal */
    private Hilo motor;
    
    /** Sonido de Salto */
        AudioClip sonido;
    
    /** Cantidad de Vidas en el juego */
    private int vidas; //vidas
    
    /** velocidad de movimiento de los vehiculos  */
    private int velocidad_bus; //
    
    /** Variable para iniciar el juego */
    private boolean inicio;
    
    /** Variable para ancho de la pantalla */
    private int ancho_pantalla;
    
    /** Variable para alto de la pantalla */
    private int alto_pantalla;
    
    /**
     * Constructor 
     * @param velocidad velocidad del movimiento de los vehiculos
     */
    public Juego(int velocidad) {
        inicio = false;
        
        //Obtengo la  resolucion del juego
        ancho_pantalla = Toolkit.getDefaultToolkit().getScreenSize().width;
        alto_pantalla = Toolkit.getDefaultToolkit().getScreenSize().height;
        
        //Inicio la cantidad de vidas
        vidas = 3;
        if(velocidad == 0){
        	velocidad_bus = velocidad+1*20;
        }else if (velocidad == 1){
        	velocidad_bus = velocidad+1*45;
        }else{
        	velocidad_bus = velocidad+3*20;
        }
       
        
        //Cargo el sonido de saltar al objeto 'sonido'
        sonido = Applet.newAudioClip(getClass().getResource("Sonidos/salto.wav"));
        //Inicializo el motor del juego y luego el Frame
        motor = new Hilo(velocidad_bus);
        frame = new JFrame("Transcolados Pro 2016");
        frame.setResizable(false);
        frame.setBounds(20, 20, 400, 485);
        frame.setDefaultCloseOperation(3); //Hago que cuando le den click a salir salga
        frame.setBackground(Color.BLACK); //Coloco el fondo de color negro
        frame.getContentPane().setLayout(null); //Elimino los manejadores de componentes
        frame.setUndecorated(true); //Elimino que sea controlado por el manejador de ventanas
    }
    
    /**
     * 
     * Con este metodo se agrega todos los elementos al frame, para dar inicio a la interfaz
     * @throws Exception Esta excepcion controla los errores en el proceso de agregar los elementos al frame
     */
    
    public void empezar_juego()
    throws Exception {
        //Agrego los buses y carros al frame 
        frame.getContentPane().add(motor.carros[0].imagen);
        frame.getContentPane().add(motor.carros[1].imagen);
        frame.getContentPane().add(motor.carros[2].imagen);
        frame.getContentPane().add(motor.carros[3].imagen);
        frame.getContentPane().add(motor.persona.imagen);
        frame.getContentPane().add(motor.buses[0].imagen);
        frame.getContentPane().add(motor.buses[1].imagen);
        frame.getContentPane().add(motor.buses[2].imagen);
        frame.getContentPane().add(motor.buses[3].imagen);
        
        //Agrego la persona, los fondos de agua y pavimento
        JLabel fondoagua = new JLabel();
        fondoagua.setIcon(new ImageIcon(getClass().getResource("Imagenes/Estacion_carriles.jpg")));
        fondoagua.setBounds(0, 40, 400, 200);
        
        frame.getContentPane().add(motor.personas_muertas[0]);
        frame.getContentPane().add(fondoagua);
       // frame.getContentPane().add(motor.personas_muertas[1]);
       // frame.getContentPane().add(motor.personas_muertas[2]);
        JLabel pavimento = new JLabel();
        pavimento.setIcon(new ImageIcon(getClass().getResource("Imagenes/carriles_acera.jpg")));
        pavimento.setBounds(0, 240, 400, 240);
        frame.getContentPane().add(motor.LVidas);
        frame.getContentPane().add(pavimento);
        
        //Agrego los bootnes de pausar y reiniciar la frame, con sus caracteristicas y opciones
        bpausa = new JButton("Pausar");
        bpausa.setBounds(0, 0, 75, 40);
        bpausa.setFocusable(false);
        frame.getContentPane().add(bpausa);
        breiniciar = new JButton("Reiniciar la partida");
        breiniciar.setBounds(76, 0, 183, 40);
        breiniciar.setFocusable(false);
        breiniciar.setEnabled(false);
        frame.getContentPane().add(breiniciar);
        bsalir = new JButton("Salir del Juego");
        bsalir.setBounds(260, 0, 140, 40);
        bsalir.setFocusable(false);
        frame.getContentPane().add(bsalir);
        
        //Boton para suspender el hilo/motor
        bpausa.addMouseListener(new  MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                //congelo el motor del juego
                motor.suspend();
                //Activo el boton de reanudar
                breiniciar.setEnabled(true);
                //Desactivo el boton de pausar
                bpausa.setEnabled(false);
            }
        });
        
        //Boton para reanudar el hilo una vez suspendido
        breiniciar.addMouseListener(new  MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                //activo el motor del juego
                motor.resume();
                //Desactivo el boton de reanudar
                breiniciar.setEnabled(false);
                //Activo el boton de pause
                bpausa.setEnabled(true);
            }
        });
        
        //Evento de que cuando presionen el boton de Salir se acabe el programa
        bsalir.addMouseListener(new  MouseAdapter(){
            public void mouseClicked(MouseEvent e){
              //  System.exit(JFrame.EXIT_ON_CLOSE);
            	frame.show(false);
            }
        });
        
        //Agrego el evento al frame de que si me presionan una tecla
        frame.addKeyListener(new  KeyAdapter(){
            public void keyPressed(KeyEvent e){
                //variable donde almaceno el codigo de la tecla presionada
                int tecla = e.getKeyCode();
                     //Si presionaron una tecla de direccion reproduzco sonido
                if((tecla > 36) && (tecla<41))
                    sonido.play();
                
                
                /* Comparo la tecla para saber a que direccion mover y lo muevo
                 * siempre y cuando se pueda (no se salga de la pantalla)*/
                
                //Muevo el persona a la izquierda y actualizo su imagen pa la izq
                if(tecla==37 &&  motor.persona.X>0) {
                    
                    motor.persona.izquierda(40);
                    motor.persona.establecer_direccion("oeste");
                    motor.persona.imagen.setBounds(motor.persona.X,motor.persona.Y,40,40);
                    motor.persona.imagen.repaint();
                    
                }
                //Muevo el persona a arriba y actualizo su imagen pa la arriba
                if(tecla==38 &&  motor.persona.Y>0) {
                    
                    motor.persona.arriba(40);
                    motor.persona.establecer_direccion("norte");
                    motor.persona.imagen.setBounds(motor.persona.X,motor.persona.Y,40,40);
                    motor.persona.imagen.repaint();
                    
                }
                //Muevo el persona a la derecha y actualizo su imagen pa la derecha
                if(tecla==39 &&  motor.persona.X<370) {
                    
                    motor.persona.derecha(40);
                    motor.persona.establecer_direccion("este");
                    motor.persona.imagen.setBounds(motor.persona.X,motor.persona.Y,40,40);
                    motor.persona.imagen.repaint();
                    
                }
                
                
                //Muevo el persona a abajo y actualizo su imagen pa la abajo
                if(tecla==40 &&  motor.persona.Y<440) {
                    motor.persona.abajo(40);
                    motor.persona.establecer_direccion("sur");
                    motor.persona.imagen.setBounds(motor.persona.X,motor.persona.Y,40,40);
                    motor.persona.imagen.repaint();
                }
                
           
            }
        });
        
        //Muestro la pantalla del juego e inicio el motor
        frame.show();
        motor.start();
      
    }
    
    
}
