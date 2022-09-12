package homework;

import java.util.*;
import java.util.stream.Collectors;

public class ComplexExamples {

    static class Person {
        final int id;

        final String name;


        Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person person)) return false;
            return getId() == person.getId() && getName().equals(person.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId(), getName());
        }
    }


    private static Person[] RAW_DATA = new Person[]{
            new Person(0, "Harry"),
            new Person(0, "Harry"), // дубликат
            new Person(1, "Harry"), // тёзка
            new Person(2, "Harry"),
            new Person(3, "Emily"),
            new Person(4, "Jack"),
            new Person(4, "Jack"),
            new Person(5, "Amelia"),
            new Person(5, "Amelia"),
            new Person(6, "Amelia"),
            new Person(7, "Amelia"),
            new Person(8, "Amelia"),
            null
    };

        /*  Raw data:

        0 - Harry
        0 - Harry
        1 - Harry
        2 - Harry
        3 - Emily
        4 - Jack
        4 - Jack
        5 - Amelia
        5 - Amelia
        6 - Amelia
        7 - Amelia
        8 - Amelia

        **************************************************
       */

    public Map<String,Long> filterData (){            /*
              Убрать дубликаты, отсортировать по идентификатору, сгруппировать по имени
              Что должно получиться
                  Key:Amelia
                  Value:4
                  Key: Emily
                  Value:1
                  Key: Harry
                  Value:3
                  Key: Jack
                  Value:1
           */

        Map<String, Long> filteredData = Arrays.stream(RAW_DATA)
                .filter(Objects::nonNull)//person shouldn't be null
                .filter(p -> Objects.nonNull(p.getName()))//people names shouldn't be null
                .distinct()
                .sorted(Comparator.comparing(Person::getId))
                .collect(Collectors.groupingBy(Person::getName, Collectors.counting()));

        for (Map.Entry e : filteredData.entrySet()) {
            System.out.println("Key: " + e.getKey() + "\n Value: " + e.getValue());
        }
        return filteredData;
    }

    public int[] sumOfNumbers() {
    /* Task2
       [3, 4, 2, 7], 10 -> [3, 7] - вывести пару менно в скобках, которые дают сумму - 10
    */
        int[] arr = new int[]{3,4,2,7};
        int sum = 10;
        int[] n = Arrays.stream(arr).filter(i->Arrays.stream(arr).anyMatch(j->i+j==sum)).limit(2).toArray();
        System.out.println(Arrays.toString(n));
        return n;
    }


    public boolean fuzzySearch(String s1, String s2) {
     /*
        Task3
            Реализовать функцию нечеткого поиска
         */

        boolean isContain = s2.chars()
                .mapToObj(s->Character.valueOf((char) s))
                .filter(s-> s1.contains(Character.valueOf(s).toString()))
                .map(Object::toString)
                .collect(Collectors.joining()).contains(s1);
        return isContain;
    }

    public static void main(String[] args) {
        System.out.println("Raw data:");
        System.out.println();


        for (Person person : RAW_DATA) {
            if (person != null) {
                System.out.println(person.id + " - " + person.name);
            }
        }

        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Duplicate filtered, grouped by name, sorted by name and id:");
        System.out.println("Task1");
        new ComplexExamples().filterData();
        System.out.println("Task2");
        new ComplexExamples().sumOfNumbers();
        System.out.println("Task3");
        new ComplexExamples().fuzzySearch("woow", "wow!"); //примеры из задания в тестах
    }
}
