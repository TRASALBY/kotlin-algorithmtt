import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int k;
	static int nLength;
	static int[] arr;
	static int answer;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		String num = st.nextToken();
		n = Integer.parseInt(num);
		nLength = num.length();
		k = Integer.parseInt(st.nextToken());

		arr = new int[k];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		backTrack(0, 0);
		System.out.println(answer);
		
	}

	static void backTrack(int depth, int nowNum) {
		if (depth <= nLength) {
			if (nowNum <= n) {
				answer = Math.max(answer, nowNum);
			}
			for (int i = 0; i < k; i++) {
				int newNum = nowNum*10 + arr[i];
				if (newNum > n)
					continue;
				backTrack(depth + 1, newNum);
			}
		} else {
			return;
		}

	}
}
