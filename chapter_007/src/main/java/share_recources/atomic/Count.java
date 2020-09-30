package share_recources.atomic;


public class Count {
    private int value = 0;


    public synchronized void  increment() {

            value++;
        System.out.println(Thread.currentThread().getName());

    }

    public int getValue() {
        return value;
    }
}
