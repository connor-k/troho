package Trie;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;
import java.util.Vector;

import sql.HousingLocation;


class TrieNode {
	boolean isEnd;  //True if the node contains at least one
					//associated housing location
	String currString;  //string associated with the current node
	private TrieNode [] children = new TrieNode[27];  //children nodes
													  //one for each letter of 
													  //the alphabet, plus ' '
	boolean isLeaf; //True if it has no children
	
	//Linked list that contains the index positions of the node's children
	private LinkedList<Integer> childList = new LinkedList<Integer>();
	
	//Vector that contains all HousingLocations associated with this node
	private Vector<HousingLocation> myHouses = new Vector<HousingLocation>();
	int firstChild;  //holds the index position of the most recently added
	                 //HousingLocation child
	
	//default constructor, creates a node with no associated string or HousingLocations
	TrieNode() {
		currString = "";
		for(int i = 0; i < 27; i++) {
			children[i] = null;
		}
		isLeaf = true;
		isEnd = false;
	}
	
	//creates a node with no children, but with an associated string
	TrieNode(String myString) {
		currString = myString;
		for(int i = 0; i < 27; i++) {
			children[i] = null;
		}
		isLeaf = true;
		isEnd = false;
	}
	
	//returns the child node of the given index position 
	//(which corresponds to a letter of the alphabet
	TrieNode getChild(int myCharPos) {
		return children[myCharPos];
	}
	
	//returns a copy of this node's associated HousingLocations
	Vector<HousingLocation> getHouses() {
		Vector<HousingLocation> newHouses = new Vector<HousingLocation>(myHouses);
		return newHouses;
	}
	
	//adds a child with the given name
	void addChild(int index, String nextLetter) {
		isLeaf = false;
		if(children[index] == null) {
			
			//The string of the child will extend the string of this node
			children[index] = new TrieNode(currString + nextLetter);
			childList.add(index);
			firstChild = index;
		}
	}
	
	//designates that this is the end of a string, adds an associated HousingLocation
	void makeEnd(HousingLocation newHouse) {
		isEnd = true;
		myHouses.addElement(newHouse);
	}
	
	//translates the index number of a child, each one corresponds to the letter of the alphabet
	String translateLetter(int num) {
		char myChar = 'a';
		for(int i = 0; i < num; i++) {
			myChar++;
		}
		if(num == 26) {
			return " ";
		}
		return "" + myChar; 
	}
	
	//returns a possible word based on the current node's string
	String getLikely() {
		if(isEnd) { //if this node has a corresponding string, it returns that string
			return currString;
		}
		//recursively calls getLikely function on child until it reaches a node
		//with a corresponding HousingLocation
		return children[firstChild].getLikely();
	}
	
	//returns all the corresponding housing locations of all child nodes
	Vector<HousingLocation> getAllChildStrings(Vector<HousingLocation> myChildren, Set<HousingLocation> houseSet) {
		getChildrenHelper(myChildren, houseSet);
		return myChildren;
	}
	
	//gets all HousingLocations of a node's children
	private void getChildrenHelper(Vector<HousingLocation> prevChildren, Set<HousingLocation> houseSet) {
		if(isEnd) {//runs if there is a HousingLocation corresponding to this node
			//adds all HousingLocations to vector
			//it uses a set of HousingLocations to make sure it doesn't add one
			//more that once
			ListIterator<HousingLocation> myIter = myHouses.listIterator();
			while(myIter.hasNext()) {
				HousingLocation newHouse = myIter.next();
				if (!houseSet.contains(newHouse)) {
					prevChildren.addElement(newHouse);
					houseSet.add(newHouse);
				}
			}
		}
		if(isLeaf) { //base case, no children left
			return;
		}
		//gets HousingLocations of all children
		ListIterator<Integer> myIt = childList.listIterator();
		while(myIt.hasNext()) {
			getChild(myIt.next()).getChildrenHelper(prevChildren, houseSet);
		}
	}
}