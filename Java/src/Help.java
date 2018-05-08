import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Help {

	public static void refList(ArrayList<Integer> res){
		res.add(1);
	}
	public static void refArray1(int [][] arr){
		arr[0][0] = 1;
	}
	public static int[][] refArray2(){
		int arr[][] = new int[][]{{1}};
		return arr;
	}



	private static void arrayFunctions() {


		// Array
		int [] a = new int[3];
		int aa[] = new int[3];
		int [] b = new int[]{1,2,3};
		int bb[] = new int[]{1,2,3};
		// Double array
		int [][]c = {{1,2,3}, {4,5,6}};
		int cc [][] = {{1,2,3}, {4,5,6}};
		// Lengths
		int length = a.length;
		length = new String(" ").length();
		System.out.println(Arrays.toString(b));
		System.out.println(Arrays.deepToString(c));
		int ccc [][][] = {{{1,2,3}}, {{4,5,6}},{{1,2,3}}, {{4,5,6}}};
		System.out.println(Arrays.deepToString(ccc));


	}
	private static void listFunctions() {
		// List
		List<Integer> l1 = Arrays.asList(1,2,3);
		List<Integer> l2 = new ArrayList<>(Arrays.asList(1,2,3));
		int length = l1.size();

		// List
		System.out.println("Printing list at once "+Arrays.toString(l1.toArray()));
		System.out.println(l1);
		Iterator<Integer> it = l1.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}


	}
	private static void mapFunctions() {
		// Map
		Map<Integer, Integer> map = new HashMap<>();
		map.put(1,11);
		map.put(2,22);
		// Map
		for(Map.Entry<Integer,Integer> entry : map.entrySet()){
			System.out.println("key : "+entry.getKey()+" value : "+entry.getValue());
		}
		System.out.println("Printing keys ");
		// returns a Set<Integer>
		for(Integer entry : map.keySet()){
			System.out.println("key : "+entry);
		}	
		// Iterator
		Iterator<Map.Entry<Integer,Integer>> it = map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<Integer,Integer> i = it.next();			
			System.out.println("key : "+i.getKey()+" value : "+i.getValue());
		}

		// Iterator
		Iterator<Integer> itset = map.keySet().iterator();
		while(itset.hasNext()){
			Integer i = itset.next();			
			System.out.println("key : "+i+" value : "+map.get(i));
		}

		// Lengths
		int length = map.size();


	}

	private static void comparableFunctions() {
		Comparable<Integer> comp = new Comparable<Integer>(){

			@Override
			public int compareTo(Integer o) {
				return 0;
			}

		};	


		Comparator<List<Integer>> c = new Comparator<List<Integer>>() {

			@Override
			public int compare(List<Integer> l1, List<Integer> l2) {
				int max = Math.max(l1.size(), l2.size());
				int min = Math.min(l1.size(), l2.size());
				for(int i = 0 ; i < max; i++){
					if(l1.size() == l2.size())	return l1.get(i) < l2.get(i) ? -1 : 1;
					if(i < min)	{
						if(l1.get(i) == l2.get(i)) continue;
						return l1.get(i) < l2.get(i) ? -1 : 1;
					}

					if(i == min && l1.size() < l2.size()){
						return -1;
					}
					if(i == min && l1.size() > l2.size()){
						return 1;
					}
				}
				return -1;

			}
		};
		ArrayList<Integer> l1 = new ArrayList<>(Arrays.asList());
		ArrayList<Integer> l2 = new ArrayList<>(Arrays.asList(13,12));
		ArrayList<Integer> l3 = new ArrayList<>(Arrays.asList(12));
		ArrayList<Integer> l4 = new ArrayList<>(Arrays.asList(13));
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		res.add(l1);
		ArrayList<Integer> a = new ArrayList<>(l2);
		Collections.sort(a);

		res.add(a);
		res.add(l3);
		res.add(l4);
		System.out.println("Printing ...");
		for(ArrayList<Integer> list : res){
			System.out.println(list);
		}
		System.out.println("Printing ...");
		Collections.sort(res,c);
		for(ArrayList<Integer> list : res){
			System.out.println(list);
		}
	}

	public static void printRef() {
		ArrayList<Integer> res = new ArrayList<>();
		refList(res);
		System.out.println(Arrays.toString(res.toArray())); // will have 1
		int [][] arr1 = new int[1][1];
		refArray1(arr1);
		int [][] arr2 = refArray2();
		System.out.println(arr1[0][0]); // will have 1
		System.out.println(arr2[0][0]); // will have 1
	}

	// Driver method
	public static void main (String[] args) 
	{


		System.out.println("Starting array functions");
		arrayFunctions();
		System.out.println("Starting map functions");
		mapFunctions();
		System.out.println("Starting list functions");
		listFunctions();
		System.out.println("Starting list functions");
		printRef();
		System.out.println("Starting comparable functions");
		comparableFunctions();

	}
}
