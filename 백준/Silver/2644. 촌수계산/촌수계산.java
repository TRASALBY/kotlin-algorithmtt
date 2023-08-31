import java.io.*;
import java.util.*;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		int m = Integer.parseInt(br.readLine());

		ArrayList<Integer>[] edgeList = new ArrayList[n + 1];
		int[] dist = new int[n + 1];
		boolean[] visited = new boolean[n + 1];
		for (int i = 1; i <= n; i++) {
			edgeList[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			edgeList[p].add(c);
			edgeList[c].add(p);
		}

		Queue<Integer> queue = new LinkedList<>();

		queue.add(start);
		visited[start] = true;
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (int next : edgeList[cur]) {
				if (!visited[next]) {
					dist[next] = dist[cur] + 1;
					visited[next] = true;
					queue.add(next);
				}
			}
		}

		int answer = visited[end] ? dist[end] : -1;

		System.out.println(answer);
	}

}
