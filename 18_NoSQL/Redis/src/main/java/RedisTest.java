public class RedisTest {

    private static final String address = "127.0.0.1";
    private static final String port = "6379";

    public static void main(String[] args) throws InterruptedException {
        RedisStorage redisStorage = new RedisStorage(address, port);
        redisStorage.init();

        for (int i = 1; i <= 20; i++) {
            redisStorage.singIn(i);
            Thread.sleep(200);
        }

        while (true) {
            if ((int) (Math.random() * 10) == 1) {
                redisStorage.pay((int) (Math.random() * 20) + 1);
            }
            System.out.println("На главной странице показываем пользователя № " + redisStorage.donateUser());
            Thread.sleep(1000);
        }
    }
}