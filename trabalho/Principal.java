
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Principal{
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static int quant_fig; 

    public static void main (String args []) throws IOException {
        
        quant_fig = Integer.parseInt(in.readLine());
        Figura [] figuras = new Figura [quant_fig];
        String [] coordenadas; //recebe x1, x2, x3

        //for (int i = 0; i < quant_fig; i++){            
        for (int i = 0; i < 1; i++){            

            coordenadas = in.readLine().split(" ");
            //criando uma figura
            figuras [i] = new Figura(
                (float)0.0, 
                Float.valueOf(coordenadas[0]).floatValue(), 
                Float.valueOf(coordenadas[1]).floatValue(), 
                Float.valueOf(coordenadas[2]).floatValue()
            );   

            System.out.println("\n\n"+figuras[i].getArea());                                             
        }
        

    }//end main

}//end class