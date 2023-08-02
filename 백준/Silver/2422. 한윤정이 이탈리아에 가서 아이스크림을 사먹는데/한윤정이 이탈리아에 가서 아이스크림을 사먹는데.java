import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	static HashSet<Integer>[] mismatchHashs;
	static int n;
	static int cnt = 0;
	static boolean[] visited;
	static LinkedList<Integer> combi = new LinkedList<>();;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		mismatchHashs = new HashSet[n + 1];
		visited = new boolean[n + 1];

		for (int i = 1; i <= n; i++) {
			mismatchHashs[i] = new HashSet<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			mismatchHashs[num1].add(num2);
			mismatchHashs[num2].add(num1);
		}

		backTrack(0, 1);

		System.out.println(cnt);

	}

	static void backTrack(int depth, int start) {
		if (depth == 3) {
			cnt += 1;
            return;
		}
		for (int i = start; i <= n; i++) {
			boolean flag = true;
			for(int num : combi) {
				if(mismatchHashs[i].contains(num)) {
					flag = false;
					break;
				}
			}
			if(flag) {
				combi.add(i);
				backTrack(depth+1, i+1);
				combi.removeLast();
			}
		}
	}

}
