package List;

import Node.Node;
import java.util.Iterator;
import Excepciones.isEmptyException;

public class linkedList <T extends Comparable<T>> implements Iterable<T> {
    private Node<T> head;
    private long lenght = 0;
    
    public boolean buscarOcurrencias(){return false;}
    public boolean agregarOrdenado(){return false;}
    
    public linkedList(Node<T> _new) {
        this();
        this.head.setNext(_new);
    }
    
    public linkedList() {
        this.head = new Node<>();
    }
    
    public boolean add(T value) {
        Node<T> _new = new Node<>(value);
        try {
            Node<T> tmp = null;
            isEmpty();
            tmp = getLastElement(this.head);
            tmp.setNext(_new);
            lenght++;
        } catch(isEmptyException e) {
            head.setNext(_new);
            lenght++;
        }
        return false;
    }
    
    public boolean add(Node<T> node) {
        return add(node.getValue());
    }
    
    private Node<T> getLastElement(Node<T> tmp) {
        if (tmp.getNext() == null)
            return tmp;
        else
            return getLastElement(tmp.getNext());
    }
    
    public boolean isThere(Node<T> node, T value) {
        if (node.getNext() == null)
            return false;
        else if (node.getNext().getValue().equals(value))
            return true;
       return  isThere(node.getNext(), value);
         
    }
    
//Como el método isThere, pero este me regresa el nodo
//Cuando se manda a llamar, siempre se debe enviar el head
    private Node<T> getNode(Node<T> node, T value) {
        if (node.getNext() == null)
            return null;
        else if (node.getNext().getValue().equals(value))
            return node;
        else
            return getNode(node.getNext(), value);
    }
    
    public boolean removeAll(T value) {
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
                tmp.setNext(tmp.getNext().getNext());
                lenght--;
                System.gc();
                return true;
            } else
                return false;
        }catch (isEmptyException e) {
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
    
    public boolean remove(Node<T> node) {
        return remove(node.getValue());
    }
    
    public boolean addAt(T value, int position) {
        Node<T> tmp = getElementAt(position);
        if (tmp == null)
            return false;
        else {
            Node<T> _new = new Node<>(value);
            if (position == 0) //Si es al inicio de la lista
            {
                _new.setNext(head.getNext());
                head.setNext(_new);
            } else {
                getPrevElement(head, tmp.getValue()).setNext(_new);
                _new.setNext(tmp);
            }
            lenght++;
            return true;
        }
    }
    
    public boolean addAt(Node<T> node, int position) {
        return addAt(node.getValue(), position);
    }
    
    public boolean addAfter(T after, T value) {
        try {
            isEmpty();
            Node<T> tmp, _new;
            
            if ((tmp = getPrevElement(head, after)) != null) {
                _new = new Node<T> (value);
                _new.setNext(tmp.getNext().getNext());
                tmp.getNext().setNext(_new);
                lenght++;
                System.gc();
                return true;
            } else
                return false;
        }catch(isEmptyException e) {
            return false;
        }
        
    }
    
// value es el nuevo valor, before es el número del que se le va agregar antes
    public boolean addBefore(T value, T before) {
        try {
            isEmpty();
            if (isThere(head, value)) {
//ref es de referencia
                Node<T> ref = getPrevElement(head, value);
//Si es nulo, es porque el before es el primer valor de la lista
                if (ref == null)
                    addStart(value);
                else {
                    Node<T> _new = new Node<>(value);
                    _new.setNext(ref.getNext());
                    ref.setNext(_new);
                    lenght++;
                }
                return true;
            } else
                return false;
        } catch (isEmptyException e) {
            return false;
        }
        
    }
    

    
    public boolean removeAfter(T value) {
        try {
            isEmpty();
            Node<T> tmp = getNode(head, value);
            if (tmp == null)
                return false;
            else {
                if (tmp.getNext() == null)
                    return false;
                else {
                    tmp.setNext(tmp.getNext().getNext());
                    lenght++;
                    return true;
                }
            }
        } catch (isEmptyException e) {
            return false;
        }
            
    }
    
    public boolean removeBefore(T value) {
        try {
            isEmpty();
            if (isThere(head, value))
                return remove(getPrevElement(head, value));
            return false;
        } catch (isEmptyException e) {
            return false;
        }
        
    }
    
    public boolean addStart(T value) {
        Node<T> _new = new Node<>(value);
        try {
            isEmpty();
            Node<T> tmp = head.getNext();
            head.setNext(_new);
            _new.setNext(tmp);
            lenght++;
            return true;
        }catch(isEmptyException e) {
            head.setNext(_new);
            return true;
        }
        
    }
    
    public boolean addStart(Node<T> node) {
        return addStart(node.getValue());
    }
    
//Aqui le cambié el parámetro de entrada, ya que más bien debe buscar el nodo
    public Node<T> getElementAt(int position){
        try {
            isEmpty();
            if (position >= lenght || position < 0)
                return null;
            else {
                Node<T> tmp = head.getNext();
                int i = 0;
                while (i < position) {
                    tmp.setNext(tmp.getNext());
                    i++;
                }
                return tmp;
            }
        } catch (isEmptyException e) {
            return null;
        }
        
    }
    
    public boolean isEmpty() throws isEmptyException {
        if (head.getNext()==null) {
            throw new isEmptyException("La lista está vaciá");
        }
        else
            return true;
    }
    
    public long lenght(){
        return this.lenght;
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
}
