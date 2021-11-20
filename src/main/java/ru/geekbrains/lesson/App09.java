package ru.geekbrains.lesson;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Сравнение объектов
public class App09 {
    public static void main(String[] args) {
        // Нам часто приходится сравнивать объекты между собой (например, сортировка объектов)
        // В случае с числовыми типами все просто
        Integer i1 = 1, i2 = 2;
        if (i1 >= i2) {
            System.out.println("i1 больше или равен i2");
        } else {
            System.out.println("i2 больше или равен i1");
        }
        // А вот со строками такой фокус не пройдет
        String a = "abc";
        String b = "bcd";
//        if (a > b) {} // error!
        // К счастью, разработчики Java позаботились об этом и добавили метод для сравнения строк
        if (a.compareTo(b) > 0) {
            System.out.println("a больше b");
        } else if (a.compareTo(b) < 0) {
            System.out.println("b больше a");
        } else {
            System.out.println("a равно b");
        }
        // Заглямем в этот метод...
        // ...
        // Мы увидим, что этот метод унаследован от метода в интерфейса Comparable
        // Если мы посмотрим, какие еще классы унаследованы от этого интерфейса, то
        // обнаружим там числовые типы, даты, и много чего еще

        // Теперь мы можем создать классы, которые можно сравнивать между собой
        // .....

        // В классе Collections есть метод, который позволяет сортировать коллекции
        final List<Dog> dogs = Arrays.asList(new Dog(), new Dog());
        Collections.sort(dogs);

        // А что делать, если нам достался класс, который не наследуется
        // от интерфейса Comparable, или же мы хотим отсортировать как-то
        // по особому?
        // Для этого можно создать специальный класс-компаратор
        class MyComparator implements Comparator<Cat> {
            @Override
            public int compare(Cat o1, Cat o2) {
                return o1.getWeight() - o2.getWeight(); // определим свой способ сравнения
            }
        }

        final MyComparator comparator = new MyComparator();
        final List<Cat> cats = Arrays.asList(new Cat(), new Cat());
        Collections.sort(cats, comparator);

        // можно создать компаратор прямо на месте, через анонимный класс
        Collections.sort(cats, new Comparator<Cat>() {
            @Override
            public int compare(Cat o1, Cat o2) {
                return o1.getWeight() - o2.getWeight();
            }
        });
        // и преобразовать его в лямбду...
        Collections.sort(cats, (o1, o2) -> o1.getWeight() - o2.getWeight());
        // или поступить вот так
        Collections.sort(cats, Comparator.comparingInt(Cat::getWeight)
                .reversed()
                .thenComparingInt(Cat::getAge));

    }
}

class Cat implements Comparable { // наследуясь от интерфейса мы должны переопределить его методы
    private int weight;
    private int age;

    @Override
    public int compareTo(Object o) {
        if (((Cat) o).getAge() > this.getAge()) { // приведение типов не очень удобно
            return -1;
        } else if (((Cat) o).getAge() < this.getAge()) {
            return 1;
        }
        if (((Cat) o).getWeight() > this.getWeight()) {
            return -1;
        } else if (((Cat) o).getWeight() < this.getWeight()) {
            return 1;
        }
        return 0;
    }

    public int getWeight() {
        return weight;
    }

    public int getAge() {
        return age;
    }
}

class Dog implements Comparable<Dog> { // лучше параметризовать интерфейс Comparable

    @Override
    public int compareTo(Dog o) { // теперь этот метод принимает Dog и преобразование типов не нужно
        return 0;
    }
}