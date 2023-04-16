package solve_15681

import java.io.StreamTokenizer
import java.util.*

private lateinit var tree: Array<ArrayList<Int>>
private lateinit var cnt: IntArray
private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val n = input()
    val r = input()
    val q = input()

    tree = Array(n+1){
        ArrayList<Int>()
    }
    cnt = IntArray(n+1) {
        1
    }

    repeat(n-1){
        val u = input()
        val v = input()
        tree[u].add(v)
        tree[v].add(u)
    }

    subTreeCnt(r)
    val sb = StringBuilder()
    repeat(q){
        val u = input()
        sb.appendLine(cnt[u])
    }

    println(sb)

}

private fun subTreeCnt(node: Int){
    tree[node].forEach { childNode ->
        tree[childNode].remove(node)
        subTreeCnt(childNode)
        cnt[node] += cnt[childNode]
    }
}