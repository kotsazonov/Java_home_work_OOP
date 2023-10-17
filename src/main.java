import java.util.List;
import java.util.ArrayList;

interface VendingMachine {
    Product getProduct(String name);
    Product getProduct(String name, double volume);
    Product getProduct(String name, double volume, int temperature);
}

class Product {
    protected String name;
    protected double volume;

    public Product(String name, double volume) {
        this.name = name;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Volume: " + volume;
    }
}

class BottleOfWatter extends Product {
    public BottleOfWatter(String name, double volume) {
        super(name, volume);
    }
}

class ГорячийНапиток extends BottleOfWatter {
    private int temperature;

    public ГорячийНапиток(String name, double volume, int temperature) {
        super(name, volume);
        this.temperature = temperature;
    }

    public int getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return super.toString() + ", Temperature: " + temperature;
    }
}

class ГорячихНапитковАвтомат implements VendingMachine {
    private List<ГорячийНапиток> products;

    public ГорячихНапитковАвтомат(List<ГорячийНапиток> products) {
        this.products = products;
    }

    @Override
    public Product getProduct(String name) {
        for (Product product : products) {
            if (product.name.equals(name))
                return product;
        }
        return null;
    }

    @Override
    public Product getProduct(String name, double volume) {
        for (ГорячийНапиток drink : products) {
            if (drink.name.equals(name) && drink.volume == volume)
                return drink;
        }
        return null;
    }

    @Override
    public Product getProduct(String name, double volume, int temperature) {
        for (ГорячийНапиток drink : products) {
            if (drink.name.equals(name) && drink.volume == volume && drink.getTemperature() == temperature)
                return drink;
        }
        return null;
    }
}

public class main {
    public static void main(String[] args) {
        ГорячийНапиток coffee = new ГорячийНапиток("Кофе", 0.25, 90);
        ГорячийНапиток tea = new ГорячийНапиток("Чай", 0.3, 85);

        List<ГорячийНапиток> list = new ArrayList<>();
        list.add(coffee);
        list.add(tea);


        ГорячихНапитковАвтомат автомат = new ГорячихНапитковАвтомат(list);

        System.out.println(автомат.getProduct("Чай", 0.3, 85));
    }
}