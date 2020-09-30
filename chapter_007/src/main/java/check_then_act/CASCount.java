package check_then_act;

import java.util.concurrent.atomic.AtomicReference;

public class CASCount<T> {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public CASCount(Integer i) {
        count.set(i);
    }

    public void increment() {
        Integer current;
        Integer next;
        do {
            current = count.get();
            next = current + 1;
        } while (!count.compareAndSet(current, next));
    }

    public int get() {
        return count.get();
    }
}
