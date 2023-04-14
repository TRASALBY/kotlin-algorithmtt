package solve_2660

import java.io.StreamTokenizer
import java.util.*

private const val INF = 1_000_000_000
private lateinit var graph: Array<IntArray>

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()

    graph = Array(n + 1) {
        IntArray(n + 1) {
            INF
        }
    }

    while (true) {
        val a = input()
        val b = input()
        if (a == -1 && b == -1) {
            break
        }
        graph[a][b] = 1
        graph[b][a] = 1
    }

    for(i in graph.indices){
        graph[i][i] = 0
    }

    for (i in 1..graph.lastIndex) {
        for (j in 1..graph.lastIndex) {
            for (k in 1..graph.lastIndex) {
                graph[j][k] = minOf(graph[j][i] + graph[i][k], graph[j][k])
            }
        }
    }

    val master = ArrayList<Int>()
    var nowMin = INF
    for (i in 1..graph.lastIndex) {
        val score = checkScore(graph[i])
        if (nowMin > score && score != 0) {
            nowMin = score
            master.clear()
            master.add(i)
        } else if (nowMin == score) {
            master.add(i)
        }
    }
    val sb = StringBuilder()
    sb.appendLine("$nowMin ${master.size}")
    master.sort()
    master.forEach{
        sb.append("$it ")
    }
    println(sb)
}

fun checkScore(score: IntArray): Int {
    var max = 0
    for (i in 1..score.lastIndex) {
        if(score[i] != INF){
            max = maxOf(max, score[i])
        }
    }
    return max
}

