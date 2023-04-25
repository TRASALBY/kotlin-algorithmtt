package solve_17182

import java.io.StreamTokenizer

private lateinit var graph: Array<IntArray>
private var min = Int.MAX_VALUE

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val s = input()

    graph = Array(n) {
        IntArray(n) {
            input()
        }
    }

    for (k in 0 until n) {
        for (i in 0 until n) {
            for (j in 0 until n) {
                graph[i][j] = minOf(graph[i][j], graph[i][k] + graph[k][j])
            }
        }
    }

    dfs(BooleanArray(n),0,s,n)
    println(min)

}

private fun dfs(visited: BooleanArray, cost: Int, num: Int, n: Int){
    var nowCost = cost
    if(visited.all{it}) {
        min = minOf(min, nowCost)
    } else {
        visited[num] = true
        for(i in 0 until n){
            if(num != i && visited[i].not()){
                visited[i] = true
                nowCost += graph[num][i]
                dfs(visited, nowCost, i, n)
                visited[i] = false
                nowCost -= graph[num][i]
            }
        }
    }
}
