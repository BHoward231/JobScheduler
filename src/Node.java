//blueprint for Node
public class Node<E> {
    private E item;
    private Node<E> previous = null;
    private Node<E> next = null;
    //sets item to current item
    public Node(E item) {
        this.item = item;
    }
    //returns item
    public E getItem() {
        return item;
    }
    //get's the next node
    public Node<E> getNext() {
        if(next == null) {
            return null;
        } else {
            return next;
        }
    }
    //retrieves the previous node
    public Node<E> getPrevious() {
        if(previous == null) {
            return null;
        } else {
            return previous;
        }
    }
    //checks previous Nodes existence
    public boolean hasPrevious() {
        return previous != null;
    }
    //checks next Nodes existence
    public boolean hasNext() {
        return next != null;
    }
    //sets item info
    public void setItem(E item) {
        this.item = item;
    }
    //assigns next
    public void setNext(Node<E> next) {
        this.next = next;
    }
    //assigns previous
    public void setPrevious(Node<E> previous) {
        this.previous = previous;
    }

    @Override
    public String toString() {
        String s = "" + item;

        s += " has coverage on: ";
        if(hasNext()) {
            s += " next node ";
        } if(hasPrevious()) {
            s += " Previous node";
        }

        return s;
    }
}
