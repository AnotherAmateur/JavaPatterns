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
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Boots[] boots = new Boots[12];
        {
            boots[0] = new Boots(Author.Hire("Petya", "Ivanov"),
                    new ShoeUpper(ShoeUpperMat.PULaminate), SoleTypes.ABS);
            boots[1] = new Boots(Author.Hire("Petya", "Ivanov"),
                    new ShoeUpper(ShoeUpperMat.PULaminate), SoleTypes.Camp);
            boots[2] = new Boots(Author.Hire("Ivan", "Petrov"),
                    new ShoeUpper(ShoeUpperMat.PULaminate), SoleTypes.ABS);
            boots[3] = new Boots(Author.Hire("Petya", "Kit"),
                    new ShoeUpper(ShoeUpperMat.Fabric), SoleTypes.ABS);
            boots[4] = new Boots(Author.Hire("Petya", "Kit"),
                    new ShoeUpper(ShoeUpperMat.Fabric), SoleTypes.Cork);
            boots[5] = new Boots(Author.Hire("Petya", "Kit"),
                    new ShoeUpper(ShoeUpperMat.Fabric), SoleTypes.Camp);
            boots[6] = new Boots(Author.Hire("Nadya", "Petrova"),
                    new ShoeUpper(ShoeUpperMat.Fabric), SoleTypes.ABS);
            boots[7] = new Boots(Author.Hire("Masha", "Ivanova"),
                    new ShoeUpper(ShoeUpperMat.Fabric), SoleTypes.Camp);
            boots[8] = new Boots(Author.Hire("Masha", "Kit"),
                    new ShoeUpper(ShoeUpperMat.Leather), SoleTypes.Cork);
            boots[9] = new Boots(Author.Hire("Nadya", "Ivanova"),
                    new ShoeUpper(ShoeUpperMat.Leather), SoleTypes.Camp);
            boots[10] = new Boots(Author.Hire("Nadya", "Kit"),
                    new ShoeUpper(ShoeUpperMat.Leather), SoleTypes.Cork);
            boots[11] = new Boots(Author.Hire("Pasha", "Ivanov"),
                    new ShoeUpper(ShoeUpperMat.Leather), SoleTypes.ABS);
        }

        pojoToJson(boots);
        List<Boots> fromJSON = jsonToPojo();

        String[] author = new String[]{"Petya", "Ivanov"};
        System.out.println("Ботинки автора: " + String.join(" ", author));
        for (Boots boot : getBootsByAuthor(fromJSON, author[0], author[1], null)) {
            System.out.println(boot);
        }
        System.out.println();

        ShoeUpperMat upper = ShoeUpperMat.Fabric;
        System.out.println("Ботинки с материалом верха: " + upper);
        for (Boots boot : getBootsByUpperMaterial(fromJSON, upper)) {
            System.out.println(boot);
        }
        System.out.println();

        SoleTypes sole = SoleTypes.ABS;
        System.out.println("Ботинки с типом подошвы: " + sole);
        for (Boots boot : getBootsBySoleType(fromJSON, sole)) {
            System.out.println(boot);
        }
        System.out.println();

        System.out.println("Авторы сортированы по количеству созданных моделей");
        for (var str : getBootsSortedByAuthors(fromJSON)) {
            System.out.println(str);
        }


//        for (Boots object : jsonToPojo()) {
//            System.out.println(object.toString());
//        }
    }


    static List<Boots> getBootsByAuthor(List<Boots> fromJSON, String firstName, String lastName, String patronymic) {

        MyComparator comparator = (String str1, String str2) -> {
            if (str1 != null && str2 != null) {
                return str1.equals(str2);
            } else {
                return str1 == null && str2 == null;
            }
        };

        return fromJSON.stream().filter((boot) -> {
            return comparator.compare(boot.getAuthorInfo().getFirstName(), firstName)
                    && comparator.compare(boot.getAuthorInfo().getLastName(), lastName)
                    && comparator.compare(boot.getAuthorInfo().getPatronymic(), patronymic);
        }).toList();
    }

    static List<Boots> getBootsByUpperMaterial(List<Boots> fromJSON, ShoeUpperMat material) {
        return fromJSON.stream().filter((boot) -> {
            return boot.getShoeUpper().toString() == material.toString();
        }).toList();
    }

    static List<Boots> getBootsBySoleType(List<Boots> fromJSON, SoleTypes type) {
        return fromJSON.stream().filter((boot) -> {
            return boot.getSoleType().toString() == type.toString();
        }).toList();
    }

    static List<String> getBootsSortedByAuthors(List<Boots> fromJSON) {
        Map<String, List<Boots>> groupedMap = fromJSON.stream().
                collect(Collectors.groupingBy(boots ->
                        String.join(" ",
                                ((Boots) boots).getAuthorInfo().getFirstName(),
                                ((Boots) boots).getAuthorInfo().getLastName())));

        List<String> sortedList = groupedMap.entrySet().stream()
                .sorted(new Comparator<Map.Entry<String, List<Boots>>>() {
                    @Override
                    public int compare(Map.Entry<String, List<Boots>> o1, Map.Entry<String, List<Boots>> o2) {
                        return o2.getValue().size() - o1.getValue().size();
                    }
                })
                .map(entry -> String.join(": ", entry.getKey(), Integer.toString(entry.getValue().size()) + " piece(s)"))
                .collect(Collectors.toList());

        return sortedList;
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
            boots = objectMapper.readValue(file, new TypeReference<List<Boots>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return boots;
    }
}
