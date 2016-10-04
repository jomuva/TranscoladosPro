/*
 * 
 */
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
 * Esta es la clase encargada de  mover los vehiculos y validar las estrelladas etc.
 * @author Jonathan Muñoz, Carlos Ortiz, Jefersson Guevara
 * @version 1.0.0.0
 */


public class Hilo extends Thread {
	
	
	
	private Random ramdom;
	
	/** Es un vector de String, donde se almacenan los mensajes de conciencia a mostrar */
	private String[] mensajesConciencia;
	
	/** Este es un objeto de clase pantallas donde se muestra el menu nueva partida */
	private Pantallas nuevaPartida;
	
	/** Este numero es el numero de vistas  */
	private int vidas; 
	
	/** Este numero entero determina la velocidad de los vehiculos */
	private int velocidadBus; 
    
    /** Sonido de clase AudioClio que sonara cada vez que sea estrellada la persona */
    AudioClip aplastado; 
    
    /** Objeto que contendra la persona que se colara */
    public Objeto persona; 
    
    /** Vector de objetos llamado buses */
    public Objeto[] buses;
    
    /** Vector de objetos llamado carros . */
    public Objeto[] carros;
    
    /** Esta etiqueta contiene la informacion de las vidas  */
    
    public JLabel LVidas;
    
    /** Vector de etiquetas personas muertas para determinar cuantos cadaveres debo contar */
    public JLabel[] personasMuertas;
    
    /** Cantidad de puntos en el juego */
   
    private int puntos;
    
    /**
     * Este es el contructor del Hilo
     *
     * @param velocidad de acuerdo al nivel de dificultad seleccionado
     */
    public Hilo(int velocidad) {
        //Inicializo los objetos
        buses = new Objeto[4];
        carros = new Objeto[4];
        personasMuertas = new JLabel[3];
        velocidadBus = velocidad; //velocidad recibida por parametro, + velocidad =  + dificil 
        vidas = 3;
        puntos = 0;
        persona = new Objeto("Imagenes/hombreverde.gif", "Imagenes/hombreverde.gif", "Imagenes/hombre_regresoverde.gif", "Imagenes/hombre_derverde.gif", "Imagenes/hombre_izqverde.gif");
        persona.establecer_direccion("norte");
        persona.X = 180;
        persona.Y = 440;
        persona.imagen.setBounds(persona.X, persona.Y, 40, 40);
           
        for(int i = 0; i < 3; i++) {
            personasMuertas[i] = new JLabel();
            personasMuertas[i].setBounds(0, 520, 40, 40);
            personasMuertas[i].setIcon(new ImageIcon(getClass().getResource("Imagenes/hombre_muertoverde.png")));
        }
        
        
        //Inicializo los sonidos
        aplastado = Applet.newAudioClip(getClass().getResource("Sonidos/aplastado.wav"));
        
        //inicializo mensajes concientizacion
        mensajesConciencia =  new String[4];
        mensajesConciencia[0] = "Tu vida vale más que el ahorro de un pasaje";
        mensajesConciencia[1] = "En casa te esperan, valora tu vida";
        mensajesConciencia[2] = "En la vida real los superheroes no existen";
        mensajesConciencia[3] = "Colarte no hace parte de los deportes Olimpicos, valora tu vida";
     
        //Inicializo y ubico a los carros y buses
        for(int i = 0; i < 4; i++) {
            buses[i] = new Objeto("Imagenes/bus.gif"); 
            buses[i].Y = (i + 1) * 40 + 40;
            if(i % 2 == 0) {
                carros[i] = new Objeto("Imagenes/autorojo.gif");
                buses[i].X = 400 - i * 6;
                carros[i].X = 400 - i * 8;
            } else {
                carros[i] = new Objeto("Imagenes/autoazul.gif");
                buses[i].X = 0 + i * 6;
                carros[i].X = 400 + i * 7;
            }
            carros[i].Y = (i + 1) * 40 + 240;
            buses[i].imagen.setBounds(buses[i].X, buses[i].Y, 150, 40);
            carros[i].imagen.setBounds(carros[i].X, carros[i].Y, 150, 40);
        }
        
        //Muestro la informaciï¿½n en la pantalla
        LVidas = new JLabel(" Vidas: " + (vidas - 1) + " Nivel: " + velocidadBus / 6 + " Puntos: " + puntos);
        LVidas.setBounds(0, 440, 200, 40);
        LVidas.setBackground(Color.BLACK);
    }
    
    /**
     * Este metodo da ejecucion al hilo
     */
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
                puntos += 2000;
                //Ubico al persona en el inicio
                persona.X = 180;
                persona.Y = 440;
                persona.imagen.setBounds(persona.X, persona.Y, 40, 40);
                //Actualizo el puntaje en la pantalla
                LVidas.setText(" Vidas: " + (vidas - 1) + " Nivel: " + velocidadBus / 6 + " Puntos: " + puntos);
            }
            
            //Chequeo si se murio
            if(esta_muerto()) {
                 //Chequeo si fue aplastado por un carro y reproduzco su sonido
                if(persona.Y >= 240 && persona.Y < 480 || persona.Y >= 40 && persona.Y <= 220)
                    aplastado.play();
                
                //Actualizo la pantalla
                LVidas.setText(" Vidas: " + (vidas - 1) + " Nivel: " + velocidadBus / 6 + " Puntos: " + puntos);
                
                //Coloco un persona muerta
                personasMuertas[0].setBounds(persona.X, persona.Y, 40, 40);
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
                	nuevaPartida.gameOver(mensajesConciencia[numRam]);
                	nuevaPartida.puntaje(puntos);
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
                	buses[i].derecha(velocidadBus);
                	if (buses[i].X > 400){
                    	buses[i].X = -50;
                	}
                    carros[i].derecha(velocidadBus + 3); 
                    if(carros[i].X  > 400)
                        carros[i].X = -100;
                    buses[i].imagen.setBounds(buses[i].X, buses[i].Y, 150, 40);
                    carros[i].imagen.setBounds(carros[i].X, carros[i].Y, 150, 40);
                    continue;
                }
                buses[i].derecha(velocidadBus);
                if(buses[i].X > 400)
                	buses[i].X = -100;
                carros[i].derecha(velocidadBus + 3);
                if(carros[i].X > 400)
                    carros[i].X = 0;
                buses[i].imagen.setBounds(buses[i].X, buses[i].Y, 150, 40);
                carros[i].imagen.setBounds(carros[i].X, carros[i].Y, 150, 40);
            }
            
            try {
                this.sleep(120); //Duermo el proceso para que se note animacion
            } catch(Exception e) { }
        } while(true);
        
       
    }
    
 
    
/**
 *
 * Este metodo, valida si debe morir la persona cuando toque un objeto de tipo bus
 * 
 *
 * @return verdadero si lo toco un vehiculo
 */
// Metodo para saber si esta muerto el persona, devuelve true si esta muerto    
    public boolean esta_muerto() {
        //Chequeo si un tronco se lo llevo
        if((persona.X < 0 || persona.X > 400) && persona.Y >= 80 && persona.Y < 240)
            return true;
        
        //Chequeo si fue atropellado
        for(int i = 0; i < 4; i++)
            if(persona.Y == carros[i].Y && (persona.X < carros[i].X + 69 && persona.X >= carros[i].X || persona.X + 40 < carros[i].X + 69 && persona.X + 30 >= carros[i].X))
                return true;
        
         //Chequeo si lo mata un transmilenio
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
