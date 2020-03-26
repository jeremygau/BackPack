public class Main {
    public static void main(String[] args) {
        String filename = "./files/sac1.txt";
        solveWithBackTrack(filename);
        solveWithDynamic(filename);
    }

    public static void solveWithBackTrack(String filename) {
        long before = System.currentTimeMillis();
        SolverBackTracking solver = new SolverBackTracking(filename);
//            System.out.println(solver.getObjectArrayList());
        System.out.println(solver.solve());
//        System.out.println("bag capacity = " + solver.getBagCapacity());
        System.out.println("better solution weight = " + solver.getTotalWeight(solver.getBetterSolution()));
        System.out.println("better solution value = " + solver.getTotalValue(solver.getBetterSolution()));
        System.out.println("time = " + (System.currentTimeMillis() - before) + " ms");
    }

    public static void solveWithDynamic(String filename) {
        long before = System.currentTimeMillis();
        SolverDynamic solver = new SolverDynamic(filename);
//        System.out.println(solver.getObjectArrayList());
        System.out.println(solver.solve());
//        System.out.println("bag capacity = " + solver.getBagCapacity());
        System.out.println("better solution weight = " + solver.getTotalWeight(solver.getBetterSolution()));
        System.out.println("better solution value = " + solver.getTotalValue(solver.getBetterSolution()));
        System.out.println("time = " + (System.currentTimeMillis() - before) + " ms");
    }

    public static void printTable(int[][] A) {
        StringBuilder string = new StringBuilder();
        for (int[] ints : A) {
            string.append("[");
            for (int j = 0; j < A[0].length; j++) {
                string.append(ints[j]).append(",");
            }
            string.deleteCharAt(string.length() - 1);
            string.append("]\n");
        }
        System.out.println(string);
    }
}
