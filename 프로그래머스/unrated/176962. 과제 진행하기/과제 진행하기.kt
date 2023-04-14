class Solution {
    fun solution(plans: Array<Array<String>>): Array<String> {
        var answer: Array<String> = arrayOf<String>()
        val answerList = ArrayList<String>()
        val planList = ArrayList<Plan>()
        for(p in plans){
            val (name,start, playTime) = p
            val (hour,minute) = start.split(":").map{it.toInt()}
            planList.add(Plan(name,Time(hour,minute), playTime.toInt()))
        }
        planList.sort()
        
        var nowTime = planList.first().time
        
        val tempPlan = ArrayList<Plan>()
        
        var i = 0
        var doingPlan : Plan
        var nextTime : Time
        
        while(i < planList.size) {
            var flag = true
            if(nowTime < planList[i].time){
                if(tempPlan.size > 0){
                    doingPlan = tempPlan.last()
                    tempPlan.removeAt(tempPlan.lastIndex)
                    nextTime = planList[i].time
                    flag = false
                } else {
                    nowTime = planList[i].time
                    continue
                }
                
            } else {
                doingPlan = planList[i]
                if(i != planList.lastIndex){
                    nextTime = planList[i+1].time
                } else {
                    nextTime = doingPlan.time.addMinute(doingPlan.playTime)
                }
            }
            val endTime = nowTime.addMinute(doingPlan.playTime)
            if(endTime > nextTime){
                doingPlan.playTime = getMinuteDiff(endTime, nextTime)
                nowTime = nextTime
                tempPlan.add(doingPlan)
            } else {
                nowTime = endTime
                answerList.add(doingPlan.name)
            }
            if(flag) {
                i++
            }
        }
        
        for(i in tempPlan.lastIndex downTo 0){
            answerList.add(tempPlan[i].name)
        }
        answer = Array(answerList.size) { idx ->
            answerList[idx]
        }
        return answer
    }
    
    fun getMinuteDiff(a: Time, b: Time) : Int {
        val big = maxOf(a,b)
        val small = minOf(a,b)
        
        val hourDiff = big.hour - small.hour
        val minuteDiff = a.minute - b.minute
        
        return hourDiff * 60 + minuteDiff
    }
}

data class Plan(val name: String, val time: Time, var playTime: Int) : Comparable<Plan> {
    fun study(){
        if(playTime != 0){
            playTime -= 1
        }
    }
    override fun compareTo(other: Plan) = compareValuesBy(this,other,{it.time})
}

data class Time(var hour: Int, var minute: Int): Comparable<Time> {
    
    fun addMinute(minute: Int): Time{
        var newMinute = this.minute + minute
        var newHour = hour
        if(newMinute >= 60) {
            newHour += newMinute / 60
            newMinute %= 60
        }
        return Time(newHour, newMinute)
        
    }
    
    
    override fun compareTo(other: Time) = compareValuesBy(this, other, {it.hour},{it.minute})
}