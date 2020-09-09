public class Node<E> {
    private E item;
    private Node<E> previous = null;
    private Node<E> next = null;

    public Node(E item) {
        this.item = item;
    }

    public E getItem() {
        return item;
    }

    public Node<E> getNext() {
        if(next == null) {
            return null;
        } else {
            return next;
        }
    }

    public Node<E> getPrevious() {
        if(previous == null) {
            return null;
        } else {
            return previous;
        }
    }

    public boolean hasPrevious() {
        return previous != null;
    }

    public boolean hasNext() {
        return next != null;
    }

    public void setItem(E item) {
        this.item = item;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

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
