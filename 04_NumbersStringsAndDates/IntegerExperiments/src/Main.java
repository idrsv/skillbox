public class Main
{
    public static void main(String[] args)
    {
        Container container = new Container();
        container.count += 7843;

        System.out.println(sumDigits(256));
    }

    public static int sumDigits(int number)
    {
        String line = String.valueOf(number);

        int sum = 0;
        for (int i = 0; i < line.length(); i++)
        {
            sum += Character.getNumericValue(line.charAt(i));
        }
        return sum;
    }

    public static int sumDigits(Integer number)
    {
        String line = Integer.toString(number);

        int sum = 0;
        for (int i = 0; i < line.length(); i++)
        {
            sum += Integer.parseInt(String.valueOf(line.charAt(i)));
        }
        return sum;
    }

}
