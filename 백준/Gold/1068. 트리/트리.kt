import java.io.StreamTokenizer
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()

    val nodes = HashMap<Int, Node>()

    for (i in 0 until n) {
        nodes[i] = Node(i, -1, ArrayList())
    }
    for (i in 0 until n) {
        val p = input()
        if (p != -1) {
            nodes[p]?.children?.add(i)
            nodes[i]?.parent = p
        }
    }


    val r = input()

    val queue: Queue<Int> = LinkedList()
    queue.add(r)
    while (queue.isNotEmpty()) {
        val cur = queue.poll()
        val node = nodes[cur]
        node?.children?.forEach {
            queue.add(it)
        }
        val p = nodes[cur]?.parent

        nodes.remove(cur)
        nodes[p]?.children?.remove(cur)
    }

    val answer = nodes.values.count { it.children.size == 0 }

    println(answer)
}

private data class Node(
    val num: Int,
    var parent: Int,
    val children: ArrayList<Int>
)