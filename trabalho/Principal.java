
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Principal{
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static int quant_fig; 
    static Figura [] figuras;

    public static Figura juntar(Figura a, Figura b){
        Figura resultante = new Figura();

        //verifica se pode juntar pela parte de cima
        if ((a.getX1() + b.getX3()) >= a.getX2()){   //tenta juntar pela parte de cima
            resultante.setX0( a.getX1() );
            resultante.setX1( a.getX1() + b.getX1() );
            resultante.setX2( resultante.getX0() + b.getX2() );
            resultante.setX3( resultante.getX0() + b.getX3() );
        }
        else{ //juntar pela parte de baixo
            resultante.setX3( a.getX2() );
            resultante.setX0( resultante.getX3() - b.getX3() );
            resultante.setX1( resultante.getX0() + b.getX1() );
            resultante.setX2( resultante.getX0() + b.getX2() );
        }
        
        return resultante.getClone();
    }


    public static void testeJuntarFigura(){
        Figura resultante = figuras[0].getClone();

        for (int i = 1; i < quant_fig; i++){

            resultante = juntar(resultante.getClone(), figuras[i].getClone());

        }
        
        System.out.println("x0 = " + resultante.getX0());
        System.out.println("x1 = " + resultante.getX1());
        System.out.println("x2 = " + resultante.getX2());
        System.out.println("x3 = " + resultante.getX3());
    }






    public static void main (String args []) throws IOException {
        
        quant_fig = Integer.parseInt(in.readLine());
        figuras = new Figura [quant_fig];
        String [] coordenadas; //recebe x1, x2, x3

        for (int i = 0; i < quant_fig; i++){            
        //for (int i = 0; i < 1; i++){            

            coordenadas = in.readLine().split(" ");
            //criando uma figura
            figuras [i] = new Figura(
                (float)0.0, 
                Float.valueOf(coordenadas[0]).floatValue(), 
                Float.valueOf(coordenadas[1]).floatValue(), 
                Float.valueOf(coordenadas[2]).floatValue()
            );   

            //System.out.println("\n\n"+figuras[i].getX3());                                             
        }
        
        testeJuntarFigura();

    }//end main

}//end class