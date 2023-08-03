import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());

		BaseBall[] baseBallArr = new BaseBall[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			baseBallArr[i] = new BaseBall(st.nextToken(), st.nextToken(), st.nextToken());
		}
		
		int answer = 0;
		boolean flag;
		for(int i = 1; i < 10; i++) {
			for(int j = 1; j < 10; j++) {
				if(i == j) continue;
				for(int k = 1; k < 10; k++) {
					if(k == i || k == j) continue;
					
					flag = true;
					for(BaseBall baseBall :  baseBallArr) {
						int[] arr = {i,j,k};
						int strikeCnt = baseBall.getStrike(arr);
						if(baseBall.strikeCnt == strikeCnt) {
							int ballCnt = baseBall.getBall(arr) - strikeCnt;
							if(baseBall.ballCnt != ballCnt) {
								flag = false;
								break;
							}
						} else {
							flag = false;
							break;
						}
					}
					if(flag) {
						answer++;
					}
				}
			}
		}
		
		System.out.println(answer);

	}

}

class BaseBall {
	int[] num = new int[3];
	HashSet<Integer> numHash = new HashSet<Integer>();
	int strikeCnt;
	int ballCnt;

	BaseBall(String strNum, String strikeCnt, String ballCnt) {

		for (int i = 0; i < 3; i++) {
			int nowNum = Character.getNumericValue(strNum.charAt(i));
			num[i] = nowNum;
			numHash.add(nowNum);
		}
		this.strikeCnt = Integer.parseInt(strikeCnt);
		this.ballCnt = Integer.parseInt(ballCnt);
	}

	int getStrike(int[] question) {
		int cnt = 0;
		for (int i = 0; i < 3; i++) {
			if (question[i] == num[i]) {
				cnt++;
			}
		}
		return cnt;
	}

	int getBall(int[] question) {
		int cnt = 0;
		for (int i = 0; i < 3; i++) {
			if (numHash.contains(question[i])) {
				cnt++;
			}
		}
		return cnt;
	}

}