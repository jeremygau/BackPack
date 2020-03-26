import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Solver {

    protected int bagCapacity;
    protected ArrayList<MyObject> objectArrayList;
    protected ArrayList<MyObject> betterSolution;

    public Solver(String filename) {
        this.betterSolution = new ArrayList<>();
        this.objectArrayList = new ArrayList<>();
        readFile(filename);
    }

    public abstract ArrayList<MyObject> solve();

    public void readFile(String filename) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            String line = bufferedReader.readLine();
            this.bagCapacity = Integer.parseInt(line);
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(" ");
                objectArrayList.add(new MyObject(Integer.parseInt(values[0]), Integer.parseInt(values[1])));
            }
            Collections.sort(objectArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getTotalWeight(List<MyObject> list) {
        int weight = 0;
        for (MyObject o : list) {
            weight += o.getWeight();
        }
        return weight;
    }

    public int getTotalValue(List<MyObject> list) {
        int value = 0;
        for (MyObject o : list) {
            value += o.getValue();
        }
        return value;
    }

    public ArrayList<MyObject> getBetterSolution() {
        return betterSolution;
    }
}
