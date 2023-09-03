import java.io.*;
import java.util.*;

class Main {

	static int n;
	static int m;
	static Egg[] eggs;
	static int maxCnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		eggs = new Egg[n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			eggs[i] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		backTrack(0);
		System.out.println(maxCnt);

	}

	static void backTrack(int nowIdx) {
		if (nowIdx == n) {
			int cnt = 0;
			for (Egg egg : eggs) {
				if (egg.durability <= 0)
					cnt++;
			}
			maxCnt = Math.max(maxCnt, cnt);
			return;
		}

		if (eggs[nowIdx].durability <= 0) {
			backTrack(nowIdx + 1);
		} else {
			boolean isAllBroken = true;
			for (int i = 0; i < n; i++) {
				if (i != nowIdx && eggs[i].durability > 0) {
					isAllBroken = false;
					eggs[i].durability -= eggs[nowIdx].weight;
					eggs[nowIdx].durability -= eggs[i].weight;
					backTrack(nowIdx + 1);
					eggs[i].durability += eggs[nowIdx].weight;
					eggs[nowIdx].durability += eggs[i].weight;
				}
			}
			if(isAllBroken) {
				backTrack(n);
			}
		}
	}

}

class Egg {
	int durability, weight;

	@Override
	public String toString() {
		return "durability=" + durability + ", weight=" + weight + "\n";
	}

	Egg(int durability, int weight) {
		this.durability = durability;
		this.weight = weight;
	}

}