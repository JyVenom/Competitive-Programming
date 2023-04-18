/*
ID: jerryya2
LANG: JAVA
PROG: friday
*/

import java.io.*;

public class friday {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("friday.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));

        int n = Integer.parseInt(br.readLine());
        int sunday = 0;
        int monday = 0;
        int tuesday = 0;
        int wednesday = 0;
        int thursday = 0;
        int friday = 0;
        int saturday = 0;

        int start = 1; //0 = sunday, 1 = monday, ... 6 = saturday
        for (int i = 0; i < n; i++){
            int year = 1900 + i;
            for (int j = 0; j < 12; j++) {
                int days;
                if (j == 1) {
                    if (isLeap(year)) {
                        days = 29;
                    }
                    else {
                        days = 28;
                    }
                }
                else if (j == 3 || j == 5 || j == 8 || j == 10) {
                    days = 30;
                }
                else {
                    days = 31;
                }
                int day = (start + 5) % 7;
                if (day == 0) {
                    sunday++;
                }
                else if (day == 1) {
                    monday++;
                }
                else if (day == 2) {
                    tuesday++;
                }
                else if (day == 3) {
                    wednesday++;
                }
                else if (day == 4) {
                    thursday++;
                }
                else if (day == 5) {
                    friday++;
                }
                else if (day == 6) {
                    saturday++;
                }
                start = (start + days) % 7;
            }
        }

        pw.println(saturday + " " + sunday + " " + monday + " " + tuesday + " " + wednesday + " " + thursday + " " + friday);
        pw.close();
    }

    private static boolean isLeap (int year) {
        if (year % 100 == 0) {
            return year % 400 == 0;
        }
        return year % 4 == 0;
    }
}