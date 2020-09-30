package nonblocking_cache;

import net.jcip.annotations.ThreadSafe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Non blocking cache
 *
 * @author Kosolapov Ilya (d_dexter@mail.ru)
 * @version 1
 * @since 29.09.2020
 */

@ThreadSafe
public class CacheStorage {
    private final Map<Integer, Base> storage = new ConcurrentHashMap<>();

    public void add(Base model) {
        storage.put(model.id, model);
    }

    public void remove(Base model) {
        storage.remove(model.id);
    }

    public void update(Base model) {
        storage.computeIfPresent(model.id, (k, v) -> {
            if(v.version != model.version) {
                throw  new OptimisticException("OptimisticException");
            }
            model.version++;
            return model;
        });
    }

    public boolean isEmpty() {
        return storage.isEmpty();
    }
}
