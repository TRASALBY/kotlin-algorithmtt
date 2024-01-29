import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<Edge>[] edgeList = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            edgeList[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            edgeList[start].add(new Edge(end, i, i + 1));
            edgeList[end].add(new Edge(start, i, i + 1));
        }

        PriorityQueue<Node> queue = new PriorityQueue<>();

        queue.add(new Node(1, 0));

        long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (dist[cur.num] < cur.cost) continue;

            for (Edge edge : edgeList[cur.num]) {
                long addTime = (((edge.startTime - cur.cost) % m) + m) % m + 1;
                if (dist[edge.end] > dist[cur.num] + addTime) {
                    dist[edge.end] = dist[cur.num] + addTime;
                    queue.add(new Node(edge.end, dist[edge.end]));
                }
            }
        }

        System.out.println(dist[n]);
    }


}

class Edge {
    int end, startTime, endTime;

    Edge(int end, int startTime, int endTime) {
        this.end = end;
        this.startTime = startTime;
        this.endTime = endTime;
    }


}

class Node implements Comparable<Node> {
    int num;
    long cost;

    Node(int num, long cost) {
        this.num = num;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node other) {
        return Long.compare(this.cost, other.cost);
    }
}