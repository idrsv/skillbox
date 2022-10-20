public interface WorkWithMongoDB {

    void addShop(String shop);

    void addProduct(String product, int price);

    void putProduct(String shop, String product);

    void showStatistics();

}