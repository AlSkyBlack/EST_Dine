package priority;

import queueArray.QueueArray;
import queue.Queue;
import Excepciones.*;
import java.util.Iterator;

public class PriorityQueue {
    static QueueArray<Double>[] dobles = new QueueArray[5];
    enum prioridad {Muy_alta, Alta, Media, Baja, Muy_baja};

    public static void insert(int prioridad, double dato) {
        try {
            dobles[prioridad].enQueue(dato);
        } catch (isFullException e) {
            System.err.println(e.getMessage());
        }

    }

    public static void _init_() {
        for (int i = 0; i < dobles.length; i++) {
            dobles[i] = new QueueArray<>(Double.class, 4);
        }
    }

    public static void print() {
        for (int i = 0; i < dobles.length; i++) {
            try {
                dobles[i].isEmpty();
                System.out.println("La prioridad: " + prioridad.values()[i]);
                for (Double a : dobles[i]) {
                    System.out.print(a + " ");
                }
                System.out.println();
            } catch (isEmptyException e) {
                System.out.println("La prioridad: " + prioridad.values()[i]
                        + " esta vacia");
                System.err.println(e.getMessage());
            }
        }

    }

    /*public static void print(int i) {
        for (QueueArray<Double> queueArray : dobles) {
            try {
                queueArray.isEmpty();
                for (Double double1 : queueArray) {
                    if (dobles.)
                    System.out.println(double1);
                }
            } catch (Exception e) {
                System.out.println("No existen elementos en esta prioridad");
            }
        }
    }*/
    public static void remove(int priority) {
        if (priority >= 0 && priority < dobles.length) {
            System.out.println("Sacando los elementos de la prioridad: " + priority);
            try {
                dobles[priority].isEmpty();
                System.out.println("La prioridad: " + prioridad.values()[priority]);
                System.out.println(dobles[priority].deQueue());
            } catch (isEmptyException e) {
                System.out.println("La prioridad: " + prioridad.values()[priority]
                        + " esta vacia");
            }
        } else {
            System.out.println("Prioridad invalida");
        }

    }

    public static void main(String[] args) {
        _init_();
        insert(prioridad.Muy_alta.ordinal(), 1d);
        insert(prioridad.Muy_baja.ordinal(), 3d);
        insert(prioridad.Media.ordinal(),    4d);
        insert(prioridad.Media.ordinal(),    5d);
        insert(prioridad.Baja.ordinal(),     6d);
        insert(prioridad.Muy_baja.ordinal(), 7d);
        //System.out.println("Removiendo la prioridad muy alta");
        remove(2);
        remove(2);
        remove(2);
        //print();
    }

}
