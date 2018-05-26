
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Principal{
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static int quant_fig; 
    static Figura [] figuras;
    //numero da permutacao atual
	private static int cont=0; 
    //armazena a permutacao corrente
	private static Figura [] p;
    private static ArrayList<Figura[]> permutacoes = new ArrayList<Figura[]>();


    /**
        metodo que junta duas figuras
    */
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


     public static void testePermuta() {
		
		p = new Figura[figuras.length];

        for (int i = 0; i < p.length; i++){
            p[i] = new Figura(); 
        }

		permuta(figuras, 0);
        imprimePermutacoes();
	}


    /**
	 * método recursivo que implementa as permutacoes
	 * @param vet
	 * @param nivel
	 */
	private static void permuta(Figura []figuras, int nivel) {		
		if (nivel == figuras.length) {
			cont++;
			//imprime();
            addPermutacao();
            //imprimePermutacoes();
		} 
        else {					
			for (int i=0; i < figuras.length; i++) {
			
				boolean achou = false;
			
				for (int j = 0; j < nivel; j++) {				
					if (p[j].getId() == figuras[i].getId()) 
                        achou = true;
				}
			
				if (!achou) {					
					p[nivel] = figuras[i].getClone();
					permuta(figuras, nivel+1);
				}
				
			} //--for			
		} //--if/else		
	} //--permuta



    /** imprime a permutacao corrente */
	private static void imprime() {
		
		System.out.println();
		System.out.print("(" + cont + ") : ");
		for (int i=0; i < p.length; i++) 
            System.out.print(p[i].getId() + " ");		
        
	} //--imprime


    private static void addPermutacao(){
        Figura [] newPerm = new Figura[figuras.length];

        for (int i = 0; i < newPerm.length; i++){
            newPerm[i] = p[i].getClone();
        }

        permutacoes.add(newPerm);
    }
  

    
    private static void imprimePermutacoes() {
        int aux = 0;
		System.out.println();
		
		for (Figura[] f : permutacoes){             
            
            aux++;
            System.out.print("(" + aux + ") : ");    
            for (int i=0; i < f.length; i++) 
                System.out.print(f[i].getId() + " ");	

            System.out.println();
        }
	} //--imprime






    /** imprime a permutacao corrente */
	private static void imprimeFiguras() {
		
		System.out.println(); cont++;
		
		for (int i=0; i < figuras.length; i++) {
            System.out.print("(" + cont + ") : ");
            System.out.println(figuras[i].getId() + " ");
            cont++;
        }
		System.out.println();
	} //--imprime



   



    public static void main (String args []) throws IOException {        
        quant_fig = Integer.parseInt(in.readLine());
        figuras = new Figura [quant_fig];
        String [] coordenadas; //recebe x1, x2, x3        
        int id = 0; //identificador da figura

        //criando figuras
        for (int i = 0; i < quant_fig; i++){                    

            coordenadas = in.readLine().split(" ");
            //criando uma figura
            figuras [i] = new Figura(
                (float)0.0, 
                Float.valueOf(coordenadas[0]).floatValue(), 
                Float.valueOf(coordenadas[1]).floatValue(), 
                Float.valueOf(coordenadas[2]).floatValue(),
                id
            );   
            id++;                                                       
        }//end for
        
        //imprimeFiguras();

        //testeJuntarFigura();
        testePermuta();

        System.out.println();
    }//end main

}//end class