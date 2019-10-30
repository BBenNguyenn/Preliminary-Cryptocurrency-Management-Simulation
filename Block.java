import java.util.Objects;
// CLASS: Block
// REMARKS: What is the purpose of this class?
//	To represent a block in the blockchain list
//	which stores information about a transaction
//	and its hashCodes (including prev block).
//-----------------------------------------
public class Block extends Node {

	private int hashCode;
	private int prevHashCode;

	
	//Constructor for a block used in the blockchain
	public Block(Transaction transCommand, Block next, int prevHashCode) {
		super(transCommand, next);
		// hashing the hashCode with transaction and previous hash code
		this.hashCode = Objects.hash(transCommand, prevHashCode);
		this.prevHashCode = prevHashCode;
	}// end constructor


	//returns hashCode
	public int getHashCode() {
		return hashCode;
	}// end getHashCode

	//returns prevHashCode
	public int getPrevHashCode() {
		return prevHashCode;
	}// end getPrevHashCode

	
}// end Block class
