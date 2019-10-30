import java.util.Objects;
// CLASS: Cryptocurrency
// REMARKS: What is the purpose of this class?
//	To represent a cryptocurrency which contains
//	related variables such as name, symbol, quantity.
//	Each also may contain its own blockchain.
//-----------------------------------------
public class Cryptocurrency extends ListItem {
	
	private String name;
	private String symbol;
	private int quantity;
	private Blockchain blockchain;

	// Constructor for a Cryptocurrency
	// PARAMETER: boolean bc
	//	determins if a blockchain should be created or not since
	//	cryptocurrencies in an Investor's portfolio do not need a blockchain.
	public Cryptocurrency(String name, String symbol, int quantity, boolean bc) {
		this.name = name;
		this.symbol = symbol;
		this.quantity = quantity;

		if (bc)
			this.blockchain = new Blockchain();
		else
			this.blockchain = null;
	}// end constructor


	//------------------------------------------------------
    // METHOD: addQuantity
    //
    // PURPOSE:
    //		Simply adds a given q to the quantity variable.
    // PARAMETERS:
    //     int q -> quantity to add
    //------------------------------------------------------
	public void addQuantity(int q) {
		this.quantity += q;
	}// end addQuantity


	//------------------------------------------------------
    // METHOD: subtractQuantity
    //
    // PURPOSE:
    //		Simply subtracts a given q to the quantity variable.
    // PARAMETERS:
    //     int q -> quantity to subtract
    //------------------------------------------------------
	public void subtractQuantity(int q) {
		this.quantity -= q;
	}// end subtractQuantity


	//------------------------------------------------------
    // METHOD: verifyBlockchain
    //
    // PURPOSE:
    //		Verifies the cryptocurrency's blockchain by
    //		hashing the information in each block starting
    //		at the head in the same manner that was done
    //		when block was added. Also gives success/fail message.
    //------------------------------------------------------
	public void verifyBlockchain() {
		boolean success = true;
		Block curr = (Block) blockchain.head;

		while ( curr != null && success ) {

			if ( Objects.hash(curr.getItem(), curr.getPrevHashCode()) == curr.getHashCode())
				curr = (Block) curr.getNext();
			else {
				success = false;
				System.out.println("\t| \t VERIFICATION of \"" + this.symbol + "\": FALED - a block has been altered");
			}// end else
		}// end while

		if (success)
			System.out.println("\t| \t\t VERIFICATION of \"" + this.symbol + "\": SUCCESS");
	}// end verifyBlockchain


	//------------------------------------------------------
    // METHOD: print
    //
    // PURPOSE:
    //		Prints information about cryptocurency and
    //		maybe also its blockchain (list of transactions)
    // PARAMETERS:
    //     boolean tab -> determins if currency should print
	//					for case of an Investor's holdings
	//					or standalone report (with blockchain).
	//					difference being the tab formating.
    //------------------------------------------------------
	@Override
	public void print(boolean tab) {

		if (tab) // printing for HOLDINGS of REPORT of an Investor
			System.out.println("\t|\t"+this.name+" (" + this.symbol+") \t QUANTITY: "+quantity);
		else {
			// printing for CRYPORT
			System.out.println("\t| REPORT for "+this.name+" ("+this.symbol+") \t QUANTITY: "+quantity);
			System.out.println("\t|-------<TRANSACTIONS>-------------------------------------------------");
			blockchain.print(true);
		}// end else
	}// end print


	//------------------------------------------------------
    // METHOD: isEqual
    //
    // PURPOSE:
    //		Determines if an a given symbol is equal to this
    //		cryptocurrency's symbol (its identifier)
    // PARAMETERS:
    //     String sym -> the symbol requested to compare
    // Returns: true if sym is equal to symbol, false if not.
    //------------------------------------------------------
	@Override
	public boolean isEqual(String sym) {
		return (this.symbol.equals(sym));
	}// end isEqual


	// GETTERS (needed) FOR CRYPTOCURRENCY
	// returns name
	public String getName() {
		return name;
	}// end getName

	// returns symbol
	public String getSymbol() {
		return symbol;
	}// end getSymbol

	// returns quantity
	public int getQuantity() {
		return quantity;
	}// end getQuantity
	
	// returns blockchain
	public Blockchain getBlockchain() {
		return blockchain;
	}// end getBlockchain
	
	
}// end Cryptocurrency class
