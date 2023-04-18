import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class ModernArt {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("art.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("art.out")); //or what it calls for ("promote.out")
        
        int size = Integer.parseInt(sc.nextLine());
        ArrayList<Integer> colors = new ArrayList<>(1);
        ArrayList<ArrayList<Integer>> data = new ArrayList<>();

        for (int i = 0; i < size; i++){
            data.add(new ArrayList<>());
        }
        for (int i = 0; i < size; i++){
            String line = sc.nextLine();
            for (int j = 0; j < size; j++){
                int value = Integer.parseInt(line.substring(j, j + 1));
                data.get(i).add(value);
                if (!colors.contains(value)){
                    colors.add(value);
                }
            }
        }

        Collections.sort(colors);
        ArrayList<ArrayList<Integer>> colors2 = new ArrayList<>(2);
        colors2.add(new ArrayList<>());
        colors2.add(new ArrayList<>());
        for (Integer color : colors) {
            colors2.get(0).add(0);
            colors2.get(1).add(color);
        }
        for (int i = 0; i < colors2.get(0).size(); i++){
            int[] topLeft = new int[2]; //column, row
            topLeft[0] = Integer.MAX_VALUE;
            topLeft[1] = Integer.MAX_VALUE;
            int[] bottomRight = new int[2]; //column, row
            bottomRight[0] = Integer.MIN_VALUE;
            bottomRight[1] = Integer.MIN_VALUE;
            int currentColor = colors2.get(1).get(i);
            for (int j = 0; j < size; j++){
                if (data.get(j).indexOf(currentColor) != -1){
                    topLeft[0] = Math.min(topLeft[0], data.get(j).indexOf(currentColor));
                    topLeft[1] = Math.min(topLeft[1], j);
                    bottomRight[0] = Math.max(bottomRight[0], data.get(j).lastIndexOf(currentColor));
                    bottomRight[1] = Math.max(bottomRight[1], j);
                }
            }
            if (colors2.get(0).size() > 1) {
                int count = 0;
                for (int j = topLeft[0]; j <= bottomRight[0]; j++) {
                    for (int k = topLeft[1]; k <= bottomRight[1]; k++){
                        if (data.get(k).get(j) == currentColor){
                            count++;
                        }
                    }
                }
                int all = (bottomRight[0] - topLeft[0] + 1) * (bottomRight[1] - topLeft[1] + 1);
                if (count == all){
                    colors2.get(0).remove(i);
                    colors2.get(1).remove(i);
                    i--;
                }else {
                    colors2.get(0).set(i, count);
                }
            }
        }
//        colors2.sort(Comparator.comparing(a -> a.get(0)));
        if (colors2.get(1).contains(0)){
            int index = colors2.get(1).indexOf(0);
            colors2.get(0).remove(index);
            colors2.get(1).remove(index);
        }
//        Collections.sort(colors2, Comparator.comparing(a -> a.get(1)));
        colors2.sort(Comparator.comparing(a -> a.get(1)));
        int min = colors2.get(0).get(0);
        if (colors2.get(0).size() > colors2.get(0).lastIndexOf(min) + 1) {
            colors2.get(0).subList(colors2.get(0).lastIndexOf(min) + 1, colors2.get(0).size()).clear();
        }
        out.println(colors2.get(0).size());
        out.close();
    }
}
