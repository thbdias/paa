public class Figura {
    private float x0;
    private float x1; 
    private float x2;
    private float x3;
    private float altura = (float)100.0;
    //para calcular area
    private float base_a;
    private float base_b;
    private float area;

    public Figura(){}

    public Figura (float x0, float x1, float x2, float x3){
        this.x0 = x0;
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        calcArea();
    }

    public void setX0(float x0){
        this.x0 = x0;
    }

    public void setX1(float x1){
        this.x1 = x1;
    }

    public void setX2(float x2){
        this.x2 = x2;
    }

    public void setX3(float x3){
        this.x3 = x3;
    }

    public float getX0(){
        return this.x0;
    }

    public float getX1(){
        return this.x1;
    }

    public float getX2(){
        return this.x2;
    }

    public float getX3(){
        return this.x3;
    }

    public float getAltura(){
        return this.altura;
    } 

    private void calcArea(){
        base_a = x1;

        if (x3 < 0)
            base_b = x2 + (x3 * (-1));
        else if (x3 > 0)
            base_b = x2 - x3;
        else
            base_b = x2;
        
        area = ((base_a + base_b)/2) * altura;
    }//enc calcArea

    public float getArea(){
        return area;
    }
    
}//end class