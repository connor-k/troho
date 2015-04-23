package Trie;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Vector;

import sql.HousingLocation;

//////////////////////////////////////////////////////////////
//
//I'm sorry this class is such a mess. It took a lot of trial
//and error to get how I wanted it.  I will clean it up.  Basically,
//it has the ability to search for partial keywords, so if "new north",
//"old north," and "Nirnkrant" are all in the trie, and you search
//"n" it will return all of the houses.  But if you search "new north,"
//it will return a vector that contains both "new north" and "old north,"
//and new north will be the first element in the vector.  So it
//is roughly sorted by relevance, favoring the houses that more
//closely match the search word(s).  Feel free to try to break it,
//additional test cases would be bomb. (See search helper!!)
//
//////////////////////////////////////////////////////////////////




public class Trie {
	TrieNode head = new TrieNode();
	
	public Trie() {
		head = new TrieNode();
	}
	
	public Trie(Vector<HousingLocation> houses) {
		for(int i = 0; i < houses.size(); i++) {
			add(houses.get(i));
		}
	}

	void add(HousingLocation newHouse) {
		TrieNode currNode = head;
		String newWord = newHouse.locationName;
		for(int i = 0; i < newWord.length(); i++) {
			if(newWord.charAt(i) == ' ') {
				addPartial(newWord.substring(i + 1, newWord.length()), newHouse);
			}
			currNode = addChar(newWord.charAt(i), currNode); 
		}
		currNode.makeEnd(newHouse);
	}
	
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
	
	private TrieNode addChar(char newChar, TrieNode node) {
		int index = getNumber(newChar);
		node.addChild(index, "" + newChar);
		return node.getChild(index);
	}
	
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
	
	public Vector<HousingLocation> findWord(String searchWord) {
		TrieNode currNode = head;
		Vector<HousingLocation> results = new Vector<HousingLocation>();
		for(int i = 0; i < searchWord.length(); i++) {
			currNode = checkLetter(currNode, searchWord.charAt(i));
			if(currNode == null) {
				return results;
			}
		}
		if(currNode.isEnd) {
			return currNode.getHouses();
		}
		return results;
	}
	
	public String findLikely(String searchWord) {
		TrieNode currNode = head;
		for(int i = 0; i < searchWord.length(); i++) {
			currNode = checkLetter(currNode, searchWord.charAt(i));
			if(currNode == null) {
				return "";
			}
		}
		if(currNode.isEnd) {
			return searchWord;
		} else {
			return currNode.getLikely();
		}
	}
	
	Vector<HousingLocation> findPartialWord(String searchWord) {
		Vector<HousingLocation> results = new Vector<HousingLocation>();
		TreeSet<HousingLocation> mySet = new TreeSet<HousingLocation>(new HousingNameComparator());
		TrieNode currNode = head;
		
		if(!findWord(searchWord).isEmpty()) {
			Vector<HousingLocation>temp = findWord(searchWord);
			for(int i = 0; i < temp.size(); i++) {
				if(!mySet.contains(temp.elementAt(i))) {
					results.addElement(temp.elementAt(i));
					mySet.add(temp.elementAt(i));
				}
			}
		} 
		
		for(int i = 0; i < searchWord.length(); i++) {
			currNode = checkLetter(currNode, searchWord.charAt(i));
			if(searchWord.charAt(i) == ' ') {
				//System.out.println(searchWord.substring(i + 1, searchWord.length()));
				findMoreWords(searchWord.substring(0, i), results, mySet);
				findMoreWords(searchWord.substring(i + 1, searchWord.length()), results, mySet);
			}
			if(currNode == null) {
				for(int j = 0; j < results.size(); j++) {
					mySet.add(results.elementAt(j));
				}
				return results;
			}
		}
		for(int i = 0; i < results.size(); i++) {
			mySet.add(results.elementAt(i));
		}
		
		return currNode.getAllChildStrings(results, mySet);
	}
	
	private void findMoreWords(String searchWord, Vector<HousingLocation> myHouses, TreeSet<HousingLocation> mySet) {
		TrieNode currNode = head;
		for(int i = 0; i < searchWord.length(); i++) {
			currNode = checkLetter(currNode, searchWord.charAt(i));
			if(currNode == null) {
				return;
			}
		}
		currNode.getAllChildStrings(myHouses, mySet);
	}
	
	private TrieNode checkLetter(TrieNode currNode, char letter) {
		int index = getNumber(letter);
		return currNode.getChild(index);
	}
	
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
