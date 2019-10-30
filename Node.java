// CLASS: Node
// REMARKS:
//		To represent an 'edge'/node in a list,
//		contatins a ListItem object and a next pointer.
//-----------------------------------------
public class Node {

	private ListItem item;
	private Node next;

	// Constructor for a standard Node
	public Node(ListItem data, Node next) {
		this.item = data;
		this.next = next;
	}// end Constructor

	//------------------------------------------------------
    // METHOD: print
    //
    // PURPOSE:
    //		Continues a recursive polymorphic printing call
	//		starting from the head node in List class
    // PARAMETERS:
    //     boolean tab -> determines formatting for later cases.
    //------------------------------------------------------
	public void print(boolean tab) {
		// print contents of list starting at this node. 	
		item.print(tab);
		if (next != null) 
			next.print(tab);
	}// end print

	// GETTERS AND SETTERS (needed) FOR NODE
	// returns item
	public ListItem getItem() {
		return item;
	}// end getItem

	// sets next
	public void setNext(Node next) {
		this.next = next;
	}// end setNext
	
	// returns next
	public Node getNext() {
		return next;
	}// end getNext

	
}// end Node class
