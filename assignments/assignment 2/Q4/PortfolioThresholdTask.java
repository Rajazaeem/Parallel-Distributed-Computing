import java.util.concurrent.RecursiveAction;

public class PortfolioThresholdTask extends RecursiveAction {
    private static final int THRESHOLD = 1000;
    private final double[] assets;
    private final int lo, hi;
    private final double riskCap;

    public PortfolioThresholdTask(double[] assets, int lo, int hi, double riskCap) {
        this.assets = assets;
        this.lo = lo;
        this.hi = hi;
        this.riskCap = riskCap;
    }

    @Override
    protected void compute() {
        int length = hi - lo;

        if (length <= THRESHOLD) {
            for (int i = lo; i < hi; i++) {
                if (assets[i] > riskCap) {
                    assets[i] *= 0.95;
                }
            }
        } else {
            int mid = (lo + hi) / 2;
            invokeAll(
                    new PortfolioThresholdTask(assets, lo, mid, riskCap),
                    new PortfolioThresholdTask(assets, mid, hi, riskCap)
            );
        }
    }
}