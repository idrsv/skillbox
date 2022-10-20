import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Введите количество ящиков");
        Scanner scanner = new Scanner(System.in);
        int numberOfBoxes = scanner.nextInt();

        int numberOfContainers = 27;
        int numberOfTrucks = 324;

        int containerNumber = 1;
        int trucksNumber = 1;

        System.out.println("Грузовик: " + 1);
        System.out.println("Контейнер: " + 1);


        for (int i = 1; i <= numberOfBoxes; i++)
        {
            System.out.println("Ящик: " + i);

            if (i % numberOfTrucks == 0)
            {
                System.out.println("Грузовик: " + ++trucksNumber);
                containerNumber = 0;
            }

            if (i % numberOfContainers == 0)
            {
                containerNumber++;
                System.out.println("Контейнер: " + containerNumber);
            }
        }
    }
}






