import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Deque<Balloon> deque = new ArrayDeque<>();
		
		
		for(int i = 1; i <= n; i ++) {
			deque.add(new Balloon(i, Integer.parseInt(st.nextToken())));
		}

		
		while(!deque.isEmpty()) {
			sb.append(deque.getFirst().index+" ");
			
			int next = deque.poll().num;
			if(deque.isEmpty()) {
				break;
			}
			if(next > 0) {
				for(int i = 0; i < next-1;i++) {
					deque.add(deque.pollFirst());
				}
			} else {
				for(int i = 0;i < Math.abs(next);i++ ) {
					deque.addFirst(deque.pollLast());
				}
			}
		}

		System.out.println(sb);
	}
}

class Balloon{
	int index;
	int num;
	
	public Balloon(int index, int num){
		this.index = index;
		this.num = num;
	}
}