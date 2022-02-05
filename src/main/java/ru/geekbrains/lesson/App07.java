package ru.geekbrains.lesson;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class App07 {
    // поговорим о wildcard

    public static void main(String[] args) {
        // вспомним пример из начала лекции
        final App07 app07 = new App07();
        final List<Snake> snakes = Arrays.asList(
                new Snake("Каа", 5),
                new Snake("Нагайна", 5)
        );
//        app07.feedAnimals(snakes); // ошибка, т.к. Collection<Animal> != Collection<Snake>
        // что делать в таких случаях?
        // переписать метод...
        app07.feedAnimals(snakes); // теперь все работает
                                   // ? называется wildcard
        // можно создать класс, параметризованный только wildcard
        final Zoo<?> zoo = new Zoo<>();
        // равносильно созданию без параметризации, но безопаснее
//        zoo.encage(new Leon("Mufasa", 10)); // не работает
        // работает, но не безопасно, т.к. можно положить любой объект
        final Zoo zoo1 = new Zoo();
        zoo1.encage(new Lion("Mufasa", 10));

        // можно и сразу создать класс, параметризованный с wildcard
        final Zoo<? extends Animal> extendsZoo = new Zoo<>();
        // что можно положить в Zoo<? extends Animal>?
//        extendsZoo.encage(new Elephant("",1)); // ошибка
//        extendsZoo.encage(new Object()) // тоже ошибка
        extendsZoo.encage(null); // кроме null ничего положить нельзя
        // здесь надо помнить, что wildcard <? extends Animal> это не тоже самое,
        // что <T extends Animal>. Определение переменной как
        // final Zoo<? extends Animal> extendsZoo означает, что мы можем приравнять ее
        final Zoo<? extends Animal> extendsZoo1 = new Zoo<Elephant>();
        final Zoo<? extends Animal> extendsZoo2 = new Zoo<Lion>();
        final Zoo<? extends Animal> extendsZoo3 = new Zoo<Snake>();
        final Zoo<? extends Animal> extendsZoo4 = new Zoo<Animal>();
//        final Zoo<? extends Animal> extendsZoo5 = new Zoo<Object>(); // а вот так нельзя!

//        extendsZoo.encage(new Elephant("Джимбо", 3)); // не работает, т.к. небезопасно
        // но это будет работать, если создать класс, ограниченный сверху
        Zoo<? super Animal> superZoo = new Zoo<>();
        // А что можно положить в superZoo?
//        superZoo.encage(new Object()); // не-а, ошибка
        superZoo.encage(new Elephant("Джимбо", 3)); // все работает
        superZoo.encage(new Animal("", 1) {
            @Override
            public String getName() {
                return super.getName();
            }

            @Override
            public int getAge() {
                return super.getAge();
            }

            @Override
            public void feed() {
                super.feed();
            }
        });
        // Опять-таки определение Zoo<? super Animal> superZoo означает, что superZoo
        // можно инициализировать вот так
        Zoo<? super Animal> superZoo1 = new Zoo<Animal>(); // можно
        Zoo<? super Animal> superZoo2 = new Zoo<Object>(); // можно
//        Zoo<? super Animal> superZoo3 = new Zoo<Lion>(); // а вот так нельзя!
    }

    private void feedAnimals(Collection<? extends Animal> animals) {
        animals.forEach(Animal::feed);
    }

}
