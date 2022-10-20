public class Loader {
    public static void main(String[] args) {
        Cat murka = new Cat(2000.0D, "Мурка", ColorOfCat.GREY_COLOR);
        Cat bobo = new Cat(200.0D, "Бобо", ColorOfCat.ORANGE_COLOR);
        murka.meow();
        System.out.println(murka.getWeight());
        System.out.println(bobo.getWeight());
        System.out.println(Cat.catCount);
    }
}