 package genericsdemo2;

import java.util.ArrayList;
import java.util.Date;

public class GenericsDemo {

    interface Container<T> {
        T getItem();
    }

    static class Box<T> implements Container<T> {
        protected T item;

        public Box(T item) { this.item = item; }
        public void set(T item) { this.item = item; }
        public T getItem() { return item; }
        public String toString() { return "Box[" + item + "]"; }
    }

    static class NumberBox<T extends Number> {
        private final T item;

        public NumberBox(T item) { this.item = item; }
        public double square() { return item.doubleValue() * item.doubleValue(); }
        public String toString() { return "NumberBox[" + item + "]"; }
    }

    static class LabeledBox<T extends Number> extends Box<T> {
        private final String label;

        public LabeledBox(T item, String label) {
            super(item);
            this.label = label;
        }

        public String getLabel() { return label; }
        public double square() { return item.doubleValue() * item.doubleValue(); }
    }

    static class TextBox extends Box<String> {
        public TextBox(String text) { super(text); }
        public int length() { return item.length(); }
    }

    static class Animal {
        public String sound() { return "some sound"; }
    }

    static class Cage<T> extends Animal {
        private final T occupant;

        public Cage(T occupant) { this.occupant = occupant; }
        public T getOccupant() { return occupant; }
    }

    static class Pair<T, S> {
        private final T first;
        private final S second;

        public Pair(T first, S second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() { return first; }
        public S getSecond() { return second; }
    }

    static class Range<A extends Number, B extends Number> {
        private final A low;
        private final B high;

        public Range(A low, B high) {
            this.low = low;
            this.high = high;
        }

        public double length() { return high.doubleValue() - low.doubleValue(); }
    }

    static void showAny(Box<?> box) {
        Object value = (Object) box.getItem();
        System.out.println("showAny -> " + value);
    }

    static double toDouble(Box<? extends Number> box) {
        Number n = box.getItem();
        return n.doubleValue();
    }

    static void addHundred(ArrayList<? super Integer> list) {
        list.add(100);
    }

    static double strict(Box<Number> box) {
        return box.getItem().doubleValue();
    }

    static <T extends Number> double addSquares(NumberBox<T> a, NumberBox<T> b) {
        return a.square() + b.square();
    }

    static <T extends Number> NumberBox<T> bigger(NumberBox<T> a, NumberBox<T> b) {
        return a.square() >= b.square() ? a : b;
    }

    static void title(String text) {
        System.out.println("\n=== " + text + " ===");
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void main(String[] args) {

        title("ArrayList");
        ArrayList<String> names = new ArrayList<String>();
        names.add("Java is fun");
        System.out.println(names.get(0));

        title("Generic class");
        Box<String> text = new Box<String>("Hello");
        Box<Integer> integer = new Box<Integer>(7);
        Box<Number> number = new Box<Number>(2.5);
        System.out.println(text + " " + integer + " " + number);

        title("Autoboxing and unboxing");
        Integer boxed = 35;
        int plain = boxed;
        Box<Double> dbl = new Box<Double>(2.5);
        double value = dbl.getItem();
        System.out.println(boxed + " | " + plain + " | " + value);

        title("Raw types");
        Box raw = new Box("Sara");
        String name = (String) raw.getItem();
        raw.set(26);
        int age = (Integer) raw.getItem();
        System.out.println(name + " | " + age);

        title("Constrained class");
        NumberBox<Integer> a = new NumberBox<Integer>(3);
 NumberBox<Integer> b = new NumberBox<Integer>(4);
        System.out.println(a + " square = " + a.square());

        title("Wildcards");
        showAny(text);
        showAny(integer);
        System.out.println("toDouble = " + toDouble(integer));
        System.out.println("strict   = " + strict(number));
        ArrayList<Number> list = new ArrayList<Number>();
        addHundred(list);
        System.out.println("super    = " + list);

        title("Generic methods");
        System.out.println("addSquares = " + addSquares(a, b));
        System.out.println("bigger     = " + bigger(a, b));

        title("Inheritance");
        LabeledBox<Integer> labeled = new LabeledBox<Integer>(6, "Six");
        System.out.println(labeled.getLabel() + " square = " + labeled.square());
        TextBox textBox = new TextBox("Generics");
        System.out.println(textBox + " length = " + textBox.length());
        Cage<String> cage = new Cage<String>("Parrot");
        System.out.println(cage.getOccupant() + " makes " + cage.sound());

        title("Multiple type parameters");
        Pair<String, Date> pair = new Pair<String, Date>("Joe", new Date());
        System.out.println(pair.getFirst() + " | " + pair.getSecond());
        Range<Integer, Double> range = new Range<Integer, Double>(2, 9.5);
        System.out.println("length = " + range.length());

        title("Generic interface");
        Container<String> container = new Box<String>("Via interface");
        System.out.println(container.getItem());

        title("Erasure");
        System.out.println(text.getClass() == integer.getClass());
    }
}