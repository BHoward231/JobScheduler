//Priority Queue implementation using a doubly-linked list as the underlying structure
public class PriorityQueue<E extends Comparable<E>> {
    DoublyLinkedList<E> list = new DoublyLinkedList<>();

    //Pushes a new object to the queue
    public void push(E item) {
        //if the list is empty, just insert the item as the head
        if(list.getHead() == null) {
            list.insertHead(item);
        } else {
            //otherwise use the push overload with the head as the start
            push(item, list.getHead());
        }
    }

    //Pushes new object the the queue using a node as a reference point
    private void push(E item, Node<E> current) {
        //if the item in the current node is greater or equal to the current value, insert it in front of that value
        if(item.compareTo(current.getItem()) >= 0) {
            list.insertInFront(item, current);
        } else if(current.getPrevious() != null) { //otherwise if there is a value behind the current node to inspect, run method with that as new current
            push(item, current.getPrevious());
        } else { //if the current value is the last in the queue and is not greater or equal then insert the current value behind it as the last value
            list.insertBehind(item, current);
        }
    }

    //Pop the first value in the queue and return it
    public E pop() {
        return list.removeHead().getItem();
    }

    //peek the first value of the queue
    public E peek() {
        return list.getHead().getItem();
    }

    //peek this last value of the queue
    public E peekLast() {
        return list.getTail().getItem();
    }

    //return whether the queue is empty or not
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
