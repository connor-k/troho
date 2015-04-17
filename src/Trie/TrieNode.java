package Trie;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;
import java.util.Vector;

//This class is also a mess that will be cleaned up
//just focus on searchHelper, I tried to make that one user
//friendly so no one would have to deal with the basic data
//structure


class TrieNode {
	boolean isEnd;
	String currString;
	private TrieNode [] children = new TrieNode[27];
	boolean isLeaf;
	private LinkedList<Integer> childList = new LinkedList<Integer>();
	private Vector<HousingLocation> myHouses = new Vector<HousingLocation>();
	int firstChild;
	TrieNode() {
		currString = "";
		for(int i = 0; i < 27; i++) {
			children[i] = null;
		}
		isLeaf = true;
		isEnd = false;
	}
	
	TrieNode(String myString) {
		currString = myString;
		for(int i = 0; i < 27; i++) {
			children[i] = null;
		}
		isLeaf = true;
		isEnd = false;
	}
	
	TrieNode getChild(int myCharPos) {
		return children[myCharPos];
	}
	
	Vector<HousingLocation> getHouses() {
		Vector<HousingLocation> newHouses = new Vector<HousingLocation>(myHouses);
		return newHouses;
	}
	
	void addChild(int index, String nextLetter) {
		isLeaf = false;
		if(children[index] == null) {
			children[index] = new TrieNode(currString + nextLetter);
			childList.add(index);
			firstChild = index;
		}
	}
	
	boolean isEnd(int index) {
		if(children[index] != null) {
			return true;
		}
		return false;
	}
	
	void makeEnd(HousingLocation newHouse) {
		isEnd = true;
		myHouses.addElement(newHouse);
	}
	
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
	
	String getLikely() {
		if(isEnd) {
			return currString;
		}
		return children[firstChild].getLikely();
	}
	
	Vector<HousingLocation> getAllChildStrings(Vector<HousingLocation> myChildren, Set<HousingLocation> houseSet) {
		getChildrenHelper(myChildren, houseSet);
		return myChildren;
	}
	
	private void getChildrenHelper(Vector<HousingLocation> prevChildren, Set<HousingLocation> houseSet) {
		if(isEnd) {
			ListIterator<HousingLocation> myIter = myHouses.listIterator();
			while(myIter.hasNext()) {
				HousingLocation newHouse = myIter.next();
				if (!houseSet.contains(newHouse)) {
					prevChildren.addElement(newHouse);
					houseSet.add(newHouse);
				}
			}
		}
		if(isLeaf) {
			return;
		}
		ListIterator<Integer> myIt = childList.listIterator();
		while(myIt.hasNext()) {
			getChild(myIt.next()).getChildrenHelper(prevChildren, houseSet);
		}
	}
}