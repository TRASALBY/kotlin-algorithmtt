import java.io.StreamTokenizer

private const val INF = 100_000_000

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val v = input()
    val e = input()

    val distance = Array(v + 1) {
        IntArray(v + 1) {
            INF
        }
    }

    repeat(e) {
        val a = input()
        val b = input()
        val c = input()

        distance[a][b] = c
    }

    for (k in 1..v) {
        for (i in 1..v) {
            for (j in 1..v) {
                distance[i][j] = minOf(distance[i][j], distance[i][k] + distance[k][j])
            }
        }
    }

    var answer = INF
    for(i in 1..v){
        answer = minOf(answer, distance[i][i])
    }
    if(answer == INF){
        answer = -1
    }

    println(answer)

}
