package solve_14725

import java.util.*

private fun main() {
    val n = readln().toInt()

    val trie = Trie()
    repeat(n) {
        val input = readln().split(" ")
        trie.insert(input.subList(1,input.size))
    }
    val sb = StringBuilder()

    trie.print(sb, 0)
    println(sb)

}

class Trie(val node: TreeMap<String, Trie> = TreeMap()){
    fun insert(foods: List<String>) {
        var child: Trie = this
        foods.forEach{
            child = child.node.computeIfAbsent(it){
                Trie(TreeMap())
            }
        }
    }

    fun print(sb: StringBuilder, depth: Int){
        for(key in node.keys){
            for(i in 0 until depth){
                sb.append("--")
            }
            sb.appendLine(key)
            node[key]?.print(sb, depth+1)
        }
    }
}