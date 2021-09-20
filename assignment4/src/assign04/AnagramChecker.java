package assign04;

import java.util.Comparator;
import java.util.HashMap;

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
			while(i != 0 && cmp.compare(key, arr[i]) < 0) {
				arr[i] = arr[i-1];
				i--;
			}
			arr[i] = key;
		}
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
		
		String annagramArray
		HashMap<String, Integer> annaMap = new HashMap<String, Integer>();
		
		
		
		for(String s : arr) {
			if(annaMap.containsValue(s)) {
				annaMap.put(s, annaMap.get(s) + 1);
			}
			else{ 
				annaMap.put(s, 0);
			}
		}
		
		
		
		
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
	
	public static String getMostFrequent(String[] str) {
		int counter = 0;
		
		
	}
	
	protected class OrderByString implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			return o1.compareTo(o2);
		}
	}
	
	public static void main(String[] args) {
		String[] testArr = {"Art" , "Tar", "Hat", "crates", "rack", "reacts", "Caters", "caster", "car"};
		String test = "";
		String testB = "";
		System.out.println(areAnagrams(test, testB));
		//System.out.println(sort(testB));
	}

}
