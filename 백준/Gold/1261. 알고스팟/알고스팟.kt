package solve_1261

import java.util.*

private val dx = arrayOf(0, 0, -1, 1)
private val dy = arrayOf(1, -1, 0, 0)

private fun main() {
    val (m, n) = readln().split(" ").map { it.toInt() }
    val maze = Array(n) {
        readln().toCharArray()
    }
    val distance = Array(n){
        IntArray(m){
            Int.MAX_VALUE
        }
    }

    distance[0][0] = 0
    val queue : Queue<Node> = LinkedList()
    queue.add(Node(0,0))

    while(queue.isNotEmpty()){
        val (x,y) = queue.poll()
        for(i in dx.indices) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            if(nx < 0 || nx >= m) continue
            if(ny < 0 || ny >= n) continue

            var nowDist = distance[y][x]
            if(maze[y][x] == '1'){
                nowDist += 1
            }

            if(distance[ny][nx] > nowDist){
                distance[ny][nx] = nowDist
                queue.add(Node(nx,ny))
            }
        }
    }

    println(distance[n-1][m-1])

}

private data class Node(val x: Int, val y: Int)
