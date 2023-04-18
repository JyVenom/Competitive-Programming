import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        int[] list2 = new int[1000000];
        for (int i = 0; i < 1000000; i++){
            int random = (int) (Math.random() * 100000);
            list.add(random);
            list2[i] = random;
        }

        long startTime = System.nanoTime();
        list.remove(0);
        long endTime = System.nanoTime();
        long durationInNano = (endTime - startTime);
        System.out.println(durationInNano);
        long startTime2 = System.nanoTime();
        System.arraycopy(list2, 1, list2, 0, 999999);
        list2[list2.length - 1] = 0;
        long endTime2 = System.nanoTime();
        long durationInNano2 = (endTime2 - startTime2);
        System.out.println(durationInNano2);
    }
}
