package solve_2405

import java.io.StreamTokenizer


fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val arr = IntArray(n) {
        input()
    }

    var maxDiff = 0
    arr.sort()

    for (i in 1 until arr.lastIndex) {
        maxDiff = maxOf(
            maxDiff,
            kotlin.math.abs(arr.last() + arr[i - 1] - 2 * arr[i]),
            kotlin.math.abs(arr.first() + arr[i + 1] - 2 * arr[i])
        )
    }
    println(maxDiff)
}