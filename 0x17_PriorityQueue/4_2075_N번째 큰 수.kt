package solve_2075

import java.io.StreamTokenizer
import java.util.*

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int{
        nextToken()
        return nval.toInt()
    }

    val n = input()

    val queue = PriorityQueue<Int>()

    for(i in 1..n){
        for(j in 1..n){
            val num = input()
            if(queue.size < n){
                queue.add(num)
            } else if( queue.peek() < num) {
                queue.poll()
                queue.add(num)
            }
        }
    }

    println(queue.peek())
}