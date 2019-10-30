// CLASS: Investor
// REMARKS: What is the purpose of this class?
//	To represent an Investor which contains
//	related variables such as name, userID, cash.
//	Each investor also contain their own list of
//	cryptocurrencies (portfolio).
//-----------------------------------------
public class Investor extends ListItem {
	private String firstName;
	private String lastName;
	private String userID;
	private int cash;
	private List portfolio; // List of cryptocurrencies

	// Constructor for an Investor, creates a new List as their portfolio
	public Investor(String firstName, String lastName, String userID, int cash) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userID = userID;
		this.cash = cash;
		this.portfolio = new List();
	}// end constructor

	
	//------------------------------------------------------
    // METHOD: print
    //
    // PURPOSE:
    //		Prints information about an investor and
    //		their holdings (portfolio crypto list)
    // PARAMETERS:
    //     boolean tab -> not used, could be for tab formatting.
    //------------------------------------------------------
	@Override
	public void print(boolean tab) {

		System.out.println("\t _______________________________________________________________________");
		System.out.println("\t| PORTFOLIO of "+this.firstName+" "+this.lastName+"\t   ID: "+this.userID
								+"\t   CASH: ["+this.cash+" CAD] \t|");
		System.out.println("\t|-------<HOLDINGS>------------------------------------------------------|");
		portfolio.print(true); // determines if
		System.out.println("\t|_______________________________________________________________________|\n");
	}// end print

	
	//------------------------------------------------------
    // METHOD: isEqual
    //
    // PURPOSE: 
	//		Determines if an a given userID is equal to this
    //		investor's userID (the identifier).
    // PARAMETERS:
    //     String id -> the userID requested to compare
    // Returns: true if userID is equal, false if not.
    //------------------------------------------------------
	@Override
	public boolean isEqual(String id) {
		return (this.userID.equals(id));
	}// end isEqual


	//------------------------------------------------------
    // METHOD: receive
    //
    // PURPOSE:    
	//		Determines whether to deposit a crypto or CAD.
    // PARAMETERS:
    //     Cryptocurrency crypto ->	crypto to deposit in
	//				   int quant -> quantity to deposit in
    //------------------------------------------------------
	public void receive(Cryptocurrency crypto, int quant) {

		if (crypto == null)
			receiveCAD(quant);
		else 
			receiveCrypto(crypto, quant);
	}// end receive


	//------------------------------------------------------
    // METHOD: send
    //
    // PURPOSE:    
	//		Determines whether to withdraw a crypto or CAD.
    // PARAMETERS:
    // 		String sym -> given currency identifier
	//		 int quant -> quantity to send
    //------------------------------------------------------
	public void send(String sym, int quant) {

		if (sym.equals("CAD"))
			sendCAD(quant);
		else
			sendCrypto(sym, quant);
	}// end send


	//------------------------------------------------------
    // METHOD: receiveCrypto
    //
    // PURPOSE:
	//		Adds this crypto with set quantity to portfolio
	//		if it doesn't exist, otherwise add given quantity.
    // PARAMETERS:
    //     Cryptocurrency crypto ->	crypto to deposit in
	//				   int quant -> quantity to add in
    //------------------------------------------------------
	public void receiveCrypto(Cryptocurrency crypto, int quantity) {
		Cryptocurrency portfolioCrypto = (Cryptocurrency) portfolio.search(crypto.getSymbol());

		if (portfolioCrypto == null)
			portfolio.addFront(new Cryptocurrency(crypto.getName(), crypto.getSymbol(), quantity, false));
		else
			portfolioCrypto.addQuantity(quantity);
	}//end receiveCrypto



	//------------------------------------------------------
    // METHOD: sendCrypto
    //
    // PURPOSE:
	//		Simply subtracts a quantity of crypto with
	//		symbol sym from investor's portfolio.
    // PARAMETERS:
    // 		String sym -> given crypto identifier
	//		 int quant -> quantity to subtract
    //------------------------------------------------------
	public void sendCrypto(String sym, int quantity) {
		((Cryptocurrency) portfolio.search(sym)).subtractQuantity(quantity);
	}// end sendCrypto


	//------------------------------------------------------
    // METHOD: receiveCAD
    //
    // PURPOSE:
	//		Simply adds a given quantity to investor's
	//		cash CAD amount.
    // PARAMETERS:
    //     int quantity -> quantity to add
    //------------------------------------------------------
	public void receiveCAD(int quantity) {
		cash += quantity;
	}// end receiveCAD


	//------------------------------------------------------
    // METHOD: sendCAD
    //
    // PURPOSE:
	//		Simply subtracts a given quantity from investor's
	//		cash CAD amount.
    // PARAMETERS:
    //     int quantity -> quantity to subtract
    //------------------------------------------------------
	public void sendCAD(int quantity) {
		cash -= quantity;
	}// end sendCAD


	//------------------------------------------------------
    // METHOD: verifyTransaction
    //
    // PURPOSE:
	//		Verifies the transaction by checking if
	//		investor has enough quantity of given crypto
	//		via sym identifier to send.
    // PARAMETERS:
    // 		String sym -> given crypto identifier
	//		 int quant -> quantity to verify for sufficiency
    // Returns: returns true if investor has sufficient quantity.
    //------------------------------------------------------
	public boolean verifyTransaction(String sym, int quant) {
		boolean success = false;
		Cryptocurrency crypto = (Cryptocurrency) portfolio.search(sym);

		if ( crypto != null ) {
			if (crypto.getQuantity() >= quant) 
				success = true;
			else
				System.out.println("\tERROR: Investor \"" + this.userID + 
						"\" has insufficient \"" + crypto.getSymbol() + "\" to send.");
		}
		else if (sym.equals("CAD")) {
			if ( cash >= quant ) 
				success =  true;
			else 
				System.out.println("\tERROR: Investor \"" + userID + 
						"\" has insufficient CAD to send.");
		}
		else
			System.out.println("\tERROR: Investor \"" + this.userID + 
					"\" does not have any \"" + sym + "\"");

		return success;
	}// end verifyTransaction
	
	// GETTER (needed) FOR INVESTOR
	// returns userID
	public String getUserID() {
		return userID;
	}// end getUserID

}// end Investor class
