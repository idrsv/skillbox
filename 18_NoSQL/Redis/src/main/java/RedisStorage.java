import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

public class RedisStorage {
    private String address;
    private String port;

    public RedisStorage() {
    }

    public RedisStorage(String address, String port) {
        this.address = address;
        this.port = port;
    }

    private RedissonClient redissonClient;
    private RKeys rKeys;
    private RScoredSortedSet<String> onlineUsers;
    private final static String KEY = "ONLINE_USERS";

    public void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + address +":" + port);
        try {
            redissonClient = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            Exc.getMessage();
        }
        rKeys = redissonClient.getKeys();
        onlineUsers = redissonClient.getScoredSortedSet(KEY);
        rKeys.delete(KEY);
    }

    private long getTime() {
        return System.nanoTime();
    }

    void shutdown() {
        redissonClient.shutdown();
    }

    void singIn(int userId) {
        onlineUsers.add(getTime(), String.valueOf(userId));
    }

    int donateUser() {
        int userId = Integer.parseInt(onlineUsers.pollFirst());
        singIn(userId);
        return userId;
    }

    void pay(int userId) {
        onlineUsers.add(0, String.valueOf(userId));
        System.out.println("Пользователь " + donateUser() + " оплатил услугу.");
    }

}