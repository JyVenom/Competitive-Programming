import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Prob18 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out), 1<<16));

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Agent[] agents = new Agent[st.countTokens()];
            for (int i = 0; i < agents.length; i++) {
                StringTokenizer st2 = new StringTokenizer(st.nextToken(), "=");

                agents[i] = new Agent(st2.nextToken(), Integer.parseInt(st2.nextToken()));
            }

            Arrays.sort(agents, (o1, o2) -> {
                if (o1.score < o2.score) {
                    return -1;
                }
                if (o1.score == o2.score) {
                    return o1.name.compareTo(o2.name);
                } else {
                    return 1;
                }
            });

            int max = 0;
            ArrayList<String> ans = new ArrayList<>();
            for (int i = 0; i < agents.length; i++) {
                int j = i + 1;
                for (; j < agents.length; j++) {
                    if (agents[j].score - agents[i].score > 10) {
                        int cur = j - i;
                        if (cur == max) {
                            ArrayList<String> tmp = new ArrayList<>();

                            for (int k = i; k < j; k++) {
                                tmp.add(agents[k].name);
                            }
                            Collections.sort(tmp);

                            for (int k = 0; k < max; k++) {
                                int res = tmp.get(k).compareTo(ans.get(k));
                                if (res > 0) {
                                    break;
                                } else if (res < 0) {
                                    ans = tmp;
                                    break;
                                }
                            }
                        } else if (cur > max) {
                            max = cur;

                            ans.clear();
                            for (int k = i; k < j; k++) {
                                ans.add(agents[k].name);
                            }
                            Collections.sort(ans);
                        }
                        break;
                    }
                }
                if (j == agents.length) {
                    int cur = j - i;
                    if (cur == max) {
                        ArrayList<String> tmp = new ArrayList<>();

                        for (int k = i; k < j; k++) {
                            tmp.add(agents[k].name);
                        }
                        Collections.sort(tmp);

                        for (int k = 0; k < max; k++) {
                            int res = tmp.get(k).compareTo(ans.get(k));
                            if (res > 0) {
                                break;
                            } else if (res < 0) {
                                ans = tmp;
                                break;
                            }
                        }
                    }
                    else if (cur > max) {
                        max = cur;

                        ans.clear();
                        for (int k = i; k < j; k++) {
                            ans.add(agents[k].name);
                        }
                        Collections.sort(ans);
                    }
                }
            }

            for (String an : ans) {
                pw.print(an + " ");
            }
            pw.println();
        }

        pw.close();
    }

    private static class Agent {
        String name;
        int score;

        public Agent(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }
}
