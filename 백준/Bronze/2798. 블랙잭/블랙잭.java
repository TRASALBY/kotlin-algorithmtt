import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb;
	
	static int[] cards;
	static int max = 0;
	static int n;
	static int m;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		
		st = new StringTokenizer(br.readLine());
		
		cards = new int[n];
		visited = new boolean[n];
		
		for(int i = 0; i < n; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		
		backTrack(0,0);
		
		System.out.println(max);
		
		
	}
		
	
	public static void backTrack(int depth, int nowSum) {
		if(nowSum > m) {
			return;
		}
		if(depth == 3 && nowSum <= m) {
			max = Math.max(max, nowSum);
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(visited[i] != true) {
				visited[i] = true;
				backTrack(depth+1, nowSum + cards[i]);
				visited[i] = false;
			}
		}
	}
}
