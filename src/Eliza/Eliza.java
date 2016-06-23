package Eliza;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;



public class Eliza {

	public static void initGame(Set<String> hedges, Set<String> qualifiers, 
			Map<String, String> replacements) {
		hedges.add("Please tell me more");
		hedges.add("Many of my patients tell me the same thing");
		hedges.add("It is getting late, maybe we had better quit");

		qualifiers.add("Why do you say that");
		qualifiers.add("You seem to think that");
		qualifiers.add("So, you are concerned that");

		replacements.put("i", "you");
		replacements.put("you", "I");
		replacements.put("me", "you");
		replacements.put("my", "your");
		replacements.put("am", "are");
	}
	
	public static String getQualifier(String input, Set<String> qualifiers, 
			Map<String, String> replacements) {
		String[] words = null;
		String newString = "";
		words = input.split(" ");
		for (String word: words) {
			if (replacements.containsKey(word.toLowerCase())) {
				newString += replacements.get(word.toLowerCase())+" ";
				//System.out.print(newString+" | ");
			} else {
				newString += word+" ";
			}
		}
		newString = getRandomString(qualifiers) + " " +newString;
		//System.out.println(newString);
		return newString;
	}

	public static String getRandomString(Set<String> set) {
		int index = getRandom(set.size());
		Iterator<String> iter = set.iterator();
		for (int i = 0; i < index; i++) {
		    iter.next();
		}
		return iter.next();
	}

	public static int getRandom(int size) {
		Random rnd = new Random();
		int i = rnd.nextInt(size);
		//System.out.println("random no: " + i);
		return (i);
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		Set<String> hedgeSet = new HashSet<String>();

		Set<String> qualifierSet = new HashSet<String>();

		Map<String, String> replacementMap = new HashMap<String, String>();

		int choice;
		String statement = "";
		String response = "";
		initGame(hedgeSet, qualifierSet, replacementMap);

		System.out.println("Good day. What is your problem? " + "Enter your response here or Q to quit: ");
		while (true) {			
			statement = scan.nextLine();
			if (statement.toUpperCase().equals("Q")) {
				break;
			}
			choice = getRandom(2);
			if (choice == 1) {
				response = getRandomString(hedgeSet);
			} else {
				response = getQualifier(statement, qualifierSet, replacementMap);
			}
			System.out.println(response);			
		}
		System.out.println("Thank you for talking with me today.");
	}

}
