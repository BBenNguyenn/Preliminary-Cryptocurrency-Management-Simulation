// CLASS: ListItem
// REMARKS: 
//		Abstract class to represent an item in the list
//		to be extended by Cryptocurrency, Investor and Transaction.
//
//-----------------------------------------
public abstract class ListItem {
	
	// prints the list item, tab is for formating, 
	// details mentioned in subclasses.
	protected abstract void print(boolean tab);
	// checks if a given identifier is equal to ListItem's,
	// details mentioned in subclasses.
	protected abstract boolean isEqual(String id);
	
	
}// end ListItem
