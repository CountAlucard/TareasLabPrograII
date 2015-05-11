
package Tarea1;

import java.util.ArrayList;
import java.util.Scanner;

public class EnterosRecursivos {
    private Scanner lea = new Scanner(System.in);
    int enteros[];

    public EnterosRecursivos(int longi) 
    {
        this.enteros = new int[longi];
    }
    
    //Funcion que permite ingresar un valor del teclado para cada posicion del arreglo.
    private void llenar(int cont)
    {
        if(cont < enteros.length){
            enteros[cont] = lea.nextInt();
            llenar(cont+1);
        }
    }
    
    public void llenar()
    {
        llenar(0);
    }
    
    //Funcion que retorna la suma de los valores guardados en el arreglo.
    private int suma(int cont, int total)
    {
        if(cont < enteros.length){
            total += enteros[cont];
            return suma(cont+1, total);
        }
        return total;
    }
    
    public int suma()
    {
        return suma(0,0);
    }
    
    //Funcion que busca un valor 
    private boolean buscar(int n, int cont)
    {
        if(cont < enteros.length){
            if(enteros[cont] == n){
                System.out.println("El valor si existe");
                return true;
            }            
            return buscar(n,cont+1);            
        }
        System.out.println("El valor no existe");
        return false;
    }
    
    public boolean buscar(int n)
    {
        return buscar(n,0);
    }
    
    //Funciones para encontrar el valor maximo dentro del arreglo.
    private int maximo(int m, int cont)
    {
        if(cont < enteros.length){
            if(enteros[cont] > m){
                m = enteros[cont];
                return maximo(m, cont+1);
            }
            return maximo(m, cont+1);
        }
        return m;
    }
    
    public int maximo()
    {
        return maximo(0,0);
    }
    
    //Funciones para imprimir tods los valores del arreglo.
    private void Print(int cont)
    {
        if(cont < enteros.length){
            System.out.print(enteros[cont] + " ");
            Print(cont+1);
        }            
    }
    
    public void Print()
    {
        Print(0);       
        System.out.println();
    }
    
    //pendiente-------------------------------
    public void numeroAlReves()
    {
        
    }
        
    //Funciones para validar si los valores en el arreglo forman un palindromo.
    private boolean palindromo(int inicio, int fin)
    {
        if(inicio<fin){
            if(enteros[inicio]== enteros[fin])
                return palindromo(inicio+1, fin-1);
            System.out.println("No es palindromo");
            return false;
        }
        System.out.println("Es palindromo");
        return true;
    }
    
    public boolean palindromo()
    {
        return palindromo(0, enteros.length-1);
    }
    
    //Funciones para ordenar los valores del arreglo. Quicksort.
    public int[] quicksort(int numeros[], int izq, int der)
    {
        if(izq >= der)
            return numeros;
        int i = izq, d = der;
        if(izq != der){
            int pivote;
            int aux;
            pivote = izq;
            
            while(izq != der){
                while(numeros[der] >= numeros[pivote] && izq < der)
                    der--;
                while(numeros[izq] < numeros[pivote] && izq < der)
                    izq++;
                
                if (der != izq){
                    aux = numeros[der];
                    numeros[der] = numeros[izq];
                    numeros[izq] = aux;
                }
            }
            
            if(izq == der){
                quicksort(numeros, i, izq-1);
                quicksort(numeros, izq+1,d);
            }           
        }
        else
            return numeros;
        return numeros;
    }
    
    public int[] quicksort(int numeros[])
    {
        return quicksort(numeros, 0, numeros.length-1);
    }
    
    public void ordenarQuickSort()
    {
        enteros = quicksort(enteros);
    }
}