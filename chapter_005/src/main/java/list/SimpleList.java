package list;

/**
 * interface simple list
 *
 * @author Kosolapov Ilya (d_dexter@mail.ru)
 * @version 1
 * @since 0.1
 */
public interface SimpleList<E> extends Iterable<E> {
    /**
     * add data
     * @param data data
     */
    void add(E data);

    /**
     *delete first element
     * @return deleted element
     * @throws java.util.NoSuchElementException if list is empty"
     */
    E delete();

    /**
     * get current capacity
     * @return size
     */
    int count();

    /**
     * get element by index
     * @param index index element
     * @return element
     */
    E get(int index);
}
