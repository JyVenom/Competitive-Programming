import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class LivestockLineup {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("lineup.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("lineup.out")); //or what it calls for ("promote.out)

        int n = Integer.parseInt(sc.nextLine());
        String[][] constraints = new String[n][2];
        ArrayList<ArrayList<String>> all = new ArrayList<>();
        ArrayList<String> finalOrder = new ArrayList<>(8);
        finalOrder.add("Beatrice");
        finalOrder.add("Belinda");
        finalOrder.add("Bella");
        finalOrder.add("Bessie");
        finalOrder.add("Betsy");
        finalOrder.add("Blue");
        finalOrder.add("Buttercup");
        finalOrder.add("Sue");
        
        for (int c1 = 0; c1 < 8; c1++){
            for (int c2 = 0; c2 < 7; c2++){
                for (int c3 = 0; c3 < 6; c3++){
                    for (int c4 = 0; c4 < 5; c4++){
                        for (int c5 = 0; c5 < 4; c5++){
                            for (int c6 = 0; c6 < 3; c6++){
                                for (int c7 = 0; c7 < 2; c7++){
                                    for (int c8 = 0; c8 < 1; c8++){
                                        ArrayList<String> temp = new ArrayList<>();
                                        ArrayList<String> remaining = new ArrayList<>(finalOrder);
                                        temp.add(remaining.get(c1));
                                        remaining.remove(c1);
                                        temp.add(remaining.get(c2));
                                        remaining.remove(c2);
                                        temp.add(remaining.get(c3));
                                        remaining.remove(c3);
                                        temp.add(remaining.get(c4));
                                        remaining.remove(c4);
                                        temp.add(remaining.get(c5));
                                        remaining.remove(c5);
                                        temp.add(remaining.get(c6));
                                        remaining.remove(c6);
                                        temp.add(remaining.get(c7));
                                        remaining.remove(c7);
                                        temp.add(remaining.get(c8));
                                        all.add(temp);
                                    }
                                }
                            }
                        }
                    }
                }
            }    
        }

        for (int i = 0; i < n; i++){
            String line = sc.nextLine();
            constraints[i][0] = line.substring(0, line.indexOf(' '));
            constraints[i][1] = line.substring(line.lastIndexOf(' ') + 1);
            finalOrder.remove(constraints[i][0]);
            finalOrder.remove(constraints[i][1]);
        }

        for (int i = 0; i < all.size(); i++){
            ArrayList<String> current = new ArrayList<>(all.get(i));
            boolean good = true;
            for (int j = 0; j < constraints.length; j++){
                if (!(Math.abs(current.indexOf(constraints[j][0]) - current.indexOf(constraints[j][1])) == 1)){
                    good = false;
                    break;
                }
            }
            if (good){
                for (int j = 0; j < 8; j++){
                    out.println(current.get(j));
                }
                break;
            }
        }

        out.close();
    }
}
