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
    
    public Euclidean(int[][] meta, int[][] prueba){
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
    public double calcularDistancia(){
        double suma=0;
        double result=0;
        for(int i=0; i<ImagenMeta.length;i++){
            for(int j=0;j<ImagenMeta[0].length;j++){
                suma+=Math.pow(abs(ImagenMeta[i][j]-ImagenPrueba[i][j]), 2);
                
            }
           
        }
        result= Math.sqrt(suma);
        System.out.println("RESULTADO EUCLIDEAN "  + result);
        return result;

    }
    
}
