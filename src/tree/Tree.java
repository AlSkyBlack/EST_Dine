package tree;

import Excepciones.isEmptyException;
import Node.Node;

public interface Tree<T extends Comparable<T>> {
    
    public void    isEmpty         ()               throws isEmptyException;
    
    public boolean add             (T value);
    public boolean add             (Node<T> node);
    public boolean remove          (T value);
    public T       search          (T value)        throws isEmptyException;
    public T       depthFirstSearch(T value)        throws isEmptyException;
    public void    balance         ()               throws isEmptyException;
    
    public T       biggest         ()               throws isEmptyException;
    public T       minor           ()               throws isEmptyException;
    
    public void    preOrder        ()               throws isEmptyException;
    public void    inOrder         ()               throws isEmptyException;
    public void    postOrder       ()               throws isEmptyException;
    
    public int     height          ()               throws isEmptyException;
    public int     width           ()               throws isEmptyException;
    public void    between         (T start, T end) throws isEmptyException;
}
