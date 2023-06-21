package mornig_study_0302

import java.io.StreamTokenizer
import java.util.*

private lateinit var graph: Array<ArrayList<Node>>
private lateinit var graphReverse: Array<ArrayList<Node>>
private lateinit var distance: IntArray
private lateinit var distanceReverse: IntArray

private data class Node(val end: Int, val time: Int) : Comparable<Node> {
    override fun compareTo(other: Node): Int = time - other.time
}

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()
    val x = input()

    graph = Array(n + 1) {
        arrayListOf()
    }
    graphReverse = Array(n + 1) {
        arrayListOf()
    }
    distance = IntArray(n + 1) { Int.MAX_VALUE }
    distanceReverse = IntArray(n + 1) { Int.MAX_VALUE }

    for (i in 0 until m) {
        val start = input()
        val end = input()
        val time = input()
        graph[start].add(Node(end, time))
        graphReverse[end].add(Node(start, time))
    }

    dijkstra(x, graph, distance)
    dijkstra(x, graphReverse, distanceReverse)

    var answer = 0
    for (i in 1..n) {
        answer = maxOf(answer, distance[i] + distanceReverse[i])
    }

    println(answer)

}

private fun dijkstra(start: Int, graph: Array<ArrayList<Node>>, distance: IntArray) {
    val queue = PriorityQueue<Node>()
    queue.add(Node(start, 0))
    distance[start] = 0

    while (queue.isNotEmpty()) {
        val (now, dist) = queue.poll()
        if (distance[now] < dist) continue
        for (node in graph[now]) {
            val cost = dist + node.time
            if (cost < distance[node.end]) {
                distance[node.end] = cost
                queue.add(Node(node.end, cost))
            }
        }
    }
}

