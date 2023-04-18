import java.util.Scanner;
class ChrisBJ {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int jack = 10, queen = 10, king = 10, ace = 11;
        int randmin = 1;
        int randmax = 4;
        int min = 2;
        int max = 14;
        int random_int = (int) Math.floor(Math.random() *(max - min + 1) + min);
        int random_int2 = (int) Math.floor(Math.random() *(max - min + 1) + min);
        int random_suit = (int) Math.floor(Math.random() * (randmax - randmin + 1) + randmin);
        System.out.println(random_int);
        System.out.println(random_suit);
        String suit = "";
        String suit2 = "";
        if (random_suit == 1) {
            suit = "Heart";
        } else if (random_suit == 2) {
            suit = "Spades";
        } else if (random_suit == 3) {
            suit = "Diamond";
        } else if (random_suit == 4) {
            suit = "Club";
        }
        if (random_suit == 1) {
            suit2 = "Heart";
        } else if (random_suit == 2) {
            suit2 = "Spades";
        } else if (random_suit == 3) {
            suit2 = "Diamond";
        } else if (random_suit == 4) {
            suit2 = "Club";
        }
        if (random_int == 11) {
            random_int = random_int - 1;
            System.out.println("Your first card is a Jack of " + suit);
            String type = "jack";
        } else if (random_int == 12) {
            random_int = random_int - 2;
            System.out.println("Your first card is a Queen of " + suit);
            String type = "queen";
        } else if (random_int == 13) {
            random_int = random_int - 3;
            System.out.println("Your first card is a King of " + suit);
            String type = "king";
        } else if (random_int == 14) {
            random_int = random_int - 3;
            System.out.println("Your first card is a Ace of " + suit);
            String type = "ace";
        } else {
            System.out.println("Your first card is a " + random_int + " of " + suit);
        }
        if (random_int2 == 11) {
            random_int = random_int - 1;
            System.out.println("Your first card is a Jack of " + suit2);
            String type2 = "jack";
        } else if (random_int2 == 12) {
            random_int = random_int - 2;
            System.out.println("Your first card is a Queen of " + suit2);
            String type2 = "queen";
        } else if (random_int2 == 13) {
            random_int = random_int - 3;
            System.out.println("Your first card is a King of " + suit2);
            String type2 = "king";
        } else if (random_int2 == 14) {
            random_int = random_int - 3;
            System.out.println("Your first card is a Ace of " + suit2);
            String type2 = "ace";
        } else {
            System.out.println("Your second card is a " + random_int2 + " of " + suit2);
        }
        int total = random_int + random_int2;
        System.out.println("Your total is: " + total);
    }
}