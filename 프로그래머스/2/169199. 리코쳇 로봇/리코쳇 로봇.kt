import java.util.*

class Solution {

    val dx = arrayOf(0, 0, 1, -1)
    val dy = arrayOf(1, -1, 0, 0)

    fun solution(board: Array<String>): Int {
        var answer: Int = 0
        val n = board.size
        val m = board.first().length

        val map = Array(n) {
            IntArray(m)
        }
        val visited = Array(n) {
            BooleanArray(m)
        }

        var start = Node(0, 0, 0)
        var goal = Node(0, 0, 0)


        for (i in 0 until n) {
            val input = board[i]
            for (j in 0 until m) {
                map[i][j] = when (input[j]) {
                    'D' -> 1
                    'R' -> {
                        start = Node(j, i, 0)
                        0
                    }

                    'G' -> {
                        goal = Node(j, i, 0)
                        0
                    }

                    else -> {
                        0
                    }
                }
            }
        }

        val queue: Queue<Node> = LinkedList()
        queue.add(start)
        visited[start.y][start.x] = true

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            for (i in dx.indices) {
                var nx = cur.x
                var ny = cur.y

                while ((nx + dx[i]) in 0 until m && (ny + dy[i]) in 0 until n &&
                        map[ny + dy[i]][nx + dx[i]] != 1) {

                    nx += dx[i]
                    ny += dy[i]
                }

                if (!visited[ny][nx]) {
                    if (nx == goal.x && ny == goal.y) {
                        return cur.count + 1
                    }
                    queue.add(Node(nx, ny, cur.count + 1))
                    visited[ny][nx] = true
                }

            }
        }

        return -1
    }
}

data class Node(val x: Int, val y: Int, val count: Int)
