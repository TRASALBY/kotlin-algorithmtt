package solve_2056

import java.util.*
import java.io.*

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }


    val n = input()
    val inputDepth = IntArray(n + 1)

    val edgeList = Array(n + 1) {
        ArrayList<Int>()
    }
    val nodeList = IntArray(n + 1)
    var nowNum = 1
    repeat(n) {
        val time = input()
        val m = input()
        repeat(m) {
            val nodeNum = input()
            edgeList[nodeNum].add(nowNum)
            inputDepth[nowNum]++
        }
        nodeList[nowNum] = time
        nowNum += 1
    }

    val queue: Queue<Int> = LinkedList()


    var time = 0
    val visited = BooleanArray(n + 1)
    visited[0] = true
    while (nodeList.any { it != 0 }) {
        time += 1
        for (i in 1..n) {
            if (inputDepth[i] == 0) {
                if (nodeList[i] != 0) {
                    nodeList[i] -= 1
                }
                if (nodeList[i] == 0 && visited[i].not()) {
                    queue.add(i)
                    visited[i] = true
                }
            }
        }
        while(queue.isNotEmpty()){
            val cur = queue.poll()
            for (end in edgeList[cur]) {
                inputDepth[end] -= 1
            }
        }
    }
    println(time)
}