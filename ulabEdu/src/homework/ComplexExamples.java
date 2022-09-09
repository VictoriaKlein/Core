package homework;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    /*Task1
    для этого вараинта:
        Duplicate filtered, grouped by name, sorted by name and id:

        Amelia:
        1 - Amelia (5)
        2 - Amelia (6)
        3 - Amelia (7)
        4 - Amelia (8)
        Emily:
        1 - Emily (3)
        Harry:
        1 - Harry (0)
        2 - Harry (1)
        3 - Harry (2)
        Jack:
        1 - Jack (4)
     */
    private static Map<String, List<Person>> filteredData = Arrays.stream(RAW_DATA)
            .filter(Objects::nonNull) //person shouldn't be null
            .filter(p -> Objects.nonNull(p.getName())) //people names shouldn't be null
            .distinct()
            .sorted(Comparator.comparing(Person::getId))
            .collect(Collectors.groupingBy(Person::getName));

    /*
    для второго варианта
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
    private static Map<String, Long> filteredData2 = Arrays.stream(RAW_DATA)
            .filter(Objects::nonNull)//person shouldn't be null
            .filter(p -> Objects.nonNull(p.getName()))//people names shouldn't be null
            .distinct()
            .sorted(Comparator.comparing(Person::getId))
            .collect(Collectors.groupingBy(Person::getName, Collectors.counting()));

    public List<Integer> sumOfNumbers() {
    /* Task2
       [3, 4, 2, 7], 10 -> [3, 7] - вывести пару менно в скобках, которые дают сумму - 10
    */
        int[] arr = new int[]{3, 4, 2, 7};
        int sum = 10;
        List<Integer> l = new ArrayList<>();
        IntStream.range(0, arr.length).forEach(i -> IntStream.range(0, arr.length).forEach(j -> {
            if (arr[i] + arr[j] == sum) l.add(arr[j]);
        }));
        System.out.println(l);
        return l;
    }

    ;

    public boolean fuzzySearch(String s1, String s2) {
     /*
        Task3
            Реализовать функцию нечеткого поиска
         */
        List<Character> sc = new ArrayList<>();
        List<Character> sl = new ArrayList<>();
        StringBuilder newS2 = new StringBuilder();

        if (s1 != null && s2 != null) {
            for (Character c : s1.toCharArray()) {
                sc.add(c);
            }
            for (Character c : s2.toCharArray()) {
                sl.add(c);
            }
        }

        //итеррируем по большей строке, проверяя содержит ли меньшая строка данный элемент
        // (используем в данном случае contain, чтобы избежать дублей во время иттерации по меньшей строке)
        sl.stream().forEach(s -> {
            if (sc.contains(s)) newS2.append(s);//создаем новую строку, соблюдая последователньость
            //   sc.remove(s); -> в случае если допускается большее количество одинаковых символов
            //   в большей строке, например, "cwheel" и "cwheeel" - true
        });
        return newS2.toString().equals(s1);
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
        //первый вариант
        filteredData.forEach((name, people) -> {
            System.out.println(name + ":");
            people.forEach(p -> System.out.println(p.getName() + "(" + p.getId() + ")"));
            System.out.println();
        });
        //второй вариант, где значение ключа определяется количеством записей с тем или иным именем (с учетом дубликатов)
        for (Map.Entry e : filteredData2.entrySet()) {
            System.out.println("Key: " + e.getKey() + "\n Value: " + e.getValue());
        }

        System.out.println("Task2");
        new ComplexExamples().sumOfNumbers();
        System.out.println("Task3");
        new ComplexExamples().fuzzySearch("wow", "wow"); //примеры из задания в тестах
    }
}
