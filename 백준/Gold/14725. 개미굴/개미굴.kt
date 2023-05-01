package solve_14725

import java.util.*

private fun main() {
    val n = readln().toInt()

    val root = Node()
    repeat(n) {
        val input = readln().split(" ")
        val k = input.first()
        var node = root
        for (i in 1..input.lastIndex) {
            node = node.leaf.computeIfAbsent(input[i]) {
                Node()
            }
        }
    }
    val sb = StringBuilder()
    root.print(sb, 0)

    println(sb)

}

class Node {
    val leaf = TreeMap<String, Node>()

    fun print(sb: StringBuilder, depth: Int) {
        for (k in leaf.keys) {
            for (i in 0 until depth) {
                sb.append("--")
            }
            sb.appendLine(k)
            leaf[k]?.print(sb, depth + 1)
        }
    }
}