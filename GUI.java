import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame {
    //Atributo de instancia de la logica
    private Juego g;
    
    //Atributos de instancia gráficos
    //paneles
    private JPanel panelCeldas;// panel donde se muestra la matriz de botones del tablero
    private JPanel panelInfo; // panel donde se encuentran las etiquetas        
    
    //botones
    private JButton [][] botones; //matriz de botones que representan las celdas tel tablero
    private JButton botonIniciar;
        
    //labels y textField
    
    private JLabel TextoNuevaApuesta;
    private JTextField nuevaApuesta;
    private JLabel etiquetaTextoPuntos;
    private JLabel etiquetaPuntos; 
    private JLabel etiquetaPerdio;
    private JLabel etiquetaTextoTesoros;
    private JLabel etiquetaTesoros;
    

    //constructor
      public GUI(){ 
            super("Minas y Tesoros");
            g= new Juego(100);         
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(new Dimension(900, 540));
            inicializarGUI();
            setVisible(true);
        }
    //metodo privado para inicializar las componentes     
      private void inicializarGUI(){
          
         this.setLayout(new BorderLayout());
         Color colorFondo= new Color(193,173,252); // color de fondo de todas las componentes
                
         TextoNuevaApuesta= new JLabel("Ingrese su apuesta");
         nuevaApuesta = new JTextField(10);
         nuevaApuesta.addActionListener(new OyenteApuesta());
         
         //Label con el texto "Puntos"
         etiquetaTextoPuntos= new JLabel("Puntos:");
         
         //Label con el puntaje inicial
         etiquetaPuntos= new JLabel(" ");
         
         //Label qie indica si sigue en juego o no
         etiquetaPerdio= new JLabel("");
         
         //label con el texto "Total tesoros"
         etiquetaTextoTesoros= new JLabel ("Total Tesoros:");
         
         //etiqueta con el numero que indica la cantidad total de tesoros
         etiquetaTesoros= new JLabel("");
         
         //armado del boton Iniciar
         botonIniciar= new JButton("Iniciar");
         botonIniciar.addActionListener(new OyenteBotonIniciar());
         botonIniciar.setEnabled(false); //inicialmente se encunetra deshabiliado
    
         //armado del panel donde se encuentran las etiquetas anteriores
         panelInfo= new JPanel();
         panelInfo.setPreferredSize(new Dimension(900,100));
         
         panelInfo.add(TextoNuevaApuesta);
         panelInfo.add(nuevaApuesta);
         
         panelInfo.add(etiquetaTextoTesoros);
         panelInfo.add(etiquetaTesoros);
         panelInfo.add(etiquetaTextoPuntos);
         panelInfo.add(etiquetaPuntos);
         panelInfo.add(etiquetaPerdio);
         panelInfo.add(botonIniciar);
         
               
         //armado del panel donde se muetran las celdas del tablero
         panelCeldas= new JPanel();
         panelCeldas.setLayout(new GridLayout(6,6)); // se le establece un gridLayout con las medidas standar del tablero 
         panelCeldas.setBackground(colorFondo);
         panelCeldas.setBorder(BorderFactory.createLineBorder(Color.black));
         inicializarBotones(); // inicializa cada uno de los botones de la matriz
        
         //se agregan los tres paneles principales al contentpane
         this.add(panelInfo,BorderLayout.NORTH);
         this.add(panelCeldas,BorderLayout.CENTER);
        
      }
      
      /**Inicializar botones: inicializa los botones que representan las celdas del tablero.
       * @todo:
       * Crear la matriz de botones. 
       * Por cada elemento de la matriz: 
       *   Crear cada uno de los botones;
       *   establecer su action command tal como se encuentra comentado en este código fuente;
       *   vincularlo con  el oyente "OyenteBotonCelda";
       *   indicar que dicho boton se encuentra deshabilitado;
       *   establecer la imagen "imagenInicial.jpg";
       *   adicionar el boton en cuestión al panelCeldas. 
        */
      
      private void inicializarBotones(){
         //completar
             botones = new JButton[6][6];
             for (int i = 0; i<6; i++){
                 for (int j = 0; j<6; j++){
                        botones[i][j] = new JButton();
                        botones[i][j].setActionCommand(i+","+j);
                        OyenteBotonCelda oyenteC = new OyenteBotonCelda();
                        botones[i][j].addActionListener(oyenteC);
                        botones[i][j].setEnabled(false);
                        botones[i][j].setIcon(new ImageIcon ("imagenInicial.jpg"));
                        panelCeldas.add(botones[i][j]);
                    }
                }
         //botones[i][j].setActionCommand(i+","+j); // se setea el action command "i,j"      
      }
      
        
       /**OyenteBotonCelda: oyente que reacciona al click de las celdas del tablero.
       Inicialmente se obtiene el action command del evento para recuperar los indices (i,j) que permiten acceder a la matriz. 
       Se obtiene el tablero para poder obtener el puntaje de la celda en cuestion:
       
       @todo:
       Deshabilitar el botón que disparó el evento. 
       Actualizar los puntos del juego obteniendo el costo de la celda y actualizar la etiquetaPuntos.
       Establecer la imagen correspondiente a su celda(obtener la ruta de la imagen a traves del tablero). Revise la sintaxis en el enunciado
       
       Si ganó o perdió el juego actualizar la "etiquetaPerdio" según corresponda ("JUEGO GANADO"-"JUEGO PERDIDO"), ademas:                    
                +habilitar el el textField "nuevaApuesta"
                +por cada uno de los botones de la matriz:
                    --deshabilitar el boton correspondiente
                    --Si la celda corresponde a la celda de un tesoro (su costo es < 0)
                        pintarla de amarillo 
                    --Si la celda corresponde a la celda de una mina (su costo es > 0)
                        pintarla de rojo                                     
                    --Establecer la imagen correspondiente a su celda(obtener la ruta de la imagen a traves del tablero).
        */
        
      private class OyenteBotonCelda implements ActionListener{ 
             public void actionPerformed(ActionEvent e){
                
                String indices=e.getActionCommand();
                String[] iyj= indices.split(",");
                int i=Integer.parseInt(iyj[0]);
                int j=Integer.parseInt(iyj[1]);
                Tablero t=g.obtenerTablero();
                
                //completar
                
                botones[i][j].setEnabled(false);
                g.actualizarPuntos(t.obtenerCosto(i, j));
                etiquetaPuntos.setText(""+g.obtenerPuntos());
                botones[i][j].setIcon(new ImageIcon(t.obtenerImagenCelda(i,j)));
                /*if(t.obtenerCosto(i,j) > 0){
                    botones[i][j].setIcon(new ImageIcon ("imagenMinaSimple.jpg"));
                    }
                    else if (t.obtenerCosto(i,j) < 0){
                            botones[i][j].setIcon(new ImageIcon ("imagenTesoro.jpg"));
                            }
                            else botones[i][j].setIcon(new ImageIcon ("ImagenCeldaLibre.jpg"));
                */
                            
                if (g.gano()==true)
                    etiquetaPerdio.setText("JUEGO GANADO");
                    else if (g.perdio()==true)
                            etiquetaPerdio.setText("JUEGO PERDIDO");
                if(g.gano() || g.perdio()){            
                    for(int f = 0; f<6; f++){       //f de filas
                        for(int c = 0; c<6; c++){       //c de columnas
                            botones[f][c].setEnabled(false);
                            botones[f][c].setIcon(new ImageIcon(t.obtenerImagenCelda(f,c)));
                            if(t.obtenerCosto(f,c) <0)
                                botones[f][c].setBackground(Color.yellow);
                            else if (t.obtenerCosto(f,c) >0)                          
                                botones[f][c].setBackground(Color.red);
                                
                        }     
                    } 
                }
                    nuevaApuesta.setEnabled(true);
             }
      }
            
       
      
    
       /**OyenteBotonIniciar:  reacciona al click del botonIniciar
     * @todo:
     * Crear un nueva instancia de juego con la apuesta ingresada en el textField "nuevaApuesta";
     * Vaciar y Deshabilitar  "nuevaApuesta";
     * Actualizar la "etiquetaPerdio" con el valor "En juego";
     * Actualizar la "etiquetaTesoros" y "etiquetaPuntos";     
     * Deshabilitar el botón Iniciar
     * Por cada uno de los botones de la matriz:
     *  -habilitarlo
     *  -pintarlo de blanco 
     *  -establecer la imagen "imagenInicial.jpg"
       
       */
      private class OyenteBotonIniciar implements ActionListener{
             public void actionPerformed(ActionEvent e){
                g = new Juego(Integer.parseInt(nuevaApuesta.getText()));
                nuevaApuesta.setText("");
                nuevaApuesta.setEnabled(false);
                etiquetaPerdio.setText("En Juego");
                etiquetaPuntos.setText(""+(g.obtenerPuntos()));
                etiquetaTesoros.setText(""+(g.obtenerTablero().cantTesoros()));
                botonIniciar.setEnabled(false);
                for(int i = 0; i<6; i++){
                    for(int j = 0; j<6; j++){
                        botones[i][j].setEnabled(true);
                        botones[i][j].setBackground(Color.white);
                        botones[i][j].setIcon(new ImageIcon ("imagenInicial.jpg"));
                    }
                }
                
            }
    }
   
    private class OyenteApuesta implements ActionListener{     
             public void actionPerformed(ActionEvent e){   
                botonIniciar.setEnabled(true);
                etiquetaTesoros.setText("");
                etiquetaPuntos.setText(""); 
                etiquetaPerdio.setText("");
                
       }   
    }
    }
    
