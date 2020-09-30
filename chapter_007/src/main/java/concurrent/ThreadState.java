package concurrent;

public class ThreadState {

    public static void main(String[] args) {
        Thread first = new Thread(()-> {});
        Thread second = new Thread(()-> {});
        System.out.println("Thread " + first.getName() + " is " + first.getState());
        System.out.println("Thread" + second.getName() + " is " + second.getState());
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED || second.getState() != Thread.State.TERMINATED) {
            System.out.println("Thread " + first.getName() + " is " + first.getState());
            System.out.println("Thread" + second.getName() + " is " + second.getState());
        }
        System.out.println("Thread " + first.getName() + " is " + first.getState());
        System.out.println("Thread" + second.getName() + " is " + second.getState());

    }
}
