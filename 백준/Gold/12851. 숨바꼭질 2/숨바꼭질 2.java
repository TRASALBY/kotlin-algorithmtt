import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] dp = new int[100_000_0];
        int[] visited = new int[100_000_0];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[n] = 0;
        visited[n] = 1;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(n);


        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == k) break;
            if (dp[cur] == Integer.MAX_VALUE) continue;

            if(cur + 1 < 100001){
                if (dp[cur] + 1 < dp[cur + 1]) {
                    queue.add(cur + 1);
                    dp[cur + 1] = dp[cur] + 1;
                    visited[cur + 1] = visited[cur];
                } else if (dp[cur] + 1 == dp[cur + 1]) {
                    visited[cur + 1] += visited[cur];
                }
            }
            if (cur - 1 >= 0) {
                if (dp[cur] + 1 < dp[cur - 1]) {
                    queue.add(cur - 1);
                    dp[cur - 1] = dp[cur] + 1;
                    visited[cur - 1] = visited[cur];
                } else if (dp[cur] + 1 == dp[cur - 1]) {
                    visited[cur - 1] += visited[cur];
                }
            }
            if (cur * 2 < 100_002) {
                if (dp[cur] + 1 < dp[cur * 2]) {
                    queue.add(cur * 2);
                    dp[cur * 2] = dp[cur] + 1;
                    visited[cur * 2] = visited[cur];
                } else if (dp[cur] + 1 == dp[cur * 2]) {
                    visited[cur * 2] += visited[cur];
                }
            }
        }

        System.out.println(dp[k]);
        System.out.println(visited[k]);

    }
}