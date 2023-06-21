import java.io.StreamTokenizer
import kotlin.math.*
import kotlin.collections.ArrayList

private lateinit var parents: IntArray

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()

    val pointList = List(n+1) {
        if(it == 0){
            Point(0,0,0)
        }else {
            Point(it, input(), input())
        }
    }

    parents = IntArray(n + 1) {
        it
    }

    repeat(m) {
        makeUnion(input(), input())
    }

    val edgeList = ArrayList<Edge>()

    for (i in 1 until n) {
        for (j in i + 1 .. n) {
            val p1 = pointList[i]
            val p2 = pointList[j]
            val dist = sqrt((p1.x - p2.x).toDouble().pow(2) + (p1.y - p2.y).toDouble().pow(2))

            edgeList.add(Edge(i, j, dist))
        }
    }

    edgeList.sort()
    var answer = 0.0

    for (edge in edgeList) {
        val (start, end, dist) = edge
        if (isUnion(start, end).not()) {
            makeUnion(start, end)
            answer += dist
        }
    }

    println(String.format("%.2f", answer))


}

private fun isUnion(x: Int, y: Int) = findParent(x) == findParent(y)

private fun findParent(x: Int): Int {
    if (parents[x] != x) {
        parents[x] = findParent(parents[x])
    }
    return parents[x]
}

private fun makeUnion(x: Int, y: Int) {
    val p1 = findParent(x)
    val p2 = findParent(y)

    if (p1 < p2) {
        parents[p2] = p1
    } else {
        parents[p1] = p2
    }
}

data class Point(val num: Int, val x: Int, val y: Int)

data class Edge(val start: Int, val end: Int, val dist: Double) : Comparable<Edge> {
    override fun compareTo(other: Edge) = compareValuesBy(this, other) { it.dist }

}