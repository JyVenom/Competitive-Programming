import java.util.Comparator;

public class Sorter implements Comparator<String[]> {
    @Override
    public int compare(String[] array1, String[] array2) {
        Integer i1 = Integer.valueOf(array1[0]);
        Integer i2 = Integer.valueOf(array2[0]);
        return -i1.compareTo(i2);
    }
}
