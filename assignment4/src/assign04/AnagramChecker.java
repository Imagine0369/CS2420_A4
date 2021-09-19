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
		
		
	}
	
	public static void main(String[] args) {
		String[] testArr = {"Art" , "Tar", "Hat", "crates", "rack", "reacts", "Caters", "caster", "car"};
		String test = "Reacts";
		String testB = "Caters";
		System.out.println(areAnagrams(test, testB));
		//System.out.println(sort(testB));
	}

}
