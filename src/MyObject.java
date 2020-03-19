public class MyObject implements Comparable{

    private int weight;
    private int value;

    public MyObject(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    public double ratio() {
        return (double) value / weight;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(Object o) {
        MyObject object = (MyObject) o;
        double comparator = this.ratio() - object.ratio();
        if (comparator < 0) return -1;
        if (comparator > 0) return 1;
        return 0;
    }

    @Override
    public String toString() {
        return "[" + weight + ", " + value +/* ", " + ratio() +*/ "]";
    }
}
