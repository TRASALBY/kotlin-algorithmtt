import java.util.*
import kotlin.collections.ArrayList

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    val trueArr = ArrayList<Int>()
    var answer = 0
    readLine().split(" ").map { it.toInt() }
        .let {
            for (i in 0 until it.first()) {
                trueArr.add(it[i + 1])
            }
        }

    val parties = Array(m) {
        val input = readLine().split(" ").map { it.toInt() }
        IntArray(input.first()) {
            input[it + 1]
        }
    }

    val people : Queue<Int> = LinkedList()

    val visited = BooleanArray(m )
    trueArr.forEach {
        people.add(it)
    }


    while(people.isNotEmpty()) {
        val person = people.poll()
        parties.forEachIndexed{  index, party ->
            if(visited[index].not()){
                if(trueArr.contains(person) && party.contains(person)){
                    visited[index] = true
                    for (i in party) {
                        if(!trueArr.contains(i)) {
                            trueArr.add(i)
                            people.add(i)
                        }
                    }
                }
            }
        }
    }

    answer = visited.count { it.not() }


    println(answer)
}