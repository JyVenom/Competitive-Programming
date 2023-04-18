import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class P13 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int t = ir.nextInt();
        while (t-- > 0) {
            int n = ir.nextInt();

            ArrayList<Box> boxes = new ArrayList<>(n);
            while (n-- > 0) {
                int l = ir.nextInt();
                int w = ir.nextInt();
                int h = ir.nextInt();


                if (l > w) {
                    if (w > h) { //xyz
                        boxes.add(new Box(h, w, l));
                    } else {
                        if (l > h) { //xzy
                            boxes.add(new Box(w, h, l));
                        } else { //zxy
                            boxes.add(new Box(w, l, h));
                        }
                    }
                } else {
                    if (l > h) { //yxz
                        boxes.add(new Box(h, l, w));
                    } else {
                        if (w > h) { //yzx
                            boxes.add(new Box(l, h, w));
                        } else { //zyx
                            boxes.add(new Box(l, w, h));
                        }
                    }
                }
            }

            boxes.sort((o1, o2) -> {
                if (o1.w > o2.w) {
                    return -1;
                }
                if (o1.w == o2.w) {
                    return Integer.compare(o2.l, o1.l);
                } else {
                    return 1;
                }
            });


            ArrayList<Box> rem = new ArrayList<>(boxes.size());
            int total = boxes.get(0).h, l = boxes.get(0).l, w = boxes.get(0).w;

            ArrayList<Box> seq = new ArrayList<>(boxes.size());
            for (int i = 1; i < boxes.size(); i++) {
                if (boxes.get(i).w <= w && boxes.get(i).l <= l) {
                    seq.add(boxes.get(i));
                    l = boxes.get(i).l;
                    w = boxes.get(i).w;
                    total += boxes.get(i).h;
                } else {
                    rem.add(boxes.get(i));
                }
            }

            if (rem.size() > 0) {
                for (Box box : rem) {
                    int tmp = box.w;
                    box.w = box.h;
                    box.h = tmp;
                }
            }
            boxes = new ArrayList<>(rem);
            rem = new ArrayList<>(boxes.size());
            for (Box box : boxes) {
                if (box.w >= seq.get(0).w && box.l >= seq.get(0).l) {
                    seq.add(0, box);
                    total += box.h;
                } else {
                    for (int j = 1, J = 0; j < seq.size(); j++, J++) {
                        if (box.w >= seq.get(j).w && box.l >= seq.get(j).l
                                && box.w <= seq.get(J).w && box.l <= seq.get(J).l) {
                            seq.add(j, box);
                            total += box.h;
                        } else {
                            rem.add(box);
                        }
                    }
                }
            }

            if (rem.size() > 0) {
                for (Box box : rem) {
                    int tmp = box.l;
                    box.l = box.h;
                    box.h = tmp;
                }
            }
            boxes = new ArrayList<>(rem);
            rem = new ArrayList<>(boxes.size());
            for (Box box : boxes) {
                if (box.w >= seq.get(0).w && box.l >= seq.get(0).l) {
                    seq.add(0, box);
                    total += box.h;
                } else {
                    for (int j = 1, J = 0; j < seq.size(); j++, J++) {
                        if (box.w >= seq.get(j).w && box.l >= seq.get(j).l
                                && box.w <= seq.get(J).w && box.l <= seq.get(J).l) {
                            seq.add(j, box);
                            total += box.h;
                        } else {
                            rem.add(box);
                        }
                    }
                }
            }

            pw.println(total);
        }

        pw.close();
    }

    private static class Box {
        int l, w, h;

        public Box(int l, int w, int h) {
            this.l = l;
            this.w = w;
            this.h = h;
        }
    }

    private static class InputReader2 {
        final private int BUFFER_SIZE = 1 << 16;
        private final DataInputStream dis;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public InputReader2() {
            dis = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        private int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
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
    }
}
