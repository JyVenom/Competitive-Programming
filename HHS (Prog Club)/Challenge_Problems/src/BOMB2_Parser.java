//import java.io.*;
//import java.util.StringTokenizer;
//
//public class BOMB2_Parser {
//    public static void main(String[] args) throws IOException {
//        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("out.txt")));
//
//        BufferedReader br = new BufferedReader(new FileReader("instructions.txt"));
//        String line;
//        while (!((line = br.readLine()) == null)) {
//            StringTokenizer st = new StringTokenizer(line);
//            st.nextToken(); //If
//            if (st.nextToken().equals("the")) {
//                String tmp = st.nextToken(); //xth
//                int num = Integer.parseInt(tmp.substring(0, tmp.length() - 2)) - 1;
//                st.nextToken(); //wire
//                st.nextToken(); //is
//                tmp = st.nextToken(); //color
//                tmp = tmp.substring(0, tmp.length() - 1); // remove comma
//                pw.print("else if (wires[" + num + "].equals(\"" + tmp + "\")) pw.println(\"Cut wire ");
//                st.nextToken(); //cut
//                st.nextToken(); //the
//                tmp = st.nextToken(); //xth
//                num = Integer.parseInt(tmp.substring(0, tmp.length() - 2));
//                pw.println(num + "\");");
//            } else { //there
//                st.nextToken(); //is/are
//                String tmp = st.nextToken(); //exactly, more, less
//                if (tmp.equals("exactly")) {
//                    int num = Integer.parseInt(st.nextToken());
//                    tmp = st.nextToken(); //color
//                    pw.print("else if (count.get(\"" + tmp + "\")==" + num + ") pw.println(\"Cut wire ");
//                    st.nextToken(); //wire(s)
//                    st.nextToken(); //cut
//                    st.nextToken(); //the
//                    tmp = st.nextToken(); //xth
//                    num = Integer.parseInt(tmp.substring(0, tmp.length() - 2));
//                    pw.println(num + "\");");
//                } else if (tmp.equals("more")) {
//                    st.nextToken(); //than
//                    int num = Integer.parseInt(st.nextToken());
//                    tmp = st.nextToken(); //color
//                    pw.print("else if (count.get(\"" + tmp + "\")>" + num + ") pw.println(\"Cut wire ");
//                    st.nextToken(); //wire(s)
//                    st.nextToken(); //cut
//                    st.nextToken(); //the
//                    tmp = st.nextToken(); //xth
//                    num = Integer.parseInt(tmp.substring(0, tmp.length() - 2));
//                    pw.println(num + "\");");
//                } else { //less
//                    st.nextToken(); //than
//                    int num = Integer.parseInt(st.nextToken());
//                    tmp = st.nextToken(); //color
//                    pw.print("else if (count.get(\"" + tmp + "\")<" + num + ") pw.println(\"Cut wire ");
//                    st.nextToken(); //wire(s)
//                    st.nextToken(); //cut
//                    st.nextToken(); //the
//                    tmp = st.nextToken(); //xth
//                    num = Integer.parseInt(tmp.substring(0, tmp.length() - 2));
//                    pw.println(num + "\");");
//                }
//            }
//        }
//        pw.println("else pw.println(\"Cut wire 1\");");
//
//        pw.close();
//    }
//}
