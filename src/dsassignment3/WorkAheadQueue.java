package dsassignment3;

import java.util.ArrayList;

/**
 *
 * @author aachu
 * @version 1
 * @param <T>
 */
public class WorkAheadQueue<T> implements WorkAheadQueueADT<T> {

    protected LinearNode<T> front;
    protected LinearNode<T> back;
    protected ArrayList<LinearNode<T>> frontThreeNodes;
    protected ArrayList<T> frontThreeElements;
    protected int numNodes;

    /**
     *
     */
    public WorkAheadQueue() {
        this.front = null;
        this.back = null;
        this.frontThreeNodes = new ArrayList<LinearNode<T>>();
        this.frontThreeElements = new ArrayList<T>();
        this.numNodes = 0;
    }
    
    
    /**
     *
     * @param x index of element to removed
     * @return element removed at index x 
     * @throws EmptyCollectionException
     * @throws InvalidArgumentException
     */
    @Override
    public T dequeue(int x) throws EmptyCollectionException, InvalidArgumentException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Queue is empty! cannot dequeue");
        }
        if (x > 2 || x < 0 || x > numNodes - 1) {
            throw new InvalidArgumentException("index invalid");
        }
        LinearNode<T> temp1 = front;
        for (int i = 0; i < x; i++) {
            temp1 = temp1.getNext();
        }
        //System.out.println(temp1.getElement());
        T removal = temp1.getElement();
        
        if (numNodes == 1) {
            front = null;
            back = null;
        } else if (temp1.getPrev() == null) { //check if first element is null;
            front = front.getNext();
            front.setPrev(null);
        } else if (temp1.getNext() == null) { //check if first element is null;
            back = back.getPrev();
            back.setNext(null);
        } else {
            temp1.getPrev().setNext(temp1.getNext()); // get index of previous node and set its next value to the node after the removed temp
            temp1.getNext().setPrev(temp1.getPrev()); // get index of of next node and set its previous value to the node before the removed temp
        }
        numNodes--;
        try {
            frontThreeElements = firstThreeElements();
            frontThreeNodes = firstThreeNodes();
        } catch (EmptyCollectionException ece) {
            
        }
        return removal;
    }

    /**
     *
     * @param x
     * @return (without removing) the element that is at place x in the queue.
     * @throws EmptyCollectionException
     * @throws InvalidArgumentException
     */
    @Override
    public T first(int x) throws EmptyCollectionException, InvalidArgumentException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Queue is empty! cannot dequeue");
        }
        if (x > 2 || x < 0 || x > numNodes -1) {
            throw new InvalidArgumentException("index invalid");
        }
        LinearNode<T> temp = front;
        for (int i = 0; i < x; i++) {
            temp = temp.getNext();
        }
        T removal = temp.getElement();
        return removal;
    }
    /**
     * Returns an ArrayList of the first three nodes in the queue.
     * @return an ArrayList of nodes of LinearNodes of T data type
     * @throws EmptyCollectionException if the queue is empty
     */
    @Override
    public ArrayList<LinearNode<T>> firstThreeNodes() throws EmptyCollectionException {
        if (isEmpty())  {
            throw new EmptyCollectionException("firstThreeElements");
        }
        ArrayList<LinearNode<T>> retArray = new ArrayList<>();
        LinearNode<T> temp = front;
        for (int i = 0; (temp != null) && (i < 3); i++) {
            retArray.add(temp);
            temp = temp.getNext();
        }
        return retArray;
    }

    /**
     * Returns an ArrayList of the first three elements in the queue.
     * @return an ArrayList of elements of T data type
     * @throws EmptyCollectionException if the queue is empty
     */
    @Override
    public ArrayList<T> firstThreeElements() throws EmptyCollectionException {
        if (this.numNodes == 0) {
            throw new EmptyCollectionException("firstThreeElements");
        }
        ArrayList<T> retArray = new ArrayList<>();
        LinearNode<T> temp = front;
        for (int i = 0; (temp != null) && (i < 3); i++) {
            retArray.add(temp.getElement());
            temp = temp.getNext();
        }
        return retArray;
    }

    /**
     * adds element to the back of the queue
     * @param element
     */
    @Override
    public void enqueue(T element) {
        LinearNode<T> newNode = new LinearNode<>(element);
        if (isEmpty()) {
            front = newNode;
            back = newNode;   
        } else {
            back.setNext(newNode);
            newNode.setPrev(back);
            back = newNode;    
        }
        numNodes++;
        try {
            frontThreeElements = firstThreeElements();
            frontThreeNodes = firstThreeNodes();
        } catch (EmptyCollectionException ece) {
            
        }
    }

    /**
     *
     * @return element at front of queue
     * @throws EmptyCollectionException
     */
    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("queue is empty");
        }
        T frontVal = front.getElement();
        if (numNodes == 1) {
         
            front = null;
            back = null;
            numNodes--;  
        } else {
            //frontVal = front.getElement();
            front = front.getNext();
            front.setPrev(null);
            numNodes--;
        }
        try {
            frontThreeElements = firstThreeElements();
            frontThreeNodes = firstThreeNodes();
        } catch (EmptyCollectionException ece) {
            
        }       
        return frontVal; 
    }

    /**
     *
     * @return element at the front of the queue
     * @throws EmptyCollectionException
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("queue is empty");
        }
        return front.getElement();
    }

    /**
     *
     * @return true if queue is empty
     */
    @Override
    public boolean isEmpty() {
        return (this.numNodes == 0);
    }

    /**
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return this.numNodes;
    }

    /**
     *
     * @return queue to string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        LinearNode curr = front;
        for (int i = 0; i < size(); i++) {
            sb.append(curr.getElement().toString());
            if (i < size() - 1) {
                sb.append(", ");
            }
            curr = curr.getNext();
        }
        return sb.toString();
    }  
} // end class
