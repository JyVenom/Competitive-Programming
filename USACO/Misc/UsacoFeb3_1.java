import java.util.Arrays;
import java.util.Scanner;

public class EmailFiling {
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		
		for (int j = 0; j < t; j++) {
			int totalF = in.nextInt();
			int totalE = in.nextInt();
			int window = in.nextInt();
			
			int[] count = new int[totalF+1];
			int[] arrayE = new int[totalE];
			for (int i = 0; i < totalE; i++) {
				int x = in.nextInt();
				arrayE[i] = x;
				count[x] += 1;
			}

			int min = 1;
			int max = min+window-1;
			
			boolean[] move = new boolean[totalF+1];
			int moveInd = 1;
			for (int i = 0; i < totalE; i++) {
				if (min <= arrayE[i] && arrayE[i] <= max) {
					count[arrayE[i]]--;
					if (count[arrayE[i]] == 0) move[arrayE[i]] = true;
					arrayE[i] = 0;
				}
				
				
				
				while (move[moveInd] && max < totalF) {
					min++;					
					moveInd++;
					max = min+window-1;
				}
				
			}
			

			
			boolean good = true;
			for (int i = totalE-1; i >= 0; i--) {
				if (arrayE[i] == 0) continue;
				if (arrayE[i] > max) {
					System.out.println("NO");
					good = false;
					break;
				}
				
				if (min <= arrayE[i] && arrayE[i] <= max) {
					count[arrayE[i]]--;
					if (count[arrayE[i]] == 0) move[arrayE[i]] = true;
					arrayE[i] = 0;
				}
				
				while (move[moveInd] && max < totalF) {
					min++;					
					moveInd++;
					max = min+window-1;
				}
			}
			
			for (int i = 1; i <= totalF; i++) {
				if (!good) break;
				
				if (count[i] > 0) {
					System.out.println("NO");
					good = false;
				}
				
				
			}
			if (good) System.out.println("YES");
			
		}
	}

}

