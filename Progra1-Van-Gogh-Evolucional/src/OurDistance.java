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
    
    public OurDistance(int[][] meta, int[][] prueba){
        this.ImagenMeta=meta;
        this.ImagenPrueba=prueba;
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
        float result=0;
        for(int i=0; i<ImagenMeta.length;i++){
            for(int j=0;j<ImagenMeta[0].length;j++){
                sum+=Math.pow(abs(ImagenMeta[i][j]-ImagenPrueba[i][j]), 3);
            }
        }
        result = sum/2;
      //  result= (result*100)/ImagenMeta.length;
        return result;
    }
}
