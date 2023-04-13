package solve_2630

import java.io.StreamTokenizer

private var answerZero = 0
private var answerOne = 0
private lateinit var board: Array<IntArray>
private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()

    board = Array(n) {
        IntArray(n) {
            input()
        }
    }

    slice(0,0,n)

    println(answerZero)
    println(answerOne)
}

fun slice(x: Int, y: Int, d: Int) {
    if (d == 1 || checkBoard(x, y, d)) {
        if (board[y][x] == 0) {
            answerZero++
        } else {
            answerOne++
        }
    } else {
        val halfD = d / 2
        slice(x, y, halfD)
        slice(x + halfD, y, halfD)
        slice(x, y + halfD, halfD)
        slice(x + halfD, y + halfD, halfD)
    }
}

fun checkBoard(x: Int, y: Int, d: Int): Boolean {
    val nowColor = board[y][x]
    for (i in y until y + d) {
        for (j in x until x + d) {
            if (board[i][j] != nowColor) {
                return false
            }
        }
    }
    return true
}
