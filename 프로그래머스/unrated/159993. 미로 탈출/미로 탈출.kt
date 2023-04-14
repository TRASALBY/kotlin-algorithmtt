import java.util.*

class Solution {
    val dx = arrayOf(-1,1,0,0)
    val dy = arrayOf(0,0,1,-1)
    
    fun solution(maps: Array<String>): Int {
        var answer: Int = 0
        
        var laber = Node(0,0)
        var start = Node(0,0)
        var end = Node(0,0)
        
        var map = Array(maps.size){ y ->
            IntArray(maps[0].length) { x ->
                when(maps[y][x]){
                    'S' -> {
                        start = Node(x,y)
                        0
                    }
                    'O' -> {
                        0
                    }
                    'X' -> {
                        -1
                    }
                    'E' -> {
                        end = Node(x,y)
                        0
                    }
                    'L' -> {
                        laber = Node(x,y)
                        0
                    }
                    else -> {
                        0
                    }
                }
            }
        }
        
        val mapCopy = Array(map.size){ y->
            IntArray(map[0].size) {x ->
                map[y][x]
            }
        }
        
        val startToLaber = bfs(start, laber,map)
        val laberToEnd = bfs(laber,end,mapCopy)
        if(startToLaber == -1 || laberToEnd == -1) {
            answer = -1
        } else {
            answer = startToLaber + laberToEnd
        }
        
        return answer
    }
    
    fun bfs(start: Node, end: Node, map: Array<IntArray>): Int{
        val queue : Queue<Node> = LinkedList()
        
        queue.add(start)
        
        while(queue.isNotEmpty()){
            val cur = queue.poll()
            
            for(i in dx.indices){
                val nx = cur.x + dx[i]
                val ny = cur.y + dy[i]
                
                if(nx < 0 || nx > map[0].lastIndex) continue
                if(ny < 0 || ny > map.lastIndex) continue
                
                if(map[ny][nx] == 0){
                    map[ny][nx] = map[cur.y][cur.x] + 1
                    if(nx == end.x && ny == end.y){
                        return map[ny][nx]
                    }
                    queue.add(Node(nx,ny))
                }
            }
        }
        
        return -1
        
    }
    
    
    
}

data class Node(val x: Int, val y: Int)