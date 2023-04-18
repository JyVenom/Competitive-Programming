import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BessieGoesMoo {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("bgm.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("bgm.out")));

        ArrayList<ArrayList<Long>> values = new ArrayList<>();
        for (int i = 0; i < 7; i++){
            values.add(new ArrayList<>());
        }
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            long value = Long.parseLong(st.nextToken());
            if ("B".equals(s)) {
                values.get(0).add(value);
            } else if ("E".equals(s)) {
                values.get(1).add(value);
            } else if ("S".equals(s)) {
                values.get(2).add(value);
            } else if ("I".equals(s)) {
                values.get(3).add(value);
            } else if ("G".equals(s)) {
                values.get(4).add(value);
            } else if ("O".equals(s)) {
                values.get(5).add(value);
            } else if ("M".equals(s)) {
                values.get(6).add(value);
            }
        }

        long count = 0;
        long B = 0;
        long G = 0;
//        long M = 0;
        long goesMoo = values.get(4).size() * values.get(5).size() * values.get(6).size();
        long bessieMoo = values.get(0).size() * values.get(3).size() * values.get(6).size();
        long bessieGoes = values.get(0).size() * values.get(1).size() * values.get(2).size() * values.get(3).size() * values.get(4).size();
        for (int b = 0; b < values.get(0).size(); b++) {
            for (int e = 0; e < values.get(1).size(); e++) {
                for (int s = 0; s < values.get(2).size(); s++) {
                    for (int i = 0; i < values.get(3).size(); i++) {
                        long bessie = values.get(0).get(b) + values.get(1).get(e) + values.get(2).get(s) + values.get(2).get(s) + values.get(3).get(i) + values.get(1).get(e);
                        if (bessie % 7 == 0) {
                            B++;
                            count += goesMoo;
                        }
                    }
                }
            }
        }
        bessieMoo -= (B * values.get(6).size() * values.get(5).size());
        for (int g = 0; g < values.get(4).size(); g++) {
            for (int o = 0; o < values.get(5).size(); o++) {
                for (int e = 0; e < values.get(1).size(); e++) {
                    for (int s = 0; s < values.get(2).size(); s++) {
                        long goes = values.get(4).get(g) + values.get(5).get(o) + values.get(1).get(e) + values.get(2).get(s);
                        if (goes % 7 == 0) {
                            G++;
                            count += bessieMoo;
                        }
                    }
                }
            }
        }
        bessieGoes -= B * G;
        for (int m = 0; m < values.get(6).size(); m++) {
            for (int o = 0; o < values.get(5).size(); o++) {
                long moo = values.get(6).get(m) + values.get(5).get(o) + values.get(5).get(o);
                if (moo % 7 == 0) {
                    count += bessieGoes;
                }
            }
        }
////        ArrayList<Integer> possible = new ArrayList<>();
//        for (int b = 0; b < values.get(0).size(); b++){
//            for (int e = 0; e < values.get(1).size(); e++){
//                for (int s = 0; s < values.get(2).size(); s++){
//                    for (int i = 0; i < values.get(3).size(); i++){
//                        for (int g = 0; g < values.get(4).size(); g++){
//                            for (int o = 0; o < values.get(5).size(); o++){
//                                for (int m = 0; m < values.get(6).size(); m++){
//                                    long bessie = values.get(0).get(b) + values.get(1).get(e) + values.get(2).get(s) + values.get(2).get(s) + values.get(3).get(i) + values.get(1).get(e);
//                                    long goes = values.get(4).get(g) + values.get(5).get(o) + values.get(1).get(e) + values.get(2).get(s);
//                                    long moo = values.get(6).get(m) + values.get(5).get(o) + values.get(5).get(o);
////                                    long value = (bessie * goes * moo);
//                                    if (bessie % 7 == 0 || goes % 7 == 0 || moo % 7 == 0){
////                                        possible.add(value);
//                                        count++;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
////        System.out.println(possible.size());
        pw.println(count);
        pw.close();
    }
}
