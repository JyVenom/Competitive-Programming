import java.io.*;

public class OutOfSorts {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("sort.in"));
        PrintWriter out = new PrintWriter(new File("sort.out"));

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];

        for (int i = 0; i < N; i++){
            A[i] = Integer.parseInt(br.readLine());
        }

        long count = 0;
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            count++;
            for (int i = 0; i < N - 1; i++) {
                if (A[i + 1] < A[i]) {
                    int temp = A[i];
                    A[i] = A[i + 1];
                    A[i + 1] = temp;
                    sorted = false;
                }
            }
        }

        out.println(count);
        out.close();
    }
}