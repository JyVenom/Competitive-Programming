import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class BessieGetsEven {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("geteven.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("geteven.out")); //or what it calls for ("promote.out)

        int n = Integer.parseInt(sc.nextLine());
        ArrayList<Integer> values = new ArrayList<>(1);
        ArrayList<ArrayList<Integer>> possibleValues = new ArrayList<>(7);
        for (int i = 0; i < 7; i++){
            possibleValues.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++){
            String line = sc.nextLine();
            char letter = line.charAt(0);
            if (letter == 'B'){
                possibleValues.get(0).add(Integer.parseInt(line.substring(2)));
            }
            else if (letter == 'E'){
                possibleValues.get(1).add(Integer.parseInt(line.substring(2)));
            }
            else if (letter == 'S'){
                possibleValues.get(2).add(Integer.parseInt(line.substring(2)));
            }
            else if (letter == 'I'){
                possibleValues.get(3).add(Integer.parseInt(line.substring(2)));
            }
            else if (letter == 'G'){
                possibleValues.get(4).add(Integer.parseInt(line.substring(2)));
            }
            else if (letter == 'O'){
                possibleValues.get(5).add(Integer.parseInt(line.substring(2)));
            }
            else if (letter == 'M'){
                possibleValues.get(6).add(Integer.parseInt(line.substring(2)));
            }
        }

        for (int b = 0; b < possibleValues.get(0).size(); b++){
            for (int e = 0; e < possibleValues.get(1).size(); e++){
                for (int s = 0; s < possibleValues.get(2).size(); s++){
                    for (int i = 0; i < possibleValues.get(3).size(); i++){
                        for (int g = 0; g < possibleValues.get(4).size(); g++){
                            for (int o = 0; o < possibleValues.get(5).size(); o++){
                                for (int m = 0; m < possibleValues.get(6).size(); m++){
                                    int value = getValue(possibleValues.get(0).get(b),
                                            possibleValues.get(1).get(e), possibleValues.get(2).get(s),
                                            possibleValues.get(3).get(i), possibleValues.get(4).get(g),
                                            possibleValues.get(5).get(o), possibleValues.get(6).get(m));
                                    if (value%2 == 0){
                                        values.add(value);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        out.println(values.size());
        out.close();
    }

    private static int getValue (int b, int e, int s, int i, int g, int o, int m){
        int bessie = b + e + s + s + i + e;
        int goes = g + o + e + s;
        int moo = m + o + o;
        return bessie * goes * moo;
    }
}
