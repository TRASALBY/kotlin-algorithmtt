package solve_16934

import java.util.*

private val nameHash = HashMap<String, Int>()

private fun main() {
    val n = readln().toInt()
    val trie = Trie()
    repeat(n){
        val name = readln()
        println(trie.insert(name))
    }
}

class Trie(val node: TreeMap<Char, Trie> = TreeMap()) {
    fun insert(name: String):String {
        var child: Trie = this
        var prefix = ""
        for(i in name.indices){
            if(prefix.isEmpty() && child.node.contains(name[i]).not()){
                prefix = name.substring(0,i+1)
            }
            child = child.node.computeIfAbsent(name[i]) {
                Trie()
            }
        }
        val nameCnt = (nameHash[name] ?: 0) + 1
        nameHash[name] = nameCnt

        if(prefix.isEmpty()){
            prefix = if (nameCnt >= 2){
                "${name}${nameCnt}"
            }else {
                name
            }
        }
        return prefix
    }
}