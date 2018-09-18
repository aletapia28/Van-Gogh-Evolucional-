
import java.awt.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author maryp
 */
public class Euclidean {
    int[][] ImagenMeta;
    int[][] ImagenPrueba;
    Genotype population;
    
    public Euclidean(int[][] meta, int[][] prueba, Genotype popu){
        this.ImagenMeta=meta;
        this.ImagenPrueba=prueba;
        this.population=popu;
    }

    public float calcularDistancia(){
        float rojo=0 ;
        float azul=0;
        float verde=0;
        float result=0;
        float promedio=0;
        for(int i=0; i<ImagenMeta.length;i++){
            for(int j=0;j<ImagenMeta[0].length;j++){
                Color m= population.getColorofPoint(i,j);
                Color n= new Color(ImagenMeta[i][j]);
                rojo+=Math.pow(m.getRed()-n.getRed(), 2);
                azul+=Math.pow(m.getBlue()-n.getBlue(), 2);
                verde+=Math.pow(m.getGreen()-n.getGreen(),2);
            }
        }
        promedio= (rojo+azul+verde)/3;
        result= (float) Math.sqrt(promedio);
     //   result = (result*100)/ImagenMeta.length;
        return result;
    }
}
