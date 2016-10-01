package Modelo;


import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Juego {
    //Ventana del juego
    public JFrame frame;
    //Botones de pausa, reanudar y salir
    public JButton bpausa;
    public JButton breiniciar;
    public JButton bsalir;
    
    //Hilo con el motor del juego
    public Hilo motor;
    
    //Sonido del salto
    AudioClip sonido;
    
    //Variables de cantidades
    public int vidas; //vidas
    public int velocidad_tronco; //velocidad de movimiento de los troncos
    boolean inicio;
    
    //Variables para guardar la resolucion
    int ancho_pantalla;
    int alto_pantalla;
    
    //Metodo de carga del juego, pido por parametro el nivel de dificultad
    public Juego(int vel_tronco) {
        inicio = false;
        
        //Obtengo la  resolucion del juego
        ancho_pantalla = Toolkit.getDefaultToolkit().getScreenSize().width;
        alto_pantalla = Toolkit.getDefaultToolkit().getScreenSize().height;
        
        //Inicio la cantidad de vidas
        vidas = 3;
        velocidad_tronco = vel_tronco;
        
        //Cargo el sonido de saltar al objeto 'sonido'
        sonido = Applet.newAudioClip(getClass().getResource("Sonidos/salto.wav"));
        //Inicializo el motor del juego y luego el Frame
        motor = new Hilo(velocidad_tronco);
        frame = new JFrame("Transcolados Pro 2016");
        frame.setResizable(false);
        frame.setBounds(20, 20, 400, 485);
        frame.setDefaultCloseOperation(3); //Hago que cuando le den click a salir salga
        frame.setBackground(Color.BLACK); //Coloco el fondo de color negro
        frame.getContentPane().setLayout(null); //Elimino los manejadores de componentes
        frame.setUndecorated(true); //Elimino que sea controlado por el manejador de ventanas
    }
    
    //Metodo para iniciar el juego
    public void empezar_juego()
    throws Exception {
        //Agrego los carros y troncos al frame 
        frame.getContentPane().add(motor.carros[0].imagen);
        frame.getContentPane().add(motor.carros[1].imagen);
        frame.getContentPane().add(motor.carros[2].imagen);
        frame.getContentPane().add(motor.carros[3].imagen);
        frame.getContentPane().add(motor.sapo.imagen);
        frame.getContentPane().add(motor.troncos[0].imagen);
        frame.getContentPane().add(motor.troncos[1].imagen);
        frame.getContentPane().add(motor.troncos[2].imagen);
        frame.getContentPane().add(motor.troncos[3].imagen);
        
        //Agrego el sapo, los fondos de agua y pavimento
        JLabel fondoagua = new JLabel();
        fondoagua.setIcon(new ImageIcon(getClass().getResource("Imagenes/agua.jpg")));
        fondoagua.setBounds(0, 40, 400, 200);
        frame.getContentPane().add(fondoagua);
        frame.getContentPane().add(motor.sapos_muertos[0]);
        frame.getContentPane().add(motor.sapos_muertos[1]);
        frame.getContentPane().add(motor.sapos_muertos[2]);
        JLabel pavimento = new JLabel();
        pavimento.setIcon(new ImageIcon(getClass().getResource("Imagenes/asfalto.jpg")));
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
                System.exit(JFrame.EXIT_ON_CLOSE);
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
                
                //Muevo el sapo a la izquierda y actualizo su imagen pa la izq
                if(tecla==37 &&  motor.sapo.X>0) {
                    
                    motor.sapo.izquierda(40);
                    motor.sapo.establecer_direccion("oeste");
                    motor.sapo.imagen.setBounds(motor.sapo.X,motor.sapo.Y,40,40);
                    motor.sapo.imagen.repaint();
                    
                }
                //Muevo el sapo a arriba y actualizo su imagen pa la arriba
                if(tecla==38 &&  motor.sapo.Y>0) {
                    
                    motor.sapo.arriba(40);
                    motor.sapo.establecer_direccion("norte");
                    motor.sapo.imagen.setBounds(motor.sapo.X,motor.sapo.Y,40,40);
                    motor.sapo.imagen.repaint();
                    
                }
                //Muevo el sapo a la derecha y actualizo su imagen pa la derecha
                if(tecla==39 &&  motor.sapo.X<370) {
                    
                    motor.sapo.derecha(40);
                    motor.sapo.establecer_direccion("este");
                    motor.sapo.imagen.setBounds(motor.sapo.X,motor.sapo.Y,40,40);
                    motor.sapo.imagen.repaint();
                    
                }
                
                
                //Muevo el sapo a abajo y actualizo su imagen pa la abajo
                if(tecla==40 &&  motor.sapo.Y<440) {
                    
                    motor.sapo.abajo(40);
                    motor.sapo.establecer_direccion("sur");
                    motor.sapo.imagen.setBounds(motor.sapo.X,motor.sapo.Y,40,40);
                    motor.sapo.imagen.repaint();
                    
                    
                }
                
           
            }
        });
        
        //Muestro la pantalla del juego e inicio el motor
        frame.show();
        motor.start();
    }
    
    
}
