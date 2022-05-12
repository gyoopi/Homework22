import java.util.*;
import java.util.stream.*;
import enums.Enums.Sex;
import enums.Enums.Education;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        // 1. Определение количество несовершеннолетних
        long minorCount = persons.stream().
                filter(x -> x.getAge() >= 18).
                count();
        System.out.println("Количество несовершеннолетних: " + minorCount);

        // 2.Составление списка призывников
        List<String> futureArmy = persons.stream().
                filter(x -> x.getAge() >= 18).
                filter(x -> x.getAge() <= 27).
                map(Person::getFamily).
                collect(Collectors.toList());

        // 3. Составление списка потенциальных работников
        List workers = persons.stream().
                filter(x -> x.getAge() >= 18).
                sorted(Comparator.comparing(Person::getFamily)).
                collect(Collectors.toList());
    }
}
