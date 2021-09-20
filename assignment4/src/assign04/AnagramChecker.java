package assign04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;


public class AnagramChecker {
	
	public static String sort(String str) {
		String lowerCase = str.toLowerCase();
		
		char[] charArr = lowerCase.toCharArray();
		
		for(int i = 1; i < charArr.length; i++) {
			char key = charArr[i];
			while(i != 0 && key < charArr[i-1]) {
				charArr[i] = charArr[i-1];
				i--;
			}
			charArr[i] = key;
		}
		String result = "";
		for(char c : charArr) {
			result += c;
		}
		
		return result;
	}
	
	public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {
		
		for(int i = 1; i < arr.length; i++) {
			T key = arr[i];
			while(i != 0 && cmp.compare(key, arr[i-1]) < 0) {
				arr[i] = arr[i-1];
				i--;
			}
			arr[i] = key;
		}
		
//		System.out.print("FROM THE METHOD: ");
//		System.out.println(Arrays.toString(arr));
	}
	
	public static boolean areAnagrams(String str1, String str2) {
		if(sort(str1).equals(sort(str2))) {
			return true;
		}
		
			return false;
	}
	
public static String[] getLargestAnagramGroup(String[] arr) {		
		//make comparator
		Comparator<String> cmp = new OrderByAnagram();
		
		//sort the array into anagram alphabetical order
		insertionSort(arr, cmp );
		System.out.println( Arrays.toString(arr) );
		
		//find where the most anagrams are
		int[] anagramIndexInfo = findBigAnagramInfo(arr);
		
		//create return string array
		String[] anagramGroup = new String[ anagramIndexInfo[2] ];
		
		//if there are no anagrams, return empty array
		if (anagramGroup.length < 1) {
			return anagramGroup;
		}
		
		//add all the words in the big anagram group to the return array
		for(int index = anagramIndexInfo[0]; index <= anagramIndexInfo[1]; index++) {
			//create a new index that begins at zero, for indexing return array
			int normalizedIndex = index - anagramIndexInfo[0];
			//add value to the return array
			anagramGroup[normalizedIndex] = arr[index];
		}
		
		return anagramGroup;
	}
	
	private static int[] findBigAnagramInfo(String[] arr) {
		//initialize variables
		int bigAnagramIndex, bigAnagramIndexEnd, bigAnagramSize, tempIndex, tempAnagramSize;
		bigAnagramIndex = bigAnagramIndexEnd = bigAnagramSize = tempIndex = tempAnagramSize = 0;
		
		boolean lastMatch = false;
		
		for(int index = 1; index < arr.length; index++) {
			//if this and last are same, AND last two were NOT a match(first match)
			if( areAnagrams( arr[index-1], arr[index] ) && !lastMatch ){
				//update size of anagram list
				tempIndex = index-1;
				tempAnagramSize = 2;
				lastMatch = true;
			} else if( areAnagrams( arr[index-1], arr[index] ) && lastMatch ) {//(match but not first match)
				//update anagram size
				tempAnagramSize++;
				lastMatch = true;
			} else if( !(areAnagrams( arr[index-1], arr[index] )) && lastMatch) {//not a match, but last one was
				//compare
				if ( tempAnagramSize > bigAnagramSize) {
					bigAnagramIndex = tempIndex;
					bigAnagramSize = tempAnagramSize;
					bigAnagramIndexEnd = index-1;
				}
				lastMatch = false;
			} else {
				lastMatch = false;
			}
		}//end for loop
		
		//double check if last match was the biggest of anagrams
		if( areAnagrams( arr[arr.length-2], arr[arr.length-1] ) && lastMatch ){
			if ( tempAnagramSize > bigAnagramSize) {
				bigAnagramIndex = tempIndex;
				bigAnagramSize = tempAnagramSize;
				bigAnagramIndexEnd = arr.length-1;
			}
		}
		//return start of anagrams, end of anagrams, and size of anagrams
		int returnVals[] = {bigAnagramIndex, bigAnagramIndexEnd, tempAnagramSize};
		return returnVals;
	}
	
	public static String[] readWordsFile(String filename) throws FileNotFoundException {
		ArrayList<String> wordList = new ArrayList<String>();
		
		File file = new File(filename);
		Scanner scan = new Scanner(file, "utf-8");	
		
		while (scan.hasNext()) {
			wordList.add(scan.next());
		}
		scan.close();
		System.out.println(wordList.toString());
		
		String [] returnList = new String [wordList.size()];
		for(int i = 0; i < wordList.size(); i++) {
			returnList[i] = wordList.get(i);
		}
		return returnList;
	}
	
	protected static class OrderByAnagram implements Comparator<String>{

		@Override
		public int compare(String o1, String o2) {
			return AnagramChecker.sort(o1).compareTo( AnagramChecker.sort(o2) );
		}
		
	}
	

	public static void main(String[] args) {
//		String[] testArr = {"Art" , "Tar", "Car", "Zap", "Apple", "Caters", "caster", "Reacts", "carets"};
//		String test = "";
//		String testB = "bazza";
//		System.out.println(areAnagrams(test, testB));
//		System.out.println(Arrays.toString(getLargestAnagramGroup(testArr)));
//		System.out.println(sort(testB));
		try {
			readWordsFile("src/assign04/sample_word_list.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
