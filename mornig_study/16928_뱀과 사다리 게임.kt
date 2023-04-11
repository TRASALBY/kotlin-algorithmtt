package solve_16928

import java.io.StreamTokenizer
import java.util.*
import kotlin.collections.HashMap

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val snake = HashMap<Int, Int>()
    val ladder = HashMap<Int, Int>()
    val n = input()
    val m = input()

    val map = IntArray(101)

    repeat(n) {
        ladder[input()] = input()
    }

    repeat(m){
        snake[input()] = input()
    }

    val queue : Queue<Node> = LinkedList()

    queue.add(Node(1,0))
    var findFlag = false
    while(queue.isNotEmpty() && findFlag.not()) {
        val (num,depth) = queue.poll()

        for(i in 1..6){
            var newNum = num+i
            if(newNum in snake.keys){
               newNum = snake[newNum]!!
            } else if(newNum in ladder.keys){
                newNum = ladder[newNum]!!
            }
            if(map[newNum] == 0){
                map[newNum] = depth + 1
                if(newNum == 100){
                    findFlag = true
                    break
                }
                queue.add(Node(newNum,depth+1))
            }
        }
    }

    println(map[100])
}

private data class Node(val num: Int, val depth: Int)