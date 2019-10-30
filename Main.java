import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;
import java.util.Scanner;
//-----------------------------------------
// REMARKS: What is the purpose of this program?
//	To simulate a cryptocurrency exchange market
//	making heavy use of OOP concepts especially
//	inheritance and polymorphism.
//-----------------------------------------
public class Main {
	
    //------------------------------------------------------
    // METHOD: main
    // PURPOSE: Executes the program....
    //------------------------------------------------------
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("\nCRYPTOCURRENCY MANAGEMENT SIMULATION\n");
		FileExecuter.executeSimulation(new Market()); // Theoretically have many markets
		System.out.println("\nProgram ended successfully.\n");
	}// end main
	
}// end MainReader
