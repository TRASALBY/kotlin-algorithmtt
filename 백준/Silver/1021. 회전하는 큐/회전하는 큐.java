import java.io.*;
import java.util.*;
class Main{
    public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		LinkedList<Integer> deque = new LinkedList<Integer>();

		int count = 0;

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= n; i++) {
			deque.add(i);
		}

		int[] seq = new int[m];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < m; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < m; i++) {

			int targetIdx = deque.indexOf(seq[i]);
			int half;

			if (deque.size() % 2 == 0) {
				half = deque.size() / 2 - 1;
			} else {
				half = deque.size() / 2;
			}

			if (targetIdx <= half) {
				for (int j = 0; j < targetIdx; j++) {
					int temp = deque.pollFirst();
					deque.offerLast(temp);
					count++;
				}
			} else { 
				for (int j = 0; j < deque.size() - targetIdx; j++) {
					int temp = deque.pollLast();
					deque.offerFirst(temp);
					count++;
				}

			}
			deque.pollFirst();
		}

		System.out.println(count);

	}
}