package Modelo;

/*Esta es la clase encargada de  mover los troncos, chequear las colisiones, etc.
 *Se podria decir que es el motor del juego
 */
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Hilo extends Thread {
   
    public int vidas; //cantidad de vidas
    
    
    public int velocidad_tronco; //velocidad a la que se mueve el tronco en el juego
    //Objetos de sonidos
    AudioClip sonido_agua; 
    AudioClip aplastado;
    //Objetos que van el juego
    public Objeto sapo;
    public Objeto troncos[];
    public Objeto carros[];
    //Etiquetas para mostrar la informacion
    public JLabel LVidas;
    public JLabel sapos_muertos[];
    //Cantidad de puntos en el juego
    int puntos;
    
    public Hilo(int velocidad) {
        //Inicializo los objetos
        troncos = new Objeto[4];
        carros = new Objeto[4];
        sapos_muertos = new JLabel[3];
        velocidad_tronco = velocidad; //velocidad recibia por parametro, + velocidad =  + dificil 
        vidas = 3;
        puntos = 0;
        
        //Inicializo el objeto tipo sapo
        sapo = new Objeto("Imagenes/sapo.gif", "Imagenes/sapo.gif", "Imagenes/sapoaba.gif", "Imagenes/sapoder.gif", "Imagenes/sapoizq.gif");
        sapo.establecer_direccion("norte");
        sapo.X = 180;
        sapo.Y = 440;
        sapo.imagen.setBounds(sapo.X, sapo.Y, 40, 40);
           
        for(int i = 0; i < 3; i++) {
            sapos_muertos[i] = new JLabel();
            sapos_muertos[i].setBounds(0, 520, 40, 40);
            sapos_muertos[i].setIcon(new ImageIcon(getClass().getResource("Imagenes/sapo_muerto.png")));
        }
        
        
        //Inicializo los sonidos
        sonido_agua = Applet.newAudioClip(getClass().getResource("Sonidos/splash.wav"));
        aplastado = Applet.newAudioClip(getClass().getResource("Sonidos/aplastado.wav"));
        
     
        //Inicializo y ubico a los carros y troncos
        for(int i = 0; i < 4; i++) {
            troncos[i] = new Objeto("Imagenes/tronco.jpg"); 
            troncos[i].Y = (i + 1) * 40 + 40;
            if(i % 2 == 0) {
                carros[i] = new Objeto("Imagenes/carro2.gif");
                troncos[i].X = 400 - i * 6;
                carros[i].X = 400 - i * 8;
            } else {
                carros[i] = new Objeto("Imagenes/carro.gif");
                troncos[i].X = 0 + i * 6;
                carros[i].X = 400 + i * 7;
            }
            carros[i].Y = (i + 1) * 40 + 240;
            troncos[i].imagen.setBounds(troncos[i].X, troncos[i].Y, 150, 40);
            carros[i].imagen.setBounds(carros[i].X, carros[i].Y, 150, 40);
        }
        
        //Muestro la informaci�n en la pantalla
        LVidas = new JLabel(" Vidas: " + (vidas - 1) + " Nivel: " + velocidad_tronco / 6 + " Puntos: " + puntos);
        LVidas.setBounds(0, 440, 200, 40);
        LVidas.setBackground(Color.BLACK);
    }
    
    public void run() {
        do
        {
            //Chequeo si se le acabaron las vidas para terminar el juego
            if(vidas <= 0)
                break;
            //Chequeo si el sapo llego a su meta y Gana
            if(sapo.Y < 80) {
                //Le sumo 1000 puntos
                puntos += 1000;
                //Ubico al sapo en el inicio
                sapo.X = 180;
                sapo.Y = 440;
                sapo.imagen.setBounds(sapo.X, sapo.Y, 40, 40);
                //Actualizo el puntaje en la pantalla
                LVidas.setText(" Vidas: " + (vidas - 1) + " Nivel: " + velocidad_tronco / 6 + " Puntos: " + puntos);
            }
            
            //Chequeo si se muri�
            if(esta_muerto()) {
                 //Chequeo si fue aplastado por un carro y reproduzco su sonido
                if(sapo.Y >= 240 && sapo.Y < 480)
                    aplastado.play();
                
                //Actualizo la pantalla
                LVidas.setText(" Vidas: " + (vidas - 1) + " Nivel: " + velocidad_tronco / 6 + " Puntos: " + puntos);
                
                //Coloco un sapo muerto
                sapos_muertos[3 - vidas].setBounds(sapo.X, sapo.Y, 40, 40);
                vidas--; //Quito una vida
                
               
                
                //Reubico el spao
                sapo.X = 180;
                sapo.Y = 440;
                sapo.imagen.setBounds(sapo.X, sapo.Y, 40, 40);
                
                //Si se quedo sin vidas acabo el juego
                if(vidas == 0)
                    break;
            }
            
            //Si el sapo se monto en un tronco, hago que se mueva con el tronco a la misma direccion
            if(sapo.Y >= 40 && sapo.Y <= 220) {
                if(sapo.Y == 80 && sapo.X >= troncos[0].X && sapo.X <= troncos[0].X + 150) {
                    sapo.izquierda(velocidad_tronco);
                    sapo.imagen.setBounds(sapo.X, sapo.Y, 40, 40);
                }
                if(sapo.Y == 120 && sapo.X >= troncos[1].X && sapo.X < troncos[1].X + 150) {
                    sapo.derecha(velocidad_tronco);
                    sapo.imagen.setBounds(sapo.X, sapo.Y, 40, 40);
                }
                if(sapo.Y == 160 && sapo.X >= troncos[2].X && sapo.X <= troncos[2].X + 150) {
                    sapo.izquierda(velocidad_tronco);
                    sapo.imagen.setBounds(sapo.X, sapo.Y, 40, 40);
                }
                if(sapo.Y == 200 && sapo.X >= troncos[3].X && sapo.X < troncos[3].X + 150) {
                    sapo.derecha(velocidad_tronco);
                    sapo.imagen.setBounds(sapo.X, sapo.Y, 40, 40);
                }
                sapo.imagen.repaint();
            }
            
            //Muevo los troncos
            for(int i = 0; i < 4; i++) {
                if(i % 2 == 0) {
                    troncos[i].izquierda(velocidad_tronco);
                    if(troncos[i].X + 150 < 0)
                        troncos[i].X = 399;
                    carros[i].izquierda(velocidad_tronco + 3);
                    if(carros[i].X + 150 < 0)
                        carros[i].X = 399;
                    troncos[i].imagen.setBounds(troncos[i].X, troncos[i].Y, 150, 40);
                    carros[i].imagen.setBounds(carros[i].X, carros[i].Y, 150, 40);
                    continue;
                }
                troncos[i].derecha(velocidad_tronco);
                if(troncos[i].X > 400)
                    troncos[i].X = -100;
                carros[i].derecha(velocidad_tronco + 3);
                if(carros[i].X > 400)
                    carros[i].X = 0;
                troncos[i].imagen.setBounds(troncos[i].X, troncos[i].Y, 150, 40);
                carros[i].imagen.setBounds(carros[i].X, carros[i].Y, 150, 40);
            }
            
            try {
                this.sleep(120); //Duermo el proceso para que se note animacion
            } catch(Exception e) { }
        } while(true);
        
        //Creo una ventana con el mensjae
        JDialog dialogo = new JDialog();
        dialogo.getContentPane().setLayout(null);
        dialogo.setTitle("GAME OVER");
        dialogo.setBounds(30, 200, 250, 100);
        
        //Boton de aceptar
        JButton aceptar = new JButton("Aceptar");
        aceptar.setBounds(75, 30, 100, 20);
        
        //Hago que cuando le den clic al bot�n de aceptar se acabe el programa
        aceptar.addMouseListener(new  MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                System.exit(JDialog.EXIT_ON_CLOSE);
            }
        });
        
        //Asigno las propiedades a la ventana de "Fin del Juego"
        dialogo.setDefaultCloseOperation(3);
        dialogo.setResizable(false);
        dialogo.getContentPane().add(aceptar);
        
        //Publico con cuantos puntos gan�
        JLabel labelwin = new JLabel("Ganaste con un total de " + puntos);
        labelwin.setBounds(2, 2, 250, 20);
        
        //Agrego el mensaje a la ventana de "Fin del Juego"
        dialogo.getContentPane().add(labelwin);
        dialogo.show();
    }
    
// Metodo para saber si esta muerto el sapo, devuelve true si esta muerto    
    public boolean esta_muerto() {
        //Chequeo si un tronco se lo llevo
        if((sapo.X < 0 || sapo.X > 400) && sapo.Y >= 80 && sapo.Y < 240)
            return true;
        
        //Chequeo si fue atropellado
        for(int i = 0; i < 4; i++)
            if(sapo.Y == carros[i].Y && (sapo.X < carros[i].X + 69 && sapo.X >= carros[i].X || sapo.X + 40 < carros[i].X + 69 && sapo.X + 30 >= carros[i].X))
                return true;
        
         //Chequeo si se ahog� y reproduzco su sonido
        if(sapo.Y >= 80 && sapo.Y <= 220) {
            for(int i = 0; i < 4; i++)
                if(sapo.Y == (i + 1) * 40 + 40 && sapo.X >= troncos[i].X - 10 && sapo.X <= troncos[i].X + 160)
                    return false;
            
            //Reproduzco el sonido del agua
            sonido_agua.play();
            return true; //retrono positivo el que se ahogo
        } else {
            return false; //retrono falso porque no se ha muerto
        }
    }
    
    
}
