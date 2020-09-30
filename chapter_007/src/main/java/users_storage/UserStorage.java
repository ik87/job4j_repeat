package users_storage;

import net.jcip.annotations.ThreadSafe;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@ThreadSafe
public class UserStorage implements IUserStorage {

    private final Map<Integer, User> users = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    @Override
    public boolean transfer(int fromId, int toId, int amount) {
        writeLock.lock();
        try {
            boolean result = false;
            User from = users.get(fromId);
            User to = users.get(toId);
            if(from != null && to != null && from.getAmount() >= amount) {
                from.setAmount( from.getAmount() - amount);
                to.setAmount(to.getAmount() + amount);
                result = true;
            }
            return result;
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean add(User user) {
        writeLock.lock();
        try {
            return users.put(user.getId(), new User(user)) != null;
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean update(User user) {
        writeLock.lock();
        try {
            boolean result = false;
            if(users.containsKey(user.getId())) {
                users.put(user.getId(), new User(user));
                result = true;
            }
            return result;
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public Map<Integer, User>  getUsers() {
        readLock.lock();
        try {
            return users;
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean delete(User user) {
        writeLock.lock();
        try {
            return users.remove(user) != null;
        } finally {
            writeLock.unlock();
        }
    }
}
