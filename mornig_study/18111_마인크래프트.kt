package solve_18111

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()
    val b = input()

    var heightSum = 0
    var minHeight = Int.MAX_VALUE
    var maxHeight = Int.MIN_VALUE
    var answer = Answer(Int.MAX_VALUE,Int.MIN_VALUE)

    val map = Array(n) {
        IntArray(m) {
            val height = input()
            heightSum += height
            minHeight = minOf(height, minHeight)
            maxHeight = maxOf(height, maxHeight)
            height
        }
    }
    for (target in minHeight..maxHeight) {
        var time = 0
        var nowInventory = b
        var flag = false
        for (i in map.indices) {
            for (j in map[0].indices) {
                if (map[i][j] > target) {
                    time += (map[i][j] - target) * 2
                    nowInventory += map[i][j] - target
                }
            }
        }
        for (i in map.indices) {
            for (j in map[0].indices) {
                if (map[i][j] < target) {
                    if (target - map[i][j] <= nowInventory) {
                        time += target - map[i][j]
                        nowInventory -= target - map[i][j]
                    } else {
                        flag = true
                        break
                    }
                }
            }
            if(flag) break
        }
        if(flag.not()){
            answer = minOf(answer, Answer(time, target))
        }
    }
    println("${answer.time} ${answer.height}")

}

private data class Answer(val time: Int, val height: Int) : Comparable<Answer>{
    override fun compareTo(other: Answer) = compareValuesBy(this, other, {it.time}, {-it.height})

}
