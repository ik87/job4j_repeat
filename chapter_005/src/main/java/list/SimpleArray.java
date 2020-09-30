package list;

import net.jcip.annotations.NotThreadSafe;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Simple array
 *
 * @author Kosolapov Ilya (d_dexter@mail.ru)
 * @version 1
 * @since 28.09.20
 */
@NotThreadSafe
public class SimpleArray<E> implements SimpleList<E> {

    private final static int DEFAULT_CAPACITY = 3;
    private Object[] container;
    private Integer count = 0;
    private Integer modCount = 0;

    public SimpleArray() {
        container = new Object[DEFAULT_CAPACITY];
    }

    private void grove() {
        Object[] tmp = new Object[container.length + DEFAULT_CAPACITY];
        System.arraycopy(container, 0, tmp, 0, container.length);
        container = tmp;
    }

    private void checkSize() {
        if(count.equals(container.length)) {
            grove();
        }
    }
    @Override
    public void add(E data) {
        container[count++] = data;
        modCount++;
    }

    @Override
    public E delete() {
        return null;
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public E get(int index) {
        if(index > count) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (E) container[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator() {
            final int modify = modCount;
            int index = 0;

            @Override
            public boolean hasNext() {
                if(modify != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < count;
            }

            @Override
            public E next() {
                if(!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) container[index++];
            }
        };
    }
}
