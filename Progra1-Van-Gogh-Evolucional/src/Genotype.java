
import java.awt.Color;
import java.util.*;
public class Genotype {
    public Circle circles[] = new Circle[GeneticAlgorithm.circleCount]; //Lista de circulos que contiene cada miembro de la población
    public int count; //Almacena la cantidad de circulos en el miembro actual de la población
    public int bg_red,bg_blue,bg_green, bg_alpha; //Los valores RGBA del background
    public float  cfitness, rfitness;
    float fitness;
    
    public Genotype(){
        for(int i=0; i<circles.length;i++){
            this.circles[i] =new Circle();
        }
    }
    public Genotype(Genotype other){
        for(int i=0; i<circles.length; i++)
            this.circles[i]=new Circle(other.circles[i]);
        this.count=other.count;
        this.bg_alpha=other.bg_alpha;
        this.bg_blue=other.bg_blue;
        this.bg_green=other.bg_green;
        this.bg_red=other.bg_red;
        this.fitness=other.fitness;
        this.cfitness=other.cfitness;
        this.rfitness=other.rfitness;
    }
    @SuppressWarnings("empty-statement")
    public Color getColorofPoint(int i, int j){
        TreeMap <Integer, Circle> treemap= new TreeMap <Integer, Circle>(); //Crea un treemap (el treemap los ordena de forma "natural" es decir, si son enteros de menor a mayor
        for (int k=0;k<circles.length; ++k){
            if(circles[k].isPointInside(i, j)){
                treemap.put(circles[k].time, circles[k]);
            }  
        }
        Iterator iter = treemap.entrySet().iterator();
        double color_red =bg_red, color_green=bg_green, color_blue=bg_blue, color_alpha=bg_alpha;
        while(iter.hasNext()){ //hasNext comprueba si sigue quedando elementos en el iterador
            Map.Entry entry = (Map.Entry)iter.next();
            Circle c = (Circle)entry.getValue();
            double t= (double)(c.alpha/255.0);
            double new_alpha=(double)(t+(1.0-t)*color_alpha);
            color_red=(t*c.red+(1.0-t)*color_red*color_alpha)/new_alpha;
            color_green=(t*c.green+(1.0-t)*color_green*color_alpha)/new_alpha;
            color_blue=(t*c.blue+(1.0-t)*color_blue*color_alpha)/new_alpha;
            color_alpha=new_alpha;
        }
        Color color = new Color((int)color_red, (int)color_green, (int)color_blue, (int)(255*color_alpha));
        return color;
    }
    public int abs(int diff){
        if(diff<0){
            return (-1*diff);
        }
        else{
            return diff;
        }
    }
    public int idnt(int diff){
         if(diff==0){
             return 1;
         }
         else{
             return 0;
         }
    }
  
    public void print(){
		for(int i=0;i<-1;++i)
			{
				Circle temp = new Circle(circles[i]);
				}
	}
    
}
