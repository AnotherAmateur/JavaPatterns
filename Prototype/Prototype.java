// Демонстрация паттерна Прототип

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        // произвели один эталонынй бургер
        Burger standardBurger = new Burger(
                100, "fatMan", true, Burger.MeatTypes.Chicken);

        // произвели еще несколько бургеров на основе эталонного
        Map<String, Burger> burgers = new HashMap<String, Burger>();
        for (int i = 0; i < 6; ++i){
            burgers.put("burger" + String.valueOf(i), standardBurger.Clone());
        }

        // изменили цену одного из бургеров для нелюбимого посетителя
        burgers.get("burger2").price = 150;

        // вывели информацию о каждом произведенном бургере
        for (Map.Entry<String, Burger> burger : burgers.entrySet()) {
            System.out.println(burger.getValue().GetInfo() + '\n');
        }
    }
}

// Бургерный интерфейс
interface IBUrger {
    public IBUrger Clone();

    public String GetInfo();
}

// Класс бургеров, реализующий интерфейс
class Burger implements IBUrger {
    public String name;
    public int price;
    public boolean withCheese;
    public MeatTypes meat;
    static enum MeatTypes {Chicken, Beef, Pork}

    Burger(int price, String name, boolean withCheese, MeatTypes meat) {
        this.price = price;
        this.name = name;
        this.withCheese = withCheese;
        this.meat = meat;
    }

    // метод возвращает копию объекта
    @Override
    public Burger Clone() {
        return new Burger(price, name, withCheese, meat);
    }

    // метод возвращает строку, содержащую значения полей объекта
    @Override
    public String GetInfo() {
        return "burger name: " + name + "\nprice: " + price + "\nwith cheese: "
                + withCheese + "\nmeat: " + meat;
    }
}

