class Solution {
    fun solution(arrayA: IntArray, arrayB: IntArray): Int {
        var answer: Int = 0
        val canDivA = ArrayList<Int>()
        val canDivB = ArrayList<Int>()
        
        getCanDiv(arrayA, canDivA)
        getCanDiv(arrayB, canDivB)
        
        answer = getAnswer(answer, canDivA, arrayB)
        answer = getAnswer(answer, canDivB, arrayA)
        
        return answer
    }
    
    fun getCanDiv(nums: IntArray, list: ArrayList<Int>){
        val minNum = nums.minOrNull() ?: 0
        for(i in 2..minNum){
            if( nums.all{it % i == 0}){
                list.add(i)
            }
        }
    }
    
    fun getAnswer(answer: Int, list: ArrayList<Int>, nums: IntArray) : Int{
        var nowAnswer = answer 
        for(i in list.lastIndex downTo 0) {
            if(nums.all{it % list[i] != 0}){
                if(nowAnswer < list[i]){
                    nowAnswer = list[i]
                }
                break
            }
        }
        return nowAnswer
    }
}