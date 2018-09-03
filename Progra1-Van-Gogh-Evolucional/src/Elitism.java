public class Elitism {
    public static Genotype population[];
    public static int POPSIZE;
    
    public Elitism(Genotype population[], int POPSIZE){
        this.population= population;
        this.POPSIZE=POPSIZE;
    }
   
    public   Genotype[] elitist() {
       int i;
       double best;
       int best_mem = 0;
       double worst;
       int worst_mem = 0;
       best = population[0].fitness;
       worst = population[0].fitness;
       for(i = 0; i < POPSIZE ; ++i) {
           if(population[i].fitness > best) {
               best = population[i].fitness;
               best_mem = i ;
           }
           if(population[i].fitness < worst) {
               worst = population[i].fitness ;
               worst_mem = i ;
           }
	}
	//	If the best individual from the new population is better than
	//	the best individual from the previous population, then
	//	copy the best from the new population; else replace the
	//	worst individual from the current population with the
	//	best one from the previous generation										
	//
	if(best >= population[POPSIZE].fitness)
            population[POPSIZE] = new Genotype(population[best_mem]);
	else{
            population[worst_mem] = new Genotype(population[POPSIZE]);
	}
     return population;
    }    
}
