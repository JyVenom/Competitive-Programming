import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Convention5 {
    private static int temp = 0;
    private static int prevBuss = 0;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("convention.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("convention.out")); //or what it calls for ("promote.out)


        int cows = sc.nextInt();
        int buses = sc.nextInt();
        int size = sc.nextInt();
        ArrayList<Integer> waitTimeList = new ArrayList<>();

        ArrayList<Integer> data = new ArrayList<>();
        for (int i = 0; i < cows; i++){
            data.add(sc.nextInt());
        }
        Collections.sort(data);

        int maxWait  = 0;
        for (int i = 1; i < data.size(); i++){
            maxWait = Math.max(maxWait, data.get(i) - data.get(i - 1));
        }
        maxWait *= size;
        if (maxWait < 0){
            maxWait = Integer.MAX_VALUE / 2;
        }
        int minWait = 1000000000;
        for (int i = 1; i < data.size(); i++){
            minWait = Math.min(minWait, data.get(i) - data.get(i - 1));
        }
        minWait *= size;
        int waitTime = (maxWait + minWait) / 2;
        if (!waitTimeCheck(data, waitTime, buses, size).equals("good")) {
            prevBuss = 0;
            waitTimeList.add(waitTime);
            System.out.println(waitTime);
            if (waitTimeCheck(data, waitTime, buses, size).equals("less")) {
                waitTime = (waitTime + minWait) / 2;
                waitTimeList.add(waitTime);
                System.out.println(waitTime);
            } else if (waitTimeCheck(data, waitTime, buses, size).equals("more")) {
                waitTime = (waitTime + maxWait) / 2;
                waitTimeList.add(waitTime);
                System.out.println(waitTime);
            }
            prevBuss = 0;
            while (!waitTimeCheck(data, waitTime, buses, size).equals("good")) {
                prevBuss = 0;
                Collections.sort(waitTimeList);
                if (waitTimeCheck(data, waitTime, buses, size).equals("less")) {
                    if (waitTimeList.indexOf(waitTime) == 0) {
                        waitTime = (waitTime + minWait) / 2;
                    } else {
                        int prev = waitTimeList.get(waitTimeList.indexOf(waitTime) - 1);
                        waitTime = (waitTime + prev) / 2;
                    }
                    waitTimeList.add(waitTime);
                    System.out.println(waitTime);
                }
                else if (waitTimeCheck(data, waitTime, buses, size).equals("more")) {
                    if (waitTimeList.indexOf(waitTime) == waitTimeList.size() - 1) {
                        waitTime = (waitTime + maxWait) / 2;
                    } else {
                        int next = waitTimeList.get(waitTimeList.indexOf(waitTime) + 1);
                        waitTime = (waitTime + next) / 2;
                    }
                    waitTimeList.add(waitTime);
                    System.out.println(waitTime);
                }
                prevBuss = 0;
            }
        }
        out.println(waitTime);
        out.close();
    }

    private static String waitTimeCheck (ArrayList<Integer> data, int waitTime, int numBuses, int cowsPerBus){
        prevBuss = 0;
        int[][] buses = new int[numBuses][cowsPerBus];
        int[] times = {data.get(0), data.get(0) + waitTime}; //inclusive of both
        int numAssigned = 0;
        int lastCowNum = -1;
        while (times[0] <= data.get(data.size() - 1)){
            int start = lastCowNum + 1;
            for (int i = start; i < data.size(); i++){
                if (data.get(i) >= times[0] && data.get(i) <= times[1]){
                    add(buses, data.get(i), prevBuss);
                    numAssigned++;
                    lastCowNum = i;
                    if (i - start == cowsPerBus - 1){
                        break;
                    }
                }
            }
            prevBuss++;
            if (lastCowNum == data.get(data.size() - 1)){
                break;
            }
            times[0] = data.get(lastCowNum + 1);
            times[1] = data.get(lastCowNum + 1) + waitTime;
        }
//        int x = 932;
//        int num = 0;
//        for (int i = 0; i < x; i++){
//            for (int j = 0; j < cowsPerBus; j++){
//                if (buses[i][j] != 0){
//                    num++;
//                }
//                else {
//                    break;
//                }
//            }
//        }
//        System.out.println(num);
//        System.out.println(data.indexOf(buses[x][0]));
//        System.out.println(data.get(data.size()-1));
//        System.out.println(buses[932][70]);
//        System.out.println(data.indexOf(buses[932][70]) + 1);
//        if (num != 71){
//            System.out.println((i+1) + ", " + num);
//            temp += num;
//        System.out.println(temp);
//        for (int i = 0; i < buses.length; i++){
//            if (buses[i][0] == 0){
//                System.out.println(i);
//            }
//        }
        if (buses[buses.length - 1][0] == 0){
            return "less";
        }
        else if (times[1] == data.get(data.size() - 1)){
            return "good";
        }
        else {
            return "more";
        }
    }

    private static void add (int[][] in, int elem, int start){
        for (int i = start; i < in.length; i++) {
            for (int j = 0; j < in[0].length; j++) {
                if (in[i][j] == 0) {
                    in[i][j] = elem;
                    prevBuss = i;
                    i = in.length - 1;
                    break;
                }
            }
        }
    }
}
