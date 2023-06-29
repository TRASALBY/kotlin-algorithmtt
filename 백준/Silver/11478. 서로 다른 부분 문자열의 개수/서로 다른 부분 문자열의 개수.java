import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {
	static int[] nums;
	static int n;
	static ArrayList<Integer> select;
	static StringBuilder sb;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		HashSet<String> hash = new HashSet();

		String input = br.readLine();
		int size = input.length();

		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size + 1; j++) {
				String a = input.substring(i, j);
				hash.add(a);
			}
		}

		System.out.println(hash.size());

	}
}