import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
	static int[] nums;
	static int n;
	static ArrayList<Integer> select;
	static StringBuilder sb;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());

		select = new ArrayList<>();
		while (n != 0) {
			nums = new int[n];
			for (int i = 0; i < n; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}

			Combination(0);
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			sb.append("\n");
		}

		System.out.println(sb);
	}

	public static void Combination(int start) {
		if (select.size() == 6) {
			for (int num : select) {
				sb.append(String.format("%d ", num));
			}
			sb.append("\n");
			return;
		}
		for (int i = start; i < n; i++) {
			select.add(nums[i]);
			Combination(i + 1);
			select.remove(select.size() - 1);
		}
	}
}