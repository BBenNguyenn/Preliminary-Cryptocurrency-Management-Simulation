// CLASS: Market
// REMARKS:
//		To represent a market (universe) containing
//		lists of investors and cryptocurrencies
//		which exist in this market.
//		Also contains all methods which execute given commands.
//-----------------------------------------
public class Market {

	private List investorsList;
	private List cryptocurrenciesList;

	// Constructor for a new market
	public Market() {
		this.investorsList = new List();
		this.cryptocurrenciesList = new List();
	}// end Constructor

	
    //------------------------------------------------------
    // METHOD: newInvestor
    //
    // PURPOSE:
	//		Creates and adds a new investor to the market
	//		by adding a new investor object to investorsList.
    // PARAMETERS:
    //		String firstName -> input first name
	//		 String lastName -> input last name
	//		   String userID -> input userID
	//				int cash -> input CAD cash
    //------------------------------------------------------
	public void newInvestor(String firstName, String lastName, String userID, int cash) {

		if (investorsList.search(userID) == null) {
			investorsList.addFront(new Investor(firstName, lastName, userID, cash));
			System.out.println("\tCREATION SUCCESS: Investor \"" 
					+ firstName +" "+ lastName + "\" ("+userID+") created [CASH: "+cash+"]\n");
		}
		else
			System.out.println("\tCREATION ERROR: Investor \"" + userID + "\" already exists.\n");
	}// end newInvestor


    //------------------------------------------------------
    // METHOD: newCurrency
    //
    // PURPOSE:	
	//		Creates and adds a new Cryptocurrency to the market
	//		by adding a new cryptocurrency object to cryptocurrenciesList.
    // PARAMETERS:
	//		  String currencyName -> input currency name
	//		String currencySymbol -> input currency symbol
	//				 int quantity -> input start quantity
    //------------------------------------------------------
	public void newCurrency(String currencyName, String currencySymbol, int quantity) {

		if (cryptocurrenciesList.search(currencySymbol) == null) {
			cryptocurrenciesList.addFront(new Cryptocurrency(currencyName, currencySymbol, quantity, true));
			System.out.println("\tCREATION SUCCESS: Cryptocurrency \"" + 
					currencyName + "\" ("+currencySymbol+") created [QUANTITY: "+quantity+"]\n");
		}	
		else
			System.out.println("\tCREATION ERROR: Cryptocurrency \"" + currencySymbol + "\" already exists.\n");
	}// end newCurrency


    //------------------------------------------------------
    // METHOD: mine
    //
    // PURPOSE:
	//		Represents mining (giving free crypto) to an investor,
	//		makes verification checks and adds transaction to
	//		cryptocurrency's blockchain.
    // PARAMETERS:
	//		   	String userID -> input userID
	//	String currencySymbol -> input currency symbol
	//			 int quantity -> input mining quantity
	//	  String transCommand -> input actual command line
    //------------------------------------------------------
	public void mine(String userID, String currencySymbol, int quantity, String transCommand) {

		Investor inv = (Investor) investorsList.search(userID);
		Cryptocurrency crypto = (Cryptocurrency) cryptocurrenciesList.search(currencySymbol);

		if ( inv != null) {
			if ( crypto != null) {
				if (crypto.getQuantity() >= quantity) {

					inv.receiveCrypto(crypto, quantity);
					crypto.subtractQuantity(quantity);
					crypto.getBlockchain().addBlock(new Transaction(transCommand));
					System.out.println("\tMINING SUCCESS:\t\""+userID+"\" mined  ["+quantity+" "+currencySymbol+"]\n");
				}
				else
					System.out.println("\tMINING ERROR: Cryptocurrency \"" + crypto.getSymbol() + "\" has insufficient quantity.\n");
			}
			else
				System.out.println("\tMINING ERROR: Cryptocurrency \"" + currencySymbol + "\" is not found.\n");
		}
		else
			System.out.println("\tMINING ERROR: Investor \"" + userID + "\" is not found.\n");
	}// end mine


    //------------------------------------------------------
    // METHOD: trade
    //
    // PURPOSE:
	//		Represents trading between two investors (trader1 & trader2),
	//		makes verification checks and adds transactions to
	//		cryptocurrencies' blockchain(s) - CAD is tradable as well.
    // PARAMETERS:
	//	  	 String trader1 -> input trader1's userID
	//	  	 String trader2 -> input trader2's userID
	//	   String currency1 -> input currency1's symbol
	//	   String currency2 -> input currency2's symbol
	//		  	 int quant1 -> input trade1 quantity in currency1 un
	//		  	 int quant2 -> input trade2 quantity in currency2 un
	//	String transCommand -> input actual command line
    //------------------------------------------------------
	public void trade(String trader1, String trader2, 
			String currency1, int quant1, 
			String currency2, int quant2, String transCommand) {

		Investor inv1 = (Investor) investorsList.search(trader1);
		Investor inv2 = (Investor) investorsList.search(trader2);
		Cryptocurrency crypto1 = (Cryptocurrency) cryptocurrenciesList.search(currency1);
		Cryptocurrency crypto2 = (Cryptocurrency) cryptocurrenciesList.search(currency2);

		if ( checkInvestors(inv1, inv2) ) {

			if ( (crypto1 != null || currency1.equals("CAD")) 
					&& (crypto2 != null || currency2.equals("CAD")) ) {

				if ( inv1.verifyTransaction(currency1, quant1) 
						&& inv2.verifyTransaction(currency2, quant2)) {

					inv1.send(currency1, quant1);
					inv2.send(currency2, quant2);
					inv1.receive(crypto2, quant2);
					inv2.receive(crypto1, quant1);

					System.out.println("\tTRADING SUCCESS: \""+trader1+"\" gives ["+quant1+" "+currency1+"] to \""+trader2+"\""
							+ " \n\t  and\t\""+trader2+"\" gives ["+quant2+" "+currency2+"] to \""+trader1+"\"\n");

					if ( crypto1 != null )
						crypto1.getBlockchain().addBlock(new Transaction(transCommand));

					if ( crypto2 != null && crypto2 != crypto1 )
						crypto2.getBlockchain().addBlock(new Transaction(transCommand));

				}
				else
					System.out.println("\tTRADING ERROR - indicated above\n");
			}
			else
				System.out.println("\tTRADING ERROR: At least one of the cryptocurrencies is not found.\n");
		}
	}// end trade


    //------------------------------------------------------
    // METHOD: checkInvestors
    //
    // PURPOSE:
	//		Checks if investors are in the market (investorsList)
	//		and if they are the same person (same ID)
    // PARAMETERS:
    //		Investor inv1 -> first investor
    //		Investor inv2 -> second investor
    // Returns: describe the return value
    //------------------------------------------------------
	public boolean checkInvestors(Investor inv1, Investor inv2) {
		boolean success = false;

		if( inv1 != null && inv2 != null) {
			if ( !inv1.isEqual(inv2.getUserID()) )
				success = true;
			else
				System.out.println("\tTRADING ERROR: Both traders are the same.\n");
		}
		else
			System.out.println("\tTRADING ERROR: At least one of the investors is not found.\n");

		return success;
	}// end checkInvestors


    //------------------------------------------------------
    // METHOD: portfolioReport
    //
    // PURPOSE:
	//		Prints out the requested investor's report using
	//		a recursive and polymorphic print which also prints
	//		the list of cryptocurrencies in their portfolio.
    // PARAMETERS:
	//		String userID -> input userID
    //------------------------------------------------------
	public void portfolioReport(String userID) {
		Investor inv = (Investor) investorsList.search(userID);
		if ( inv != null) 
			inv.print(false);
		else
			System.out.println("\tREPORT ERROR: Investor \"" + userID + "\" is not found.\n");
	}// end portfolioReport


    //------------------------------------------------------
    // METHOD: cryptoReport
    //
    // PURPOSE:
	//		Prints out the requested cryptocurrency's report using
	//		a recursive and polymorphic print which also prints
	//		the list of transactions.
    // PARAMETERS:
	//		String currencySymbol -> input currency symbol
    //------------------------------------------------------
	public void cryptoReport(String currencySymbol) {
		Cryptocurrency crypto = (Cryptocurrency) cryptocurrenciesList.search(currencySymbol);

		if ( crypto != null ) {
			System.out.println("\t ______________________________________________________________________");
			crypto.print(false);
			crypto.verifyBlockchain();
			System.out.println("\t|______________________________________________________________________|\n");
		}
		else
			System.out.println("\tREPORT ERROR: Cryptocurrency \"" + currencySymbol + "\" is not found.\n");	
	}// end cryptoReport


}// end Market class

