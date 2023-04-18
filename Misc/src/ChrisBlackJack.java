import java.util.Scanner;

public class ChrisBlackJack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int jack = 10, queen = 10, king = 10, ace = 11;
        int i = 5, answer;
        int randmin = 1;
        int randmax = 4;
        int min = 2;
        int max = 14;
        int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
        int random_suit = (int) Math.floor(Math.random() * (randmax - randmin + 1) + randmin);
        System.out.println(random_int);
        String suit = "";
        if (random_suit == 1) {
            suit = "Heart";
        }
        if (random_suit == 2) {
            suit = "Spades";
        }
        if (random_suit == 3) {
            suit = "Diamond";
        }
        if (random_suit == 4) {
            suit = "Club";
        }
        System.out.println("Your card is a " + random_int + " " + suit);
    }
}
