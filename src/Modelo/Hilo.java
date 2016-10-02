package Modelo;

/*
 */
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;
/**
 
 * @author Jonathan Muñoz, Carlos Ortiz, Jefersson Guevara
 * @version 1.0.0.0
 * 
 */


public class Hilo extends Thread {
	/**
	 * @ Hilo Esta es la clase encargada de  mover los vehiculos y validar las estrelladas etc.
	 * @param Random Este atributo contiene el numero aleatorio para los mensajes aleatorios
	 *
	 */
	
	private Random ramdom;
	private String mensajesConciencia[];
	private Pantallas nuevaPartida;
    public int vidas; //cantidad de vidas
    public int velocidad_bus; //velocidad a la que se mueve el bus en el juego
    AudioClip aplastado; //Objetos de sonidos
    public Objeto persona; //Objetos que van el juego
    public Objeto buses[];
    public Objeto carros[];
    //Etiquetas para mostrar la informacion
    public JLabel LVidas;
    public JLabel personas_muertas[];
    //Cantidad de puntos en el juego
    int puntos;
    
    public Hilo(int velocidad) {
        //Inicializo los objetos
        buses = new Objeto[4];
        carros = new Objeto[4];
        personas_muertas = new JLabel[3];
        velocidad_bus = velocidad; //velocidad recibida por parametro, + velocidad =  + dificil 
        vidas = 3;
        puntos = 0;
        
        /*
         * Inicializo el objeto tipo persona 
         */
        persona = new Objeto("Imagenes/persona.gif", "Imagenes/persona.gif", "Imagenes/personaaba.gif", "Imagenes/personader.gif", "Imagenes/personaizq.gif");
        persona.establecer_direccion("norte");
        persona.X = 180;
        persona.Y = 440;
        persona.imagen.setBounds(persona.X, persona.Y, 40, 40);
           
        for(int i = 0; i < 3; i++) {
            personas_muertas[i] = new JLabel();
            personas_muertas[i].setBounds(0, 520, 40, 40);
            personas_muertas[i].setIcon(new ImageIcon(getClass().getResource("Imagenes/persona_muerto.png")));
        }
        
        
        //Inicializo los sonidos
        aplastado = Applet.newAudioClip(getClass().getResource("Sonidos/aplastado.wav"));
        
        //inicializo mensajes concientizacion
        mensajesConciencia =  new String[4];
        mensajesConciencia[0] = "Tu vida vale más que el ahorro de un pasaje";
        mensajesConciencia[1] = "En casa te esperan, valora tu vida";
        mensajesConciencia[2] = "En la vida real los superheroes no existen";
        mensajesConciencia[3] = "Colarte no hace parte de los deportes Olimpicos, valora tu vida";
     
        //Inicializo y ubico a los carros y troncos
        for(int i = 0; i < 4; i++) {
            buses[i] = new Objeto("Imagenes/tronco.jpg"); 
            buses[i].Y = (i + 1) * 40 + 40;
            if(i % 2 == 0) {
                carros[i] = new Objeto("Imagenes/carro2.gif");
                buses[i].X = 400 - i * 6;
                carros[i].X = 400 - i * 8;
            } else {
                carros[i] = new Objeto("Imagenes/carro.gif");
                buses[i].X = 0 + i * 6;
                carros[i].X = 400 + i * 7;
            }
            carros[i].Y = (i + 1) * 40 + 240;
            buses[i].imagen.setBounds(buses[i].X, buses[i].Y, 150, 40);
            carros[i].imagen.setBounds(carros[i].X, carros[i].Y, 150, 40);
        }
        
        //Muestro la informaciï¿½n en la pantalla
        LVidas = new JLabel(" Vidas: " + (vidas - 1) + " Nivel: " + velocidad_bus / 6 + " Puntos: " + puntos);
        LVidas.setBounds(0, 440, 200, 40);
        LVidas.setBackground(Color.BLACK);
    }
    
    public void run() {
    	int numRam;
        do
        {
            //Chequeo si se le acabaron las vidas para terminar el juego
            if(vidas <= 0)
                break;
            //Chequeo si el persona llego a su meta y Gana
            if(persona.Y < 80) {
                //Le sumo 1000 puntos
                puntos += 1000;
                //Ubico al persona en el inicio
                persona.X = 180;
                persona.Y = 440;
                persona.imagen.setBounds(persona.X, persona.Y, 40, 40);
                //Actualizo el puntaje en la pantalla
                LVidas.setText(" Vidas: " + (vidas - 1) + " Nivel: " + velocidad_bus / 6 + " Puntos: " + puntos);
            }
            
            //Chequeo si se murio
            if(esta_muerto()) {
                 //Chequeo si fue aplastado por un carro y reproduzco su sonido
                if(persona.Y >= 240 && persona.Y < 480 || persona.Y >= 40 && persona.Y <= 220)
                    aplastado.play();
                
                //Actualizo la pantalla
                LVidas.setText(" Vidas: " + (vidas - 1) + " Nivel: " + velocidad_bus / 6 + " Puntos: " + puntos);
                
                //Coloco un persona muerta
                personas_muertas[0].setBounds(persona.X, persona.Y, 40, 40);
                vidas--; //Quito una vida
               
                
                //Reubico la persona
                persona.X = 180;
                persona.Y = 440;
                persona.imagen.setBounds(persona.X, persona.Y, 40, 40);
                
                //Si se quedo sin vidas acabo el juego
                if(vidas == 0){
                	//Creo una ventana con el mensaje
                	nuevaPartida = new Pantallas();
                	ramdom = new Random();
                	numRam = ramdom.nextInt(4);
                	System.out.println(numRam);
                	nuevaPartida.gameOver(mensajesConciencia[numRam]);
                	
                	this.stop();
                	this.destroy();
                }
                nuevaPartida = new Pantallas();
            	ramdom = new Random();
            	numRam = ramdom.nextInt(4);
            	nuevaPartida.MensajeConciencia(mensajesConciencia[numRam]);
            }
            
                  
            //Muevo los 4 Transmilenios
            for(int i = 0; i < 4; i++) {
                if(i % 2 == 0) {
                	buses[i].derecha(velocidad_bus);
                	if (buses[i].X > 400){
                    	buses[i].X = -50;
                	}
                    carros[i].derecha(velocidad_bus + 3); 
                    if(carros[i].X  > 400)
                        carros[i].X = -100;
                    buses[i].imagen.setBounds(buses[i].X, buses[i].Y, 150, 40);
                    carros[i].imagen.setBounds(carros[i].X, carros[i].Y, 150, 40);
                    continue;
                }
                buses[i].derecha(velocidad_bus);
                if(buses[i].X > 400)
                	buses[i].X = -100;
                carros[i].derecha(velocidad_bus + 3);
                if(carros[i].X > 400)
                    carros[i].X = 0;
                buses[i].imagen.setBounds(buses[i].X, buses[i].Y, 150, 40);
                carros[i].imagen.setBounds(carros[i].X, carros[i].Y, 150, 40);
            }
            
            try {
                this.sleep(120); //Duermo el proceso para que se note animacion
            } catch(Exception e) { }
        } while(true);
        
        //Creo una ventana con el mensaje
        JDialog dialogo = new JDialog();
        dialogo.getContentPane().setLayout(null);
        dialogo.setTitle("GAME OVER");
        dialogo.setBounds(30, 200, 250, 100);
        
        //Boton de aceptar
        JButton aceptar = new JButton("Aceptar");
        aceptar.setBounds(75, 30, 100, 20);
        
        //Hago que cuando le den clic al botï¿½n de aceptar se acabe el programa
        aceptar.addMouseListener(new  MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                System.exit(JDialog.EXIT_ON_CLOSE);
            }
        });
        
        //Asigno las propiedades a la ventana de "Fin del Juego"
        dialogo.setDefaultCloseOperation(3);
        dialogo.setResizable(false);
        dialogo.getContentPane().add(aceptar);
        
        //Publico con cuantos puntos gano
        JLabel labelwin = new JLabel("Ganaste con un total de " + puntos);
        labelwin.setBounds(2, 2, 250, 20);
        
        //Agrego el mensaje a la ventana de "Fin del Juego"
        dialogo.getContentPane().add(labelwin);
        dialogo.show();
    }
    
 
    
// Metodo para saber si esta muerto el persona, devuelve true si esta muerto    
    public boolean esta_muerto() {
        //Chequeo si un tronco se lo llevo
        if((persona.X < 0 || persona.X > 400) && persona.Y >= 80 && persona.Y < 240)
            return true;
        
        //Chequeo si fue atropellado
        for(int i = 0; i < 4; i++)
            if(persona.Y == carros[i].Y && (persona.X < carros[i].X + 69 && persona.X >= carros[i].X || persona.X + 40 < carros[i].X + 69 && persona.X + 30 >= carros[i].X))
                return true;
        
         //Chequeo si se ahoga 
        if(persona.Y >= 80 && persona.Y <= 220) {
            for(int i = 0; i < 4; i++)
                if(persona.Y == (i + 1) * 40 + 40 && persona.X >= buses[i].X - 10 && persona.X <= buses[i].X + 160)
                    return true;
            
            return false; //retorno positivo el que se ahogo
        } else {
            return false; 
        }
    }
    
    
}
