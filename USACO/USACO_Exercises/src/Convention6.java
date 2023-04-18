import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Convention6 {
    private static int prevBuss = 0;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("convention.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("convention.out")); //or what it calls for ("promote.out)


        int cows = sc.nextInt();
        int buses = sc.nextInt();
        int size = sc.nextInt();
        ArrayList<Integer> waitTimeList = new ArrayList<>();

        ArrayList<Integer> data = new ArrayList<>();
        for (int i = 0; i < cows; i++) {
            data.add(sc.nextInt());
        }
        Collections.sort(data);

        int maxWait = 0;
        for (int i = 1; i < data.size(); i++) {
            maxWait = Math.max(maxWait, data.get(i) - data.get(i - 1));
        }
        maxWait *= size;
        if (maxWait < 0) {
            maxWait = Integer.MAX_VALUE / 2;
        }
        int minWait = 1000000000;
        for (int i = 1; i < data.size(); i++) {
            minWait = Math.min(minWait, data.get(i) - data.get(i - 1));
        }
        minWait *= size;
        int waitTime = (maxWait + minWait) / 2;
        if (!waitTimeCheck(data, waitTime, buses, size).equals("good")) {
            prevBuss = 0;
            waitTimeList.add(waitTime);
            Collections.sort(waitTimeList);
            if (waitTimeCheck(data, waitTime, buses, size).equals("less")) {
                waitTime = (waitTime + minWait) / 2;
            } else {
                waitTime = (waitTime + maxWait) / 2;
            }
            waitTimeList.add(waitTime);
            Collections.sort(waitTimeList);
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
                } else {
                    if (waitTimeList.indexOf(waitTime) == waitTimeList.size() - 1) {
                        waitTime = (waitTime + maxWait) / 2;
                    } else {
                        int next = waitTimeList.get(waitTimeList.indexOf(waitTime) + 1);
                        if (next == waitTime) {
                            waitTime++;
                        } else {
                            waitTime = (waitTime + next) / 2;
                        }
                    }
                }
                waitTimeList.add(waitTime);
                Collections.sort(waitTimeList);
                if (waitTimeList.lastIndexOf(waitTime) < waitTimeList.size() - 1) {
                    if ((waitTimeList.lastIndexOf(waitTime) - waitTimeList.indexOf(waitTime) >= 2 && waitTimeList.lastIndexOf(waitTime + 1) - waitTimeList.indexOf(waitTime + 1) >= 1) || (waitTimeList.lastIndexOf(waitTime) - waitTimeList.indexOf(waitTime) >= 1 && waitTimeList.lastIndexOf(waitTime + 1) - waitTimeList.indexOf(waitTime + 1) >= 2)) {
                        if (waitTimeList.get(waitTimeList.lastIndexOf(waitTime) + 1) - 1 == waitTime) {
                            if (!waitTimeCheck(data, waitTime, buses, size).equals("good")) {
                                waitTime++;
                            }
                            break;
                        }
                    }
                }
//                System.out.println(waitTime);
            }
        }
        out.println(waitTime);
        out.close();
    }

    private static String waitTimeCheck(ArrayList<Integer> data, int waitTime, int numBuses, int cowsPerBus) {
        prevBuss = 0;
        int[][] buses = new int[numBuses][cowsPerBus];
        int[] times = {data.get(0), data.get(0) + waitTime}; //inclusive of both
        int numAssigned = 0;
        int lastCowNum = -1;
        while (times[0] <= data.get(data.size() - 1)) {
            long startTime = System.nanoTime();
            int start = lastCowNum + 1;
            for (int i = start; i < data.size(); i++) {
                if (data.get(i) >= times[0] && data.get(i) <= times[1]) {
                    add(buses, data.get(i), prevBuss);
                    if (prevBuss == numBuses) {
                        break;
                    }
                    numAssigned++;
                    lastCowNum = i;
                    if (i - start == cowsPerBus - 1) {
                        break;
                    }
                }
            }
            if (prevBuss == numBuses) {
                break;
            }
            prevBuss++;
            if (lastCowNum == data.size() - 1) {
                break;
            }
            times[0] = data.get(lastCowNum + 1);
            times[1] = data.get(lastCowNum + 1) + waitTime;
            long endTime = System.nanoTime();
            long durationInNano = (endTime - startTime);
            System.out.println(durationInNano);
        }

        if (numAssigned < data.size()) {
            return "more";
        } else if (times[1] == data.get(data.size() - 1)) {
            return "good";
        } else {
            return "less";
        }
    }

    private static void add(int[][] in, int elem, int start) {
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
