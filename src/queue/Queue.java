package queue;

import Excepciones.*;

public interface Queue <T> {
    
    public boolean enQueue(T value) throws isFullException;
    public T       deQueue()        throws isEmptyException;
    public boolean removeAll()      throws isEmptyException;
    public void    isFull()         throws isFullException;
    public void    isEmpty()        throws isEmptyException;
    public T       front()          throws isEmptyException;
    public T       last()           throws isEmptyException;
    
}
