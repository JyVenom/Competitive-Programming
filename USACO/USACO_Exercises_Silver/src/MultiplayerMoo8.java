import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class MultiplayerMoo8 {
//    private static int count = 0; //Jerry
//    private static int step = 0; //Jerry
    public static void main(String[] args) throws IOException {
//        long start = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("multimoo.in"));
        PrintWriter out = new PrintWriter(new File("multimoo.out"));

        int n = Integer.parseInt(br.readLine());
        int[][] grid = new int[n][n];

        //get data
        int temp;
        String line;
        for (int i = 0; i < n; i++) {
            temp = 0;
            line = br.readLine();
            for (int j = 0; j < n - 1; j++) {
                grid[i][j] = Integer.parseInt(line.substring(temp, line.indexOf(' ', temp)));
                temp = line.indexOf(' ', temp) + 1;
            }
            grid[i][n - 1] = Integer.parseInt(line.substring(temp));
        }

        //Step 0
        int max = 0;
        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    int size = 0;
                    size = region(i, j, grid[i][j], grid, visited, size);
                    max = Math.max(max, size);
                }
            }
        }

        //Step 1
        ArrayList<ArrayList<Integer>> cowCord = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //Get current cow number
                int cow = grid[i][j];
                int index = binarySearch2d(cowCord, 0, cowCord.size() - 1, cow);
                //Add if not yet added
                if (index == -1) {
                    int temp2 = binarySearchPos2d(cowCord, 0, cowCord.size() - 1, cow);
                    cowCord.add(temp2, new ArrayList<>());
                    cowCord.get(temp2).add(cow);
                    cowCord.get(temp2).add(i);
                    cowCord.get(temp2).add(j);
                } else {
                    cowCord.get(index).add(i);
                    cowCord.get(index).add(j);
                }
            }
        }

        //Step 2
        ArrayList<ArrayList<Integer>> pairs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //Current cow and cow to its right
                if (j != n - 1) {
                    if (grid[i][j] != grid[i][j + 1]) {
                        int first;
                        int second;
                        //Assign values
                        if (grid[i][j] < grid[i][j + 1]) {
                            first = grid[i][j];
                            second = grid[i][j + 1];
                        } else {
                            first = grid[i][j + 1];
                            second = grid[i][j];
                        }
                        if (pairs.size() == 0) {
                            pairs.add(new ArrayList<>());
                            pairs.get(0).add(first);
                            pairs.get(0).add(second);
                        }
                        //Add the pair
                        else {
                            int index = binarySearch2d(pairs, 0, pairs.size() - 1, first);
                            //If the first cow doesn't have an array list, make one
                            if (index == -1) {
                                int temp2 = binarySearchPos2d(pairs, 0, pairs.size() - 1, first);
                                pairs.add(temp2, new ArrayList<>());
                                pairs.get(temp2).add(first);
                                pairs.get(temp2).add(second);
                            }
                            //If the first cow has an array list, add second cow if it does not already exist
                            else {
                                if (binarySearch1d(pairs.get(index), 0, pairs.get(index).size() - 1, second) == -1) {
                                    int temp2 = binarySearchPos1d(pairs.get(index), 0, pairs.get(index).size() - 1, second);
                                    pairs.get(index).add(temp2, second);
                                }
                            }
                        }
                    }
                }
                //Current cow and cow below it
                if (i != n - 1) {
                    if (grid[i][j] != grid[i + 1][j]) {
                        int first;
                        int second;
                        //Assign values
                        if (grid[i][j] < grid[i + 1][j]) {
                            first = grid[i][j];
                            second = grid[i + 1][j];
                        } else {
                            first = grid[i + 1][j];
                            second = grid[i][j];
                        }
                        if (pairs.size() == 0) {
                            pairs.add(new ArrayList<>());
                            pairs.get(0).add(first);
                            pairs.get(0).add(second);
                        }
                        //Add the pair
                        else {
                            int index = binarySearch2d(pairs, 0, pairs.size() - 1, first);
                            //If the first cow doesn't have an array list, make one
                            if (index == -1) {
                                int temp2 = binarySearchPos2d(pairs, 0, pairs.size() - 1, first);
                                pairs.add(temp2, new ArrayList<>());
                                pairs.get(temp2).add(first);
                                pairs.get(temp2).add(second);
                            }
                            //If the first cow has an array list, add second cow if it does not already exist
                            else {
                                if (binarySearch1d(pairs.get(index), 0, pairs.get(index).size() - 1, second) == -1) {
                                    int temp2 = binarySearchPos1d(pairs.get(index), 0, pairs.get(index).size() - 1, second);
                                    pairs.get(index).add(temp2, second);
                                }
                            }
                        }
                    }
                }
            }
        }
//        long start = System.currentTimeMillis(); //Jerry

        //Step 3
//        step = 3; //Jerry
        int twoMax = 0;
        if (cowCord.size() == 2) {
            twoMax = n * n;
        } else {
            for (ArrayList<Integer> pair : pairs) {
//                long start = System.nanoTime();
                int cow1 = pair.get(0);
                int cow2;
                for (int pair2 = 1; pair2 < pair.size(); pair2++) {
                    //Make combined array list
                    cow2 = pair.get(pair2);
                    ArrayList<Integer> combined = new ArrayList<>();
                    int pair1Index = binarySearch2d(cowCord, 0, cowCord.size() - 1, cow1);
                    int pair2Index = binarySearch2d(cowCord, 0, cowCord.size() - 1, cow2);
                    for (int i = 1; i < cowCord.get(pair1Index).size(); i += 2) {
//                        count += 2; //Jerry
                        combined.add(cowCord.get(pair1Index).get(i) * n + cowCord.get(pair1Index).get(i + 1));
                    }
                    for (int i = 1; i < cowCord.get(pair2Index).size(); i += 2) {
//                        count += 2; //Jerry
                        combined.add(cowCord.get(pair2Index).get(i) * n + cowCord.get(pair2Index).get(i + 1));
                    }
                    Collections.sort(combined);

                    if (combined.size() == 2){
                        if (combined.get(1) - combined.get(0) == 0){
                            twoMax = Math.max(twoMax, 2);
                        }
                        else if (combined.get(1) - combined.get(0) == n){
                            twoMax = Math.max(twoMax, 2);
                        }
                    }
                    else {
                        ArrayList<Integer> region = new ArrayList<>();
                        ArrayList<Integer> prevColumns = new ArrayList<>();
                        int startRow = combined.get(0) / n;
                        while (combined.get(0) / n == startRow){
                            prevColumns.add(combined.get(0) % n);
                            region.add(combined.get(0));
                            combined.remove(0);
                            if (combined.size() == 0){
                                break;
                            }
                        }
                        int prevColNum = prevColumns.get(0);
                        ArrayList<Integer> sizes = new ArrayList<>();
                        for (int i = 0; i < prevColumns.size(); i++){
                            sizes.add(1);
                            if (prevColumns.get(i) - prevColNum == 1){
                                sizes.set(i, sizes.get(i - 1) + 1);
                                twoMax = Math.max(twoMax, sizes.get(i));
                                for (int j = i - 1; j >= 0; j--){
                                    if (prevColumns.get(j) + 1 == prevColumns.get(j + 1)){
                                        sizes.set(j, sizes.get(j) + 1);
                                    }
                                    else {
                                        break;
                                    }
                                }
                            }
                            prevColNum = prevColumns.get(i);
                        }
                        for (int i = 1; i < sizes.size(); i++){
//                            if (sizes.get(i).equals(sizes.get(i - 1)) && prevColumns.get(i) == prevColumns.get(i - 1) + 1){
                            if (prevColumns.get(i) == prevColumns.get(i - 1) + 1){
                                region.set(i, region.get(i - 1));
                            }
                        }
                        //Calculate all region sizes and update max each time
                        while (combined.size() > 0) {
//                            if (cow1 == 1 && cow2 == 2){
//                                System.out.println();
//                            }
                            ArrayList<Integer> region2 = new ArrayList<>();
                            ArrayList<Integer> columns = new ArrayList<>();
                            int startRow2 = combined.get(0) / n;
                            while (combined.get(0) / n == startRow2){
                                columns.add(combined.get(0) % n);
                                region2.add(combined.get(0));
                                combined.remove(0);
                                if (combined.size() == 0){
                                    break;
                                }
                            }
                            prevColNum = columns.get(0);
                            ArrayList<Integer> sizes2 = new ArrayList<>();
                            for (int i = 0; i < columns.size(); i++){
                                sizes2.add(1);
                                if (columns.get(i) - prevColNum == 1){
                                    sizes2.set(i, sizes2.get(i - 1) + 1);
                                    twoMax = Math.max(twoMax, sizes2.get(i));
                                    for (int j = i - 1; j >= 0; j--){
                                        if (columns.get(j) + 1 == columns.get(j + 1)){
                                            sizes2.set(j, sizes2.get(j) + 1);
                                        }
                                        else {
                                            break;
                                        }
                                    }
                                }
                                prevColNum = columns.get(i);
                            }
                            for (int i = 1; i < sizes2.size(); i++){
//                                if (sizes2.get(i).equals(sizes2.get(i - 1)) && columns.get(i) == columns.get(i - 1) + 1){
                                if (columns.get(i) == columns.get(i - 1) + 1){
                                    region2.set(i, region2.get(i - 1));
                                }
                            }

                            for (int i = 0; i < columns.size(); i++) {
                                int index = binarySearchRemove(prevColumns, 0, prevColumns.size() - 1, columns.get(i));
                                if (index != -1 && !region.get(index).equals(region2.get(i))){
                                    int r1 = region.get(index);
                                    int r2 = region2.get(i);
                                    int value = sizes2.get(i) + sizes.get(index);
                                    twoMax = Math.max(twoMax, value);
//                                    if (twoMax == 88){
//                                        System.out.println();
//                                    }
                                    for (int j = 0; j < region.size(); j++){
                                        if (region.get(j) == r2){
                                            sizes.set(j, value);
                                        }
                                        if (region.get(j) == r1){
                                            region.set(j, r2);
                                            sizes.set(j, value);
                                        }
                                    }
                                    for (int j = 0; j < region2.size(); j++){
                                        if (region2.get(j) == r2){
                                            sizes2.set(j, value);
                                        }
                                        if (region2.get(j) == r1){
                                            region2.set(j, r2);
                                            sizes2.set(j, value);
                                        }
                                    }
//                                    sizes2.set(i, value);
//                                    twoMax = Math.max(twoMax, sizes2.get(i));
//                                    for (int j = i - 1; j >= 0; j--) {
//                                        if (columns.get(j) == columns.get(j + 1) - 1) {
//                                            sizes2.set(j, sizes2.get(j) + value);
//                                        } else {
//                                            break;
//                                        }
//                                    }
//                                    for (int j = i + 1; j < columns.size(); j++) {
//                                        if (columns.get(j) == columns.get(j - 1) + 1) {
//                                            sizes2.set(j, sizes2.get(j) + value);
//                                        } else {
//                                            break;
//                                        }
//                                    }
//                                    int startNum = prevColumns.get(index);
//                                    int end = index;
//                                    int prevValue = prevColumns.get(index);
//                                    prevColumns.remove(index);
//                                    sizes.remove(index);
//                                    for (int j = index - 1; j >= 0; j--){
//                                        if (prevColumns.get(j) == prevValue - 1){
//                                            end = j;
//                                            prevValue = prevColumns.get(j);
//                                            prevColumns.remove(j);
//                                            sizes.remove(j);
//                                        }
//                                        else {
//                                            break;
//                                        }
//                                    }
//                                    prevValue = startNum;
//                                    for (int j = end; j < prevColumns.size(); j++) {
//                                        if (prevColumns.get(j) == prevValue + 1) {
//                                            prevValue = prevColumns.get(j);
//                                            prevColumns.remove(j);
//                                            sizes.remove(j);
//                                            j--;
//                                        } else {
//                                            break;
//                                        }
//                                    }
                                }
                            }
                            prevColumns = new ArrayList<>(columns);
                            sizes = new ArrayList<>(sizes2);
                            region = new ArrayList<>(region2);
                        }
                    }
                }
//                long end = System.nanoTime();
//                if (end - start > 10000000){
//                    System.out.println(end - start);
//                }
            }
        }

        out.println(max);
        out.println(twoMax);
        out.close();
//        System.out.println(count); //Jerry
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
    }

    private static int binarySearch1d(ArrayList<Integer> arr, int l, int r, int x) {
        if (r >= l) {
//            if (step == 3) {
//                count++; //Jerry
//            }
            int mid = l + (r - l) / 2;

            // If the element is present at the
            // middle itself
            if (arr.get(mid) == x && mid != 0)
                return mid;

            // If element is smaller than mid, then
            // it can only be present in left sub array
            if (arr.get(mid) > x)
                return binarySearch1d(arr, l, mid - 1, x);

            // Else the element can only be present
            // in right sub array
            return binarySearch1d(arr, mid + 1, r, x);
        }

        // We reach here when element is not present
        // in array
        return -1;
    }

    private static int binarySearch2d(ArrayList<ArrayList<Integer>> arr, int l, int r, int x) {
//        if (step == 3) {
//            count++; //Jerry
//        }
        if (r >= l) {
            int mid = l + (r - l) / 2;

            // If the element is present at the
            // middle itself
            if (arr.get(mid).get(0) == x)
                return mid;

            // If element is smaller than mid, then
            // it can only be present in left sub array
            if (arr.get(mid).get(0) > x)
                return binarySearch2d(arr, l, mid - 1, x);

            // Else the element can only be present
            // in right sub array
            return binarySearch2d(arr, mid + 1, r, x);
        }

        // We reach here when element is not present
        // in array
        return -1;
    }

    private static int binarySearchRemove(ArrayList<Integer> arr, int l, int r, int x) {
//        if (step == 3) {
//            count++; //Jerry
//        }
        if (r >= l) {
            int mid = l + (r - l) / 2;

            // If the element is present at the
            // middle itself
            if (arr.get(mid) == x)
                return mid;

            if (arr.get(mid) > x)
                return binarySearchRemove(arr, l, mid - 1, x);
            else
                return binarySearchRemove(arr, mid + 1, r, x);
        }

        // We reach here when element is not present
        // in array
        return -1;
    }

    private static int binarySearchPos1d(ArrayList<Integer> arr, int l, int r, int x) {
//        if (step == 3) {
//            count++; //Jerry
//        }
        if (r >= l) {
            int mid = l + (r - l) / 2;

            // If the element is present at the
            // middle itself
            if (arr.get(mid) == x && mid != 0)
                return mid;

            // If element is smaller than mid, then
            // it can only be present in left sub array
            if (arr.get(mid) > x)
                return binarySearchPos1d(arr, l, mid - 1, x);

            // Else the element can only be present
            // in right sub array
            return binarySearchPos1d(arr, mid + 1, r, x);
        }

        // We reach here when element is not present
        // in array
        return l;
    }

    private static int binarySearchPos2d(ArrayList<ArrayList<Integer>> arr, int l, int r, int x) {
//        if (step == 3) {
//            count++; //Jerry
//        }
        if (r >= l) {
            int mid = l + (r - l) / 2;

            // If the element is present at the
            // middle itself
            if (arr.get(mid).get(0) == x && mid != 0)
                return mid;

            // If element is smaller than mid, then
            // it can only be present in left sub array
            if (arr.get(mid).get(0) > x)
                return binarySearchPos2d(arr, l, mid - 1, x);

            // Else the element can only be present
            // in right sub array
            return binarySearchPos2d(arr, mid + 1, r, x);
        }

        // We reach here when element is not present
        // in array
        return l;
    }

    private static int region(int i, int j, int value, int[][] grid, boolean[][] visited, int size) {
        visited[i][j] = true;
        size++;
        if (i != 0) {
            if (grid[i - 1][j] == value && !visited[i - 1][j]) {
                size = region(i - 1, j, value, grid, visited, size);
            }
        }
        if (j != 0) {
            if (grid[i][j - 1] == value && !visited[i][j - 1]) {
                size = region(i, j - 1, value, grid, visited, size);
            }
        }
        if (i != grid.length - 1 && !visited[i + 1][j]) {
            if (grid[i + 1][j] == value && !visited[i + 1][j]) {
                size = region(i + 1, j, value, grid, visited, size);
            }
        }
        if (j != grid[i].length - 1) {
            if (grid[i][j + 1] == value && !visited[i][j + 1]) {
                size = region(i, j + 1, value, grid, visited, size);
            }
        }
        return size;
    }
}
