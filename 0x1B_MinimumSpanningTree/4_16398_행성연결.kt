package solve_16398

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()

    val edgeList = ArrayList<Edge>(n*n)

    for(i in 0 until n){
        for (j in 0 until n){
            edgeList.add(Edge(i,j,input()))
        }
    }

    val parent = IntArray(n + 1) { it }
    fun findRoot(node: Int): Int {
        if (parent[node] == node) return node
        parent[node] = findRoot(parent[node])
        return parent[node]
    }

    fun isUnion(a: Int, b: Int): Boolean {
        return findRoot(a) == findRoot(b)
    }

    fun makeUnion(a: Int, b: Int) {
        val r1 = findRoot(a)
        val r2 = findRoot(b)
        if (r1 > r2) parent[r1] = r2
        else parent[r2] = r1
    }

    fun kruskal(){
        edgeList.sortWith { a, b -> a.distance.compareTo(b.distance) }
        var sum = 0L
        for (i in edgeList.indices) {
            val (s, e, d) = edgeList[i]
            if (isUnion(s, e).not()) {
                makeUnion(s, e)
                sum += d
            }
        }
        println(sum)
    }
    kruskal()
}

private data class Edge(val start: Int, val end: Int, val distance: Int)

