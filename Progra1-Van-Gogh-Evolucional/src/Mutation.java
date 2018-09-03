
import java.util.Random;


public class Mutation {
    public static int POPSIZE;
    public static int circleCount;
    public static double PMUTATION;
    public static double PMUTATIONCOLOR;
    public static Genotype population[];
    public static int row;
    public static int col;
    public static int RADIUSLIMIT;
    
    
    public Mutation(int POPSIZE, int circleCount, double PMUTATION, double PMUTATIONCOLOR, Genotype population[], int row, int col, int RADIUSLIMIT){
        this.POPSIZE=POPSIZE;
        this.PMUTATION=PMUTATION;
        this.PMUTATIONCOLOR=PMUTATIONCOLOR;
        this.RADIUSLIMIT=RADIUSLIMIT;
        this.circleCount=circleCount;
        this.col=col;
        this.row=row;
        this.population=population;
    }
    public Genotype[] mutate() {
        for(int i = 0; i < POPSIZE; i++) {
            Random randomGenerator = new Random();
            for(int j = 0 ; j<circleCount;++j){
                double x = randomGenerator.nextInt(10000) / 10000.0;
                if(x<PMUTATION){
                    x= randomGenerator.nextInt(10000) / 10000.0;
                    if(x < PMUTATIONCOLOR) {						
                        population[i].circles[j].x = new Integer(randomGenerator.nextInt(row));
                    }
                    x= randomGenerator.nextInt(10000) / 10000.0;
                    if(x < PMUTATIONCOLOR) {	
                        population[i].circles[j].y = new Integer(randomGenerator.nextInt(col));
                    }
                    x= randomGenerator.nextInt(10000) / 10000.0;
                    if(x < PMUTATIONCOLOR) {	
                        population[i].circles[j].red = new Integer(randomGenerator.nextInt(255));
                    }
                    x= randomGenerator.nextInt(10000) / 10000.0;
                    if(x < PMUTATIONCOLOR) {	
                        population[i].circles[j].green = new Integer(randomGenerator.nextInt(255));
                    }
                    x= randomGenerator.nextInt(10000) / 10000.0;
                    if(x < PMUTATIONCOLOR) {	
                        population[i].circles[j].blue = new Integer(randomGenerator.nextInt(255));
                    }
                    x= randomGenerator.nextInt(10000) / 10000.0;
                    if(x < PMUTATIONCOLOR) {	
                        population[i].circles[j].alpha = new Integer(randomGenerator.nextInt(255));
                    }
                    x= randomGenerator.nextInt(10000) / 10000.0;
                    if(x < PMUTATION) {	
                        population[i].circles[j].radius = new Integer(randomGenerator.nextInt(RADIUSLIMIT));
                    }
                    x= randomGenerator.nextInt(10000) / 10000.0;
                    if(x < PMUTATION) {	
                        population[i].circles[j].time = new Integer(randomGenerator.nextInt(10000));
                    }
		}
            }
	}
        return population;
 	}
}