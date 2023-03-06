// Демонстрация паттерна Мост

public class Main {
    public static void main(String[] args) {
        // строим небоскребы и дома из глины и камня
        BuildingBuilder building1 = new BuildHouse(new Clay(), 200);
        building1.Build();
        BuildingBuilder building2 = new BuildHouse(new Stone(), 200);
        building2.Build();
        BuildingBuilder building3 = new BuildSkycraper(new Clay(), 200);
        building3.Build();
        BuildingBuilder building4 = new BuildSkycraper(new Stone(), 200);
        building4.Build();
    }
}

interface IMaterial {
    public void Prepare();
}

class Stone implements IMaterial {

    @Override
    public void Prepare() {
        System.out.println("Stones are prepared.");
    }
}

class Clay implements IMaterial {

    @Override
    public void Prepare() {
        System.out.println("Clay is prepared.");
    }
}

abstract class BuildingBuilder {
    protected IMaterial material;
    protected double area;

    BuildingBuilder(IMaterial material, double area) {
        this.material = material;
        this.area = area;
    }

    public abstract void Build();
}

class BuildHouse extends BuildingBuilder {
    BuildHouse(IMaterial material, double area) {
        super(material, area);
    }

    @Override
    public void Build() {
        material.Prepare();
        System.out.println("House is built." + '\n');
    }
}

class BuildSkycraper extends BuildingBuilder {
    BuildSkycraper(IMaterial material, double area) {
        super(material, area);
    }

    @Override
    public void Build() {
        material.Prepare();
        System.out.println("Skycraper is built." + '\n');
    }
}
