package solve_9019

import java.util.*


private val orders = arrayOf("D", "S", "L", "R")


fun main() = with(System.`in`.bufferedReader()) {

    repeat(readLine().toInt()) {
        val st = StringTokenizer(readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()

        bfs(a,b)
    }

}


fun bfs(now: Int, target: Int){
    val queue: Queue<Resister> = LinkedList()
    val visited = BooleanArray(10001)
    queue.add(Resister(now, StringBuilder()))

    while (queue.isNotEmpty()) {
        val cur = queue.poll()
        for (order in orders) {
            var newNum = cur.num
            when (order) {
                "D" -> {
                    newNum = cur.num * 2
                    if (newNum > 9999) {
                        newNum %= 10000
                    }
                }
                "S" -> {
                    newNum = cur.num
                    if (newNum == 0) {
                        newNum = 10000
                    }
                    newNum -= 1
                }
                "L" -> {
                    val temp = cur.num / 1000
                    newNum = cur.num % 1000 * 10 + temp
                }
                "R" -> {
                    val temp = cur.num % 10
                    newNum = cur.num /10 + temp * 1000
                }
            }
            val sb = StringBuilder(cur.nowOrder.toString())
            sb.append(order)
            if(newNum == target){
                println(sb)
                queue.clear()
                return
            }
            if(visited[newNum].not()){
                queue.add(Resister(newNum, sb))
                visited[newNum] = true
            }
        }
    }
}

private data class Resister(val num: Int, val nowOrder: StringBuilder)