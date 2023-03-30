package solve_21940

import java.io.StreamTokenizer
import java.util.*

private const val INF = 1000000000

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()
    val graph = Array(n+1){
        IntArray(n+1){
            INF
        }
    }

    for(i in 1..n){
        graph[i][i] = 0
    }

    repeat(m){
        val s = input()
        val e = input()
        val t = input()

        graph[s][e] = t
    }

    for(i in 1..n){
        for (j in 1..n){
            for(k in 1..n){
                graph[j][k] = minOf(graph[j][i] + graph[i][k] , graph[j][k])
            }
        }
    }

    val k = input()
    val friendsCity = IntArray(k){
        input()
    }
    val answer = ArrayList<Int>()
    var nowTime = Int.MAX_VALUE
    for(city in 1..n){
        var max = 0
        friendsCity.forEach{
            max = maxOf(graph[city][it] + graph[it][city], max)
        }

        if(nowTime > max){
            nowTime = max
            answer.clear()
            answer.add(city)
        } else if(nowTime == max){
            answer.add(city)
        }
    }

    val sb = StringBuilder()
    answer.forEach{
        sb.append("$it ")
    }

    println(sb)
}

