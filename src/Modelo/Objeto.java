package Modelo;


import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Esta es la clase que administra los objetos atraves de su movimiento
 * @author Jonathan Muñoz, Carlos Ortiz, Jefersson Guevara
 * @version 1.0.0.0
 */
public class Objeto {
    
    /**Variable de posicion en el eje X  */
    public int X;
    
    /**Variable de posicion en el eje X */
    public int Y;
    
    /** Variable Jlabel para cargar las imagenes y poderlos mover */
       public JLabel imagen;
    
    /** Se crea un icono de imagen para la posicion  norte de la persona*/
    public ImageIcon personan; 
    
    /**  Se crea un icono de imagen para la posicion  sur de la persona */
    public ImageIcon personas; 
    
    /**  Se crea un icono de imagen para la posicion  este de la persona */
    public ImageIcon personae; 
    
    /** Se crea un icono de imagen para la posicion  oeste de la persona  */
    public ImageIcon personao; 
    
    /**
     * Inicializo las coordenadas y la imagenes cuya ruta se pide por parametro
     *
     * @param ruta donde se encuentra almacenado el icono a cargar
     */
    public Objeto(String ruta) {
        //inicializo las coordenadas y la imagene cuya ruta se pide por parametro
        X = Y = 0;
        imagen = new JLabel();
        imagen.setIcon(new ImageIcon(getClass().getResource(ruta)));
    }
    
    /**
     * Este es el contructor de la persona
     *
     * @param ruta de la imagen
     * @param persona1 es la imagen con perspectiva norte
     * @param persona2 es la imagen con perspectiva sur
     * @param persona3 es la imagen con perspectiva este
     * @param persona4 es la imagen con perspectiva oeste
     */
    //
    public Objeto(String ruta, String persona1, String persona2, String persona3, String persona4) {
         //inicializo las coordenadas y la imagenes de las direcciones del sapo
        X = Y = 0;
        imagen = new JLabel();
        imagen.setIcon(new ImageIcon(getClass().getResource(ruta)));
        personan = new ImageIcon(getClass().getResource(persona1));
        personas = new ImageIcon(getClass().getResource(persona2));
        personae = new ImageIcon(getClass().getResource(persona3));
        personao = new ImageIcon(getClass().getResource(persona4));
    }
    
    
    /**
     *  Arriba:Este metodo mueve la persona en el eje Y positivo
     * @param num recibe la posicion actual
     */
 
    public void arriba(int num) {
        Y -= num;
    }
    /**
     * Abajo: Este metodo mueve la persona en el eje Y negativo
     * @param num recibe la posicion actual
     */
    public void abajo(int num) {
        Y += num;
    }
    
    /**
     * Izquierda: Este metodo mueve la persona en el eje X negativo
     * @param num recibe la posicion actual
     */
    public void izquierda(int num) {
        X -= num;
    }
    
    /**
     * Derecha: Este metodo mueve la persona en el eje Y positivo
     * @param num recibe la posicion actual
     */
    public void derecha(int num) {
        X += num;
    }
    
    
    /**
     * Metodo para decirle que imagen poner segun la direccion que se movio
     *  @param dir La direccion en que se ha movido
     */
    //
    public void establecer_direccion(String dir) {
        if(dir.compareTo("norte") == 0)
            imagen.setIcon(personan);
        if(dir.compareTo("sur") == 0)
            imagen.setIcon(personas);
        if(dir.compareTo("oeste") == 0)
            imagen.setIcon(personao);
        if(dir.compareTo("este") == 0)
            imagen.setIcon(personae);
    }
    
    
}
