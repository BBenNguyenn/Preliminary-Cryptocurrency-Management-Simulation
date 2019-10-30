// CLASS: Transaction
// REMARKS:
//		To contain information about a transaction 
//		(mine or trade) in the form of a single String.
//-----------------------------------------
public class Transaction extends ListItem {

	private String transCommand;
	
	// Constructor for a transaction
	public Transaction(String transCommand) {
		this.transCommand = transCommand;
	}// end Constructor
	
	
	//------------------------------------------------------
    // METHOD: print
    //
    // PURPOSE:
    //		Prints the transaction string
    // PARAMETERS:
    //     	boolean tab -> determines if there should be a tab,
	//		for formatting only.
    //------------------------------------------------------
	@Override
	protected void print(boolean tab) {
		if (tab)
			System.out.println("\t|\t Transaction: [ " + this.transCommand + " ]");
		else
			System.out.println("Transaction: [ " + this.transCommand + " ]");
	}// end print
	

	//------------------------------------------------------
    // METHOD: isEqual
    //
    // PURPOSE:
    //		Determines if the given transaction is the same
    // PARAMETERS:
    //     String trans -> the transaction requested to compare
    // Returns: true if is equal, false if not.
    //------------------------------------------------------
	@Override
	protected boolean isEqual(String trans) {
		return (this.transCommand.equals(trans));
	}// end isEqual

	
}// end Transaction class
