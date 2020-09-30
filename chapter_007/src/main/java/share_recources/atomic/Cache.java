package share_recources.atomic;

public final class Cache {
    private static volatile Cache cache;

    public static Cache instOf() {
        if (cache == null) {
            synchronized (Cache.class) {
                if(cache == null) {
                    cache = new Cache();
                }
            }
        }
        return cache;
    }

    public static void main(String[] args) {

    }
}