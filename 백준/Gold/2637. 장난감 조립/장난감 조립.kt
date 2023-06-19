import org.w3c.dom.NodeList
import java.io.StreamTokenizer
import java.util.*
import kotlin.collections.ArrayList

private lateinit var inDegree : IntArray
private lateinit var graph : Array<LinkedList<Int>>
private lateinit var nodeList: List<Node>

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()


    nodeList = List(n + 1) {
        Node(num = it)
    }
    inDegree = IntArray(n+1)
    graph = Array(n+1){
        LinkedList()
    }

    repeat(m) {
        val x = input()
        val y = input()
        val k = input()
        nodeList[x].need.add(NeedNode(y, k))
        inDegree[x] += 1
        graph[y].add(x)
    }




    val basicNode = HashSet<Int>()

    nodeList.forEach{
        if(it.need.isEmpty()) {
            basicNode.add(it.num)
        }
    }
    basicNode.remove(0)
    val searchOrder = sort()

    val dp = Array(n + 1) {
        IntArray(n + 1)
    }

    for(i in searchOrder){

        val queue = nodeList[i].need

        while(queue.isNotEmpty()){
            val needNode = queue.poll()
            if(basicNode.contains(needNode.num)){
                dp[i][needNode.num] += needNode.cnt
            } else {
                dp[i] += dp[needNode.num] * needNode.cnt
            }
        }
    }

    val sb = StringBuilder()

    dp[n].forEachIndexed { index, cnt ->
        if(basicNode.contains(index)){
            sb.appendLine("$index $cnt")
        }
    }
    print(sb)
}


private fun sort() : ArrayList<Int> {

    val queue = PriorityQueue<Int>()
    val searchOrder = ArrayList<Int>()

    for (i in 1..inDegree.lastIndex) {
        if (inDegree[i] == 0) {
            queue.add(i)
        }
    }

    while (queue.isNotEmpty()) {
        val cur = queue.poll()
        for (i in graph[cur]) {
            inDegree[i]--
            if (inDegree[i] == 0) {
                queue.add(i)
            }
        }
        searchOrder.add(cur)
    }
    return searchOrder
}

private operator fun IntArray.plus(other: IntArray) =
    IntArray(this.size) {
        this[it] + other[it]
    }

private operator fun IntArray.times(y: Int) =
    IntArray(this.size) {
        this[it] * y
    }

private data class Node(
    val num: Int,
    val need: PriorityQueue<NeedNode> = PriorityQueue()
)

private data class NeedNode(
    val num: Int,
    val cnt: Int
): Comparable<NeedNode> {
    override fun compareTo(other: NeedNode) = this.num - other.num
}