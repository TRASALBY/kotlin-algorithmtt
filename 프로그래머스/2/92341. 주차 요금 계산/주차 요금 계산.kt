import kotlin.math.*

class Solution {
    fun solution(fees: IntArray, records: Array<String>): IntArray {
        val (basicTime, basicPrice, unitTime, unitPrice) = fees
        
        var timeArr = IntArray(10001)
        val lastTimeArr = IntArray(10001)
        
        val carSet = HashSet<Int>()
        
        val recordList = records.map {
            val(time, numS, type) = it.split(" ")
            val (hour, minute) = time.split(":").map{it.toInt()}
            
            val num = numS.toInt()
            val nowTime = hourToMin(hour, minute)
            
            if(type == "IN"){
                carSet.add(num)
                lastTimeArr[num] = nowTime
            } else {
                timeArr[num] +=  nowTime - lastTimeArr[num]
                carSet.remove(num)
            }
        }
        
        carSet.forEach{
            timeArr[it] += hourToMin(23, 59) - lastTimeArr[it]
        }
        
        val answer = timeArr.filter{it > 0}.map{
            if(it <= basicTime){
                basicPrice
            } else {
                basicPrice + ceil((it - basicTime) / unitTime.toDouble()).toInt() * unitPrice
            }
        }.toIntArray()
        
        return answer
    }
    
    
    fun hourToMin(hour: Int, minute: Int) = hour * 60 + minute
    
    
    data class Record(val hour : Int, val minute: Int, val num: Int, val type: String)
}