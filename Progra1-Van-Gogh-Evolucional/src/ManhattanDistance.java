
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
public class ManhattanDistance {
        int[][] ImagenMeta;
    int[][] ImagenPrueba;
    Genotype population;
    
    public ManhattanDistance(int[][] meta, int[][] prueba, Genotype population){
        this.ImagenMeta=meta;
        this.ImagenPrueba=prueba;
        this.population=population;
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
                rojo+=Math.abs(m.getRed()-n.getRed());
                azul+=Math.abs(m.getBlue()-n.getBlue());
                verde+=Math.abs(m.getGreen()-n.getGreen());
            }
        }
        promedio= (rojo+azul+verde)/3;
        result= promedio;
     //   result = (result*100)/ImagenMeta.length;
        return result;
    }

}
