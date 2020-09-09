public class PriorityQueue<E extends Comparable<E>> {
    DoublyLinkedList<E> list = new DoublyLinkedList<>();

    public void push(E item) {
        if(list.getHead() == null) {
            list.insertHead(item);
        } else {
            push(item, list.getHead());
        }
    }

    private void push(E item, Node<E> current) {
        if(item.compareTo(current.getItem()) >= 0) {
            list.insertInFront(item, current);
        } else if(current.getPrevious() != null) {
            push(item, current.getPrevious());
        } else {
            list.insertBehind(item, current);
        }
    }

    public E pop() {
        return list.removeHead().getItem();
    }

    public E peek() {
        return list.getHead().getItem();
    }

    public E peekLast() {
        return list.getTail().getItem();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
