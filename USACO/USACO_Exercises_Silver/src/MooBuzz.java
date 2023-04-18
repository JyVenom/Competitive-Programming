import java.io.*;

public class MooBuzz {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("moobuzz.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moobuzz.out")));

        int n = Integer.parseInt(br.readLine());
        br.close();

        int num = 15 * (n/8);
        int rem = n % 8;
        if (rem == 0){
            num -= 1;
        }
        else if (rem == 1){
            num += 1;
        }
        else if (rem == 2){
            num += 2;
        }
        else if (rem == 3){
            num += 4;
        }
        else if (rem == 4){
            num += 7;
        }
        else if (rem == 5){
            num += 8;
        }
        else if (rem == 6){
            num += 11;
        }
        else if (rem == 7){
            num += 13;
        }
        out.println(num);
        out.close();
    }
}
