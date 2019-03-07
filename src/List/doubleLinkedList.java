package List;

import Excepciones.isEmptyException;
import Node.Node;
import java.util.Iterator;

public class doubleLinkedList<T extends Comparable<T>> implements Iterable<T>, Comparable<T> {
    
    private Node<T> head, tail;
    private long lenght;
    
    public doubleLinkedList() {
        head = new Node<>();
        tail = new Node<>();
        lenght = 0;
    }
    
    public doubleLinkedList(T value) {
        this();
        Node<T> _new = new Node<>(value);
        head.setNext(_new);
        tail.setBack(_new);
        lenght++;
    }
    
    public doubleLinkedList(Node<T> node) {
        this(node.getValue());
    }
    
    public boolean add(T value) {
        Node<T> _new = new Node<>(value);
        if (_new == null)
            return false;
        else {
            try {
                isEmpty();
                Node<T> tmp = tail.getBack();
                tmp.setNext(_new);
                _new.setBack(tmp);
                
                tail.setBack(_new);
            }catch (isEmptyException e) {
                head.setNext(_new);
                tail.setBack(_new);
            }
            lenght++;
            return true;
        }
        
    }
    
    public boolean add(Node<T> node) {
        return add(node.getValue());
    }
    
    public boolean isThere(Node<T> node, T value) {
        if (node.getNext() == null)
            return false;
        else if (node.getNext().getValue().equals(value))
            return true;
       return  isThere(node.getNext(), value);
         
    }
    
    private Node<T> isThereNode(Node<T> node, T value) {
        if (node.getNext() == null)
            return null;
        else if (node.getNext().getValue().equals(value))
            return node.getNext();
        else
            return isThereNode(node.getNext(), value);
        
    }
    
    
    public boolean removeAll(T value){
        boolean flag = false;
        try {
            isEmpty();
            while (isThere(head, value)) {
                remove(value);
                flag = true;
            }
            return flag;
        }catch (isEmptyException e) {
            return flag;
        }
    }
    
    public boolean remove(T value) {
        try {
            isEmpty();
            Node<T> tmp = getPrevElement(head, value);
            if (tmp != null) {
                
                if (tmp.getNext() == null && tmp.getBack() == null) {   //  es el único
                    head.setNext(null);
                    tail.setBack(null);
                }
                
                else if (tmp.getNext().getBack() == null) {  //  primero de la lista
                    tmp.getNext().getNext().setBack(null);
                    head.setNext(tmp.getNext().getNext());
                }else if (tmp.getNext().getNext() == null) {   //  es el último
                    tmp.setNext(tail);
                    tail.setBack(tmp);
                }
                else{
                    tmp.setNext(tmp.getNext().getNext());
                    tmp.getNext().setBack(tmp);
                }
                
                
                lenght--;
                System.gc();
                return true;
            }
            else
                return false;
        }catch(isEmptyException e) {
            return false;
        }
        
    }
    
    private Node<T> getPrevElement(Node<T> node, T value) {
        if (node.getNext() == null)
            return null;
        if (node.getNext().getValue().equals(value))
            return node;
        else 
            return getPrevElement(node.getNext(), value);
    }
    
    public boolean remove(Node<T> node){
        return remove(node.getValue());
    }
    
    public boolean addAt(T value, int position) {
        Node<T> tmp = getElementAt(position);
// Si el nuevo nodo es nulo regresa falso
// Si el getElementAt regresa nulo, significa que la posición no exite
        if (tmp == null || value == null)
            return false;
        else {
            Node<T> _new = new Node<>(value);
// Si el anterior del temporal es nulo, significa que es el primero de la lista
            if (tmp.getBack() == null) {
                head.setNext(_new);
                _new.setBack(null);
                _new.setNext(tmp);
                tmp.setBack(_new);
            }
            else {
                tmp.getBack().setNext(_new);
                _new.setBack(tmp.getBack());
                _new.setNext(tmp);
                tmp.setBack(_new);
            }
            lenght++;
            return true;
        }
        
    }
    
    public boolean addAt(Node<T> node, int position){
        return addAt(node.getValue(), position);
    }
    
// Ya que existe el addAt, decidí agregar el removeAt
    public boolean removeAt(int position) {
        Node<T> tmp = getElementAt(position);
// Si tmp es nulo, significa que no exite en la lista
        if (tmp == null)
            return false;
        else {
// Si el anterior Y el siguiente son nulos, significa que es el único en la lista
            if (tmp.getBack() == null && tmp.getNext() == null) {
                head.setNext(null);
                tail.setBack(null);
            } else {
                
            }
            System.gc();
            lenght--;
            return true;
        }
        
    }
    
    public boolean addAfter(T after, T value){
        try {
            isEmpty();
            Node<T> tmp = isThereNode(head, after);
            if (tmp == null)
                return false;
            else {
                Node<T> _new = new Node<>(value);
                if (tmp.getNext() == null) {
                    tmp.setNext(_new);
                    _new.setBack(tmp);
                    tail.setBack(_new);
                }
                else {
                    _new.setNext(tmp.getNext());
                    tmp.getNext().setBack(_new);
                    tmp.setNext(_new);
                    _new.setBack(tmp);
                }
                lenght++;
                return true;
            }
        } catch (isEmptyException e) {
            return false;
        }
        
        
    }
    
    public boolean addBefore(T value, T before){
        try {
            isEmpty();
            Node<T> tmp = isThereNode(head, before);
            if (tmp == null)
                return false;
            else {
                Node<T> _new = new Node<T>(value);
                if (tmp.getBack() == null) { // Si está al inicio de la lista
                    tmp.setBack(_new);
                    head.setNext(_new);
                    _new.setNext(tmp);
                }
                else {  //  Si el before está en medio o al final de la lista
                    tmp.getBack().setNext(_new);
                    _new.setBack(tmp.getBack());
                    tmp.setBack(_new);
                    _new.setNext(tmp);
                }
                lenght++;
                return true;
            }
        } catch (isEmptyException e) {
            return false;
        }
        
    }
    
    public boolean removeAfter(T value){
        try {
            isEmpty();
            Node<T> tmp = isThereNode(head, value);
            if (tmp != null && tmp.getNext() != null)
                return removeFrom(tmp);
            else
                return false;
        } catch(isEmptyException e) {
            return false;
        }
        
    }
    
//Agregué este método porque el removeAfter borraba TODO
    public boolean removeOneAfter(T after) {
        try {
            isEmpty();
            Node<T> tmp = isThereNode(head, after);
            if (tmp == null || tmp.getNext() == null)
                return false;
            else {
                if (tmp.getNext().getNext() == null) {
                    tmp.setNext(null);
                    tail.setBack(tmp);
                } else {
                    tmp.getNext().getNext().setBack(tmp);
                    tmp.setNext(tmp.getNext().getNext());
                }
                System.gc();
                lenght--;
                return true;
            }
        } catch (isEmptyException e) {
            return false;
        }
    }
    
    private boolean removeFrom(Node<T> node) {
        Node<T> tmp = node;
        if (tmp.getNext() == null)
            return false;
        else if(tmp.getNext().getNext() == null) {
            tmp.setNext(null);
            tail.setBack(tmp);
        }else {
            tmp.setNext(tmp.getNext().getNext());
            tmp.getNext().setBack(tmp);
        }
        return true;
    }
    
    public boolean removeBefore(T before) {
        try {
            isEmpty();
            Node<T> tmp = isThereNode(head, before);
            if (tmp == null)
                return false;
            else {
                if (tmp.getBack() == null)
                    return false;
                else {
                    if (tmp.getBack().getBack() == null) {
                        head.setNext(tmp);
                        tmp.setBack(null);
                    } else {
                        tmp.getBack().getBack().setNext(tmp);
                        tmp.setBack(tmp.getBack().getBack());
                    }
                    System.gc();
                    lenght--;
                    return true;
                }
            }
        } catch (isEmptyException e) {
            return false;
        }
        
    }
    
    public boolean addStart(T value) {
        Node<T> _new = new Node<>(value);
        if (_new == null)
            return false;
        else {
            try {
                isEmpty();
                _new.setNext(head.getNext());
                head.getNext().setBack(_new);
                head.setNext(_new);
            } catch (isEmptyException e) {
                head.setNext(_new);
                tail.setBack(_new);
            }
            lenght++;
            return true;
        }
        
    }
    
// Este método regresa el nodo en la posición buscada, si no está regresa nulo
    public Node<T> getElementAt(int position) {
        try {
            isEmpty();
            if (position < 0 || position >= lenght)
                return null;
            else {
                return getElementAtPart2(head, 0, position);
            }
        } catch (isEmptyException e) {
            return null;
        }
    }
    
//  Este es la parte que cicla del método anterior
    private Node<T> getElementAtPart2(Node<T> node, int position, int stop) {
        if (position == stop)
            return node.getNext();
        else
            return getElementAtPart2(node.getNext(), position + 1, stop);
    }
    
    public boolean isEmpty() throws isEmptyException {
        if (head.getNext()==null) {
            throw new isEmptyException("La lista está vaciá");
        }
        else
            return true;
    }
    
    public long lenght() {
        return this.lenght;
    }
    
    public void rprint() {
        rprint(tail);
    }
    
    public void rprint(Node<T> node) {
        if (node.getBack() == null)
            return;
        else {
            System.out.println(node.getBack().getValue());
            rprint(node.getBack());
        }
    }
    
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> cpy = head.getNext(), sub_head;
            @Override
            public boolean hasNext() {
                if (cpy == null)
                    return false;
                else {
                    sub_head = cpy;
                    cpy = cpy.getNext();
                    return true;
                }
            }
            
            @Override
            public T next() {
                return sub_head.getValue();
            }
        };
    }

    @Override
    public int compareTo(T t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
