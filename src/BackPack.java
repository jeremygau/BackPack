import java.util.HashSet;
import java.util.Set;

public class BackPack {

    private int capacity;
    private Set<Object> content;

    public BackPack(int capacity) {
        this.capacity = capacity;
        this.content = new HashSet<>();
    }
}
