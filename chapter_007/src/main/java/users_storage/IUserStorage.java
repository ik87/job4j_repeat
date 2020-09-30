package users_storage;

import java.util.Map;

public interface IUserStorage {
    boolean transfer(int fromId, int toId, int amount);
    boolean add(User user);
    boolean update(User user);
    boolean delete(User user);
    Map<Integer, User> getUsers();
}
