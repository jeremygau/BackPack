import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Solver {

    private int bagCapacity;
    private ArrayList<MyObject> objectArrayList;
    private ArrayList<MyObject> betterSolution;

    public Solver(String filename) {
        this.betterSolution = new ArrayList<>();
        this.objectArrayList = new ArrayList<>();
        readFile(filename);
    }

    public ArrayList<MyObject> solve() {
        backtrack(new ArrayList<MyObject>(), 0, objectArrayList);
        return betterSolution;
    }

    public void backtrack(ArrayList<MyObject> a, int k, ArrayList<MyObject> input) {
//        System.out.println(a);
        if (isSolution(a, k, input)) {
            processSolution(a, k, input);
        }
        if (k >= input.size()) {
            return;
        }
        ArrayList<MyObject> candidates = constructCandidates(a, k, input);
        for (int i = 0; i < candidates.size(); i ++) {
            a.add(k, candidates.get(i));
            if (getTotalWeight(a) <= bagCapacity)
                backtrack(a, k + 1, input);
            a.remove(k);
        }
    }

    private boolean isSolution(ArrayList<MyObject> a, int k, ArrayList<MyObject> input) {
        return getTotalWeight(a) <= bagCapacity;
    }

    private void processSolution(ArrayList<MyObject> a, int k, ArrayList<MyObject> input) {
        if (getTotalValue(a) > getTotalValue(this.betterSolution)) {
            betterSolution.clear();
            betterSolution.addAll(a);
        }
    }

    public ArrayList<MyObject> constructCandidates(ArrayList<MyObject> a, int k, ArrayList<MyObject> input) {
        ArrayList<MyObject> list = new ArrayList<>();
        int start = 0;
        if (!a.isEmpty())
            start = input.indexOf(a.get(a.size()-1))+1;
        for (int i = start; i < input.size(); i++) {
            list.add(input.get(i));
        }
        return list;
    }

    public int getTotalWeight(ArrayList<MyObject> list) {
        int weight = 0;
        for (MyObject o : list) {
            weight += o.getWeight();
        }
        return weight;
    }

    public int getTotalValue(ArrayList<MyObject> list) {
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
