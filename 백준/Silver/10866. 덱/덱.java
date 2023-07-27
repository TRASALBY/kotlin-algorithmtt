import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		Deque<Integer> queue = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			switch (st.nextToken()) {
			case ("push_front"):
				queue.addFirst(Integer.parseInt(st.nextToken()));
				break;
			case ("push_back"):
				queue.addLast(Integer.parseInt(st.nextToken()));
				break;
			
			case ("pop_front"):
				if (queue.isEmpty()) {
					sb.append(-1).append("\n");
				} else {
					sb.append(queue.pollFirst()).append("\n");
				}
				break;
			case ("pop_back"):
				if (queue.isEmpty()) {
					sb.append(-1).append("\n");
				} else {
					sb.append(queue.pollLast()).append("\n");
				}
				break;
			case ("size"):
				sb.append(queue.size()).append("\n");
				break;
			case ("empty"):
				if (queue.isEmpty()) {
					sb.append(1).append("\n");
				} else {
					sb.append(0).append("\n");
				}
				break;
			case ("front"):
				if (queue.isEmpty()) {
					sb.append(-1).append("\n");
				} else {
					sb.append(queue.peekFirst()).append("\n");
				}
				break;
			case ("back"):
				if (queue.isEmpty()) {
					sb.append(-1).append("\n");
				} else {
					sb.append(queue.peekLast()).append("\n");
				}
				break;
			}
		}
		System.out.println(sb);
	}
}