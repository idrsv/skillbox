public class Cat {
    private static final int QUANTITY_OF_EYES = 2;
    private static final int MIN_WEIGHT = 1500;
    private static final int MAX_WEIGHT = 15000;
    public static int catCount;
    private double weight;
    private double originWeight;
    private double food;
    private ColorOfCat colorOfCat;
    private String name;

    public Cat(double weight, String name, ColorOfCat colorOfCat) {
        this.weight = weight;
        this.name = name;
        this.originWeight = weight;
        ++catCount;
        if (weight < 15000.0D && weight > 1500.0D) {
            System.out.println("Одна из кошек умерла");
        } else {
            --catCount;
        }

    }

    private Cat copyCat() {
        Cat newCat = new Cat(this.getWeight(), this.getName(), this.getColorOfCat());
        return newCat;
    }

    public void setColorOfCat(ColorOfCat colorOfCat) {
        this.colorOfCat = colorOfCat;
    }

    public ColorOfCat getColorOfCat() {
        return this.colorOfCat;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void meow() {
        if (this.weight > 1500.0D && this.weight < 15000.0D) {
            this.weight -= 500.0D;
        }

    }

    public void feed(Double amount) {
        this.food += amount;
        this.weight += amount;
    }

    public void drink(Double amount) {
        this.weight += amount;
    }

    public void pee() {
        if (this.weight > 1500.0D && this.weight < 15000.0D) {
            this.weight *= 0.97D;
        }

        System.out.println("Weight: " + this.getWeight());
    }

    public Double getWeight() {
        return this.weight;
    }

    public Double getFood() {
        return this.food;
    }

    public int getCount() {
        return catCount;
    }

    public String getStatus() {
        if (this.weight < 1500.0D) {
            return "Dead";
        } else if (this.weight > 15000.0D) {
            return "Exploded";
        } else {
            return this.weight > this.originWeight ? "Sleeping" : "Playing";
        }
    }
}
