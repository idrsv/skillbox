import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Field;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkWithMongo implements WorkWithMongoDB {

    private static MongoClient mongoClient;
    private static MongoCollection<Document> shopsCollection;
    private static MongoCollection<Document> productsCollection;

    private static final String DATABASE_NAME = "store";
    private static final String COLLECTION_SHOPS = "shops";
    private static final String COLLECTION_PRODUCTS = "products";

    public static void init(String url, int port) {
        mongoClient = new MongoClient(url, port);
        MongoDatabase mongoDatabase = mongoClient.getDatabase(DATABASE_NAME);
        shopsCollection = mongoDatabase.getCollection(COLLECTION_SHOPS);
        productsCollection = mongoDatabase.getCollection(COLLECTION_PRODUCTS);
        shopsCollection.drop();
        productsCollection.drop();
    }

    @Override
    public void addShop(String shop) {
        Document newShop = new Document()
                .append("Shops", shop)
                .append("Products", new ArrayList<String>());
        shopsCollection.insertOne(newShop);
        System.out.println("Shop - " + shop + " has been added!");
        System.out.println("_______________________");
    }

    @Override
    public void addProduct(String product, int price) {
        Document newProduct = new Document()
                .append("Products", product)
                .append("Price", price);
        productsCollection.insertOne(newProduct);
        System.out.println("Product - " + product + " by price " + price + " has been added!");
        System.out.println("_______________________");
    }

    @Override
    public void putProduct(String product, String shop) {
        Document existingShop = shopsCollection.find(new Document("Shops", shop)).first();
        Document updatedShop = new Document("$push", new Document("Products", product));
        shopsCollection.updateOne(existingShop, updatedShop);
        System.out.println("Product - " + product + " has been added to shop - " + shop);
        System.out.println("_______________________");
    }

    @Override
    public void showStatistics() {
        System.out.println("_______________________");

        showShops();
        showItems();

        Bson lookup = Aggregates.lookup("products", "product", "products", "result");
        Bson unwind = Aggregates.unwind("$result");

        Bson priceLt100 = Aggregates.addFields(new Field<>(
                "priceLt100",
                new Document("$cond",
                        new Document("if",
                                new Document("$lt", Arrays.asList("$result.Price", 100)))
                                .append("then", 1)
                                .append("else", 0)
                )
        ));

        Bson group = Aggregates.group(
                "$Shops",
                Accumulators.sum("countProducts", 1),
                Accumulators.max("maxPrice", "$result.Price"),
                Accumulators.avg("avgPrice", "$result.Price"),
                Accumulators.min("minPrice", "$result.Price"),
                Accumulators.sum("priceLt100", "$priceLt100"));


        AggregateIterable<Document> priceStatistics = shopsCollection.aggregate(Arrays.asList(lookup, unwind, priceLt100 , group));

        for (Document shop : priceStatistics) {
            System.out.println("Shop: " + shop.get("_id"));
            System.out.println("Number of products: " + shop.get("countProducts"));
            System.out.println("Lowest price: " + shop.get("minPrice"));
            System.out.println("Highest price: " + shop.get("maxPrice"));
            System.out.println("Average price: " + shop.get("avgPrice"));
            System.out.println("The number of goods is less than 100: " + shop.get("priceLt100"));
            System.out.println("_______________________");
        }
    }

    public static void showShops() {
        int i = 0;
        System.out.println("Shops");
        for (Document document : shopsCollection.find()) {
            List<String> products = (List<String>) document.get("Products");
            System.out.println(++i + ".Shop: " + document.getString("Shops"));
            System.out.println("Number of products: " + products.size());
            System.out.println("Products: " + products);
        }
        System.out.println("_______________________");
    }

    public static void showItems() {
        int i = 0;
        System.out.println("Products");
        for (Document document : productsCollection.find()) {
            System.out.println(++i + ".Product: " + document.getString("Products"));
            System.out.println("Prise:  " + document.getInteger("Price"));
        }
        System.out.println("_______________________");
    }
}