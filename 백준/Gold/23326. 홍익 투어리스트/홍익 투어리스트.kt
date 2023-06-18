import java.io.StreamTokenizer
import java.lang.StringBuilder
import java.util.TreeSet

private lateinit var treeSet: TreeSet<Int>
private lateinit var sb: StringBuilder

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val q = input()

    treeSet = TreeSet<Int>()
    sb = StringBuilder()

    repeat(n) {
        if (input() == 1) {
            treeSet.add(it)
        }
    }
    val nowPosition = Position(0, n)

    repeat(q) {
        when (input()) {
            1 -> {
                val i = input() - 1
                if (treeSet.contains(i)) {
                    treeSet.remove(i)
                } else {
                    treeSet.add(i)
                }
            }

            2 -> {
                val x = input()
                nowPosition.move(x)
            }

            3 -> {
                find(nowPosition.now, n)
            }
        }
    }

    println(sb.toString().trim())
}

private fun find(start: Int, size: Int){
    if (treeSet.isEmpty()) {
        sb.appendLine(-1)
    } else{
        val upper = treeSet.ceiling(start)
        if (upper != null) {
            sb.appendLine(upper - start)
        } else {
            val lower = treeSet.first()
            sb.appendLine(size - start + lower)
        }
    }
}



private data class Position(var now: Int, val size: Int) {
    fun move(x: Int) {
        now =  (now + x) % size
    }
}