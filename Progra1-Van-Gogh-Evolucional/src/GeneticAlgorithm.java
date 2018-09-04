
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class GeneticAlgorithm {
    public static int POPSIZE = 10;
    public static int MAXGENS = 150000;
    public static double PXOVER = 0.8;
    public static double PMUTATION = 0.1;
    public  static Genotype population[];
    public  static Genotype newpopulation[];
    public static int row, col;
    public static int circleCount = 50;
    public static int[][] result;
    public static int RADIUSLIMIT=175;
    public static double PMUTATIONCOLOR = .2; 
    public static ImageWindow ventana = new ImageWindow();
    public static ImageIcon images[] = new ImageIcon[MAXGENS+1];
    public static int contImages = 0;
    
    private static int[][] convertTo2D(BufferedImage image){
        final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        final int width = image.getWidth();
        final int height = image.getHeight();
        int[][] result = new int[height][width];
        if (image.getAlphaRaster() != null) {
            final int pixelLength = 3;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;
		argb += ((int) pixels[pixel + 1] & 0xff);			   // blue
		argb += (((int) pixels[pixel + 2] & 0xff) << 8);		// green
		argb += (((int) pixels[pixel + 3] & 0xff) << 16);	   // red
		result[row][col] = argb;
		col++;
		if (col == width) {
                    col = 0;
                    row++;
		}
            }
	} 
	else {
            final int pixelLength = 3;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += ((int) pixels[pixel] & 0xff);				   // blue
                argb += (((int) pixels[pixel + 1] & 0xff) << 8);		// green
                argb += (((int) pixels[pixel + 2] & 0xff) << 16);	   // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
	}
		return result;
    }
   
    public static void convertColorArrayToImage(int[][] color, int generation, int member, String imageName, String directory) {
        String curr = imageName ;
	String path = directory+curr +"-"+ Integer.toString(generation) + ".jpg";
	BufferedImage image = new BufferedImage(color[0].length, color.length, BufferedImage.TYPE_INT_RGB);
	for(int i = 0; i < color.length; i++)
            for(int j = 0; j < color[0].length; j++)
		image.setRGB(j, i, color[i][j]);
      //  ventana.setSize(color[0].length, color.length);
        File ImageFile = new File(path);
	try {
           ImageIcon img = new ImageIcon(image);
           ventana.jLabel1.setIcon(img);
           images[contImages] =img;
           contImages++;
           System.out.println(contImages);
          // ImageIO.write(image, "jpg", ImageFile);
	}
	catch(Exception e) {
            e.printStackTrace();
	}
    }

   public static void crossover() {
 	int mem;
	int one = 0;
	int first = 0;
	double x;
	for(mem = 0; mem < POPSIZE; ++mem) {
            Random randomGenerator = new Random();
            x = (randomGenerator.nextInt(1000)) / 1000.0;
            if(x < PXOVER) {
                ++first;
                if(first % 2 == 0)
                    Xover(one, mem);
                else
                    one = mem;
            }
	}
    }
    public static void evaluate() {
        for(int member = 0; member < POPSIZE; member++)
            population[member].getFitness(result);
    }
    
    public static void initialize() {
        population = new Genotype[POPSIZE + 1];
        newpopulation = new Genotype[POPSIZE + 1];
 	Random randomGenerator = new Random();
        for(int i = 0 ; i <= POPSIZE ; i++) {
            population[i] = new Genotype();
            newpopulation[i] = new Genotype();
            population[i].count = circleCount;
            for(int j = 0 ; j < circleCount ; j++) {
                population[i].circles[j].x = new Integer(randomGenerator.nextInt(row));
                population[i].circles[j].y = new Integer(randomGenerator.nextInt(col));
                population[i].circles[j].blue = new Integer(randomGenerator.nextInt(255));
                population[i].circles[j].red = new Integer(randomGenerator.nextInt(255));
                population[i].circles[j].green = new Integer(randomGenerator.nextInt(255));
		population[i].circles[j].alpha = new Integer(randomGenerator.nextInt(255));
		population[i].circles[j].radius = new Integer(randomGenerator.nextInt(RADIUSLIMIT));
		population[i].circles[j].time = new Integer(randomGenerator.nextInt(10000));
 		}
 		population[i].bg_red =255;
		population[i].bg_green = 255;
		population[i].bg_blue = 255;
		population[i].bg_alpha = 0;
 	}
 }

    public static void keep_the_best() {
        int cur_best;
        int mem;
        int i;
        cur_best = 0;
        for(mem = 1; mem < POPSIZE; mem++) {
            if(population[mem].fitness > population[cur_best].fitness)
		cur_best = mem;
        }
            population[POPSIZE] = new Genotype(population[cur_best]);
 }     
    public static double randval(double low, double high) {
        Random randomGenerator = new Random();
        return ((double)(randomGenerator.nextInt(1000)) / 1000.0) *(high - low) + low;
    }
    public static void selector() {
        int i, j, mem;
        double p, sum = 0.0;
	//
	//	Find total fitness of the population
	//
	for(mem = 0; mem < POPSIZE; mem++)
            sum = sum + population[mem].fitness;
	//
	//	Calculate the relative fitness.
	//
	for(mem = 0; mem < POPSIZE; mem++)
            population[mem].rfitness = population[mem].fitness / sum;	 
        population[0].cfitness = population[0].rfitness;
	//
	//	Calculate the cumulative fitness.
	//
	for(mem = 1; mem < POPSIZE; mem++)
            population[mem].cfitness = population[mem-1].cfitness +	population[mem].rfitness;
	//
	//	Select survivors using cumulative fitness.
	//
	// System.out.println("cfitness values new");
	// for(int z = 0 ; z < POPSIZE ; z++) {
	// 	System.out.println(z + " : " + population[z].cfitness);
	// }

	for(i = 0; i < POPSIZE; i++){
            Random randomGenerator = new Random();
            p = (randomGenerator.nextInt(1000)) / 1000.0;
            if(p < population[0].cfitness) {
		newpopulation[i] = new Genotype(population[0]);}
            else
            {
                for(j = 0; j < POPSIZE; j++) {
                    if(p >= population[j].cfitness && p < population[j+1].cfitness) {
			newpopulation[i] = new Genotype(population[j+1]);
			break ;
                    }
		}
            }
        }
	//
	//	Once a new population is created, copy it back
	//
		// System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		// System.out.println("New pop");
		// for(i = 0; i < POPSIZE; i++) {
		// 	System.out.println(newpopulation[i].fitness);
		// 	population[i] = new Genotype(newpopulation[i]);
		// }
		// System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
 	}
    	public static void Xover(int one, int two) {
 		Genotype a = new Genotype(population[one]);
 		Genotype b = new Genotype(population[two]);

 		Random randomGenerator = new Random();
 		int k = randomGenerator.nextInt(a.circles.length);
 		for(int i = 0 ; i < k ; i++) {
 			Circle tmp = new Circle(a.circles[i]);
 			a.circles[i] = new Circle(b.circles[i]) ;
 			b.circles[i] = new Circle(tmp) ;
 		}
 		
 		population[one] = new Genotype(a) ;
 		population[two] = new Genotype(b) ;
 	}
    public static void generator(Genotype best, double old_fitness){
    	for(int generation = 0 ; generation < MAXGENS ; generation++) {
            Calendar calendario = new GregorianCalendar();
            System.out.println("Inicio: "+calendario.get(Calendar.HOUR_OF_DAY) + ":" + calendario.get(Calendar.MINUTE) + ":" + calendario.get(Calendar.SECOND));
            selector();
            Calendar calendario1 = new GregorianCalendar();
            System.out.println("Después selector: "+calendario1.get(Calendar.HOUR_OF_DAY) + ":" + calendario1.get(Calendar.MINUTE) + ":" + calendario1.get(Calendar.SECOND));
            crossover();
            Calendar calendario2 = new GregorianCalendar();
            System.out.println("Después crossover: "+calendario2.get(Calendar.HOUR_OF_DAY) + ":" + calendario2.get(Calendar.MINUTE) + ":" + calendario2.get(Calendar.SECOND));
            Mutation mut= new Mutation(POPSIZE, circleCount, PMUTATION, PMUTATIONCOLOR, population, row, col, RADIUSLIMIT);
            population= mut.mutate();
            //mutate();
            Calendar calendario3 = new GregorianCalendar();
            System.out.println("Después mutación: "+calendario3.get(Calendar.HOUR_OF_DAY) + ":" + calendario3.get(Calendar.MINUTE) + ":" + calendario3.get(Calendar.SECOND));
            evaluate();
            Calendar calendario4 = new GregorianCalendar();
            System.out.println("Después evluación: "+calendario4.get(Calendar.HOUR_OF_DAY) + ":" + calendario4.get(Calendar.MINUTE) + ":" + calendario4.get(Calendar.SECOND));
            Elitism eli = new Elitism(population, POPSIZE);
            population = eli.elitist();
            //elitist();
            Calendar calendario5 = new GregorianCalendar();
            System.out.println("Después etilist: "+calendario5.get(Calendar.HOUR_OF_DAY) + ":" + calendario5.get(Calendar.MINUTE) + ":" + calendario5.get(Calendar.SECOND));
            best = new Genotype(population[POPSIZE]);
            double new_fitness = best.fitness;
            if(old_fitness!=new_fitness){
                int answer[][] = new int[row][col];
                for(int i = 0 ; i < row ; i++) {
                    for(int j = 0 ; j < col ; j++) {
                        Color c = population[POPSIZE].getColorofPoint(i, j);
                        answer[i][j] = (c.getRed() << 16) | (c.getGreen() << 8) | c.getBlue();
											//answer[i][j] = (c.getRed() << 24) | (c.getGreen() << 16) | (c.getBlue() << 8) | c.getAlpha();
			}
                }
                convertColorArrayToImage(answer, generation,POPSIZE,"images", "images");
            }
            old_fitness = new_fitness;
            Calendar calendario6 = new GregorianCalendar();
            System.out.println("Fin: "+calendario6.get(Calendar.HOUR_OF_DAY) + ":" + calendario6.get(Calendar.MINUTE) + ":" + calendario6.get(Calendar.SECOND));
            System.out.println(generation + "\t" + population[POPSIZE].fitness);
            String gen = Integer.toString(generation);
            String adap = Double.toString(population[POPSIZE].fitness);
            ventana.jLabel4.setText(gen);
            ventana.jLabel6.setText(adap);
            //noodfhdfio
        }
    }    
    
    public static void main(String[] args) throws IOException {
  	BufferedImage image = ImageIO.read(GeneticAlgorithm.class.getResource("images1.jpg"));	//read the image into the image object		
        ImageIcon im = new ImageIcon(image);
        ventana.setVisible(true);
        ventana.jLabel2.setIcon(im);
        result = convertTo2D(image);
	row = result.length;
	col = result[0].length;
	Genotype best = null;
	initialize();
	best = new Genotype(population[POPSIZE]);
	best.print();
	evaluate();
	best = new Genotype(population[POPSIZE]);
	best.print();
	keep_the_best();
	best = new Genotype(population[POPSIZE]);
	best.print();
	double old_fitness = -1;
        generator(best, old_fitness);
    }
}

