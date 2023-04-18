import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class DoYouKnowYourABCs2 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int t = ir.nextInt();
        while (t-- > 0) {
            int n = ir.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = ir.nextInt();
            }

            Arrays.sort(arr);

            if (n == 7) {
                int a = arr[0];
                int b = arr[1];
                int c = arr[6] - a - b;


                if (0 < a && a <= b && b <= c) {
                    pw.println(numMissing(arr, a, b, c) == 0 ? 1 : 0);
                } else {
                    pw.println(0);
                }
            } else if (n == 6) {
                HashSet<ArrayList<Integer>> all = new HashSet<>();

                // Not missing a, b, or c
                {
                    int a = arr[0];
                    int b = arr[1];

                    { //c is less than a+b
                        int c = arr[2];

                        if (0 < a && a <= b && b <= c) {
                            if (numMissing(arr, a, b, c) == 0) {
                                ArrayList<Integer> tmp = new ArrayList<>(3);
                                tmp.add(a);
                                tmp.add(b);
                                tmp.add(c);
                                all.add(tmp);
                            }
                        }
                    }

                    {
                        int c = arr[3];

                        if (0 < a && a <= b && b <= c) {
                            if (numMissing(arr, a, b, c) == 0) {
                                ArrayList<Integer> tmp = new ArrayList<>(3);
                                tmp.add(a);
                                tmp.add(b);
                                tmp.add(c);
                                all.add(tmp);
                            }
                        }

                    }
                }

                // Missing a
                {
                    int b = arr[0];
                    { //c is less than a+b
                        int c = arr[1];
                        int a = arr[2] - b;

                        if (0 < a && a <= b && b <= c) {
                            if (numMissing(arr, a, b, c) == 0) {
                                ArrayList<Integer> tmp = new ArrayList<>(3);
                                tmp.add(a);
                                tmp.add(b);
                                tmp.add(c);
                                all.add(tmp);
                            }
                        }
                    }

                    {
                        int a = arr[1] - b;
                        int c = arr[2];

                        if (0 < a && a <= b && b <= c) {
                            if (numMissing(arr, a, b, c) == 0) {
                                ArrayList<Integer> tmp = new ArrayList<>(3);
                                tmp.add(a);
                                tmp.add(b);
                                tmp.add(c);
                                all.add(tmp);
                            }
                        }
                    }
                }

                // Missing b
                {
                    int a = arr[0];
                    { //c is less than a+b
                        int c = arr[1];
                        int b = arr[2] - a;

                        if (0 < a && a <= b && b <= c) {
                            if (numMissing(arr, a, b, c) == 0) {
                                ArrayList<Integer> tmp = new ArrayList<>(3);
                                tmp.add(a);
                                tmp.add(b);
                                tmp.add(c);
                                all.add(tmp);
                            }
                        }
                    }

                    {
                        int b = arr[1] - a;
                        int c = arr[2];

                        if (0 < a && a <= b && b <= c) {
                            if (numMissing(arr, a, b, c) == 0) {
                                ArrayList<Integer> tmp = new ArrayList<>(3);
                                tmp.add(a);
                                tmp.add(b);
                                tmp.add(c);
                                all.add(tmp);
                            }
                        }
                    }
                }

                // Missing c
                {
                    int a = arr[0];
                    int b = arr[1];
                    int c = arr[3] - a;

                    if (0 < a && a <= b && b <= c) {
                        if (numMissing(arr, a, b, c) == 0) {
                            ArrayList<Integer> tmp = new ArrayList<>(3);
                            tmp.add(a);
                            tmp.add(b);
                            tmp.add(c);
                            all.add(tmp);
                        }
                    }
                }

                pw.println(all.size());
            } else if (n == 5) {
                HashSet<ArrayList<Integer>> all = new HashSet<>();

                // Missing a and b
                {
                    { //c is less than a+b
                        int c = arr[0];
                        int a = arr[2] - c;
                        int b = arr[3] - c;

                        if (0 < a && a <= b && b <= c) {
                            if (numMissing(arr, a, b, c) == 0) {
                                ArrayList<Integer> tmp = new ArrayList<>(3);
                                tmp.add(a);
                                tmp.add(b);
                                tmp.add(c);
                                all.add(tmp);
                            }
                        }
                    }

                    {
                        int c = arr[1];
                        int a = arr[2] - c;
                        int b = arr[3] - c;

                        if (0 < a && a <= b && b <= c) {
                            if (numMissing(arr, a, b, c) == 0) {
                                ArrayList<Integer> tmp = new ArrayList<>(3);
                                tmp.add(a);
                                tmp.add(b);
                                tmp.add(c);
                                all.add(tmp);
                            }
                        }
                    }
                }

                // Missing a and c
                {
                    int b = arr[0];
                    int a = arr[1] - b;
                    int c = arr[3] - b;

                    if (0 < a && a <= b && b <= c) {
                        if (numMissing(arr, a, b, c) == 0) {
                            ArrayList<Integer> tmp = new ArrayList<>(3);
                            tmp.add(a);
                            tmp.add(b);
                            tmp.add(c);
                            all.add(tmp);
                        }
                    }
                }

                // Missing b and c
                {
                    int a = arr[0];
                    int b = arr[1] - a;
                    int c = arr[2] - a;

                    if (0 < a && a <= b && b <= c) {
                        if (numMissing(arr, a, b, c) == 0) {
                            ArrayList<Integer> tmp = new ArrayList<>(3);
                            tmp.add(a);
                            tmp.add(b);
                            tmp.add(c);
                            all.add(tmp);
                        }
                    }
                }

                // Missing a and not b, c, or a+b
                {
                    int b = arr[0];

                    {//c is less than a + b
                        int c = arr[1];
                        int a = arr[2] - b;

                        if (0 < a && a <= b && b <= c) {
                            if (numMissing(arr, a, b, c) == 0) {
                                ArrayList<Integer> tmp = new ArrayList<>(3);
                                tmp.add(a);
                                tmp.add(b);
                                tmp.add(c);
                                all.add(tmp);
                            }
                        }
                    }

                    {
                        int c = arr[2];
                        int a = arr[1] - b;

                        if (0 < a && a <= b && b <= c) {
                            if (numMissing(arr, a, b, c) == 0) {
                                ArrayList<Integer> tmp = new ArrayList<>(3);
                                tmp.add(a);
                                tmp.add(b);
                                tmp.add(c);
                                all.add(tmp);
                            }
                        }
                    }
                }

                //Missing a and a+b
                {
                    int b = arr[0];
                    int c = arr[1];
                    int a = arr[2] - c;

                    if (0 < a && a <= b && b <= c) {
                        if (numMissing(arr, a, b, c) == 0) {
                            ArrayList<Integer> tmp = new ArrayList<>(3);
                            tmp.add(a);
                            tmp.add(b);
                            tmp.add(c);
                            all.add(tmp);
                        }
                    }
                }

                //missing b and not a, c, or a+b
                {
                    int a = arr[0];

                    {//if c is less than a+b
                        int c = arr[1];
                        int b = arr[2] - a;

                        if (0 < a && a <= b && b <= c) {
                            if (numMissing(arr, a, b, c) == 0) {
                                ArrayList<Integer> tmp = new ArrayList<>(3);
                                tmp.add(a);
                                tmp.add(b);
                                tmp.add(c);
                                all.add(tmp);
                            }
                        }
                    }

                    {
                        int c = arr[2];
                        int b = arr[1] - a;

                        if (0 < a && a <= b && b <= c) {
                            if (numMissing(arr, a, b, c) == 0) {
                                ArrayList<Integer> tmp = new ArrayList<>(3);
                                tmp.add(a);
                                tmp.add(b);
                                tmp.add(c);
                                all.add(tmp);
                            }
                        }
                    }
                }

                //missing b and a+b
                {
                    int a = arr[0];
                    int c = arr[1];
                    int b = arr[3] - c;

                    if (0 < a && a <= b && b <= c) {
                        if (numMissing(arr, a, b, c) == 0) {
                            ArrayList<Integer> tmp = new ArrayList<>(3);
                            tmp.add(a);
                            tmp.add(b);
                            tmp.add(c);
                            all.add(tmp);
                        }
                    }
                }

                //missing c and not a+b+c
                {
                    int a = arr[0];
                    int b = arr[1];
                    int c = arr[4] - a - b;

                    if (0 < a && a <= b && b <= c) {
                        if (numMissing(arr, a, b, c) == 0) {
                            ArrayList<Integer> tmp = new ArrayList<>(3);
                            tmp.add(a);
                            tmp.add(b);
                            tmp.add(c);
                            all.add(tmp);
                        }
                    }
                }

                //missing c and a+b+c
                {
                    int a = arr[0];
                    int b = arr[1];
                    int c = arr[3] - a;

                    if (0 < a && a <= b && b <= c) {
                        if (numMissing(arr, a, b, c) == 0) {
                            ArrayList<Integer> tmp = new ArrayList<>(3);
                            tmp.add(a);
                            tmp.add(b);
                            tmp.add(c);
                            all.add(tmp);
                        }
                    }
                }

                // Missing other
                {
                    int a = arr[0];
                    int b = arr[1];

                    {//if c is less than a+b
                        int c = arr[2];

                        if (0 < a && a <= b && b <= c) {
                            if (numMissing(arr, a, b, c) == 0) {
                                ArrayList<Integer> tmp = new ArrayList<>(3);
                                tmp.add(a);
                                tmp.add(b);
                                tmp.add(c);
                                all.add(tmp);
                            }
                        }
                    }

                    {
                        int c = arr[3];

                        if (0 < a && a <= b && b <= c) {
                            if (numMissing(arr, a, b, c) == 0) {
                                ArrayList<Integer> tmp = new ArrayList<>(3);
                                tmp.add(a);
                                tmp.add(b);
                                tmp.add(c);
                                all.add(tmp);
                            }
                        }

                    }
                }

                pw.println(all.size());
            } else if (n == 4) {
                HashSet<ArrayList<Integer>> all = new HashSet<>();

                // Missing a, b, c
                {
                    int a = (int) (((long) arr[0] + (long) arr[1] - (long) arr[2]) / 2); //(a+b)+(a+c)-(b+c)=2a
                    int b = arr[0] - a;
                    int c = arr[1] - a;

                    if (0 < a && a <= b && b <= c) {
                        if (numMissing(arr, a, b, c) == 0) {
                            ArrayList<Integer> tmp = new ArrayList<>(3);
                            tmp.add(a);
                            tmp.add(b);
                            tmp.add(c);
                            all.add(tmp);
                        }
                    }
                }

                // Missing a, b, a+b
                {
                    int c = arr[0];
                    int a = arr[1] - c;
                    int b = arr[2] - c;

                    if (0 < a && a <= b && b <= c) {
                        if (numMissing(arr, a, b, c) == 0) {
                            ArrayList<Integer> tmp = new ArrayList<>(3);
                            tmp.add(a);
                            tmp.add(b);
                            tmp.add(c);
                            all.add(tmp);
                        }
                    }
                }

                // Missing a, b, a+c
                {
                    {//if c is less than a+b
                        int c = arr[0];
                        int b = arr[2] - c;
                        int a = arr[1] - b;

                        if (0 < a && a <= b && b <= c) {
                            if (numMissing(arr, a, b, c) == 0) {
                                ArrayList<Integer> tmp = new ArrayList<>(3);
                                tmp.add(a);
                                tmp.add(b);
                                tmp.add(c);
                                all.add(tmp);
                            }
                        }
                    }

                    {
                        int c = arr[1];
                        int b = arr[2] - c;
                        int a = arr[0] - b;

                        if (0 < a && a <= b && b <= c) {
                            if (numMissing(arr, a, b, c) == 0) {
                                ArrayList<Integer> tmp = new ArrayList<>(3);
                                tmp.add(a);
                                tmp.add(b);
                                tmp.add(c);
                                all.add(tmp);
                            }
                        }
                    }
                }

                // Missing a, b, b+c
                {
                    { //if c is less than a+b
                        int c = arr[0];
                        int a = arr[2] - c;
                        int b = arr[1] - a;

                        if (0 < a && a <= b && b <= c) {
                            if (numMissing(arr, a, b, c) == 0) {
                                ArrayList<Integer> tmp = new ArrayList<>(3);
                                tmp.add(a);
                                tmp.add(b);
                                tmp.add(c);
                                all.add(tmp);
                            }
                        }
                    }

                    {
                        int c = arr[1];
                        int a = arr[2] - c;
                        int b = arr[0] - a;

                        if (0 < a && a <= b && b <= c) {
                            if (numMissing(arr, a, b, c) == 0) {
                                ArrayList<Integer> tmp = new ArrayList<>(3);
                                tmp.add(a);
                                tmp.add(b);
                                tmp.add(c);
                                all.add(tmp);
                            }
                        }
                    }
                }

                // Missing a, b, a+b+c
                {

                    { //if c is less than a+b
                        int c = arr[0];
                        int a = arr[2] - c;
                        int b = arr[3] - c;

                        if (0 < a && a <= b && b <= c) {
                            if (numMissing(arr, a, b, c) == 0) {
                                ArrayList<Integer> tmp = new ArrayList<>(3);
                                tmp.add(a);
                                tmp.add(b);
                                tmp.add(c);
                                all.add(tmp);
                            }
                        }
                    }

                    {
                        int c = arr[1];
                        int a = arr[2] - c;
                        int b = arr[3] - c;

                        if (0 < a && a <= b && b <= c) {
                            if (numMissing(arr, a, b, c) == 0) {
                                ArrayList<Integer> tmp = new ArrayList<>(3);
                                tmp.add(a);
                                tmp.add(b);
                                tmp.add(c);
                                all.add(tmp);
                            }
                        }
                    }
                }

                // Missing a, c, a+b
                {
                    int b = arr[0];
                    int c = arr[2] - b;
                    int a = arr[1] - c;

                    if (0 < a && a <= b && b <= c) {
                        if (numMissing(arr, a, b, c) == 0) {
                            ArrayList<Integer> tmp = new ArrayList<>(3);
                            tmp.add(a);
                            tmp.add(b);
                            tmp.add(c);
                            all.add(tmp);
                        }
                    }
                }

                // Missing a, c, a+c
                {
                    int b = arr[0];
                    int a = arr[1] - b;
                    int c = arr[2] - b;

                    if (0 < a && a <= b && b <= c) {
                        if (numMissing(arr, a, b, c) == 0) {
                            ArrayList<Integer> tmp = new ArrayList<>(3);
                            tmp.add(a);
                            tmp.add(b);
                            tmp.add(c);
                            all.add(tmp);
                        }
                    }
                }

                // Missing a, c, b+c
                {
                    int b = arr[0];
                    int a = arr[1] - b;
                    int c = arr[2] - a;

                    if (0 < a && a <= b && b <= c) {
                        if (numMissing(arr, a, b, c) == 0) {
                            ArrayList<Integer> tmp = new ArrayList<>(3);
                            tmp.add(a);
                            tmp.add(b);
                            tmp.add(c);
                            all.add(tmp);
                        }
                    }
                }

                // Missing a, c, a+b+c
                {
                    int b = arr[0];
                    int a = arr[1] - b;
                    int c = arr[3] - b;

                    if (0 < a && a <= b && b <= c) {
                        if (numMissing(arr, a, b, c) == 0) {
                            ArrayList<Integer> tmp = new ArrayList<>(3);
                            tmp.add(a);
                            tmp.add(b);
                            tmp.add(c);
                            all.add(tmp);
                        }
                    }
                }

                // Missing b, c, a+b
                {
                    int a = arr[0];
                    int c = arr[1] - a;
                    int b = arr[2] - c;

                    if (0 < a && a <= b && b <= c) {
                        if (numMissing(arr, a, b, c) == 0) {
                            ArrayList<Integer> tmp = new ArrayList<>(3);
                            tmp.add(a);
                            tmp.add(b);
                            tmp.add(c);
                            all.add(tmp);
                        }
                    }
                }

                // Missing b, c, a+c
                {
                    int a = arr[0];
                    int b = arr[1] - a;
                    int c = arr[2] - b;

                    if (0 < a && a <= b && b <= c) {
                        if (numMissing(arr, a, b, c) == 0) {
                            ArrayList<Integer> tmp = new ArrayList<>(3);
                            tmp.add(a);
                            tmp.add(b);
                            tmp.add(c);
                            all.add(tmp);
                        }
                    }
                }

                // Missing b, c, b+c
                {
                    int a = arr[0];
                    int b = arr[1] - a;
                    int c = arr[2] - a;

                    if (0 < a && a <= b && b <= c) {
                        if (numMissing(arr, a, b, c) == 0) {
                            ArrayList<Integer> tmp = new ArrayList<>(3);
                            tmp.add(a);
                            tmp.add(b);
                            tmp.add(c);
                            all.add(tmp);
                        }
                    }
                }

                // Missing b, c, a+b+c
                {
                    int a = arr[0];
                    int b = arr[1] - a;
                    int c = arr[2] - a;

                    if (0 < a && a <= b && b <= c) {
                        if (numMissing(arr, a, b, c) == 0) {
                            ArrayList<Integer> tmp = new ArrayList<>(3);
                            tmp.add(a);
                            tmp.add(b);
                            tmp.add(c);
                            all.add(tmp);
                        }
                    }
                }

                // Missing a, a+b, a+c
                {
                    int b = arr[0];
                    int c = arr[1];
                    int a = arr[3] - b - c;

                    if (0 < a && a <= b && b <= c) {
                        if (numMissing(arr, a, b, c) == 0) {
                            ArrayList<Integer> tmp = new ArrayList<>(3);
                            tmp.add(a);
                            tmp.add(b);
                            tmp.add(c);
                            all.add(tmp);
                        }
                    }
                }

                // Missing a, a+b, b+c
                {
                    int b = arr[0];
                    int c = arr[1];
                    int a = arr[2] - c;

                    if (0 < a && a <= b && b <= c) {
                        if (numMissing(arr, a, b, c) == 0) {
                            ArrayList<Integer> tmp = new ArrayList<>(3);
                            tmp.add(a);
                            tmp.add(b);
                            tmp.add(c);
                            all.add(tmp);
                        }
                    }
                }

                // Missing a, a+c, b+c
                {
                    int b = arr[0];

                    {//if c is less than a+b
                        int c = arr[1];
                        int a = arr[2] - b;

                        if (0 < a && a <= b && b <= c) {
                            if (numMissing(arr, a, b, c) == 0) {
                                ArrayList<Integer> tmp = new ArrayList<>(3);
                                tmp.add(a);
                                tmp.add(b);
                                tmp.add(c);
                                all.add(tmp);
                            }
                        }
                    }

                    {
                        int c = arr[2];
                        int a = arr[1] - b;

                        if (0 < a && a <= b && b <= c) {
                            if (numMissing(arr, a, b, c) == 0) {
                                ArrayList<Integer> tmp = new ArrayList<>(3);
                                tmp.add(a);
                                tmp.add(b);
                                tmp.add(c);
                                all.add(tmp);
                            }
                        }
                    }
                }

                // Missing b, a+b, a+c
                {
                    int a = arr[0];
                    int c = arr[1];
                    int b = arr[2] - c;

                    if (0 < a && a <= b && b <= c) {
                        if (numMissing(arr, a, b, c) == 0) {
                            ArrayList<Integer> tmp = new ArrayList<>(3);
                            tmp.add(a);
                            tmp.add(b);
                            tmp.add(c);
                            all.add(tmp);
                        }
                    }
                }

                // Missing b, a+b, b+c
                {
                    int a = arr[0];
                    int c = arr[1];
                    int b = arr[3] - a - c;

                    if (0 < a && a <= b && b <= c) {
                        if (numMissing(arr, a, b, c) == 0) {
                            ArrayList<Integer> tmp = new ArrayList<>(3);
                            tmp.add(a);
                            tmp.add(b);
                            tmp.add(c);
                            all.add(tmp);
                        }
                    }
                }

                // Missing b, a+c, b+c
                {
                    int a = arr[0];

                    {//if c is less than a+b
                        int c = arr[1];
                        int b = arr[2] - a;

                        if (0 < a && a <= b && b <= c) {
                            if (numMissing(arr, a, b, c) == 0) {
                                ArrayList<Integer> tmp = new ArrayList<>(3);
                                tmp.add(a);
                                tmp.add(b);
                                tmp.add(c);
                                all.add(tmp);
                            }
                        }
                    }

                    {
                        int c = arr[2];
                        int b = arr[1] - a;

                        if (0 < a && a <= b && b <= c) {
                            if (numMissing(arr, a, b, c) == 0) {
                                ArrayList<Integer> tmp = new ArrayList<>(3);
                                tmp.add(a);
                                tmp.add(b);
                                tmp.add(c);
                                all.add(tmp);
                            }
                        }

                    }
                }

                // Missing c, a+b, a+c
                {
                    int a = arr[0];
                    int b = arr[1];
                    int c = arr[2] - b;

                    if (0 < a && a <= b && b <= c) {
                        if (numMissing(arr, a, b, c) == 0) {
                            ArrayList<Integer> tmp = new ArrayList<>(3);
                            tmp.add(a);
                            tmp.add(b);
                            tmp.add(c);
                            all.add(tmp);
                        }
                    }
                }

                // Missing c, a+b, b+c
                {
                    int a = arr[0];
                    int b = arr[1];
                    int c = arr[2] - a;

                    if (0 < a && a <= b && b <= c) {
                        if (numMissing(arr, a, b, c) == 0) {
                            ArrayList<Integer> tmp = new ArrayList<>(3);
                            tmp.add(a);
                            tmp.add(b);
                            tmp.add(c);
                            all.add(tmp);
                        }
                    }
                }

                // Missing c, a+c, b+c
                {
                    int a = arr[0];
                    int b = arr[1];
                    int c = arr[3] - a - b;

                    if (0 < a && a <= b && b <= c) {
                        if (numMissing(arr, a, b, c) == 0) {
                            ArrayList<Integer> tmp = new ArrayList<>(3);
                            tmp.add(a);
                            tmp.add(b);
                            tmp.add(c);
                            all.add(tmp);
                        }
                    }
                }

                // Not missing a, b, or c but missing a+b
                {
                    int a = arr[0];
                    int b = arr[1];

                    int c = arr[2];

                    if (0 < a && a <= b && b <= c) {
                        if (numMissing(arr, a, b, c) == 0) {
                            ArrayList<Integer> tmp = new ArrayList<>(3);
                            tmp.add(a);
                            tmp.add(b);
                            tmp.add(c);
                            all.add(tmp);
                        }
                    }
                }

                //Not missing a, b, c, or a+b
                {
                    int a = arr[0];
                    int b = arr[1];

                    {//c is less than a+b
                        int c = arr[2];

                        if (0 < a && a <= b && b <= c) {
                            if (numMissing(arr, a, b, c) == 0) {
                                ArrayList<Integer> tmp = new ArrayList<>(3);
                                tmp.add(a);
                                tmp.add(b);
                                tmp.add(c);
                                all.add(tmp);
                            }
                        }
                    }

                    {
                        int c = arr[3];

                        if (0 < a && a <= b && b <= c) {
                            if (numMissing(arr, a, b, c) == 0) {
                                ArrayList<Integer> tmp = new ArrayList<>(3);
                                tmp.add(a);
                                tmp.add(b);
                                tmp.add(c);
                                all.add(tmp);
                            }
                        }

                    }
                }

                pw.println(all.size());
            }
        }

        pw.close();
    }

    private static int numMissing(int[] arr, int a, int b, int c) {
        HashSet<Integer> pos = new HashSet<>(7);
        pos.add(a);
        pos.add(b);
        pos.add(c);
        pos.add(a + b);
        pos.add(b + c);
        pos.add(c + a);
        pos.add(a + b + c);
        int count = 0;
        for (int i : arr) {
            if (!pos.contains(i)) {
                count++;
            }
        }
        return count;
    }

    private static class InputReader2 {
        private final int BUFFER_SIZE = 1 << 16;
        private final DataInputStream dis;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public InputReader2() {
            dis = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        private String nextLine() throws IOException {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        private int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        private long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        private double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }
            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = dis.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        private void close() throws IOException {
            dis.close();
        }
    }
}
