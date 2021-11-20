package ru.geekbrains.lesson;


public class App04 {
    // параметризованные методы
    // Эти методы очень удобны, но не следует их путать с методами
    // в дженериках (см класс Zoo)
    // Чтобы создать параметризованный метод, нужно указать псевдоним типа в
    // угловых скобках
//    public <T> T create() {
//    }
    //

    public static void main(String[] args) {
        // Рассмотрим на примере нашего зоопарка - вернуться к App02
        Zoo<Elephant> elephantZoo = Zoo.create(); // вместо <A> будет подставлен Elephant

        // рассмотрим еще один пример:
        final boolean isSimbaEncage = Zoo.encage(Zoo.create(), new Lion("Simba", 1));

        // не всегда работает правильно (компилятор не всегда может понять тип) -
        // нужно указывать явно
        final Lion simba = Zoo.<Lion>create().release(new Lion("Simba", 1));

    }
    // И еще раз как создать параметризованный метод
    public <T, P1, P2> T create(P1 arg1, P2 arg2) {
        return null; // new T() не сработает
    }


}

