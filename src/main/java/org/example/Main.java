package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Random rand = new Random();

        // Задание 1А
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(10);
        }

        for (int i : array) {
            System.out.print(i + " ");
        }

        System.out.println();

        // Задание 1B

        List<Integer> list = new ArrayList<>();
        for (int j : array) {
            list.add(j);
        }
        System.out.println(list);

        // Задание 1C

        Collections.sort(list);
        System.out.println(list);

        // Задание 1D

        Collections.reverse(list);
        System.out.println(list);

        // Задание 1E

        Collections.shuffle(list);
        System.out.println(list);

        // Задание 1F

        for (int i = 1; i < list.size(); i++) {
            int j = i - 1;
            Collections.swap(list, i, j);

        }
        System.out.println(list);

        // Задание 1G

        for (int i = 0; i < list.size(); i++) {
           for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).equals(list.get(j))) {
                    list.remove(j);
               }
           }
       }
       System.out.println(list);

        // Задание 1H

        for (int i = 0; i < list.size(); i++) {
            int count = 0;
            for (int j = 0; j < list.size(); j++) {
                if (list.get(i).equals(list.get(j))) {
                    count++;
                }
            }
            if (count < 2) {
                list.remove(i);
                i--;
            }
        }
        System.out.println(list);

        // Задание 1I

        int[] listToArray = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            listToArray[i] = list.get(i);
        }
        System.out.println(Arrays.toString(listToArray));

        // Задание 1J

        for (int k : listToArray) {
            int count = 0;
            for (int j : listToArray) {
                if (k == j) {
                    count++;
                }
            }
            System.out.println(k + " - " + count);
        }

        System.out.println();

        // Задание 2

        PrimesGeneratorTest generatorTest = new PrimesGeneratorTest();
        generatorTest.test();

        System.out.println();

        // Задание 3

        List<Human> humans = new ArrayList<>();
        humans.add(new Human("Poli", "Sokolova", 20));
        humans.add(new Human("Sasha", "Makarenkova", 25));
        humans.add(new Human("Vero", "Berdnikova", 30));

        //Сортивка по имени
        Collections.sort(humans);
        for (Human h : humans) {
            System.out.println(h);
        }

        System.out.println();

        //Сортивка по фамилии
        humans.sort(new HumanComparatorByLastName());
        for (Human h : humans) {
            System.out.println(h.toString());
        }

        System.out.println();

        // Задание 3a

        HashSet<Human> h = new HashSet<Human>(humans);

        System.out.println("Реализация с HashSet: " + h);
        System.out.println();

        // Задание 3b

        LinkedHashSet<Human> l = new LinkedHashSet<Human>(humans);

        System.out.println("Реализация с LinkedHashSet: " + l);
        System.out.println();

        // Задание 3c

        TreeSet<Human> t = new TreeSet<Human>(humans);

        System.out.println("Реализация с TreeSet: " + t);
        System.out.println();

        // Задание 3d

        TreeSet<Human> treeSetByLastName = new TreeSet<Human>(new HumanComparatorByLastName());
        treeSetByLastName.addAll(humans);
        System.out.println("Реализация с TreeSet(сортированный): ");
        for (Human human : treeSetByLastName) {
            System.out.println(human);
        }
        System.out.println();

        // Задание 3e

        TreeSet<Human> treeSetByAge = new TreeSet<Human>(new Comparator<Human>() {
            @Override
            public int compare(Human human1, Human human2) {
                return Integer.compare(human1.age, human2.age);
            }
        });

        treeSetByAge.addAll(humans);
        System.out.println("Реализация с TreeSet(анонимный компаратор): ");
        for (Human human : treeSetByAge) {
            System.out.println(human);
        }

        System.out.println();


        // Задание 4

        Map<String, Integer> wordCounts = new HashMap<>();
        StringBuilder word = new StringBuilder();
        String text = "This is a test test".toLowerCase();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);


            if (Character.isLetterOrDigit(ch)) {
                word.append(ch);
            } else {

                if (!word.isEmpty()) {
                    String currentWord = word.toString();

                    if (wordCounts.containsKey(currentWord)) {
                        wordCounts.put(currentWord, wordCounts.get(currentWord) + 1);
                    } else {
                        wordCounts.put(currentWord, 1);
                    }

                    word.setLength(0);
                }
            }
        }

        if (!word.isEmpty()) {
            String currentWord = word.toString();

            if (wordCounts.containsKey(currentWord)) {
                wordCounts.put(currentWord, wordCounts.get(currentWord) + 1);
            } else {
                wordCounts.put(currentWord, 1);
            }
        }
        System.out.println(wordCounts);
        System.out.println();

        // Задание 5

        Map<Integer, String> originalMap = new HashMap<>();
        originalMap.put(1, "Один");
        originalMap.put(2, "Два");
        originalMap.put(3, "Три");

        Map<String, Integer> invertedMap = invertMap(originalMap);

        for (Map.Entry<String, Integer> entry : invertedMap.entrySet()) {
            System.out.println("Ключ: " + entry.getKey() + ", Значение: " + entry.getValue());
        }

    }

    public static <K, V> Map<V, K> invertMap(Map<K, V> originalMap) {

        Map<V, K> invertedMap = new HashMap<>();

        for (Map.Entry<K, V> entry : originalMap.entrySet()) {
            invertedMap.put(entry.getValue(), entry.getKey());
        }

        return invertedMap;
    }
}

class PrimesGenerator implements Iterator<Integer> {

    private final int count;
    private int generatedCount;
    private int current;

    public PrimesGenerator(int count) {
        this.count = count;
        this.generatedCount = 0;
        this.current = 1;
    }

    public static boolean isPrime(int n) {

        for (int i = 2; i < (n / 2) + 1 ; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean hasNext() {
        return generatedCount < count;
    }

    @Override
    public Integer next() {
        while (true) {
            current++;
            if (isPrime(current)) {
                generatedCount++;
                return current;
            }
        }
    }
}

class PrimesGeneratorTest {

    public void test() {
        PrimesGenerator generator = new PrimesGenerator(10);

        List<Integer> primes = new ArrayList<>();

        while (generator.hasNext()) {
            primes.add(generator.next());
        }

        System.out.println("Простые числа в прямом порядке:");
        for (int prime : primes) {
            System.out.print(prime + " ");
        }

        System.out.println("\nПростые числа в обратном порядке:");
        for (int i = primes.size() - 1; i >= 0; i--) {
            System.out.print(primes.get(i) + " ");
        }

        System.out.println();
    }
}

class Human implements Comparable<Human> {

    public String name;
    public String surname;
    public int age;

    Human(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    @Override
    public int compareTo(Human human) {
        return this.name.compareTo(human.name);
    }

    @Override
    public String toString() {
        return "Имя = " + name + ", Фамилия = " + surname + ", Возраст = " + age;
    }
}

class HumanComparatorByLastName implements Comparator<Human> {

    public int compare(Human human1, Human human2) {
        return human1.surname.compareTo(human2.surname);
    }
}