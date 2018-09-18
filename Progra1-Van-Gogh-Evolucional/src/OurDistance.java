
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
public class OurDistance {
    int[][] ImagenMeta;
    int[][] ImagenPrueba;
    Genotype population;
    public OurDistance(int[][] meta, int[][] prueba, Genotype popu){
        this.ImagenMeta=meta;
        this.ImagenPrueba=prueba;
        this.population=popu;
    }
    public int abs(int diff){
        if(diff<0){
            return (-1*diff);
        }
        else{
            return diff;
        }
    }
    public float CalcularDistancia(){
        float sum=0;
        float rojo=0;
        float verde=0;
        float azul=0;
        float result=0;
        for(int i=0; i<ImagenMeta.length;i++){
            for(int j=0;j<ImagenMeta[0].length;j++){
                Color m= population.getColorofPoint(i,j);
                Color n= new Color(ImagenMeta[i][j]);
                rojo+=Math.abs(m.getRed()-n.getRed());
                verde+=Math.abs(m.getGreen()-n.getGreen());
                azul+=Math.abs(m.getGreen()-n.getGreen());
            }
        }
        sum=(rojo+verde+azul)/3;
        sum=sum/2;
        result = (float) Math.sqrt(sum);
      //  result= (result*100)/ImagenMeta.length;
        return result;
    }
}
