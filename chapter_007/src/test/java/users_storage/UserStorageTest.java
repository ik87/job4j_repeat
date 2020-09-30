package users_storage;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStorageTest {
    @Test
    public void whenTransferThenTransfered() throws InterruptedException {
        CyclicBarrier cb = new CyclicBarrier(2);
        User user1 = new User(1, 100);
        User user2 = new User(2, 200);
        IUserStorage userStorage = new UserStorage();

        Thread th1 = new Thread(() -> {
            try {
                cb.await();
                System.out.println(Thread.currentThread().getName() + " is working ");
                userStorage.add(user1);
                cb.await();
                userStorage.transfer(1, 2, 50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + " is complete ");
            }


        });
        Thread th2 = new Thread(() -> {
            try {
                cb.await();
                System.out.println(Thread.currentThread().getName() + " is working ");
                userStorage.add(user2);
                cb.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + " is complete ");
            }
        });

        th1.start();
        th2.start();

        th1.join();
        th2.join();

        var users = userStorage.getUsers();

        assertThat(users.get(1).getAmount(), is(50));
        assertThat(users.get(2).getAmount(), is(250));


    }

}