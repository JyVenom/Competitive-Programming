public class test {
    public static void main(String[] args) {
        int n = 200000;

        long count = 0;

        for (int i = 1; i <= n; i++) {
            count += Math.log10(i);
        }
        count += n;

        System.out.println(count);
    }
}
