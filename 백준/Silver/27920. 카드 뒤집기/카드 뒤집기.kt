import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()

    val deque = ArrayDeque<Int>()


    for (i in 1..n) {
        if (i % 2 == 1) {
            deque.addFirst(i)
        } else {
            deque.addLast(i)
        }
    }

    val arr = deque.toIntArray()

    var now = arr.indexOf(1) + 1

    val sb = StringBuilder()
    sb.append("YES\n")
    for (i in arr) {
        sb.append("$i ")
    }
    sb.append("\n")


    for (i in 0 until n) {
        if (i % 2 == 1) {
            now += i
        } else {
            now -= i
        }
        sb.append("$now ")
    }

    println(sb)


}
