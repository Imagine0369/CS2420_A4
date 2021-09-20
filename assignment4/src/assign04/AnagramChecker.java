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
		//Create hashmap with all versions of annagrams
		//increment key values everytime that annagram is found
		//get most common annagram
		//if word the annagram of word in orignal string equals key, add that word to return string
		
		String[] copyArr = new String [arr.length];
		String[] annagramArray = new String [arr.length];
		HashMap<String, Integer> annaMap = new HashMap<String, Integer>();
		String mostCommonAnnagram = "";
		ArrayList<String> uniqueAnagrams = new ArrayList<String>();
		
		//Copy values of initial array into copyArray
		for(int i = 0; i < arr.length; i++) {
			copyArr[i] = arr[i];
		}
		
		
		//Create new array with initial arrays annagram values
		for(int j = 0; j < arr.length; j++) {
			annagramArray[j] = sort(arr[j]);
		}
		System.out.print("Anagram Array: ");
		System.out.println(Arrays.toString(annagramArray));
		
		
		//Populate hashmap with annagram count
		for(String s : annagramArray) {
			if(annaMap.containsKey(s)) {
				annaMap.replace(s, (annaMap.get(s)) + 1);
			}
			else{ 
				annaMap.put(s, 0);
				uniqueAnagrams.add(s);
			}
		}
	
		mostCommonAnnagram = uniqueAnagrams.get(0);
		for(String s : uniqueAnagrams) {
			if(annaMap.get(s) > annaMap.get(mostCommonAnnagram)) {
				s = mostCommonAnnagram;
			}
		}
			uniqueAnagrams.clear();
			
			for(int l = 0; l < copyArr.length; l++) {
				if(sort(copyArr[l]).equals(mostCommonAnnagram)) {
					uniqueAnagrams.add(copyArr[l]);
				}
			}
		
			String[] returnArr = new String[uniqueAnagrams.size()];
		for(int m = 0; m < uniqueAnagrams.size(); m++) {
			returnArr[m] = uniqueAnagrams.get(m);
		}
		
		return returnArr;
		
		
//		
//		String[] temp = new String[arr.length];
//		for(int i = 0; i < arr.length; i++) {
//			temp[i] = arr[i];
//		}
//		
//		//Make annagram array
//		for(int i = 0; i < arr.length; i++) {
//			arr[i] = sort(arr[i]);
//		}
//		insertionSort(arr, new OrderByString ());		
//		getMostFrequent(arr);
	}
	
	
//	public static String getMostFrequent(String[] str) {
//		int counter = 0;
//		
//		
//	}
	
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
	
	protected static class OrderByString implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			return o1.compareTo(o2);
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
