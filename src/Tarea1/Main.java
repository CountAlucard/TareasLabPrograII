
package Tarea1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner lea = new Scanner(System.in);
        EnterosRecursivos m = new EnterosRecursivos(5);
        int respuesta = 0;       
        boolean opcion1 = false;
       
        System.out.println("Elige una opcion: ");        
        
        do{
            System.out.println();
            System.out.println("1- Llenar el arreglo");
            System.out.println("2- Sumar los valores del arreglo");
            System.out.println("3- Buscar un numero -x- en el arreglo");
            System.out.println("4- Encontrar el valor maximo en el arreglo");
            System.out.println("5- Imprimir el arreglo");
            System.out.println("6- Imprimir un numero -x- en el arreglo Al Reves");
            System.out.println("7- Verificar si los valores en el arreglo arman un palindromo");
            System.out.println("8- Ordenar los valores del arreglo e imprimirlos");

            respuesta = lea.nextInt();

            switch(respuesta){
                case 1:
                    System.out.println("Ingrese los valores del arreglo: ");
                    m.llenar();
                    opcion1 = true;
                    break;
                case 2:
                    if(opcion1){
                        System.out.println("La suma total es: ");
                        System.out.println(m.suma());
                        break;
                    }
                    System.out.println("Debe llenar el arreglo primero!");
                case 3:
                    if(opcion1){
                        System.out.println("Ingrese el valor a buscar: ");
                        m.buscar(lea.nextInt());
                        break;
                    }                
                case 4:
                    if(opcion1){
                        System.out.println("El valor maximo es: ");
                        System.out.println(m.maximo());
                        break;
                    }                
                case 5:
                    if(opcion1){
                        System.out.println("Valores en el arreglo: ");
                        m.Print();
                        break;
                    }                
                case 6:
                    if(opcion1){
                        System.out.println("Ingrese la posicion del valor que desea invertir: ");
                        m.numeroAlReves();
                        break;
                    }                
                case 7:
                    if(opcion1){
                        m.palindromo();
                        break;
                    }                
                case 8:
                    if(opcion1){
                        System.out.println("Los valores en el arreglo de forma ordenada: ");
                        m.ordenarQuickSort();
                        m.Print();
                        break;
                    }                
            }
        }while(respuesta <= 8);
       
    }
}
