import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {-1, 1, 0, 0};
    static int n;
    static int answer = 0;
    static int[][] map;

    static Shark babyShark;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int now = Integer.parseInt(st.nextToken());
                if (now == 9) {
                    babyShark = new Shark(j, i);
                    map[i][j] = 0;
                } else {
                    map[i][j] = now;
                }
            }
        }

        findFeed();
        System.out.println(answer);
    }

    private static void findFeed() {
        while (true) {
            PriorityQueue<Fish> fishQueue = new PriorityQueue<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] != 0 && map[i][j] < babyShark.size) {
                        fishQueue.add(getDist(j, i));
                    }
                }
            }

            if (fishQueue.isEmpty() || fishQueue.peek().dist == Integer.MAX_VALUE) {
                break;
            } else {
                Fish fish = fishQueue.poll();
                babyShark.eat(fish);
                map[fish.y][fish.x] = 0;
                answer += fish.dist;
            }
        }

    }

    private static Fish getDist(int x, int y) {
        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(babyShark.x, babyShark.y));
        dist[babyShark.y][babyShark.x] = 0;

        while (!queue.isEmpty()) {
            Position cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n && map[ny][nx] <= babyShark.size) {
                    if (dist[cur.y][cur.x] + 1 < dist[ny][nx]) {
                        dist[ny][nx] = dist[cur.y][cur.x] + 1;
                        queue.add(new Position(nx, ny));
                    }
                }
            }
        }

        return new Fish(x, y, dist[y][x]);

    }
}

class Shark {
    int size, x, y, nowEat;

    Shark(int x, int y) {
        size = 2;
        nowEat = 0;
        this.x = x;
        this.y = y;
    }

    void eat(Fish feed) {
        this.x = feed.x;
        this.y = feed.y;
        nowEat += 1;
        if (nowEat == size) {
            nowEat = 0;
            size++;
        }
    }
}

class Fish implements Comparable<Fish> {
    int x, y, dist;

    Fish(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }

    @Override
    public int compareTo(Fish other) {
        if (this.dist == other.dist) {
            if (this.y == other.y) {
                return this.x - other.x;
            } else {
                return this.y - other.y;
            }
        } else {
            return this.dist - other.dist;
        }
    }
}

class Position {
    int x, y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

