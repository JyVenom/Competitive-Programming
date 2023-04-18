/*
ID: jerryya2
LANG: JAVA
PROG: ride
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class ride {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("ride.in"));
        PrintWriter out = new PrintWriter(new File("ride.out"));

        String a = br.readLine();
        String b = br.readLine();
        int sumA = a.charAt(0) - 64;
        for (int i = 1; i < a.length(); i++){
            int c = a.charAt(i) - 64;
            sumA *= c;
        }
        int sumB = b.charAt(0) - 64;
        for (int i = 1; i < b.length(); i++){
            int c = b.charAt(i) - 64;
            sumB *= c;
        }
        if (sumA % 47 == sumB % 47){
            out.println("GO");
        }
        else {
            out.println("STAY");
        }
        out.close();
    }
}
