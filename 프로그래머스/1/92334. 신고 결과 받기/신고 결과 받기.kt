import java.util.*

class Solution {
    fun solution(idList: Array<String>, report: Array<String>, k: Int): IntArray {
        var answer = IntArray(idList.size)
        
        val userReport: MutableMap<String, MutableSet<String>> = HashMap()
        
        
        
        report.forEach{
            val (a, b) = it.split(" ")
            val reportSet = userReport[b] ?: mutableSetOf()
            if(reportSet.contains(a).not()){
                reportSet.add(a)
            }
            userReport[b] = reportSet
        }
        
        userReport.forEach{
            val key = it.key
            val value = it.value
            if(value.size >= k){
                value.forEach{ reportor ->
                    answer[idList.indexOf(reportor)]++
                }
            }
        }
        
        
        
        
        
        
        return answer
    }
}