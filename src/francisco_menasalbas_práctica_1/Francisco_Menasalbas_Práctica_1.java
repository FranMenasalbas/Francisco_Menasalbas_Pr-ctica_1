package francisco_menasalbas_práctica_1;

import java.util.Arrays;
import java.util.Random;

public class Francisco_Menasalbas_Práctica_1 {

    public static void Mensaje_Random(int[] Mensaje) {

        Random Número_Random = new Random();  //random
        for (int i = 0; i < Mensaje.length; i++) {
            Mensaje[i] = (Número_Random.nextBoolean()) ? 1 : 0;
        }//for
    }//psv

    public static void Noise_Reciver(int[] Hamming) {
        int counter = 0;
        int sum = 0;
        int N_Error;
        int Zone_Error; //donde se van a producir los errores
        int Zone_Error_2; //este se usa para cuando hay dos errores

        //Noise
        Random NºErrores = new Random();
        for (int i = 0; i < 1; i++) {
            N_Error = (NºErrores.nextInt(3) + 0); //el tres no sale nunca
            Zone_Error = (NºErrores.nextInt(Hamming.length) + 0);//posicion en la que se va a crear el error
            Zone_Error_2 = (NºErrores.nextInt(Hamming.length) + 0);//posición en la que se va a crear el error
            if (N_Error == 0) {
                System.out.println("No se han producido errores");
            }//if sin errores
            if (N_Error == 1) {
                System.out.println("Se ha producido un error");
                if (Hamming[Zone_Error] == 0) {
                    Hamming[Zone_Error] = 1;
                    //break;
                }//if
                else {
                    Hamming[Zone_Error] = 0;
                }//else

                //Reciver
                for (int f = 1; f < Hamming.length; f++) {//recorre el Hamming
                    if (Is_Potencia(f)) {//se para en las potencias de 2
                        for (int p = 1; p < Hamming.length; p++) {//recorre el Haming otra vez
                            if ((f & p) == f) {//mira si la posición en a la que está mirando le corresponde
                                if (Hamming[p] == 1) {//esa posición tiene un 1??
                                    counter++;//el contador sube
                                }//if
                            }//if
                        }//for
                    }//if
                    if (counter % 2 == 0 & Hamming[f] == 1) {//si el contador es par y la posicion del Hamming es uno se suma su posicion
                        sum += f;
                    }//if
                    if (counter % 2 == 1 & Hamming[f] == 0) {//si el contador es impar y la posicion del Hamming es cero se suma su posicion
                        sum += f;
                    }//if
                    counter = 0;//reinicia el contador
                }//for Reciver
                System.out.println("El error está en la posición " + sum);
            }//if un error

            if (N_Error == 2) {
                System.out.println("Se han producido dos errores");
                if (Hamming[Zone_Error] == 0) {
                    Hamming[Zone_Error] = 1;
                }//if
                else {
                    Hamming[Zone_Error] = 0;
                }//else
                if (Hamming[Zone_Error_2] == 0) {
                    Hamming[Zone_Error_2] = 1;
                }//if
                else {
                    Hamming[Zone_Error_2] = 0;
                }//else
            }//if dos errores            
        }//for 
    }//psv

    public static boolean Is_Potencia(int i) {//return true == esa posicion es multiplo de 2

        for (int p = 0; p < 10; p++) {
            if (Math.pow(2, p) == i || i == 0) {
                return true;
            }//if
        }//for
        return false;
    }//psv

    public static void main(String[] args) {
        //variables / constantes
        final int Tamaño = 8; //Este va a ser el tamaño del mensaje
        int Bits_Redundantes = 0;
        int counter = 0;
        int sum = 0;

        //Generador del mensaje
        int[] Mensaje = new int[Tamaño];
        Mensaje_Random(Mensaje);

        //Sender
        while (Math.pow(2, Bits_Redundantes) < Bits_Redundantes + Tamaño + 1) {
            Bits_Redundantes++;
        }//while
        int Tamaño_Total = Bits_Redundantes + Tamaño + 1;

        //Hamming
        int[] Hamming = new int[Tamaño_Total];  //rellena el Hamming con el mensaje
        int j = 0;  //esta es la posicion que se va copiar del mensaje al Hamming
        for (int i = 0; i < Hamming.length; i++) {
            if (Is_Potencia(i) == false) {//if no es potencia de dos
                Hamming[i] = Mensaje[j];
                j++;
            }//else
        }//for
        for (int i = 0; i < Hamming.length; i++) {  //rellena los bits de redundancia
            if (Is_Potencia(i) == true) {//if es potencia de 2
                for (int k = 0; k < Hamming.length; k++) {
                    if ((i & k) == i) { //le mira?
                        if (Hamming[k] == 1) {
                            counter++;
                        }//if
                    }//if
                    if (counter % 2 == 0) {
                        Hamming[i] = 0; //si el nº total de 1`s del mensaje es par entonces se pondrá un 0
                    }//if
                    else {
                        Hamming[i] = 1;//si el nº total de 1`s del mensaje es impar entonces se pondrá un 1
                    }
                }//for
            }//if
            System.out.print(+Hamming[i] + ",");
        }//for

        for (int i = 0; i < Hamming.length; i++) {
            if (Hamming[i] == 0) {
                for (int h = 0; h < Hamming.length; h++) {
                    sum += Hamming[i];
                    if (sum % 2 == 0) {
                        Hamming[0] = 0; //si la suma del arr(Hamming) es par, entonces se pondrá un 0
                    }//if
                    if (sum % 2 != 0) {
                        Hamming[0] = 1; //si la suma del arr(Hamming) es impar, entonces se pondrá un 1
                    }//if
                }//for
            }//if que calcula el nº de redundancia total (el primer numero del Hamming)
        }//for
        //Noise y reciver
        Noise_Reciver(Hamming);
        for (int i = 0; i < Hamming.length; i++) {
            System.out.print(Hamming[i] + ",");
        }//for
    }//main
}//public class
