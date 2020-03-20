import java.util.spi.AbstractResourceBundleProvider;

public class Main {
    public static void main(String[] args) {
//        double moyenne = 0;
//        int repetition  = 1;
//        for (int i = 0; i < repetition; i++) {
//
//        }
//        moyenne = moyenne / repetition;
//        System.out.println(moyenne + " ms");
        String filename = "./files/sac1_2.txt";
        solveWithArray(filename);
        solveWithStack(filename);
    }

    public static void solveWithArray(String filename) {
        long before = System.currentTimeMillis();
        Solver solver = new Solver(filename);
//            System.out.println(solver.getObjectArrayList());
        System.out.println(solver.solve());
        System.out.println("bag capacity = " + solver.getBagCapacity());
        System.out.println("better solution weight = " + solver.getTotalWeight(solver.getBetterSolution()));
        System.out.println("better solution value = " + solver.getTotalValue(solver.getBetterSolution()));
        System.out.println("time = " + (System.currentTimeMillis() - before) + " ms");
    }

    public static void solveWithStack(String filename) {
        long before = System.currentTimeMillis();
        SolverStack solverStack = new SolverStack(filename);
//            System.out.println(solverStack.getObjectArrayList());
        System.out.println(solverStack.solve());
        System.out.println("bag capacity = " + solverStack.getBagCapacity());
        System.out.println("better solution weight = " + solverStack.getTotalWeight(solverStack.getBetterSolution()));
        System.out.println("better solution value = " + solverStack.getTotalValue(solverStack.getBetterSolution()));
        System.out.println("time = " + (System.currentTimeMillis() - before) + " ms");
    }
}
