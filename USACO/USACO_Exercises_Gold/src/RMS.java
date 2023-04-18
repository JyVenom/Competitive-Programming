public class RMS {
    public static void main(String[] args) {
        int n = 100;
        double fra = (2 * Math.PI) / n;
        double sum = 0;
        for (int i = 0; i < n; i++) {
            double val = Math.sin(i * fra);
            sum += val * val;
        }
        double mean = sum / n;
        double res = Math.sqrt(mean);
        System.out.println(res);
    }
}
