import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CowEvolution {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("evolution.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("evolution.out")); //or what it calls for ("evolution.out")

        boolean no_Uppercase = true;
        int lines = Integer.parseInt(sc.nextLine());
        String[][] evolution_traits = new String[lines][25];
        String temp;
        String temp_word;
        int prev_index = 1;
        for (int i = 0; i < lines; i++) {
            temp = sc.nextLine();
            int i1 = Integer.parseInt(String.valueOf(temp.charAt(0)));
            for (int x = 0; x < i1; x++) {
                if (x != i1 - 1) {
                    temp_word = temp.substring(prev_index + 1, temp.indexOf(" ", prev_index + 1));
                    for (int y = 0; y < temp_word.length(); y++){
                        if (Character.isUpperCase(temp_word.charAt(y))){
                            no_Uppercase = false;
                        }
                    }
                } else {
                    temp_word = temp.substring(prev_index + 1);
                    for (int y = 0; y < temp_word.length(); y++){
                        if (Character.isUpperCase(temp_word.charAt(y))){
                            no_Uppercase = false;
                        }
                    }
                }
                prev_index = temp.indexOf(" ", prev_index + 1);
                evolution_traits[i][x] = temp_word;
            }
            prev_index = 1;
        }

        if (no_Uppercase) {
            boolean good = true;
            int number_in_common = 0;
            for (int i = 0; i < evolution_traits.length; i++) {
                for (int x = i + 1; x < evolution_traits.length; x++) {
                    for (int a = 0; a < evolution_traits[i].length; a++) {
                        for (int b = 0; b < evolution_traits[x].length; b++) {
                            if (evolution_traits[i][a] != null && evolution_traits[i][a].equals(evolution_traits[x][b])) {
                                number_in_common++;
                            }
                        }
                    }
                    int i_elements = 0;
                    int x_elements = 0;
                    for (int a = 0; a < evolution_traits[i].length; a++) {
                        if (evolution_traits[i][a] != null) {
                            i_elements++;
                        }
                    }
                    for (int b = 0; b < evolution_traits[x].length; b++) {
                        if (evolution_traits[x][b] != null) {
                            x_elements++;
                        }
                    }

                    boolean i_bigger = false;
                    if (i_elements > x_elements){
                        i_bigger = true;
                    }
                    if (i_bigger) {
                        if (number_in_common > 0) {
                            if (!(number_in_common == x_elements)) {
                                good = false;
                                break;
                            }
                        }
                    }else{
                        if (number_in_common > 0){
                            if (!(number_in_common == i_elements)){
                                good = false;
                                break;
                            }
                        }
                    }
                    if (i_elements >= 2 && x_elements >= 2) {
                        if (!(number_in_common == 0 || number_in_common >= 1)) {
                            good = false;
                            break;
                        }
                    } else if (i_elements == 0 && x_elements == 0) {
                        good = false;
                        break;
                    } else if (i_elements == 1 && x_elements == 1) {
                        if (number_in_common == 1) {
                            good = false;
                            break;
                        }
                    } else if (i_elements == x_elements) {
                        if (number_in_common == i_elements) {
                            good = false;
                            break;
                        }
                    }
                    number_in_common = 0;
                }
            }
            if (good) {
                out.println("yes");
            } else {
                out.println("no");
            }
            out.close();
        }

        else {
            out.println("no");
            out.close();
        }
    }
}
