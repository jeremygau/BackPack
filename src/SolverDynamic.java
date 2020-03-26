import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SolverDynamic {

    private int bagCapacity;
    private ArrayList<MyObject> objectArrayList;
    private ArrayList<MyObject> betterSolution;
    private int[][] dynamicTable;
    private int[] valuesTable;
    private int[] weightTable;
    private int objectNumber;

    public SolverDynamic(String filename) {
        this.betterSolution = new ArrayList<>();
        this.objectArrayList = new ArrayList<>();
        readFile(filename);
        this.valuesTable = constructValueTable();
        this.weightTable = constructWeightTable();
        this.objectNumber = objectArrayList.size();
        this.dynamicTable = computeSubProblems(valuesTable, weightTable, objectNumber, bagCapacity);
    }

    public void solve() {
        betterSolution = constructSolution(objectNumber-1, bagCapacity, new ArrayList<>());
    }

    public int[] constructValueTable() {
        int[] V = new int[objectArrayList.size()];
        for (int i = 0; i < objectArrayList.size(); i++) {
            MyObject o = objectArrayList.get(i);
            V[i] = o.getValue();
        }
        return V;
    }

    public int[] constructWeightTable() {
        int[] P = new int[objectArrayList.size()];
        for (int i = 0; i < objectArrayList.size(); i++) {
            MyObject o = objectArrayList.get(i);
            P[i] = o.getWeight();
        }
        return P;
    }

    public int[][] computeSubProblems(int[] V, int[] P, int n, int Pmax) {
        int [][] A = new int[n][Pmax+1];
        for (int p = 0; p <= Pmax; p++) {
            if (P[0] > p) {
                A[0][p] = 0;
            }
            else {
                A[0][p] = V[0];
            }
        }
        for (int i = 1; i < n; i++) {
            for (int p = 0; p <= Pmax; p++) {
                if (P[i] > p) {
                    A[i][p] = A[i - 1][p];
                }
                else {
                    A[i][p] = Math.max(A[i - 1][p], A[i - 1][p - P[i]] + V[i]);
                }
            }
        }
        return A;
    }

    private ArrayList<MyObject> constructSolution(int i, int p, ArrayList<MyObject> currentConfiguration) {
        if (i == 0) {
            if (weightTable[i] <= p) {
                currentConfiguration.add(objectArrayList.get(i));
            }
            return currentConfiguration;
        }
        if (dynamicTable[i - 1][p] == dynamicTable[i][p]) return constructSolution(i - 1,  p, currentConfiguration);
        currentConfiguration.add(objectArrayList.get(i));
        return constructSolution(i - 1 , p - objectArrayList.get(i).getWeight(), currentConfiguration);
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
}
