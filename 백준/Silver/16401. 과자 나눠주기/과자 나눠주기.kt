package `kotlin-algorithm`.`0x13_BinarySearch`

import java.util.StringTokenizer

fun main() {
    var st = StringTokenizer(readln())

    val m = st.nextToken().toInt()
    val n = st.nextToken().toInt()

    st = StringTokenizer(readln())

    var start = 1
    var end = 1
    val snackLength = IntArray(n) {
        val length = st.nextToken().toInt()
        if (end < length) {
            end = length
        }
        length
    }
    var answer = 0
    while (start <= end) {
        var total = 0
        val mid = (start + end) / 2

        for (l in snackLength) {
            if (l >= mid) {
                total += l / mid
            }
        }

        if (total >= m) {
            start = mid + 1
            answer = mid
        } else {
            end = mid - 1
        }
    }
    println(answer)

}