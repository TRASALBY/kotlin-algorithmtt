import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        ArrayDeque<Character> deque = new ArrayDeque<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int s = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        String input = br.readLine();
        st = new StringTokenizer(br.readLine());
        int answer = 0;

        HashMap<Character, Integer> countHash = new HashMap<>();
        countHash.put('A', Integer.parseInt(st.nextToken()));
        countHash.put('C', Integer.parseInt(st.nextToken()));
        countHash.put('G', Integer.parseInt(st.nextToken()));
        countHash.put('T', Integer.parseInt(st.nextToken()));

        int idx = 0;

        int a = 0;
        int c = 0;
        int g = 0;
        int t = 0;
        while (idx < input.length()) {
            while (deque.size() < p) {
                Character now = input.charAt(idx++);
                deque.add(now);
                switch (now) {
                    case 'A':
                        a++;
                        break;
                    case 'C':
                        c++;
                        break;
                    case 'G':
                        g++;
                        break;
                    case 'T':
                        t++;
                        break;
                }
            }

            if(a >= countHash.get('A') && c >= countHash.get('C') && g >= countHash.get('G') && t >= countHash.get('T')){
                answer++;
            }

            Character removeChar = deque.pollFirst();

            switch (removeChar) {
                case 'A':
                    a--;
                    break;
                case 'C':
                    c--;
                    break;
                case 'G':
                    g--;
                    break;
                case 'T':
                    t--;
                    break;
            }
        }
        System.out.println(answer);
    }
}