package solve_11659

import java.io.StreamTokenizer

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()
    val numArray = IntArray(n) {
        input()
    }
    var sum = 0
    val sumArray = IntArray(n + 1) { idx ->
        if (idx != 0) {
            sum += numArray[idx - 1]
        }
        sum
    }

    val sb = StringBuilder()
    repeat(m) {
        val s = input()
        val e = input()

        sb.appendLine(sumArray[e] - sumArray[s - 1])
    }

    println(sb)

}
