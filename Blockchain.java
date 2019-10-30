// CLASS: Blockchain
// REMARKS: What is the purpose of this class?
//	To represent a blockchain which is a list
//	of blocks, hence Blockchain extends List
//	to act much like a list with added
//	addBlock method for its specific use.
//-----------------------------------------
public class Blockchain extends List{

	private Block prev;
	
	// Constructor for the blockchain list which contains blocks (like nodes)
	public Blockchain() {
		super();
		this.prev = null;
	}// end constructor


	//------------------------------------------------------
    // METHOD: addBlock
    //
    // PURPOSE:
    //		Creates and adds a block to the end of the blockchain
    //		list using a prev pointer for O(1) runtime.
    // PARAMETERS:
    //		Transaction transCommand
    //			Holds information about transaction to put in block.
    //------------------------------------------------------
	public void addBlock( Transaction transCommand ) {

		if (head == null) {// first block edge-case
			head = new Block(transCommand, null, 0);
			prev = (Block) head;
		}
		else {
			prev.setNext(new Block(
					transCommand, null, prev.getHashCode()));
			prev = (Block) prev.getNext();
		}// end else
	}// end addBlock

	
}// end Blockchain class
