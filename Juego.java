

public class Juego
{private Tablero t;
 private int puntos;
 private int tesoros;
 
 public Juego(int p){   
     t=new Tablero();
     puntos=p;   
     tesoros=0;
    }
    
 public Tablero obtenerTablero(){ 
     return t;
    }
 public int obtenerPuntos(){
     return puntos;
    }
 public void actualizarPuntos(int d){
    if (d<0) 
        tesoros++;
    puntos=puntos-d;
    
    }
    
 public boolean perdio(){ 
     return puntos<=0;
    }   
 
 public boolean gano(){          
     return tesoros==t.cantTesoros();
    }     
}

