package compressor;

public class Run {
	public static void main(String args[]) {
		if(args.length != 2) {
			System.out.println(
					"Error : Required two command line arguments.	\n" + 
					"Example : java -cp file_name.jar compressor.Run input_file_location output_file_location	\n" +
					"Please try again."
			);
		} else {
			String inputFile = args[0];
			String outputFile = args[1];
			WordCompressor wc = new WordCompressor();
			wc.readFromFile(inputFile);
			wc.writeToFile(outputFile);
		}
	}
}
