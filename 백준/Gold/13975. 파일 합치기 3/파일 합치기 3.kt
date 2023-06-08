import java.io.StreamTokenizer
import java.lang.StringBuilder
import java.util.*

val sb = StringBuilder()
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input() :Long {
        nextToken()
        return nval.toLong()
    }
    val t = input()

    repeat(t.toInt()){
        val k = input()
        val queue = PriorityQueue<Long>()

        repeat(k.toInt()){
            queue.add(input())
        }

        var cost = 0L
        while (queue.isNotEmpty()){
            if(queue.size >= 2){
                val q1 = queue.poll()
                val q2 = queue.poll()
                queue.add(q1+q2)
                cost += q1 + q2
            }
            else {
                queue.poll()
                sb.appendLine(cost)
            }
        }

    }
    println(sb)

}