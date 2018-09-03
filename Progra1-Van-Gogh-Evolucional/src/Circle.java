public class Circle {
    public int x,y; //Cordenadas del centro del circulo
    public int radius; //El radio del circulo
    public int red, green, blue; //Los valores RGB de los colores del circulo
    public int alpha; //Valor del color alpha
    public int time; //Momento en el que se agrego el circulo
    
    //Función que determina si el punto está dentro del circulo
    public boolean isPointInside(int i, int j){
        return (this.x-i) * (this.x-i) +(this.y-j) * (this.y-j) <= this.radius*this.radius;
    }
    //Constructor 1
    public Circle(){
    }
    //Constructor 2
    public Circle(Circle other){
        this.x=other.x;
        this.y=other.y;
        this.radius=other.radius;
        this.red=other.red;
        this.green=other.green;
        this.blue=other.blue;
        this.alpha=other.alpha;
        this.time=other.time;
    }
    
}