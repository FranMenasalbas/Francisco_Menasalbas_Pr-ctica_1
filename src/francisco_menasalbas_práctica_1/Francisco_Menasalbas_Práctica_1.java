package francisco_menasalbas_práctica_1;

import java.util.Arrays;
import java.util.Random;

public class Francisco_Menasalbas_Práctica_1 {
    public static void Mensaje_Random (int Tamaño){
        int[][] Mensaje = new int[Tamaño][Tamaño];
        Random Número_Random = new Random();  //random
        for(int i = 0; i < Mensaje.length; i++){   
            for(int j = 0; j < Mensaje[i].length; j++){
                Mensaje[i][j] = (Número_Random.nextBoolean()) ? 1 : 0;
            }//for j
        }//for i
        for(int[] arr: Mensaje){
            System.out.println(Arrays.toString(arr));
        }//for
    }
    public static void main(String[] args) {
        
        //generador del mensaje
        final int Tamaño = 4;
        Mensaje_Random(Tamaño);
    }//main

}//public class
