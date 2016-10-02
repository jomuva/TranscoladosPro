package Modelo;


import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Objeto {
    //Variables para las coordenadas del objeto
    public int X;
    public int Y;
    //Imagen a Mostrar
    public JLabel imagen;
    
    //estas imagenes s�lo se aprovechan para el sapo y son las direcciones del mismo
    public ImageIcon personan; //norte
    public ImageIcon personas; //sur
    public ImageIcon personae; //este
    public ImageIcon personao; //oeste
    
    public Objeto(String ruta) {
        //inicializo las coordenadas y la imagene cuya ruta se pide por parametro
        X = Y = 0;
        imagen = new JLabel();
        imagen.setIcon(new ImageIcon(getClass().getResource(ruta)));
    }
    
    //Este es el contructor del sapo
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
    
    
    //Metodos para mover las coordenadas del sapo segun la direccion
    public void arriba(int num) {
        Y -= num;
    }
    
    public void abajo(int num) {
        Y += num;
    }
    
    public void izquierda(int num) {
        X -= num;
    }
    
    public void derecha(int num) {
        X += num;
    }
    
    
    //Metodo para decirle que imagen poner segun la direccion que se movi�
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
