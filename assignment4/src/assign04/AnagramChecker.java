package assign04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Mike Phelps, Seth Polevoi
 * 
 *         This class can be used to check if two strings are anagrams of each
 *         other. It also has the ability to take in a list or file of strings
 *         and reveal the largest group of anagrams.
 *
 */
public class AnagramChecker {

	/**
	 * This method takes a String argument and returns the lexicographically sorted
	 * version of that String.
	 * 
	 * @param str String to be sorted
	 * @return Will return a lexicographically sorted version of the argument string
	 */
	public static String sort(String str) {
		//Creat arr of characters in str
		char[] charArr = str.toCharArray();

		// User insertion sort to sort the characters in the array
		for (int i = 1; i < charArr.length; i++) {
			char key = charArr[i];
			while (i != 0 && key < charArr[i - 1]) {
				charArr[i] = charArr[i - 1];
				i--;
			}
			charArr[i] = key;
		}

		// Build a new string using the sorted characters
		String result = "";
		for (char c : charArr) {
			result += c;
		}
		// Return the String built above
		return result;
	}

	/**
	 * Sorts an array of generic type T using insertion sort. Argument array will be
	 * divided into two sections, a sorted section and an unsorted section. The
	 * sorted section will ultimately grow to the size of the entire array by
	 * inserting each value from the unsorted section into the proper position
	 * within the sorted array. The proper ordering of elements is determined by the
	 * passed in comparator.
	 * 
	 * @param <T> Generic type of the method
	 * @param arr array to be sorted
	 * @param cmp comparator used to establish proper order of the array
	 */
	public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {

		// loop through unsorted section of array
		for (int i = 1; i < arr.length; i++) {
			// Set key to first value in unsorted array
			T key = arr[i];
			// Compare it to each value in the sorted array.
			// Place it in a position where it is greater than the value to its left
			// But less than the value to its right. (Or at the 0th index if it is the
			// smallest
			// value in the sorted section
			while (i != 0 && cmp.compare(key, arr[i - 1]) < 0) {
				arr[i] = arr[i - 1];
				i--;
			}
			arr[i] = key;
		}

	}

	/**
	 * This method returns true if the two argument strings are anagrams of each
	 * other. If they are not anagrams of each other, will return false.
	 * 
	 * @param str1 first string used for comparison
	 * @param str2 second string used for comparison
	 * @return will return true if argument strings are anagrams of each other,
	 *         false if they are not.
	 */
	public static boolean areAnagrams(String str1, String str2) {
		// Uses sort method to order strings lexicographically.
		// Then compares them using Strings .equals method.
		String lowerStr1 = str1.toLowerCase();
		String lowerStr2 = str2.toLowerCase();
		if (sort(lowerStr1).equals(sort(lowerStr2))) {
			// If they are anagrams of each other, return true
			return true;
		}

		// If they are not anagrams of each other, return false
		return false;
	}

	/**
	 * This method returns the largest group of anagrams in the input array of
	 * words. It returns an empty array if there are no anagrams in the input array.
	 * 
	 * @param arr A String array
	 * @return A String array with the largest group of anagrams. Will return an
	 *         empty array if there are no anagrams in the input array.
	 */
	public static String[] getLargestAnagramGroup(String[] arr) {
		// make comparator
		Comparator<String> cmp = new OrderByAnagram();

		// sort the array into anagram alphabetical order
		insertionSort(arr, cmp);

		// find where the most anagrams are
		int[] anagramIndexInfo = findBigAnagramInfo(arr);

		// create return string array
		String[] anagramGroup = new String[anagramIndexInfo[2]];

		// if there are no anagrams, return empty array
		if (anagramGroup.length < 1) {
			return anagramGroup;
		}

		// add all the words in the big anagram group to the return array
		for (int index = anagramIndexInfo[0]; index <= anagramIndexInfo[1]; index++) {
			// create a new index that begins at zero, for indexing return array
			int normalizedIndex = index - anagramIndexInfo[0];
			// add value to the return array
			anagramGroup[normalizedIndex] = arr[index];
		}

		return anagramGroup;
	}

	/**
	 * This method returns a range of Strings which are anagrams of each other. It
	 * does so by comparing which group of anagrams occur the most within an array
	 * of pre-sorted anagrams.
	 * 
	 * @param arr Array of Strings which have been lexicographically sorted
	 * @return returns the index range of the largest group of anagrams as well as
	 *         the size of that range.
	 */
	private static int[] findBigAnagramInfo(String[] arr) {
		// initialize variables
		int bigAnagramIndex, bigAnagramIndexEnd, bigAnagramSize, tempIndex, tempAnagramSize;
		bigAnagramIndex = bigAnagramIndexEnd = bigAnagramSize = tempIndex = tempAnagramSize = 0;

		boolean lastMatch = false;

		for (int index = 1; index < arr.length; index++) {
			// if this and last are same, AND last two were NOT a match(first match)
			if (areAnagrams(arr[index - 1], arr[index]) && !lastMatch) {
				// update size of anagram list
				tempIndex = index - 1;
				tempAnagramSize = 2;
				lastMatch = true;
			} else if (areAnagrams(arr[index - 1], arr[index]) && lastMatch) {// (match but not first match)
				// update anagram size
				tempAnagramSize++;
				lastMatch = true;
			} else if (!(areAnagrams(arr[index - 1], arr[index])) && lastMatch) {// not a match, but last one was
				// compare
				if (tempAnagramSize > bigAnagramSize) {
					bigAnagramIndex = tempIndex;
					bigAnagramSize = tempAnagramSize;
					bigAnagramIndexEnd = index - 1;
				}
				lastMatch = false;
			} else {
				lastMatch = false;
			}
		} // end for loop

		// double check if last match was the biggest of anagrams
		if (areAnagrams(arr[arr.length - 2], arr[arr.length - 1]) && lastMatch) {
			if (tempAnagramSize > bigAnagramSize) {
				bigAnagramIndex = tempIndex;
				bigAnagramSize = tempAnagramSize;
				bigAnagramIndexEnd = arr.length - 1;
			}
		}
		// return start of anagrams, end of anagrams, and size of anagrams
		int returnVals[] = { bigAnagramIndex, bigAnagramIndexEnd, bigAnagramSize };
		return returnVals;
	}

	/**
	 * Helper method used to convert file into an array of Strings. If the file is
	 * empty, will return an empty array. If file is not found, will return and
	 * empty array. Otherwise, will return an array of Strings built from the passed
	 * in file.
	 * 
	 * @param filename file used to fill array with String values.
	 * @return If the file is empty, will return an empty array. If file is not
	 *         found, will return and empty array. Otherwise, will return an array
	 *         of Strings built from the passed in file
	 */
	public static String[] readWordsFile(String filename) {
		// Create ArrayList which will be used to carry string values from file
		ArrayList<String> wordList = new ArrayList<String>();

		// initialize scanner with passed in file
		File file = new File(filename);
		Scanner scan;
		// if file throws FileNotFoundException, return an empty array
		try {
			scan = new Scanner(file, "utf-8");
		} catch (FileNotFoundException e) {
			String[] emptyArr = { "" };
			return emptyArr;
		}
		// Add each String in the file to our ArrayList
		while (scan.hasNext()) {
			wordList.add(scan.next());
		}
		scan.close();

		// Create String array which will be used as our return array.
		String[] returnList = new String[wordList.size()];

		// Copy values from our ArrayList into our return array
		for (int i = 0; i < wordList.size(); i++) {
			returnList[i] = wordList.get(i);
		}
		// Retrun our return array
		return returnList;
	}

	/**
	 * This method returns the largest group of anagrams in a file of words. It
	 * returns an empty array if the file is not found or if the file is empty.
	 * 
	 * @param fileName
	 * @return
	 */
	public static String[] getLargestAnagramGroup(String fileName) {

		// Calls readWordsFile to covert file into an array of Strings
		// Then calls getLargestAnagramGroup to find largest anagram group within the
		// String array returned by readWordsFile
		return getLargestAnagramGroup(readWordsFile(fileName));

	}

	/**
	 * This comparator lexicographically compares two lexicographically sorted
	 * strings.
	 * 
	 * @author Mike, Seth Polevoi
	 *
	 */
	protected static class OrderByAnagram implements Comparator<String> {

		/**
		 * Compares two Strings that have been pre-sorted lexicographically. If o1 come
		 * first lexicographically, return value less than 0. If o1 and o2 are equal,
		 * return 0. If o1 comes later then o2 lexicographically, return a value greater
		 * than 0.
		 *
		 */
		@Override
		public int compare(String o1, String o2) {
			return AnagramChecker.sort(o1).compareTo(AnagramChecker.sort(o2));
		}

	}

}
