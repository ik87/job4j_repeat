package share_recources.atomic;

import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class Node<T> {
    private final Node next;
    private final T value;

    public Node(final Node next, final T value) {
        this.next = next;
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public T getValue() {
        return value;
    }
}
