import java.io.StreamTokenizer

private lateinit var stars: Array<CharArray>

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()

    stars = Array(n) {
        CharArray(n * 2 - 1){
            ' '
        }
    }

    star(0,n-1,n)

    val sb = StringBuilder()
    for(i in 0 until n){
        for(j in 0 until 2 * n - 1){
            sb.append(stars[i][j])
        }
        sb.appendLine()
    }
    println(sb)

}

private fun star(r: Int, c: Int, n: Int) {
    if (n == 3) {
        stars[r][c] = '*'
        stars[r + 1][c + 1] = '*'
        stars[r + 1][c - 1] = '*'
        stars[r + 2][c - 2] = '*'
        stars[r + 2][c - 1] = '*'
        stars[r + 2][c] = '*'
        stars[r + 2][c + 1] = '*'
        stars[r + 2][c + 2] = '*'
    } else {
        val cut = n / 2
        star(r, c, cut)
        star(r + cut, c - cut, cut)
        star(r + cut, c + cut, cut)
    }
}



