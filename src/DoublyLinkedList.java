public class DoublyLinkedList<E extends Comparable<E>> {
    private Node<E> head, tail = null;

    //Inserts a new item at the head of the list
    public void insertHead(E item) {
        Node<E> newNode = new Node<>(item);

        //if there is already a head, use the overloaded method
        if(head != null) {
            insertHead(item, head);
        }

        //Whatever happens, set the head to the new node
        head = newNode;
    }

    //Overloaded version of insertHead for if there is already a head. More efficient than insertAfter method
    public void insertHead(E item, Node<E> original) {
        Node<E> newNode = new Node<>(item);

        newNode.setPrevious(original);
        original.setNext(newNode);
    }

    //Inserts an item behind the given node
    public void insertBehind(E item, Node<E> after) {
        Node<E> newNode = new Node<>(item);
        //set the next node of the newly created node to the after node
        newNode.setNext(after);
        //if the after node has a node behind it, set that nodes next node to the new one(Prevents null calls)
        if(after.hasPrevious()) {
            after.getPrevious().setNext(newNode);
            newNode.setPrevious(after.getPrevious());
        }
        //sets the after node's new previous to the new node, completing the new chain
        after.setPrevious(newNode);

        //if the new node is now the last node in the chain, make it the tail
        if(!newNode.hasPrevious()) {
            tail = newNode;
        }
    }

    //Inserts an item in front of the given node
    public void insertInFront(E item, Node<E> before) {
        Node<E> newNode = new Node<>(item);
        //sets the new nodes previous value to the before node
        newNode.setPrevious(before);
        //if the before node has a node in front of it, set that nodes previous value to the new node and set new nodes next to it
        if(before.hasNext()) {
            before.getNext().setPrevious(newNode);
            newNode.setNext(before.getNext());
        }
        //set the before nodes next value to the new node
        before.setNext(newNode);

        //if the new node is the fist node in the chain, set it as the head
        if(!newNode.hasNext()) {
            head = newNode;
        }
    }

    //Removes the head of the chain and sets the new head. Returns the original head
    public Node<E> removeHead() {
        Node<E> oldHead = head;
        //if the head is the only value in the chain, set the head to null
        if(!getHead().hasPrevious()) {
            head = null;
        } else {
            //otherwise, set the new head to the previous value of the chain and set it's next value to null. Garbage collector does the rest
            Node<E> newHead = head.getPrevious();
            newHead.setNext(null);
            head = newHead;
        }

        //return the old head
        return oldHead;
    }

    //return empty if the head is null
    public boolean isEmpty() {
        return head == null;
    }

    //return the head
    public Node<E> getHead() {
        return head;
    }

    //return the tail
    public Node<E> getTail() {
        return tail;
    }
}
