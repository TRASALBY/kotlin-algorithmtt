package solve_6497

import java.io.StreamTokenizer
import java.util.*

private const val INF = Int.MAX_VALUE
private lateinit var edgeList : ArrayList<Edge>
private lateinit var parent : IntArray
private var defaultCost = 0
private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    edgeList = ArrayList<Edge>()
    while(true){
        val m = input()
        val n = input()

        if(m == 0 && n == 0){
            break
        }
        parent = IntArray(m){
            it
        }
        edgeList.clear()
        defaultCost = 0
        repeat(n){
            val x = input()
            val y = input()
            val z = input()
            edgeList.add(Edge(x,y,z))
            edgeList.add(Edge(y,x,z))
            defaultCost +=z
        }
        println(kruskal())
    }
}

fun isUnion(a: Int, b: Int): Boolean {
    return findRoot(a) == findRoot(b)
}

fun findRoot(node: Int): Int {
    return if(parent[node] == node) node
    else{
        findRoot(parent[node])
    }
}

fun makeUnion(a: Int, b:Int){
    val p1 = findRoot(a)
    val p2 = findRoot(b)

    if(p1 < p2) {
        parent[p2] = p1
    } else {
        parent[p1] = p2
    }
}

fun kruskal() : Int{
    edgeList.sort()
    var sum = 0
    for(edge in edgeList){
        val (start, end, cost) = edge
        if(isUnion(start,end).not()){
            makeUnion(start,end)
            sum += cost
        }
    }
    return defaultCost - sum

}

private data class Edge(val start: Int, val end: Int, val cost: Int) : Comparable<Edge> {
    override fun compareTo(other: Edge) = this.cost - other.cost
}