package searcher;

import java.util.Scanner;

public class Run {
	public static void main(String args[]) {
		if(args.length != 2) {
			System.out.println(
					"Error : Required two command line arguments.	\n" + 
					"Example : java -cp file_name.jar Searcher.Run input_file_location count\n" +
					"Please try again."
			);
		} else {
			String address = args[0];
			int count = Integer.parseInt(args[1]);
			OptimalSearcher os = new OptimalSearcher(address, count);
			System.out.println("The number of words in the ArrayList is " + os.count());
			System.out.println("Enter the number of search words : ");
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			int q = sc.nextInt();
			for(int i=0; i<q; i++ ) {
				String s = sc.next();
				boolean present = os.search(s);
				if(present) {
					System.out.println("The word " + s + " is found.");
				} else {
					System.out.println("The word " + s + " is NOT found.");
				}
			}
		}
	}
}
