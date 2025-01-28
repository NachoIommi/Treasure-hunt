import java.util.*;

public class Tablero
{   private static final int max=20;
    
    private Celda[][] m;
    private int cantMinas;
    private int cantTesoros;
    
    
    public Tablero(){
        cantMinas=0;
        cantTesoros=0;
        m= new Celda[6][6];
        Random n= new Random();
        for(int i=0; i<m.length;i++)
            for(int j=0;j<m[0].length;j++)
              if((cantTotalMinasTesoros()<max && n.nextInt()%2==0)&&(n.nextInt()%2==0))
                {
                    if((n.nextInt()%2==0)&&(n.nextInt()%2==0))
                        {m[i][j]= new Tesoro();
                         cantTesoros++;}
                    else    
                        {m[i][j]= new Mina();
                         cantMinas++;}
                }
               else
                 m[i][j]= new Celda();
    }
   
    public int cantMinas(){
        return cantMinas;
    }
    
    public int cantTesoros(){
        return cantTesoros;
    }
    
    public int cantTotalMinasTesoros(){
        return cantMinas+cantTesoros;
    }
    
    public int obtenerCosto(int i, int j){
        return m[i][j].obtenerCosto();
    }
    
    public int cantFil(){ return m.length;}
    
    public int cantCol(){return m[0].length;}
           

    
    public String obtenerImagenCelda(int i, int j){
        return m[i][j].obtenerImagen();
    }
}
