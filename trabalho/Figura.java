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
    private int id;

    public Figura(){
        this.x0 = this.x1 = this.x2 = this.x3 = this.base_a = this.base_b = this.area = (float)0.0;
        this.id = -1;
    }

    public Figura (float x0, float x1, float x2, float x3, int id){
        this.x0 = x0;
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.id = id;
        //calcAreaFigura();
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

    public void calcAreaFigura(){
        base_a = x1;

        if (x3 < 0)
            base_b = x2 + (x3 * (-1));
        else if (x3 > 0)
            base_b = x2 - x3;
        else
            base_b = x2;
        
        area = ((base_a + base_b)/2) * altura;
    }//enc calcAreaFigura

    public float getArea(){
        return this.area;
    }


    public Figura getClone(){
        Figura clone = new Figura();        
        clone.setX0( this.x0 );
        clone.setX1( this.x1 );
        clone.setX2( this.x2 );
        clone.setX3( this.x3 );
        clone.setId( this.id );

        return clone;
    }//end getClone

    public void newFigura (Figura f){
        this.x0 = f.getX0();
        this.x1 = f.getX1();
        this.x2 = f.getX2();
        this.x3 = f.getX3();
        this.id = f.getId();
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }
    
}//end class