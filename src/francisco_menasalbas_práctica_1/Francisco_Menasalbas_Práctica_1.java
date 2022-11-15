package francisco_menasalbas_práctica_1;

import java.util.Arrays;
import java.util.Random;

public class Francisco_Menasalbas_Práctica_1 {

    public static void Mensaje_Random(int[] Mensaje) {

        Random Número_Random = new Random();  //random
        for (int i = 0; i < Mensaje.length; i++) {
            Mensaje[i] = (Número_Random.nextBoolean()) ? 1 : 0;
            System.out.print(Mensaje[i] + ",");//Printea el mensaje por pantalla
        }//for
    }//psv

    public static boolean Is_Potencia(int i) {

        for (int p = 0; p < 10; p++) {
            if (Math.pow(2, p) == i || i == 0) {
                return true;
            }//if
        }//for
        return false;
    }//psv

    public static void main(String[] args) {

        //generador del mensaje
        final int Tamaño = 8; //Este va a ser el tamaño del mensaje
        int[] Mensaje = new int[Tamaño];
        Mensaje_Random(Mensaje);

        //Sender
        int Bits_Redundantes = 0;
        while (Math.pow(2, Bits_Redundantes) < Bits_Redundantes + Tamaño + 1) {
            Bits_Redundantes++;
        }//while

        int Tamaño_Total = Bits_Redundantes + Tamaño + 1;
        System.out.println(" " + Tamaño_Total);  //Printea el tamaño total del mensaje
        System.out.println(Bits_Redundantes);  //Printea los bits de redundancia necesarios para el mensaje

        //Hamming
        int[] Hamming = new int[Tamaño_Total];  //crea un array con el tamaño del mensaje mas los bits de redundancia y el bit de redundancia global(Aun está vacio)
        int j = 0;  //esta es la posicion que se va copiar del mensaje al Hamming
        for (int i = 0; i < Hamming.length; i++) {
            if (Is_Potencia(i) == true) {
                Hamming[i] = 1;
            }//if
            if (Is_Potencia(i) == false) {
                Hamming[i] = Mensaje[j];
                j++;
            }//else
            System.out.print(Hamming[i] + ",");
        }//for
        //en el hamming hay q arreglar lo de q el primer nº del array "Hamming" sea un 1 y no el primer nº del "Mensaje"
    }//main

}//public class
