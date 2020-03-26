import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SolverBackTracking {

    private int bagCapacity;
    private ArrayList<MyObject> objectArrayList;
    private ArrayList<MyObject> betterSolution;

    public SolverBackTracking(String filename) {
        this.betterSolution = new ArrayList<>();
        this.objectArrayList = new ArrayList<>();
        readFile(filename);
    }

    public ArrayList<MyObject> solve() {
        backtrack(new ArrayList<>());
        return betterSolution;
    }

    public void backtrack(List<MyObject> a) {
//        System.out.println(a);
        if (isSolution(a)) {
            processSolution(a);
        }
        ArrayList<MyObject> candidates = constructCandidates(a, objectArrayList);
        for (MyObject candidate : candidates) {
            if (getTotalWeight(a) + candidate.getWeight() <= bagCapacity) {
                a.add(a.size(), candidate);
                backtrack(a);
                a.remove(a.size()-1);
            }
        }
    }

    private boolean isSolution(List<MyObject> a) {
        return getTotalWeight(a) <= bagCapacity;
    }

    private void processSolution(List<MyObject> a) {
        if (getTotalValue(a) > getTotalValue(this.betterSolution)) {
            betterSolution.clear();
            betterSolution.addAll(a);
        }
    }

    public ArrayList<MyObject> constructCandidates(List<MyObject> a, ArrayList<MyObject> input) {
        ArrayList<MyObject> list = new ArrayList<>();
        int start = 0;
        if (!a.isEmpty())
            start = input.indexOf(a.get(a.size() - 1)) + 1;
        for (int i = start; i < input.size(); i++) {
            list.add(input.get(i));
        }
        return list;
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

    public int getBagCapacity() {
        return bagCapacity;
    }

    public ArrayList<MyObject> getObjectArrayList() {
        return objectArrayList;
    }

    public ArrayList<MyObject> getBetterSolution() {
        return betterSolution;
    }
}