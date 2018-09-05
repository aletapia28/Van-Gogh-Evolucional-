
import java.awt.Color;
import java.util.*;
public class Genotype {
    public Circle circles[] = new Circle[GeneticAlgorithm.circleCount]; //Lista de circulos que contiene cada miembro de la población
    public int count; //Almacena la cantidad de circulos en el miembro actual de la población
    public int bg_red,bg_blue,bg_green, bg_alpha; //Los valores RGBA del background
    public double  cfitness, rfitness;
    double fitness;
    
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
        //Color color = new Color(bg_red, bg_green, bg_blue);
        Iterator iter = treemap.entrySet().iterator();
        // int entries = 0;
        // int sum_r = 0, sum_g = 0, sum_b=0;
        double color_red =bg_red, color_green=bg_green, color_blue=bg_blue, color_alpha=bg_alpha;
        while(iter.hasNext()){ //hasNext comprueba si sigue quedando elementos en el iterador
            //entries ++;
            Map.Entry entry = (Map.Entry)iter.next();
            Circle c = (Circle)entry.getValue();
            double t= (double)(c.alpha/255.0);
            // double new_bg_alpha = (double)(color.getAlpha()/255.0);
            double new_alpha=(double)(t+(1.0-t)*color_alpha);
            // System.out.println("red = "+Float.toString((double)((t * c.red + (1.0 - t) * color.getRed()*new_bg_alpha )/new_alpha)));
            // int bg_red = color.getRed();
            // int bg_green = color.getGreen();
            // int bg_blue = color.getBlue();
            // sum_r += c.red;
            // sum_g += c.green;
            // sum_b += c.blue;
            color_red=(t*c.red+(1.0-t)*color_red*color_alpha)/new_alpha;
            color_green=(t*c.green+(1.0-t)*color_green*color_alpha)/new_alpha;
            color_blue=(t*c.blue+(1.0-t)*color_blue*color_alpha)/new_alpha;
            color_alpha=new_alpha;
            // color = new Color((int)((t * c.red + (1.0 - t) *bg_red *new_bg_alpha )/new_alpha), (int)((t * c.green + (1.0 - t) * bg_green*new_bg_alpha)/new_alpha), (int)((t * c.blue + (1.0 - t) * bg_blue*new_bg_alpha)/new_alpha),(int)(new_alpha*255.0) );	
        }
        Color color = new Color((int)color_red, (int)color_green, (int)color_blue, (int)(255*color_alpha));
        // if(entries !=0)
	// {
	// 		sum_r = (int)(sum_r/(entries*1.0));
	// 		sum_g = (int)(sum_g/(entries*1.0));
	// 		sum_b = (int)(sum_b/(entries*1.0));
	// 		color = new Color(sum_r, sum_g, sum_b);
	// }
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
    public double getFitness(int[][] result){
        double ans=0;
        int countA=result.length;
        int countB=result[0].length;
        for(int i=0; i<result.length;i++){
            for(int j=0; j< result[0].length; j++){
                
                Color m= getColorofPoint(i,j);
                Color n= new Color(result[i][j]);
               // double moda= Math.sqrt(m.getRed()*m.getRed()+ m.getBlue()*m.getBlue()+m.getGreen()*m.getGreen());
               // double modb= Math.sqrt(n.getRed()*n.getRed()+n.getBlue()*n.getBlue()+n.getGreen()*n.getGreen());
                ans += ((Math.pow(2,abs(m.getRed() - n.getRed())/255.0) + Math.pow(2,abs(m.getGreen() - n.getGreen())/255.0) + Math.pow(2,abs(m.getBlue() - n.getBlue())/255.0)) )/6.0;
            }
        }
       // System.out.println("Largo result: "+countA+" largo result[0]: "+countB);
       this.fitness= 100000.0/ans;
       return this.fitness;
    }
    public void print(){
		//for(int i=0;i<GeneticAlgorithm.circleCount;++i)
		for(int i=0;i<-1;++i)
			{
				Circle temp = new Circle(circles[i]);
					//System.out.println("x = "+Integer.toString(temp.x)+" |y = "+Integer.toString(temp.y)+" |radius = "+Integer.toString(temp.radius) + " |red= "+ Integer.toString(temp.red) + " |green = "+ Integer.toString(temp.green)+" |blue= "+Integer.toString(temp.blue)+" |alpha= "+Float.toString(temp.alpha)+" |time= "+Integer.toString(temp.time));
			}
	}
    
}
