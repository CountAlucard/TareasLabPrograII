
package Tarea1;

public class Main {
    public static void main(String[] args) {
        EnterosRecursivos e = new EnterosRecursivos(5);
        
        e.llenar();
                     
        System.out.println("El valor maximo en el arreglo es: " +e.maximo());
    }
}
