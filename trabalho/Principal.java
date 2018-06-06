
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
    //cada posicao do array 'figura_finial' representa uma posição do array 'permutacoes'
    //coordenada final de cada permutação
    private static ArrayList<Figura> coordenada_final = new ArrayList<Figura>();    
    private static float areaTotalFiguras = (float)0.0;
    private static ArrayList<Float> areaTotalPano = new ArrayList<Float>();    
    private static ArrayList<Float> areaTotalDesperdicio = new ArrayList<Float>(); 
    private static float menorDesperdicio = (float)0.0;   


    /**
        metodo que junta duas figuras
    */
    public static Figura juntar(Figura a, Figura b){
        Figura resultante = new Figura();
        //System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        //verifica se pode juntar pela parte de cima
        if ((a.getX1() + b.getX3()) >= a.getX2()){   //tenta juntar pela parte de cima
            resultante.setX0( a.getX1() );
            resultante.setX1( a.getX1() + b.getX1() );
            resultante.setX2( resultante.getX0() + b.getX2() );
            resultante.setX3( resultante.getX0() + b.getX3() );
            //System.out.print("cima = ");
        }
        else{ //juntar pela parte de baixo
            resultante.setX3( a.getX2() );
            resultante.setX0( resultante.getX3() - b.getX3() );
            resultante.setX1( resultante.getX0() + b.getX1() );
            resultante.setX2( resultante.getX0() + b.getX2() );
            //System.out.print("baixo = ");
        }
        //System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
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


    /**
        metodo que inicia a permutacao
    */
    public static void fazerPermuta() {
		
		p = new Figura[figuras.length];

        for (int i = 0; i < p.length; i++){
            p[i] = new Figura(); 
        }

		permuta(figuras, 0);        
	}//--fazerPermuta


    /**
	 * método recursivo que implementa as permutacoes	 	 
	 */
	private static void permuta(Figura []figuras, int nivel) {		
		if (nivel == figuras.length) {
			cont++;
			//imprime();
            addNovaPermutacao();
            juntarFigurasNovaPermutacao();            
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


    /** adiciona uma nova permutação no arraylist de permutações */
    private static void addNovaPermutacao(){
        Figura [] newPerm = new Figura[figuras.length];

        for (int i = 0; i < newPerm.length; i++){
            newPerm[i] = p[i].getClone();
        }

        permutacoes.add(newPerm);
    }//--addNovaPermutacao

    


    /** 
        metodo que junta as figuras de acordo com a nova permutação
        para cada junção uma figura geral é formada com
        o x0 e x3 inicial
        e x1 e x2 final, 
        ou seja os dois primeiros pontos no inicio do pano
        e os dois pontos que estão no final do pano.
        Esse método obtem as coordenadas finais de uma permutação.
    */
    private static void juntarFigurasNovaPermutacao(){
        Figura novaFigura = new Figura();
        Figura [] perm = permutacoes.get( permutacoes.size()-1 ); //ultima permutacao        
        Figura resultante = new Figura(); //dados do último trapezoide

        novaFigura.setX0(perm[0].getX0());  //dados da primeira figura
        novaFigura.setX3(perm[0].getX3());  //dados da primeiroa figura                

        resultante = perm[0].getClone();

        for (int i = 1; i < perm.length; i++)
            resultante = juntar(resultante.getClone(), perm[i].getClone()); //anterior com a proxima

        novaFigura.setX1(resultante.getX1()); //dados do ultimo trapezoide
        novaFigura.setX2(resultante.getX2()); //dados do ultimo trapezoide

        coordenada_final.add(novaFigura.getClone());
    }//--juntarFigurasNovaPermutacao
    
  
    /**
        metodo que imprime todas permutacoes 
    */
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
	} //--imprimePermutacoes


    /**
        metodo que calcula a area de todas as figuras
    */
    private static void calcAreaTotalFiguras(){
        areaTotalFiguras = (float)0.0;        
        //pegando qualquer permutação para calcular areas das figuras
        Figura[] f = permutacoes.get(0); 

        for (int i=0; i < f.length; i++) {
            f[i].calcAreaFigura();
            areaTotalFiguras += f[i].getArea();                
        }           
    }//--calcAreas



    /** 
        metodo que as figuras
    */
	private static void imprimeFiguras() {
		
		System.out.println(); cont++;
		
		for (int i=0; i < figuras.length; i++) {
            System.out.print("(" + cont + ") : ");
            System.out.println(figuras[i].getId() + " ");
            cont++;
        }
		System.out.println();
	} //--imprime


    /**
        metodo que calcula a area total do pano em cada permutacao
    */
    private static void calcAreaPano(){
        Float areaTotal = (float)0.0;        
        Float base_a = (float)0.0;
        Float base_b = (float)0.0;
        
        //percorrer arraylista q contem uma coordenada final de cada permutacao
        for (Figura f : coordenada_final){
            areaTotal = (float)0.0; 
            base_a = calcDistancia(f.getX0(), f.getX1());
            base_b = calcDistancia(f.getX3(), f.getX2());

            if (base_a > base_b){
                areaTotal = base_a * 100;
                areaTotalPano.add(areaTotal);
            }
            else{
                areaTotal = base_b * 100;
                areaTotalPano.add(areaTotal);
            }            
        }//end for                            
        
    }//--calcAreaPano


    /**
        a => x0 ou x3
        b => x1 ou x2
    */
    private static float calcDistancia(float a, float b){        
        float resp = (float)0.0;

        if ((a < 0) && (b < 0))
            resp = (a * (-1)) - (b * (-1));
        else if ((a < 0) && (b >= 0))
            resp = (a * (-1)) + b;
        else if((a >= 0) && (b >= 0))        
            resp = b - a;

        return resp;
    }//--calcDistancia


    /**
        metodo que calcula a area de desperdício de cada permutacação
    */
    private static void calcDesperdicio(){
        float aux;
        menorDesperdicio = areaTotalFiguras;

        //percorrer a area total do pano de cada permutacao
        for (Float a : areaTotalPano){
            aux = a - areaTotalFiguras;
            areaTotalDesperdicio.add(aux);
            
            //menor desperdicio
            if (aux < menorDesperdicio)
                menorDesperdicio = aux;
        }

        System.out.println("\n\n menor = " + menorDesperdicio);
        //for (Float b : areaTotalDesperdicio)
          //  System.out.println("desperdicio: " + b);
        //System.out.println("\n\n");
    }//--calcDesperdicio


    /**
        metodo que calcula a melhor combinação
    */
    private static void calcMelhorCombinacao(){
        int pos = 0;

        //percorrer o valor de desperdicio de cada permutação
        for (int i = 0; i < areaTotalDesperdicio.size()-1; i++){
            pos = 0;
            if (areaTotalDesperdicio.get(i) == menorDesperdicio){
                pos = i; //posicao com menor desperdicio
                i = areaTotalDesperdicio.size(); //sair do loop
            }
        }//end for

        //exibir
        Figura []fig = permutacoes.get(pos);
        System.out.println("\n\n\nMELHOR COMBINAÇÃO:\n");
        for (Figura f : fig)
            System.out.print(f.getId() + " ");
        System.out.println("\n\n");
    }//--calcMelhorCombinacao



    private static void controle(){
        //imprimeFiguras(); 
        System.out.println("\nFazendo permutacoes...");       
        fazerPermuta();
        //imprimePermutacoes();
        System.out.println("\ncalculando area total das figuras...");       
        calcAreaTotalFiguras();
        //System.out.println("area total figuras = " + areaTotalFiguras);
        //testeJuntarFigura();
        System.out.println("\ncalculando area do pano...");       
        calcAreaPano();
        System.out.println("\ncalculando desperdicio...");       
        calcDesperdicio();
        System.out.println("\ncalculando melhor combinação...");       
        calcMelhorCombinacao();
    }//end controle



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
        
        controle();

        System.out.println();
    }//end main

}//end class