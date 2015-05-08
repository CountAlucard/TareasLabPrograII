
package Tarea1;

import java.util.ArrayList;
import java.util.Scanner;

public class EnterosRecursivos {
    Scanner lea = new Scanner(System.in);
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
                return true;
            }            
            return buscar(n,cont+1);            
        }
        return false;
    }
    
    public boolean buscar(int n)
    {
        return buscar(n,0);
    }
    
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
    }
    
}