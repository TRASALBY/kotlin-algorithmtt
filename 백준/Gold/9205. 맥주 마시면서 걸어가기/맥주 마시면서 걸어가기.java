import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static final int MAX_VALUE = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());

        for (int t = 0; t < tc; t++) {
            int n = Integer.parseInt(br.readLine());
            Point[] arr = new Point[n + 2];
            for (int i = 0; i < n + 2; i++) {
                st = new StringTokenizer(br.readLine());
                arr[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            int[][] distArr = new int[n + 2][n + 2];

            for(int i = 0; i < n+1; i++){
                for(int j = i+1; j < n+2; j++){
                    int dist = calDist(arr[i], arr[j]);
                    distArr[i][j] = dist;
                    distArr[j][i] = dist;
                }
            }



            for (int k = 0; k < n + 2; k++) {
                for (int i = 0; i < n + 2; i++) {
                    for (int j = 0; j < n + 2; j++) {
                        distArr[i][j] = Math.min(distArr[i][j], distArr[i][k] + distArr[k][j]);
                        if(distArr[i][j] <= 1000){
                            distArr[i][j] = 0;
                        }
                    }
                }
            }

            if (distArr[0][n+1] == 0) {
                sb.append("happy").append("\n");
            } else {
                sb.append("sad").append("\n");
            }
        }

        System.out.println(sb);
    }


    static int calDist(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);

    }
}

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "x : " + this.x + " y : " + this.y;
    }

}