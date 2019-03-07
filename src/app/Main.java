package app;

import List.doubleLinkedList;
import List.linkedList;
import Node.Node;
public class Main {
    
    //static linkedList<Double> dobles = new linkedList<>();
    static doubleLinkedList<Double> dobles = new doubleLinkedList<>();
    
    /*
    public static void fill(int n, int m) {
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dobles.add( (Double)Math.abs(Math.floor(Math.random()*(n-m+1)+m)));
            }
            
        }
        
    }
    */
    public static void main(String[] args) {
        
        dobles.add(10d);
        dobles.add(20d);
        dobles.add(35d);
        dobles.add(45d);
        dobles.add(55d);
        dobles.add(60d);
        
// Prueba de addAt
        dobles.addAt(700d, 6);
        
// Prueba de getElementAt
// if (dobles.getElementAt(6) != null)
// System.out.println(dobles.getElementAt(6).getValue());
        
        
        
        
        /*
        if (dobles.removeBefore(18d))
            System.out.println("Se borró el valor");
        else
            System.out.println("No se puede");
        */
        
        
        //dobles.removeAll(9d);
        
        //fill(1, 62650);
        
        for (Double object : dobles) {
            System.out.println(object);
        }
        
        //Node<Double> nodo = new Node<Double>();
        //nodo.setValue(18d);
        //dobles.add(nodo);
    }
    
}
