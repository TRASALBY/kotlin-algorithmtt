package solve_13418

import java.io.StreamTokenizer

private lateinit var parent: IntArray
private val edgeList = ArrayList<Edge>()
private lateinit var visited: BooleanArray

private const val UP = 0
private const val DOWN = 1

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    fun findParent(a: Int): Int {
        return if (parent[a] == a) a
        else {
            parent[a] = findParent(parent[a])
            parent[a]
        }
    }

    fun isUnion(a: Int, b: Int) = findParent(a) == findParent(b)


    fun makeUnion(a: Int, b: Int) {
        val rootA = findParent(a)
        val rootB = findParent(b)

        if (rootA > rootB) {
            parent[rootA] = rootB
        } else {
            parent[rootB] = rootA
        }
        visited[a] = true
        visited[b] = true
    }

    val n = input()
    val m = input()


    var start = input()
    var end = input()
    var type = input()

    val defaultCnt = if (type == UP) 1 else 0
    val defaultParent = IntArray(n + 1) { it }
    defaultParent[1] = 0

    parent = defaultParent.clone()
    visited = BooleanArray(n + 1)
    makeUnion(start, end)

    repeat(m) {
        start = input()
        end = input()
        type = input()
        edgeList.add(Edge(start, end, type))
    }
    edgeList.sortWith(compareBy { it.type })

    var visited = BooleanArray(n + 1)

    var cnt = defaultCnt
    for (edge in edgeList) {
        if (visited.all { it }) break
        val (s, e, t) = edge
        if (isUnion(s, e).not()) {
            makeUnion(s, e)
            if (t == UP) {
                cnt++
            }
        }
    }
    val max = cnt * cnt

    parent = defaultParent.clone()
    edgeList.reverse()
    visited = BooleanArray(n + 1)
    cnt = defaultCnt
    for (edge in edgeList) {
        if (visited.all { it }) break
        val (s, e, t) = edge
        if (isUnion(s, e).not()) {
            makeUnion(s, e)
            if (t == UP) {
                cnt++
            }
        }
    }
    val min = cnt * cnt

    println(max - min)
}

data class Edge(val start: Int, val end: Int, val type: Int)