import java.util.ArrayList;
import java.util.Collections;

public class CTFA32 {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> comb = new ArrayList<>();
        ArrayList<Integer> data = new ArrayList<>();
        for (int i = 0; i < 30; i++){
            data.add(i);
        }
        add(data, comb);
//        for (int i = 0; i < comb.size(); i++){
//            for (int j = i + 1; j < comb.size(); j++){
//                if (same(comb.get(i), comb.get(j))){
//                    comb.remove(j);
//                    j--;
//                }
//            }
//        }
        System.out.println(comb.size());
    }

    private static void shift (ArrayList<Integer> list){
        int temp = list.get(0);
        list.remove(0);
        list.add(temp);
    }

    private static boolean same(ArrayList<Integer> l1, ArrayList<Integer> l2){
        for (int i = 0; i < l1.size(); i++) {
            int temp = l1.get(0);
            if (l2.contains(temp)) {
                l2.remove((Integer) temp);
                l1.remove(0);
            } else {
                return false;
            }
        }
        return true;
    }

    private static void add (ArrayList<Integer> list, ArrayList<ArrayList<Integer>> data){
        Collections.sort(list);
        if (!data.contains(list)) {
            data.add(list);
        }
        if (list.size() > 1) {
            ArrayList<Integer> l2 = new ArrayList<>(list);
            l2.remove(0);
            add(l2, data);
            shift(list);
            add(list, data);
        }
    }
}
