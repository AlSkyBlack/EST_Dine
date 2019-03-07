package queueSimpleList;

import Excepciones.isEmptyException;
import Excepciones.isFullException;
import Node.Node;
import queue.Queue;

public class QueueSimpleList <T extends Comparable<T>> implements Queue<T>{
    private int size;
    private int count = 0;
    private Class<T> type = null;
    private Node<T> head;
    
    public QueueSimpleList(Class <T> type, int size) {
        head = new Node<>();
        this.type = type;
        this.size = size;
    }
    
    public QueueSimpleList(Class <T> type) {
        this(type, 10);
    }

    @Override
    public boolean enQueue(T value) throws isFullException {
        try {
            isFull();
            if (value == null)
                return false;
            else {
                Node<T> _new = new Node<>(value);
                if (head.getNext() == null)
                    head.setNext(_new);
                else
                    getLastNode(head).setNext(_new);
                count++;
                return true;
            }
        } catch (isFullException e) {
            throw e;
        }
    }

    @Override
    public T deQueue() throws isEmptyException {
        try {
            isEmpty();
            T tmp = head.getNext().getValue();
            if (head.getNext().getNext() == null) {
                head.getNext().setValue(null);
                head.setNext(null);
            } else {
                Node<T> referencia = head.getNext();
                head.setNext(referencia.getNext());
                referencia.setNext(null);
                referencia.setValue(null);
            }
            System.gc();
            count--;
            return tmp;
        } catch (isEmptyException e) {
            throw e;
        }
    }

    @Override
    public boolean removeAll() throws isEmptyException {
        try {
            isEmpty();
            count = 0;
            return removeAllPart2();
        } catch (isEmptyException e) {
            throw e;
        }
    }
    
    private boolean removeAllPart2() {
        if (head.getNext() != null) {
            removeAllPart3(head);
            return removeAllPart2();
        } else
            return true;
    }
    
    private void removeAllPart3(Node<T> node) {
        if (node.getNext().getNext() == null) {
            head.getNext().setValue(null);
            head.setNext(null);
        } else {
            Node<T> ref = head.getNext();
            head.setNext(ref.getNext());
            ref.setNext(null);
            ref.setValue(null);
        }
    }

    @Override
    public void isFull() throws isFullException {
        if (count == size)
            throw new isFullException("Full Queue");
    }

    @Override
    public void isEmpty() throws isEmptyException {
        if (count == 0)
            throw new isEmptyException("Empty Queue");
    }

    @Override
    public T front() throws isEmptyException {
        try {
            isEmpty();
            return getLastNode(head).getValue();
        } catch (isEmptyException e) {
            throw e;
        }
    }

    @Override
    public T last() throws isEmptyException {
        try {
            isEmpty();
            return head.getNext().getValue();
        } catch (isEmptyException e) {
            throw e;
        }
    }
    
    public static void main(String[] args) {
        QueueSimpleList<Double> dobles = new QueueSimpleList<>(Double.class, 6);
        try {
            dobles.enQueue(4d);
            dobles.enQueue(5d);
            dobles.enQueue(6d);
            dobles.enQueue(7d);
            //System.out.println("Salió el valor: " + dobles.deQueue() + " de la cola");
            dobles.enQueue(403d);
            /*if(dobles.removeAll())
                System.out.println("Se eliminó todo");*/
            System.out.println("The last is " + dobles.last());
            System.out.println("The front is " + dobles.front());
        } catch (isFullException | isEmptyException e) {
            System.out.println(e.getMessage());
        } finally {
            dobles.print();
        }
    }
    
    public void print() {
        if (head.getNext() != null) {
            printPart2(head);
            System.out.println();
            System.out.println("Hay " + count + " elementos");
        }
        else
            System.out.println("Empty Queue");
    }
    
    private void printPart2(Node<T> node) {
        if (node.getNext() != null) {
            System.out.print(" " + node.getNext().getValue());
            printPart2(node.getNext());
        }
    }
    
    public Node<T> getLastNode(Node<T> node) {
        if (node.getNext() == null)
            return node;
        else
            return getLastNode(node.getNext());
    }
    
}
