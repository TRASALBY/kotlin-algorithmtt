package mornig_study

import java.io.*

private const val maxLen = 16
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()
    val r = input()

    val graph = Array(n + 1) {
        IntArray(n + 1) {
            maxLen
        }
    }

    val itemArray = IntArray(n + 1)

    for (i in 1..itemArray.lastIndex) {
        itemArray[i] = input()
    }

    for(i in 1..graph.lastIndex){
        graph[i][i] = 0
    }

    for (i in 0 until r) {
        val start = input()
        val end = input()
        val cost = input()
        graph[start][end] = cost
        graph[end][start] = cost
    }
    for (i in 1..n) {
        for (j in 1..n) {
            for (k in 1..n) {
                graph[j][k] = minOf(graph[j][k], graph[j][i] + graph[i][k])
            }
        }
    }

    var answer = 0
    for (i in 1..n) {
        var nowItemCnt = 0
        for (j in graph[i].indices) {
            if (graph[i][j] <= m) {
                nowItemCnt += itemArray[j]
            }
        }
        answer = maxOf(nowItemCnt, answer)
    }

    println(answer)
}