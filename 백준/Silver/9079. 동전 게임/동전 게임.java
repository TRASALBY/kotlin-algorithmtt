import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static Queue<Board> queue;
	static int nowMin;
	static HashMap<Integer, Integer> numHash;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int tc = Integer.parseInt(br.readLine());
		int[][] board;

		for (int t = 0; t < tc; t++) {
			board = new int[3][3];
			numHash = new HashMap<Integer, Integer>();
			nowMin = Integer.MAX_VALUE;
			// 입력을 0또는 1로 치환
			for (int i = 0; i < 3; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 3; j++) {
					if (st.nextToken().charAt(0) == 'H') {
						board[i][j] = 0;
					} else {
						board[i][j] = 1;
					}
				}
			}
			queue = new LinkedList<>();
			int nowBinaryNum = getBinaryNum(board);
			if(nowBinaryNum == 0 || nowBinaryNum == 511) {
				nowMin = 0;
			} else {
				queue.add(new Board(0, board));
				numHash.put(nowBinaryNum, 0);
			}
			

			while (!(queue.isEmpty())) {
				Board cur = queue.poll();
				nowBinaryNum = getBinaryNum(cur.board);
				if (numHash.containsKey(nowBinaryNum) && numHash.get(nowBinaryNum) > cur.cnt) {
					continue;
				}

				// 가로
				for (int i = 0; i < 3; i++) {
					int[][] newBoard = arrayCopy(cur.board);
					for (int j = 0; j < 3; j++) {
						newBoard[i][j] = newBoard[i][j] == 0 ? 1 : 0;
					}
					checkBoard(cur.cnt, newBoard);
				}

				// 세로
				for (int i = 0; i < 3; i++) {
					int[][] newBoard = arrayCopy(cur.board);
					for (int j = 0; j < 3; j++) {
						newBoard[j][i] = newBoard[j][i] == 0 ? 1 : 0;
					}
					checkBoard(cur.cnt, newBoard);
				}

				// 대각선1
				int[][] newBoard = arrayCopy(cur.board);
				for (int i = 0; i < 3; i++) {
					newBoard[i][i] = newBoard[i][i] == 0 ? 1 : 0;
				}
				checkBoard(cur.cnt, newBoard);

				// 대각선2
				newBoard = arrayCopy(cur.board);
				for (int i = 0; i < 3; i++) {
					newBoard[i][2 - i] = newBoard[i][2 - i] == 0 ? 1 : 0;
				}
				checkBoard(cur.cnt, newBoard);
			}

			if (nowMin == Integer.MAX_VALUE) {
				sb.append(-1);
			} else {
				sb.append(nowMin);
			}
			sb.append("\n");

		}

		System.out.println(sb);
	}

	public static void checkBoard(int cnt, int[][] board) {
		int binaryNum = getBinaryNum(board);
		if (binaryNum == 0 || binaryNum == 511) {
			nowMin = Math.min(nowMin, cnt + 1);
		}
		if (!(numHash.containsKey(binaryNum))) {
			queue.add(new Board(cnt + 1, board));
			numHash.put(binaryNum, cnt + 1);
		}
	}

	public static int[][] arrayCopy(int[][] board) {
		int[][] newBoard = new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				newBoard[i][j] = board[i][j];
			}
		}
		return newBoard;
	}

	public static int getBinaryNum(int[][] board) {
		int sum = 0;
		int nowBinary = 1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				sum += board[i][j] * nowBinary;
				nowBinary *= 2;
			}
		}
		return sum;
	}

}

class Board {
	int cnt;
	int[][] board;

	Board(int cnt, int[][] board) {
		this.cnt = cnt;
		this.board = board;
	}
}
