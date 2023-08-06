import java.util.*;


class Solution {
    static int[][] graph;
    
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        
        graph = new int[n+1][n+1];
        
        for(int i = 0; i < wires.length; i++){
            int v1 = wires[i][0];
            int v2 = wires[i][1];
            
            graph[v1][v2] = 1;
            graph[v2][v1] = 1;            
        }
        
        for(int i = 0; i < wires.length; i++){
            Wire lastWire = null;
            Wire nowWire = null;
            if(i != 0){
                lastWire = new Wire(wires[i-1][0], wires[i-1][1]);
            }
            nowWire = new Wire(wires[i][0], wires[i][1]);
            cutWire(lastWire, nowWire);
            
           answer = Math.min(answer,  Math.abs(n - bfs(1,n) * 2));
        }
        
        
        return answer;
    }
    
    public void cutWire(Wire last, Wire now){
        if(last != null){
            graph[last.v1][last.v2] = 1;
            graph[last.v2][last.v1] = 1;
        }
        graph[now.v1][now.v2] = 0;
        graph[now.v2][now.v1] = 0;
    }
    
    public int bfs(int start, int n){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        
        int cnt = 1;
        boolean[] visited = new boolean[n + 1];
        visited[0] = true;
        visited[start] = true;
        
        
        
        while(!(queue.isEmpty())){
            int cur = queue.poll();
            for(int i = 1; i <= n; i++){
                if(!visited[i] && graph[cur][i] == 1){
                    visited[i] = true;
                    queue.add(i);
                    cnt++;
                }
            }
        }
        
        
        return cnt;
        
    }
    
    
}

class Wire{
    int v1,v2;
    Wire(int v1, int v2){
        this.v1 = v1;
        this.v2 = v2;
    }
}
