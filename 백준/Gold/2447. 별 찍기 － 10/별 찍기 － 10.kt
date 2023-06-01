package solve_2447

private val sb = StringBuilder()
private fun main() {
    val n = readln().toInt()

    for (i in 0 until n) {
        for (j in 0 until n) {
            drawStar(i, j, n / 3)
        }
        sb.appendLine()
    }

    println(sb)
}

private fun drawStar(i: Int, j: Int, n: Int) {
    if ((i / n) % 3 == 1 && (j / n) % 3 == 1) {
        sb.append(" ")
    } else if (n < 3) {
        sb.append("*")
    } else {
        drawStar(i, j, n / 3)
    }
}