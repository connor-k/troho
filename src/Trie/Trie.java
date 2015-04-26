package Trie;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.Vector;

import sql.HousingLocation;

//////////////////////////////////////////////////////////////
//
//This custom Trie has the ability to search for partial keywords, so if "new north",
//"old north," and "Nirnkrant" are all in the trie, and you search
//"n" it will return all of the houses.  But if you search "new north,"
//it will return a vector that contains both "new north" and "old north,"
//and new north will be the first element in the vector.  So it
//is roughly sorted by relevance, favoring the houses that more
//closely match the search word(s).  
//
//////////////////////////////////////////////////////////////////




public class Trie {
	TrieNode head = new TrieNode();
	
<<<<<<< HEAD
	public Trie() {
		head = new TrieNode();
	}
	
	public Trie(Vector<HousingLocation> houses) {
=======
	//default constructor, makes an empty trie
	Trie() {
	}
	
	//adds the names and HousingLocations of all houses in vector
	Trie(Vector<HousingLocation> houses) {
>>>>>>> master
		for(int i = 0; i < houses.size(); i++) {
			add(houses.get(i));
		}
	}

	//adds a single house to the trie
	void add(HousingLocation newHouse) {
		TrieNode currNode = head;
		String newWord = newHouse.locationName;
		
		//adds characters to the trie, one by one
		for(int i = 0; i < newWord.length(); i++) {
			//adds second word if there is a space in the search words
			if(newWord.charAt(i) == ' ') {
				addPartial(newWord.substring(i + 1, newWord.length()), newHouse);
			}
			//creates a new node with associated string and house
			currNode = addChar(newWord.charAt(i), currNode); 
		}
		//assigns HousingLocation to the last node in the word
		currNode.makeEnd(newHouse);
	}
	
	//runs if there is a space in the word
	//adds a partial house name to trie
	void addPartial(String partialName, HousingLocation myHouse) {
		TrieNode currNode = head;
		for(int i = 0; i < partialName.length(); i++) {
			if(partialName.charAt(i) == ' ') {
				addPartial(partialName.substring(i + 1, partialName.length()), myHouse);
			}
			currNode = addChar(partialName.charAt(i), currNode); 
		}
		currNode.makeEnd(myHouse);
	}
	
	//creates new TrieNode and returns new node
	private TrieNode addChar(char newChar, TrieNode node) {
		int index = getNumber(newChar);
		node.addChild(index, "" + newChar);//adds a child if one is not present
		return node.getChild(index);
	}
	
	//returns index position associated with the letter
	private int getNumber(Character myChar) {
		char defChar = ' ';
		char newChar = myChar;
		if(myChar == defChar) {
			return 26;
		}
		newChar = Character.toLowerCase(myChar);
		int num = newChar - 'a';
		return num;
	}
	
	//returns a housing vector if the name matches the provided search word
	public Vector<HousingLocation> findWord(String searchWord) {
		TrieNode currNode = head;
		Vector<HousingLocation> results = new Vector<HousingLocation>();
		for(int i = 0; i < searchWord.length(); i++) {
			currNode = checkLetter(currNode, searchWord.charAt(i));
			if(currNode == null) {
				return results;
			}
		}
		if(currNode.isEnd) { //returns all associated houses on node matching word
			return currNode.getHouses();
		}
		return results;
	}
	
	//provided with a partial string, this suggests a word that is in the trie as a
	//possible way to finish that word
	public String findLikely(String searchWord) {
		TrieNode currNode = head;
		for(int i = 0; i < searchWord.length(); i++) {
			currNode = checkLetter(currNode, searchWord.charAt(i));
			if(currNode == null) {//if no words matching partial, returns empty string
				return "";
			}
		}
		if(currNode.isEnd) { //returns the current string if the node
							 //represents the end of a housing name
			return searchWord;
		} else {
			String resultString = currNode.getLikely();
			int length = searchWord.length();
			resultString = resultString.substring(length);
			return (searchWord + resultString);
		}
	}
	
	//finds returns a vector of housing locations whose names
	//contain the partial keyword provided
	Vector<HousingLocation> findPartialWord(String searchWord) {
		Vector<HousingLocation> results = new Vector<HousingLocation>();
		TreeSet<HousingLocation> mySet = new TreeSet<HousingLocation>(new HousingNameComparator());
		TrieNode currNode = head;
		
		//adds all results that match search words exactly
		if(!findWord(searchWord).isEmpty()) {
			Vector<HousingLocation>temp = findWord(searchWord);
			for(int i = 0; i < temp.size(); i++) {
				if(!mySet.contains(temp.elementAt(i))) {
					results.addElement(temp.elementAt(i));
					mySet.add(temp.elementAt(i));
				}
			}
		} 
		
		//iterates until the end of the search words
		//finds the node corresponding to the substring
		for(int i = 0; i < searchWord.length(); i++) {
			currNode = checkLetter(currNode, searchWord.charAt(i));
			if(searchWord.charAt(i) == ' ') {
				findMoreWords(searchWord.substring(0, i), results, mySet);
				findMoreWords(searchWord.substring(i + 1, searchWord.length()), results, mySet);
			}
			if(currNode == null) {//adds all results located
								  //at the node
				for(int j = 0; j < results.size(); j++) {
					mySet.add(results.elementAt(j));
				}
				return results;
			}
		}
		//adds all current results to the set
		for(int i = 0; i < results.size(); i++) {
			mySet.add(results.elementAt(i));
		}
		//adds all strings that begin with the search words
		return currNode.getAllChildStrings(results, mySet);
	}
	
	//finds more possible search results based on partial keywords
	private void findMoreWords(String searchWord, Vector<HousingLocation> myHouses, TreeSet<HousingLocation> mySet) {
		TrieNode currNode = head;
		//tries to find a match
		for(int i = 0; i < searchWord.length(); i++) {
			currNode = checkLetter(currNode, searchWord.charAt(i));
			if(currNode == null) {
				return;
			}
		}
		//returns all possible keywords that begin with searchWord
		currNode.getAllChildStrings(myHouses, mySet);
	}
	
	//returns the child with the corresponding letter
	private TrieNode checkLetter(TrieNode currNode, char letter) {
		int index = getNumber(letter);
		return currNode.getChild(index);
	}
	
	//compares two houses based on which name is alphabetically first
	//used for the housing set
	private class HousingNameComparator implements Comparator<HousingLocation> {
		public int compare(HousingLocation o1, HousingLocation o2) {
			String firstName = o1.locationName;
			String secondName = o2.locationName;
			if(firstName.compareTo(secondName) < 0) {
				return -1;
			}
			if(firstName.compareTo(secondName) > 0) {
				return 1;
			}
			return 0;
		}
		
	}
}