import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class SolverDynamic extends Solver {

    private int[][] dynamicTable;
    private int[] valuesTable;
    private int[] weightTable;
    private int objectNumber;

    public SolverDynamic(String filename) {
        super(filename);
        this.valuesTable = constructValueTable();
        this.weightTable = constructWeightTable();
        this.objectNumber = objectArrayList.size();
        this.dynamicTable = computeSubProblems(valuesTable, weightTable, objectNumber, bagCapacity);
    }

    public ArrayList<MyObject> solve() {
        betterSolution = constructSolution(objectNumber-1, bagCapacity, new ArrayList<>());
        return betterSolution;
    }

    public int[] constructValueTable() {
        return constructTable(MyObject::getValue);
    }

    public int[] constructWeightTable() {
        return constructTable(MyObject::getWeight);
    }

    public int[] constructTable(Function<MyObject, Integer> function) {
        int[] t = new int[objectArrayList.size()];
        for (int i = 0; i < objectArrayList.size(); i++) {
            MyObject o = objectArrayList.get(i);
            t[i] = function.apply(o);
        }
        return t;
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
}
