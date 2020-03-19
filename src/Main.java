import java.util.spi.AbstractResourceBundleProvider;

public class Main {
    public static void main(String[] args) {

        long before = System.currentTimeMillis();

        Solver solver = new Solver("./files/sac-1.txt");
        System.out.println(solver.getObjectArrayList());
        System.out.println(solver.solve());
        System.out.println("bag capacity = " + solver.getBagCapacity());
        System.out.println("better solution weight = " + solver.getTotalWeight(solver.getBetterSolution()));
        System.out.println("better solution value = " + solver.getTotalValue(solver.getBetterSolution()));

        System.out.println("time = " + (System.currentTimeMillis() - before) + " ms");
    }
}
