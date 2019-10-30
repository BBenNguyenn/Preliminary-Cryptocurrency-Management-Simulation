// CLASS: List
// REMARKS: What is the purpose of this class?
//		To form a singly-linked list with a
//		simple head Node pointer and an addFront
//		and search methods.
//-----------------------------------------
public class List {

	protected Node head; // "protected" for access in Blockchain class

	// Constructor for standard linked-list
	public List() {
		head = null; 
	}

	//------------------------------------------------------
    // print
    //
    // PURPOSE:
    //		Initializes a recursive polymorphic printing call
	//		starting at the head node
    // PARAMETERS:
    //     boolean tab -> determines formatting for later cases.
    //------------------------------------------------------
	public void print(boolean tab) {

		if (head != null) {
			head.print(tab);
		}
	}

	
	//------------------------------------------------------
    // METHOD: addFront
    //
    // PURPOSE:
	//		Adds a ListItem object to the front of the list.
    // PARAMETERS:
    //     ListItem data -> ListItem object to add to list
    //------------------------------------------------------
	public void addFront( ListItem data ) {
		head = new Node(data, head);
	}// end addFront


	//------------------------------------------------------
    // METHOD: search
    //
    // PURPOSE:
	//		Searches the list given an identifier and returns
	//		the item, if not found, returns null
    // PARAMETERS:
    //     String id -> the identifier of a ListItem
    // Returns: the searched item, or null if not found
    //------------------------------------------------------
	// Linearly searches for node with specific identifier.
	public ListItem search(String id) {
		Node curr = head;

		while ( curr != null && !curr.getItem().isEqual(id) )
			curr = curr.getNext();

		// Programming practices never mentioned no multiple returns,
		// hopefully its okay for this class.... :D
		if (curr != null)
			return curr.getItem();
		else
			return null;
	}// end search

}// end List class
