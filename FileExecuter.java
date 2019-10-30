import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
// CLASS: FileExecuter
// REMARKS: What is the purpose of this class?
//		To contain the function which executes the input file.
//-----------------------------------------
public class FileExecuter {

	//------------------------------------------------------
    // METHOD: executeSimulation
    //
    // PURPOSE:
    //		Reads in commands from a given text file and executes
    //		market methods to run the simulation based on those
    //		commands.
    // PARAMETERS:
    //     Market market -> the market to execute simulation on
    //------------------------------------------------------
	public static void executeSimulation(Market market) throws FileNotFoundException {
		System.out.println("Greetings user,\nplease enter input file location path \n(or name if in current directory)");

		Scanner inputReader = new Scanner(System.in);
		String filename = inputReader.nextLine();
		inputReader.close();

		Scanner fileReader = new Scanner(new FileReader(filename));

		// Variables for a line
		String line;
		Scanner lineReader;
		String command;

		while (fileReader.hasNextLine()) {

			line = fileReader.nextLine();

			if (line.length() != 0) { // if blank line do nothing

				lineReader = new Scanner(line).useDelimiter(" ");
				command = lineReader.next();

				if (line.charAt(0) == '#') // if line starts with '#' print out comment
					System.out.println(line);

				else if (command.equals("NEW"))
					market.newInvestor(lineReader.next(), lineReader.next(),
											lineReader.next(), Integer.parseInt(lineReader.next()));

				else if (command.equals("CRYPTO"))
					market.newCurrency(lineReader.next(), lineReader.next(),
											Integer.parseInt(lineReader.next()));

				else if (command.equals("MINE"))
					market.mine(lineReader.next(), lineReader.next(),
									 Integer.parseInt(lineReader.next()), line);

				else if (command.equals("TRADE"))
					market.trade(lineReader.next(), lineReader.next(), lineReader.next(),
									  Integer.parseInt(lineReader.next()), lineReader.next(),
									  Integer.parseInt(lineReader.next()), line);

				else if (command.equals("REPORT"))
					market.portfolioReport(lineReader.next());

				else if (command.equals("CRYPORT"))
					market.cryptoReport(lineReader.next());

				else if (command.equals("END")) {
					System.out.println("DONE");
					break; // file could still have more lines
				}		   // hence this stops the while loop.
				else
					System.out.println("**ERROR: Invalid command**");
			}// end if for skipping blank lines
		}// end while
		fileReader.close();
	}// end executeSimulation

}// end FileExecuter class
