import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());
            Map<String, Integer> closeMap = new HashMap<>();

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                st.nextToken();
                String part = st.nextToken();

                if (closeMap.containsKey(part)) {
                    closeMap.put(part, closeMap.get(part) + 1);
                } else {
                    closeMap.put(part, 1);
                }
            }
            int answer = 1;
            for(String key : closeMap.keySet()){
                answer *= closeMap.get(key) + 1;
            }
            answer -= 1;
            System.out.println(answer);
        }
    }
}