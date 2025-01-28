

public class Celda
{
 protected int c;
  
  public Celda(){
    
      c=0;
    }
    
  public int obtenerCosto(){
    
      return c;
    }
    
  public String obtenerImagen(){
               
      String pathImg="imagenCeldaLibre.jpg";
        
      return pathImg;  
       
    }  
}
