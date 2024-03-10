import java.io.StreamTokenizer
import java.util.*


val dx = intArrayOf(1, -1, 0, 0)
val dy = intArrayOf(0, 0, -1, 1)

const val T = 2
const val R = 0
const val B = 3
const val L = 1

lateinit var map: Array<IntArray>
var r = 0
var c = 0

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    r = input()
    c = input()
    val t = input()

    val queue: Queue<Point> = LinkedList()
    val airCleaner: ArrayList<Point> = arrayListOf()

    map = Array(r) { y ->
        IntArray(c) { x ->
            val num = input()
            if (num == -1) {
                airCleaner.add(Point(x, y))
            }
            num
        }
    }

    val newMap = Array(r) {
        IntArray(c)
    }

    repeat(t) {
        for (i in 0 until r) {
            for (j in 0 until c) {
                if (map[i][j] > 0) {
                    queue.add(Point(j, i))
                }
            }
        }

        airCleaner.forEach {
            newMap[it.y][it.x] = -1
        }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            var cnt = 0
            for (i in dx.indices) {
                val nx = cur.x + dx[i]
                val ny = cur.y + dy[i]

                if (nx < 0 || nx >= c || ny < 0 || ny >= r || map[ny][nx] == -1) continue

                newMap[ny][nx] += map[cur.y][cur.x] / 5
                cnt += 1
            }

            newMap[cur.y][cur.x] += map[cur.y][cur.x] - ((map[cur.y][cur.x] / 5) * cnt)
        }
        for (i in 0 until r) {
            for (j in 0 until c) {
                map[i][j] = newMap[i][j]
                newMap[i][j] = 0
            }
        }
        val top = airCleaner.first()
        val bottom = airCleaner.last()


        cleanTop(top, intArrayOf(T, R, B, L))
        cleanBottom(bottom, intArrayOf(B, R, T, L))
    }

    var answer = 0

    for (i in 0 until r) {
        for (j in 0 until c) {
            if(map[i][j] == -1) continue
            answer += map[i][j]
        }
    }

    println(answer)

}


fun cleanTop(start: Point, sequence: IntArray) {
    var now = start
    var next = now.move(T)
    var idx = 0
    while (idx < 4) {
        val dir = sequence[idx]
        if (next.x in 0 until c && next.y in 0 .. start.y) {
            if (map[now.y][now.x] != -1) {
                map[now.y][now.x] = if(map[next.y][next.x] != -1) {
                    map[next.y][next.x]
                } else {
                    0
                }
            }
            now = next
            next = now.move(dir)
        } else {
            next = now
            idx++
        }
    }
}
fun cleanBottom(start: Point, sequence: IntArray) {
    var now = start
    var next = now.move(B)
    var idx = 0
    while (idx < 4) {
        val dir = sequence[idx]
        if (next.x in 0 until c && next.y in start.y until r) {
            if (map[now.y][now.x] != -1) {
                map[now.y][now.x] = if(map[next.y][next.x] != -1) {
                    map[next.y][next.x]
                } else {
                    0
                }
            }
            now = next
            next = now.move(dir)
        } else {
            next = now
            idx++
        }
    }
}


data class Point(val x: Int, val y: Int) {
    fun move(dir: Int) = Point(x + dx[dir], y + dy[dir])
}
