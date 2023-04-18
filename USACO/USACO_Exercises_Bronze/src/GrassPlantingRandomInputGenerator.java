public class GrassPlantingRandomInputGenerator {
    public static void main (String[] args){
        int[][] output = new int[100][2];
        int temp;
        for (int i = 0; i < 100; i++){
            temp = getRandomIntegerBetweenRange(1, 100);
            if (getOccurrencesOfXInList(output, temp) < 3){
                output[i][0] = temp;
            }
            else {
                while (!(getOccurrencesOfXInList(output, temp) < 3)){
                    temp = getRandomIntegerBetweenRange(1, 100);
                    if (getOccurrencesOfXInList(output, temp) < 3){
                        output[i][0] = temp;
                        break;
                    }
                }
            }
            temp = getRandomIntegerBetweenRange(1, 100);
            if (getOccurrencesOfXInList(output, temp) < 3 && output[i][0] != temp){
                output[i][1] = temp;
            }
            else {
                while (!(getOccurrencesOfXInList(output, temp) < 3 && output[i][0] != temp)){
                    temp = getRandomIntegerBetweenRange(1, 100);
                    if (getOccurrencesOfXInList(output, temp) < 3 && output[i][0] != temp){
                        output[i][1] = temp;
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < output.length; i++){
            System.out.println(output[i][0] + " " + output[i][1]);
        }
        System.out.println();
    }
    public static int getRandomIntegerBetweenRange(int min, int max){
        return (int)((Math.random()*((max-min)+1))+min);
    }
    public static int getOccurrencesOfXInList(int[][] list, int x){
        int occurrences = 0;
        for (int i = 0; i < list.length; i++){
            for (int j = 0; j < list[0].length; j++){
                if (list[i][j] == x){
                    occurrences++;
                }
            }
        }
        return occurrences;
    }
}
