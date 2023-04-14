package solve_2630

import java.io.StreamTokenizer
import java.util.*

const val INF = Int.MAX_VALUE
private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()
    val k = input()
    val x = input()

    val map = Array(n+1) {
        LinkedList<Node>()
    }

    repeat(m) {
        val a = input()
        val b = input()
        map[a].add(Node(1, b))
    }

    val queue = PriorityQueue<Node>()
    queue.add(Node(0,x))

    val distArray = IntArray(n+1){ INF}
    distArray[x] = 0

    while(queue.isNotEmpty()){
        val (dist, index) = queue.poll()
        if(distArray[index] < dist) continue
        for(i in map[index]){
            val newDist = dist + i.dist
            if(newDist < distArray[i.index]){
                distArray[i.index] = newDist
                queue.add(Node(newDist, i.index))
            }
        }
    }
    if(distArray.any{it == k}.not()) {
        println(-1)
    } else {
        for(i in distArray.indices){
            if(distArray[i] == k){
                println(i)
            }
        }
    }

}

data class Node(val dist: Int, val index: Int) : Comparable<Node>{
    override fun compareTo(other: Node) = this.dist - other.dist
}


