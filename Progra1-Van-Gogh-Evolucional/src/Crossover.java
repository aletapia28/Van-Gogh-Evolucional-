
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author maryp
 */

public class Crossover {
        public  int POPSIZE;
        public  double PXOVER;
        Genotype population[];
        
        Crossover(int ps,double px, Genotype[] popu){
            this.POPSIZE=ps;
            this.PXOVER=px;
            this.population=popu;
        }
       public  Genotype[] crossover() {
 	int mem;
	int one = 0;
	int first = 0;
	double x;
	for(mem = 0; mem < this.POPSIZE; ++mem) {
            Random randomGenerator = new Random();
            x = (randomGenerator.nextInt(1000)) / 1000.0;
            if(x < this.PXOVER) {
                ++first;
                if(first % 2 == 0)
                    Xover(one, mem);
                else
                    one = mem;
            }
	}
        return this.population;
    }
       public  void Xover(int one, int two) {
 		Genotype a = new Genotype(this.population[one]);
 		Genotype b = new Genotype(this.population[two]);

 		Random randomGenerator = new Random();
 		int k = randomGenerator.nextInt(a.circles.length);
 		for(int i = 0 ; i < k ; i++) {
 			Circle tmp = new Circle(a.circles[i]);
 			a.circles[i] = new Circle(b.circles[i]) ;
 			b.circles[i] = new Circle(tmp) ;
 		}
 		
 		this.population[one] = new Genotype(a) ;
 		this.population[two] = new Genotype(b) ;

 	}
    
}
