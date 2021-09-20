package assign04;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

import assign04.AnagramChecker.OrderByAnagram;

class AnagramCheckerTest {
	
	//------------------------- sort tests starts---------------------------
	@Test
	void testSortTwoCharacters() {
		assertEquals("ab", AnagramChecker.sort("ba"));
		assertEquals("az", AnagramChecker.sort("za"));
		assertEquals("pq", AnagramChecker.sort("pq"));
		assertEquals("gy", AnagramChecker.sort("yg"));
	}
	@Test
	void testSortSimpleCase() {
		assertEquals("hjno", AnagramChecker.sort("john"));
		assertEquals("acilnv", AnagramChecker.sort("calvin"));
		assertEquals("ddeloo", AnagramChecker.sort("doodle"));
		assertEquals("abcde", AnagramChecker.sort("edcba"));
	}
	@Test
	void testSortPreSorted() {
		assertEquals("hjno", AnagramChecker.sort("hjno"));
		assertEquals("acilnv", AnagramChecker.sort("aclinv"));
		assertEquals("ddeloo", AnagramChecker.sort("ddeloo"));
		assertEquals("abcde", AnagramChecker.sort("abcde"));
	}
	@Test
	void testSortCapitals() {
		assertEquals("Jhno", AnagramChecker.sort("John"));
		assertEquals("ANVcil", AnagramChecker.sort("cAlViN"));
		assertEquals("DDELOO", AnagramChecker.sort("DOODLE"));
		assertEquals("Eabcd", AnagramChecker.sort("Edcba"));
	}
	@Test
	void testSortEmpty() {
		assertEquals("", AnagramChecker.sort(""));
	}
	@Test
	void testSortWithNumbers() {
		assertEquals("7Jhno", AnagramChecker.sort("Jo7hn"));
		assertEquals("1acilnv", AnagramChecker.sort("1calvin"));
		assertEquals("34ddeloo", AnagramChecker.sort("d4oo3dle"));
		assertEquals("03abcde", AnagramChecker.sort("3edcba0"));
	}
	//------------------------- sort tests ends-----------------------------
	
	
	//------------------------- insertionSort tests starts---------------------------
	@Test
	void testInsertionSortInteger() {
		Integer[] testArr = {2,6,4,1,-1,7,8,0,-3,89,4};
		
		Comparator<Integer> cmp = new OrderByInteger();
		AnagramChecker.insertionSort(testArr, cmp );
		
		Integer[] testVals = {-3,-1,0,1,2,4,4,6,7,8,89};
		assertArrayEquals(testVals, testArr);
		
	}
	@Test
	void testInsertionSortAlpha() {
		String[] testArr = {"art" , "tar", "hat", "crates", "rack", "reacts", "caters", "caster", "car"};
		
		Comparator<String> cmp = new OrderByAlpha();
		AnagramChecker.insertionSort(testArr, cmp );
		
		String[] testVals = {"art","car","caster", "caters","crates","hat","rack", "reacts","tar"};
		assertArrayEquals(testVals, testArr);
	}
	@Test
	void testInsertionSortAnagram() {
		String[] testArr = {"cat","tab","join","bat","zzzzz"};
		
		Comparator<String> cmp = new OrderByAnagram();
		AnagramChecker.insertionSort(testArr, cmp );
		
		String[] testVals = {"tab","bat","cat", "join","zzzzz"};
		assertArrayEquals(testVals, testArr);
	}
	@Test
	void testInsertionSortAnagramWithNumbers() {
		String[] testArr = {"ca3t","3tab2","dans","da2ns","jo84in","ba21t","zzzz0zzz","dans8"};
		
		Comparator<String> cmp = new OrderByAnagram();
		AnagramChecker.insertionSort(testArr, cmp );
		
		String[] testVals = {"zzzz0zzz","ba21t","3tab2","da2ns", "ca3t","jo84in","dans8","dans"};
		assertArrayEquals(testVals, testArr);
	}
	@Test
	void testInsertionSortAnagramWithCapitals() {
		String[] testArr = {"cAt","TaB","join","Cat","joIn","bat","CAT","jOOhns"};
		
		Comparator<String> cmp = new OrderByAnagram();
		AnagramChecker.insertionSort(testArr, cmp );
		
		String[] testVals = {"CAT", "cAt", "TaB", "Cat", "joIn", "jOOhns", "bat", "join"};
		assertArrayEquals(testVals, testArr);
	}
	@Test
	void testInsertionSortEmptyArray() {
		String[] testArr = new String[0];
		
		Comparator<String> cmp = new OrderByAnagram();
		AnagramChecker.insertionSort(testArr, cmp );
		
		String[] testVals = {};
		assertArrayEquals(testVals, testArr);
	}
	@Test
	void testInsertionSortPreSorted() {
		String[] testArr = {"tab","bat","cat","cat", "cat", "joohns","join","join"};
		
		Comparator<String> cmp = new OrderByAnagram();
		AnagramChecker.insertionSort(testArr, cmp );
		
		String[] testVals = {"tab","bat","cat","cat", "cat", "joohns","join","join"};
		assertArrayEquals(testVals, testArr);
	}
	@Test
	void testInsertionSortSameValues() {
		String[] testArr = {"TaB","bat","cAt","Cat","TaB","bat","cAt","Cat",};
		
		Comparator<String> cmp = new OrderByAnagram();
		AnagramChecker.insertionSort(testArr, cmp );
		
		String[] testVals = {"cAt","cAt","TaB","TaB","Cat","Cat","bat","bat"};
		assertArrayEquals(testVals, testArr);
	}
	//------------------------- insertionSort tests ends-----------------------------
	
	
	//------------------------- areAnagrams tests starts---------------------------
	@Test
	void testAreAnagramsStandard() {
		assertTrue( AnagramChecker.areAnagrams("done","node") );
	}
	@Test
	void testAreAnagramsWithCaps() {
		assertTrue( AnagramChecker.areAnagrams("doNe","NODE") );
	}
	@Test
	void testAreAnagramsEmpty() {
		assertTrue( AnagramChecker.areAnagrams("","") );
	}
	@Test
	void testAreAnagramsSameWord() {
		assertTrue( AnagramChecker.areAnagrams("done","done") );
	}
	@Test
	void testAreAnagramsFalse() {
		assertFalse( AnagramChecker.areAnagrams("john","NODE") );
	}
	@Test
	void testAreAnagramsWithNumbers() {
		assertTrue( AnagramChecker.areAnagrams("0899","9890") );
	}
	//------------------------- areAnagrams tests ends-----------------------------
	
	
	//------------------------- getLargestAnagramGroup tests starts---------------------------
	@Test
	void testGetLargestAnagramGroupOneGroup() {
		String[] testArr = {"cat","tab","join","bat"};
		String[] testVals = {"tab", "bat"};
		assertArrayEquals( testVals, AnagramChecker.getLargestAnagramGroup(testArr) );
	}
	@Test
	void testGetLargestAnagramGroupTwoGroups() {
		String[] testArr = {"caster","tab","join","bat","crates","steacr","ratesc"};
		String[] testVals = {"caster", "crates", "steacr", "ratesc"};
		assertArrayEquals( testVals, AnagramChecker.getLargestAnagramGroup(testArr) );
	}
	@Test
	void testGetLargestAnagramGroupNoAnagrams() {
		String[] testArr = {"cat","tab","join","ba3t","Caat","cate","cant"};
		String[] testVals = new String[0];
		assertArrayEquals( testVals, AnagramChecker.getLargestAnagramGroup(testArr) );
	}
	@Test
	void testGetLargestAnagramGroupCapitals() {
		String[] testArr = {"castEr","Tab","joIn","bat","crAtes","steacr","ratesc","Abt"};
		String[] testVals = {"castEr", "crAtes", "steacr", "ratesc"};
		System.out.println( Arrays.toString(testArr) );
		
		System.out.println( Arrays.toString(AnagramChecker.getLargestAnagramGroup(testArr)) );
	}
	//------------------------- getLargestAnagramGroup tests ends-----------------------------
	
	protected static class OrderByAnagram implements Comparator<String>{

		@Override
		public int compare(String o1, String o2) {
			return AnagramChecker.sort(o1).compareTo( AnagramChecker.sort(o2) );
		}
	}
	
	protected static class OrderByInteger implements Comparator<Integer>{

		@Override
		public int compare(Integer o1, Integer o2) {
			return o1-o2;
		}
	}
	
	protected static class OrderByAlpha implements Comparator<String>{

		@Override
		public int compare(String o1, String o2) {
			return o1.compareTo( o2 );
		}
	}

}
