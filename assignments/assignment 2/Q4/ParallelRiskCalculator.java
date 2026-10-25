import java.util.concurrent.RecursiveTask;

public class ParallelRiskCalculator extends RecursiveTask<Double> {
    private static final int THRESHOLD = 1000;
    private final double[] assets;
    private final int lo, hi;

    public ParallelRiskCalculator(double[] assets, int lo, int hi) {
        this.assets = assets;
        this.lo = lo;
        this.hi = hi;
    }

    @Override
    protected Double compute() {
        int length = hi - lo;
        if (length <= THRESHOLD) {
            double sum = 0.0;
            for (int i = lo; i < hi; i++) sum += assets[i];
            return sum;
        } else {
            int mid = (lo + hi) / 2;
            ParallelRiskCalculator left  = new ParallelRiskCalculator(assets, lo, mid);
            ParallelRiskCalculator right = new ParallelRiskCalculator(assets, mid, hi);
            left.fork();
            right.fork();
            return left.join() + right.join();
        }
    }
}
