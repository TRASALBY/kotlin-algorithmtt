package `kotlin-algorithm`.Programmers.level2

class Solution {
    fun solution(elements: IntArray): Int {
        val newElements = elements + elements

        val answers = HashSet<Int>()

        for(size in 1..elements.size){
            for(start in elements.indices){
                var sum = 0
                val end = start + size - 1
                for(i in start..end){
                    sum += newElements[i]
                }
                answers.add(sum)
            }
        }

        return answers.count()
    }
}