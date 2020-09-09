import java.util.Optional;

public class DoublyLinkedList<E extends Comparable<E>> {
    private Node<E> head, tail = null;


    public void insertHead(E item) {
        Node<E> newNode = new Node<>(item);

        if(head != null) {
            insertHead(item, head);
        }

        head = newNode;
    }

    public void insertHead(E item, Node<E> original) {
        Node<E> newNode = new Node<>(item);

        newNode.setPrevious(original);
        original.setNext(newNode);
    }

    public void insertBehind(E item, Node<E> after) {
        Node<E> newNode = new Node<>(item);
        newNode.setNext(after);
        if(after.hasPrevious()) {
            after.getPrevious().setNext(newNode);
        }
        newNode.setPrevious(after.getPrevious());
        after.setPrevious(newNode);
        if(!newNode.hasPrevious()) {
            tail = newNode;
        }
    }

    public void insertInFront(E item, Node<E> before) {
        Node<E> newNode = new Node<>(item);
        newNode.setPrevious(before);
        if(before.hasNext()) {
            before.getNext().setPrevious(newNode);
        }
        newNode.setNext(before.getNext());
        before.setNext(newNode);

        if(!newNode.hasNext()) {
            head = newNode;
        }
    }

    public Node<E> removeHead() {
        Node<E> oldHead = head;
        if(!getHead().hasPrevious()) {
            head = null;
        } else {
            Node<E> newHead = head.getPrevious();
            newHead.setNext(null);
            head = newHead;
        }

        return oldHead;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public Optional<Node<E>> find(E item) {
        if(tail == null) {
            return Optional.empty();
        }
        Node<E> curr = tail;

        do {
            if(curr.getItem().compareTo(item) == 0) {
                return Optional.of(curr);
            }
        } while(curr.getNext() != null);

        return Optional.empty();
    }

    public Node<E> getHead() {
        return head;
    }

    public Node<E> getTail() {
        return tail;
    }
}
