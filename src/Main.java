import java.util.spi.AbstractResourceBundleProvider;

public class Main {
    public static void main(String[] args) {
        double moyenne = 0;
        int repetition  = 100;
        for (int i = 0; i < repetition; i++) {

            long before = System.currentTimeMillis();

            Solver solver = new Solver("./files/sac1_2.txt");
//            System.out.println(solver.getObjectArrayList());
            System.out.println(solver.solve());
//            System.out.println("bag capacity = " + solver.getBagCapacity());
//            System.out.println("better solution weight = " + solver.getTotalWeight(solver.getBetterSolution()));
//            System.out.println("better solution value = " + solver.getTotalValue(solver.getBetterSolution()));

//            System.out.println("time = " + (System.currentTimeMillis() - before) + " ms");
            moyenne += System.currentTimeMillis() - before;
        }
        moyenne = moyenne / repetition;
        System.out.println(moyenne + " ms");
    }
}
