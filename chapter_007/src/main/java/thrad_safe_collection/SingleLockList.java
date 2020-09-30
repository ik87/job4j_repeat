package thrad_safe_collection;

import list.SimpleArray;
import list.SimpleList;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {

    SimpleList<T> simpleList = new SimpleArray<>();

    public synchronized void add(T value) {
        this.simpleList.add(value);
    }

    public synchronized T get(int index) {
        return simpleList.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(simpleList).iterator();
    }

    private SimpleList<T> copy(SimpleList<T> simpleList) {
        SimpleList<T> tmp = new SimpleArray<>();
        for(T el : simpleList) {
            tmp.add(el);
        }
        return tmp;
    }
}
