import java.io.*;

public class CowSteepleChaseII2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cowjump.in"));
        PrintWriter out = new PrintWriter(new File("cowjump.out"));

        int N = Integer.parseInt(br.readLine());
        int[][] data = new int[N][4];

        int temp;
        String line;
        for (int i = 0; i < N; i++){
            line = br.readLine();
            data[i][0] = Integer.parseInt(line.substring(0, line.indexOf(' ')));
            temp = line.indexOf(' ') + 1;
            data[i][1] = Integer.parseInt(line.substring(temp, line.indexOf(' ', temp)));
            temp = line.indexOf(' ', temp) + 1;
            data[i][2] = Integer.parseInt(line.substring(temp, line.indexOf(' ', temp)));
            temp = line.indexOf(' ', temp) + 1;
            data[i][3] = Integer.parseInt(line.substring(temp));
        }

        int[] count = new int[N];
        int x1, x2, x3, x4;
        int y1, y2, y3, y4;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (j == i){
                    continue;
                }
                x1 = data[i][0];
                x2 = data[i][2];
                x3 = data[j][0];
                x4 = data[j][2];
                y1 = data[i][1];
                y2 = data[i][3];
                y3 = data[j][1];
                y4 = data[j][3];

                if (x1 == x2 && x3 == x4){
                    if (x1 == x3){
                        if (y2 >= y3 && y2 <= y4){
                            count[i]++;
                            break;
                        }
                        else if (y2 >= y4 && y2 <= y3){
                            count[i]++;
                            break;
                        }
                        else if (y1 >= y3 && y2 <= y4){
                            count[i]++;
                            break;
                        }
                        else if (y1 >= y4 && y2 <= y3){
                            count[i]++;
                            break;
                        }
                    }
                }
                else if (x1 == x2){
                    if ((x1 >= x3 && x1 <= x4) ||(x1 >= x4 && x1 <= x3)){
                        int a1 = (y2 - y1) / (x2 - x1);
                        int b1 = y1 - a1 * x1;
                        int a2 = (y4 - y3) / (x4 - x3);
                        int b2 = y3 - a2 * x3;

                        if (a1 * x3 + b1 == a2 * x3 + b2){
                            count[i]++;
                            break;
                        }
                    }
                }
                else if (x3 == x4){
                    if ((x3 >= x1 && x3 <= x2) ||(x3 >= x2 && x3 <= x1)) {
                        int a1 = (y2 - y1) / (x2 - x1);
                        int b1 = y1 - a1 * x1;
                        int a2 = (y4 - y3) / (x4 - x3);
                        int b2 = y3 - a2 * x3;

                        if (a1 * x3 + b1 == a2 * x3 + b2){
                            count[i]++;
                            break;
                        }
                    }
                }
                else {
                    int a1 = (y2 - y1) / (x2 - x1);
                    int b1 = y1 - a1 * x1;
                    int a2 = (y4 - y3) / (x4 - x3);
                    int b2 = y3 - a2 * x3;

                    if (a1 == a2){
                        if (b1 == b2){
                            if (x3 >= x1 && x3 <= x2){
                                count[i]++;
                                break;
                            }
                            else if (x4 >= x1 && x4 <= x2){
                                count[i]++;
                                break;
                            }
                            else if (x3 >= x2 && x3 <= x1){
                                count[i]++;
                                break;
                            }
                            else if (x4 >= x2 && x4 <= x1){
                                count[i]++;
                                break;
                            }
                        }
                    }
                    else {
                        int x = -(b1-b2)/(a1-a2);

                        if ((Math.min(x1, x2) < x && x < Math.max(x1, x2)) && (Math.min(x3, x4) < x && x < Math.max(x3, x4))){
                            count[i]++;
                            break;
                        }
                    }
                }
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++){
            if (count[i] > count[max]){
                max = i;
            }
        }

        out.println(max + 1);
        out.close();
    }
}
