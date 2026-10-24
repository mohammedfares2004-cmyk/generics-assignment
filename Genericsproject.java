package genericsproject;

class Box<T> {
    private T value;

    public Box(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public String toString() {
        return "Box[" + value + "]";
    }
}

class NumberBox<T extends Number> {
    private T value;

    public NumberBox(T value) {
        this.value = value;
    }

    public double square() {
        double d = value.doubleValue();
        return d * d;
    }
}

class Pair<K, V> {
    private K key;
    private V val;

    public Pair(K key, V val) {
        this.key = key;
        this.val = val;
    }

    public K getKey() { return key; }
    public V getVal() { return val; }
}

public class Genericsproject {

    static void printBox(Box<?> b) {
        System.out.println("Value: " + b.getValue());
    }

    public static void main(String[] args) {
        Box<String> b1 = new Box<String>("Java");
        Box<Integer> b2 = new Box<Integer>(10);
        System.out.println(b1);
        System.out.println(b2);
        int x = b2.getValue();
        System.out.println("x = " + x);

        NumberBox<Integer> n1 = new NumberBox<Integer>(5);
        NumberBox<Double> n2 = new NumberBox<Double>(2.5);
        System.out.println("5 squared = " + n1.square());
        System.out.println("2.5 squared = " + n2.square());

        Pair<String, Integer> p = new Pair<String, Integer>("Age", 20);
        System.out.println(p.getKey() + " = " + p.getVal());

        printBox(b1);
        printBox(b2);
    }
}