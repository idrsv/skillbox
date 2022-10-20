public class Main
{
    public static void main(String[] args)
    {
        String alphabet = "abcdefgehijklmnopqrstuvwxyzABCDEFGEHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < alphabet.length(); i++)
        {
            char c = alphabet.charAt(i);
            int code = (int) c;
            System.out.println(alphabet.charAt(i) + " - Номер в таблице символов ASCII = " + code );
        }
    }
}
