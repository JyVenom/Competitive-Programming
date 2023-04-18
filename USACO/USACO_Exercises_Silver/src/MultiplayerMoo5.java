import java.io.*;
import java.util.ArrayList;

public class MultiplayerMoo5 {
    private static int count = 0; //Jerry
    private static int step = 0; //Jerry
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
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

        //Step 3
        step = 3; //Jerry
        int twoMax = 0;
        if (cowCord.size() == 2) {
            twoMax = n * n;
        } else {
            for (ArrayList<Integer> pair : pairs) {
//                long start = System.nanoTime();
                int cow1 = pair.get(0);
                int cow2;
                for (int pair2 = 1; pair2 < pair.size(); pair2++) {
                    count++; //Jerry
                    //Make combined array list
                    cow2 = pair.get(pair2);
                    ArrayList<ArrayList<Integer>> combined = new ArrayList<>();
                    combined.add(new ArrayList<>());
                    combined.add(new ArrayList<>());
                    int pair1Index = binarySearch2d(cowCord, 0, cowCord.size() - 1, cow1);
                    int pair2Index = binarySearch2d(cowCord, 0, cowCord.size() - 1, cow2);
                    for (int i = 1; i < cowCord.get(pair1Index).size(); i += 2) {
                        combined.get(0).add(cowCord.get(pair1Index).get(i));
                        combined.get(1).add(cowCord.get(pair1Index).get(i + 1));
                    }
                    for (int i = 1; i < cowCord.get(pair2Index).size(); i += 2) {
                        count++; //Jerry
                        int temp2 = binarySearchPairPos(combined, 0, combined.get(0).size() - 1, cowCord.get(pair2Index).get(i), cowCord.get(pair2Index).get(i + 1));
                        if (temp2 == combined.get(0).size()) {
                            combined.get(0).add(cowCord.get(pair2Index).get(i));
                            combined.get(1).add(cowCord.get(pair2Index).get(i + 1));
                        } else {
                            combined.get(0).add(temp2, cowCord.get(pair2Index).get(i));
                            combined.get(1).add(temp2, cowCord.get(pair2Index).get(i + 1));
                        }
                    }

                    if (combined.get(0).size() == 2){
                        if (combined.get(0).get(0).equals(combined.get(0).get(1)) && Math.abs(combined.get(1).get(0) - combined.get(1).get(1)) == 1){
                            twoMax = Math.max(twoMax, 2);
                        }
                        else if (combined.get(1).get(0).equals(combined.get(1).get(1)) && Math.abs(combined.get(0).get(0) - combined.get(0).get(1)) == 1){
                            twoMax = Math.max(twoMax, 2);
                        }
                    }
                    else {
                        //Mark all coordinates as true
                        boolean[][] notVisited = new boolean[n][n];
                        for (int i = 0; i < combined.get(0).size(); i++) {
                            count++; //Jerry
                            notVisited[combined.get(0).get(i)][combined.get(1).get(i)] = true;
                        }

                        //Calculate all region sizes and update max each time
                        while (combined.get(0).size() > 0) {
                            int size = 0;
                            //Calculate current region size and update max
                            size = twoRegion(combined.get(0).get(0), combined.get(1).get(0), notVisited, size, combined);
                            twoMax = Math.max(twoMax, size);
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
        System.out.println(count); //Jerry
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static int binarySearch1d(ArrayList<Integer> arr, int l, int r, int x) {
        if (r >= l) {
            if (step == 3) {
                count++; //Jerry
            }
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
        if (step == 3) {
            count++; //Jerry
        }
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

    private static int binarySearchRemove(ArrayList<ArrayList<Integer>> arr, int l, int r, int x, int y) {
        if (step == 3) {
            count++; //Jerry
        }
        if (r >= l) {
            int mid = l + (r - l) / 2;

            // If the element is present at the
            // middle itself
            if (arr.get(0).get(mid) == x && arr.get(1).get(mid) == y)
                return mid;

            if (arr.get(0).get(mid) > x)
                return binarySearchRemove(arr, l, mid - 1, x, y);
            else if (arr.get(0).get(mid) == x)
                if (arr.get(1).get(mid) > y)
                    return binarySearchRemove(arr, l, mid - 1, x, y);
                else
                    return binarySearchRemove(arr, mid + 1, r, x, y);
            else
                return binarySearchRemove(arr, mid + 1, r, x, y);
        }

        // We reach here when element is not present
        // in array
        return -1;
    }

    private static int binarySearchPos1d(ArrayList<Integer> arr, int l, int r, int x) {
        if (step == 3) {
            count++; //Jerry
        }
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
        if (step == 3) {
            count++; //Jerry
        }
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

    private static int binarySearchPairPos(ArrayList<ArrayList<Integer>> arr, int l, int r, int x, int y) {
        if (step == 3) {
            count++; //Jerry
        }
        if (r >= l) {
            int mid = l + (r - l) / 2;

            // If the element is present at the
            // middle itself
            if (arr.get(0).get(mid) == x && arr.get(1).get(mid) == y && mid != 0)
                return mid;

            if (arr.get(0).get(mid) > x)
                return binarySearchPairPos(arr, l, mid - 1, x, y);
            else if (arr.get(0).get(mid) == x)
                if (arr.get(1).get(mid) > y)
                    return binarySearchPairPos(arr, l, mid - 1, x, y);
                else
                    return binarySearchPairPos(arr, mid + 1, r, x, y);
            else
                return binarySearchPairPos(arr, mid + 1, r, x, y);
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

    private static int twoRegion(int i, int j, boolean[][] notVisited, int size, ArrayList<ArrayList<Integer>> coordinates) {
        if (step == 3) {
            count++; //Jerry
        }
        notVisited[i][j] = false;
        size++;
        int index = binarySearchRemove(coordinates, 0, coordinates.get(0).size() - 1, i, j);
        if (index != -1) {
            coordinates.get(0).remove(index);
            coordinates.get(1).remove(index);
        }
        if (i != 0) {
            if (notVisited[i - 1][j]) {
                size = twoRegion(i - 1, j, notVisited, size, coordinates);
            }
        }
        if (j != 0) {
            if (notVisited[i][j - 1]) {
                size = twoRegion(i, j - 1, notVisited, size, coordinates);
            }
        }
        if (i != notVisited.length - 1) {
            if (notVisited[i + 1][j]) {
                size = twoRegion(i + 1, j, notVisited, size, coordinates);
            }
        }
        if (j != notVisited[i].length - 1) {
            if (notVisited[i][j + 1]) {
                size = twoRegion(i, j + 1, notVisited, size, coordinates);
            }
        }
        return size;
    }
}
