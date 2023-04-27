// 5. Необходимо создать класс Ботинки с возможностью хранить информацию об обуви с указанием ID модели,
// авторе модели (Фамилия, Имя, Отчество, дата приема на работу, дата увольнения, если уволен),
// типе подошвы, материале верха.
// Конструкторы, сеттеры и геттеры всех полей наполняются по смыслу и внутренним предпочтениям.
// Создать дополнительные классы: Материал верха.
// Заполнить информацию о 12 ботинках.
// Произвести выгрузку в JSON файл и обратное считывание.
// Результат считывания с JSON файла вывести на экран.

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Boots[] boots = new Boots[12];
        boots[0] = new Boots(Author.Hire("Petya", "Ivanov"),
                new ShoeUpper(ShoeUpperMat.PULaminate), SoleTypes.ABS);
        boots[1] = new Boots(Author.Hire("Petya", "Petrov"),
                new ShoeUpper(ShoeUpperMat.PULaminate), SoleTypes.ABS);
        boots[2] = new Boots(Author.Hire("Ivan", "Petrov"),
                new ShoeUpper(ShoeUpperMat.PULaminate), SoleTypes.ABS);
        boots[3] = new Boots(Author.Hire("Petya", "Kit"),
                new ShoeUpper(ShoeUpperMat.PULaminate), SoleTypes.ABS);
        boots[4] = new Boots(Author.Hire("Ivan", "Kit"),
                new ShoeUpper(ShoeUpperMat.Fabric), SoleTypes.Camp);
        boots[5] = new Boots(Author.Hire("Masha", "Petrova"),
                new ShoeUpper(ShoeUpperMat.Fabric), SoleTypes.Camp);
        boots[6] = new Boots(Author.Hire("Nadya", "Petrova"),
                new ShoeUpper(ShoeUpperMat.Fabric), SoleTypes.Camp);
        boots[7] = new Boots(Author.Hire("Masha", "Ivanova"),
                new ShoeUpper(ShoeUpperMat.Fabric), SoleTypes.Camp);
        boots[8] = new Boots(Author.Hire("Masha", "Kit"),
                new ShoeUpper(ShoeUpperMat.Leather), SoleTypes.Cork);
        boots[9] = new Boots(Author.Hire("Nadya", "Ivanova"),
                new ShoeUpper(ShoeUpperMat.Leather), SoleTypes.Cork);
        boots[10] = new Boots(Author.Hire("Nadya", "Kit"),
                new ShoeUpper(ShoeUpperMat.Leather), SoleTypes.Cork);
        boots[11] = new Boots(Author.Hire("Pasha", "Ivanov"),
                new ShoeUpper(ShoeUpperMat.Leather), SoleTypes.Cork);


        pojoToJson(boots);

        for (Boots object : jsonToPojo()) {
            System.out.println(object.toString());
        }
    }

    static void pojoToJson(Boots[] boots) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());

        try {
            writer.writeValue(Paths.get("Boots.json").toFile(), boots);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static public List<Boots> jsonToPojo() {
        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
        File file = new File("Boots.json");
        List<Boots> boots;
        try {
            boots = objectMapper.readValue(file, new TypeReference<List<Boots>>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return boots;
    }
}
