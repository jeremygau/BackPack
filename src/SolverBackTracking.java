import java.util.ArrayList;
import java.util.List;

public class SolverBackTracking extends Solver {

    public SolverBackTracking(String filename) {
        super(filename);
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
                a.remove(a.size() - 1);
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
}