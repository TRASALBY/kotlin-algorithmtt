package solve_21944

import java.util.*

private fun main() {
    val n = readln().toInt()
    val problemTree = TreeSet<Problem>()

    val groupList = Array(101){
        TreeSet<Problem>()
    }
    val problemHash = HashMap<Int,Problem>()
    repeat(n){
        val (p,l,g) = readln().split(" ").map{it.toInt()}
        val problem = Problem(p,l,g)
        problemTree.add(problem)
        groupList[g].add(problem)
        problemHash[p] = problem
    }

    val sb = StringBuilder()

    val m = readln().toInt()
    repeat(m){
        val input = readln().split(" ")
        var result : Int? = null
        when(input[0]) {
            "recommend" -> {
                val g = input[1].toInt()
                val x = input[2].toInt()
                result = if(x == 1) {
                    groupList[g].last().p
                } else {
                    groupList[g].first().p
                }
            }
            "recommend2" -> {
                val x = input[1].toInt()
                result = if(x == 1) {
                    problemTree.last().p
                } else {
                    problemTree.first().p
                }
            }

            "recommend3" -> {
                val x = input[1].toInt()
                val l = input[2].toInt()
                result = if(x == 1) {
                    val problem = problemTree.ceiling(Problem(0,l,0))
                    problem?.p ?: -1
                } else {
                    val problem = problemTree.floor(Problem(0,l,0))
                    problem?.p ?: -1
                }
            }
            "add" -> {
                val p = input[1].toInt()
                val l = input[2].toInt()
                val g = input[3].toInt()
                val problem = Problem(p,l,g)
                problemHash[p] = problem
                problemTree.add(problem)
                groupList[g].add(problem)
                result = null
            }
            "solved" -> {
                val p = input[1].toInt()
                val problem = problemHash[p] ?: Problem(0,0,0)
                problemHash.remove(p)
                val g = problem.g
                problemTree.remove(problem)
                groupList[g].remove(problem)
                result = null
            }
            else -> Unit
        }
        if(result != null){
            sb.appendLine(result)
        }
    }
    println(sb)
}

private data class Problem(val p: Int, val l:Int, val g: Int) : Comparable<Problem>{
    override fun compareTo(other: Problem) = compareValuesBy(this, other, {it.l}, {it.p})
}