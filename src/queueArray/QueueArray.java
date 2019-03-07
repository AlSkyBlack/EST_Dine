package queueArray;

import Excepciones.isEmptyException;
import Excepciones.isFullException;
import java.lang.reflect.Array;
import java.util.Iterator;
import queue.Queue;

public class QueueArray <T extends Comparable <T>> implements Queue<T>, Iterable<T> {
    
    private T[]      queue;
    private int      size;
    private int      front = -1;
    private int      back = 0;
    private int      count = 0;
    private Class<T> type = null;

    public QueueArray(Class <T> type, int size) {
        this.type  = type;
        this.size  = size;
        this.queue = (T[])Array.newInstance(type, size);
    }
    
    public QueueArray(Class <T> type) {
        this(type, 10);
    }

    @Override
    public boolean enQueue(T value) throws isFullException {
        try {
            isFull();
            queue[back ++ % size] = value;  //  La variable back verifica donde se inserta el nuevo
            count++;  // Cuantos estan en la cola
            return true;
            
        } catch (isFullException e) {
            System.err.println("Full Queue");
            return false;
        }
        
    }

    @Override
    public T deQueue() throws isEmptyException {
        try {
            isEmpty();
            count--;
            return queue[++front % size];  //  front es la variable donde va a salir el siguiente valor
        } catch (isEmptyException e) {
            System.err.println("Empty Queue");
            return null;
        }
    }

    @Override
    public boolean removeAll() throws isEmptyException {
        front = -1;
        back = 0;
        count = 0;
        return true;
    }

    @Override
    public T front() throws isEmptyException {
        return queue[(front + 1) % size];
    }

    @Override
    public T last() throws isEmptyException {
        return queue[(back - 1) % size];
    }

    int _front;
    int _count;
    @Override
    public Iterator<T> iterator() {
        _front = front;
        _count = count;
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return (_count-- != 0);
            }
            @Override
            public T next() {
                return queue[++_front % size];
            }
        };
    }

    @Override
    public void isEmpty() throws isEmptyException {
        if (count == 0)
            throw new isEmptyException("Empty Queue");
    }
    
    @Override
    public void isFull() throws isFullException {
        if (count == size)
            throw new isFullException("Full Queue");
    }

    public static void main(String[] args) {
        QueueArray<Double> dobles = new QueueArray<>(Double.class, 4);
        try {
            dobles.enQueue(20d);
            dobles.enQueue(17d);
            dobles.enQueue(12d);
            System.out.println("Salió el valor: " + dobles.deQueue());
            dobles.enQueue(-40d);
            dobles.enQueue(15d);
            dobles.enQueue(-1d);
            System.out.println(dobles.front());
            System.out.println(dobles.last());
        } catch (isEmptyException | isFullException e) {
            System.err.println(e.getMessage());
        }
    }
}
