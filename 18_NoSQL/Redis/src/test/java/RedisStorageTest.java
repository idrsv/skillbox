import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
public class RedisStorageTest {
    private RedisStorage redisStorage;

    @Container
    public GenericContainer redis = new GenericContainer("redis:5.0.3-alpine")
            .withExposedPorts(6379);

    @BeforeEach
    void setup() {
        String address = redis.getHost();
        Integer port = redis.getFirstMappedPort();

        redisStorage = new RedisStorage(address, String.valueOf(port));
        redisStorage.init();
    }

    @Test
    public void singInTest(){
        redisStorage.singIn(20);
        int actual = redisStorage.donateUser();
        int expected = 20;
        assertEquals(expected, actual);
    }

}
