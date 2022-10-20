import java.util.Scanner;

public class Main {
    private static final String MONGO_URL = "127.0.0.1";
    private static final int MONGO_PORT = 27017;

    private static final String CMD_ADD_SHOP = "ADD_SHOP";
    private static final String CMD_ADD_PRODUCT = "ADD_PRODUCT";
    private static final String CMD_PUT_PRODUCT = "PUT_PRODUCT";
    private static final String CMD_SHOW = "STATISTICS";
    private static final String CMD_EXIT = "EXIT";


    public static void main(String[] args) {
        WorkWithMongo workWithMongo = new WorkWithMongo();

        WorkWithMongo.init(MONGO_URL, MONGO_PORT);

        System.out.println("Please, use next commands:" +
                "\n\tADD_SHOP store_name" +
                "\n\tADD_PRODUCT product_name product_price" +
                "\n\tPUT_PRODUCT product_name store_name" +
                "\n\tSTATISTICS" +
                "\n\tEXIT" +
                "\n\t");


            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();

            while (!command.equals(CMD_EXIT) && !command.isEmpty()) {
                String[] str = command.split(" ");
                switch (str[0]) {
                    case CMD_ADD_SHOP:
                        workWithMongo.addShop(str[1]);
                        break;
                    case CMD_ADD_PRODUCT:
                        workWithMongo.addProduct(str[1], Integer.parseInt(str[2]));
                        break;
                    case CMD_PUT_PRODUCT:
                        workWithMongo.putProduct(str[1], str[2]);
                        break;
                    case CMD_SHOW:
                        workWithMongo.showStatistics();
                        break;
                    default:
                        System.out.println("Error.Wrong command!");
                }
                command = scanner.nextLine();
            }
    }
}
