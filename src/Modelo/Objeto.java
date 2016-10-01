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
    public ImageIcon sapon; //norte
    public ImageIcon sapos; //sur
    public ImageIcon sapoe; //este
    public ImageIcon sapoo; //oeste
    
    public Objeto(String ruta) {
        //inicializo las coordenadas y la imagene cuya ruta se pide por parametro
        X = Y = 0;
        imagen = new JLabel();
        imagen.setIcon(new ImageIcon(getClass().getResource(ruta)));
    }
    
    //Este es el contructor del sapo
    public Objeto(String ruta, String sapo1, String sapo2, String sapo3, String sapo4) {
         //inicializo las coordenadas y la imagenes de las direcciones del sapo
        X = Y = 0;
        imagen = new JLabel();
        imagen.setIcon(new ImageIcon(getClass().getResource(ruta)));
        sapon = new ImageIcon(getClass().getResource(sapo1));
        sapos = new ImageIcon(getClass().getResource(sapo2));
        sapoe = new ImageIcon(getClass().getResource(sapo3));
        sapoo = new ImageIcon(getClass().getResource(sapo4));
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
            imagen.setIcon(sapon);
        if(dir.compareTo("sur") == 0)
            imagen.setIcon(sapos);
        if(dir.compareTo("oeste") == 0)
            imagen.setIcon(sapoo);
        if(dir.compareTo("este") == 0)
            imagen.setIcon(sapoe);
    }
    
    
}
