import kotlin.math.sqrt

class Solution {
    fun solution(n: Int, k: Int): Int {
        var answer: Int = 0
        val sb = StringBuilder()
        
        var num = n
        while(num != 0){
            sb.append(num % k)
            num /= k
        }
        val nums = sb.reversed().split("0").filter{it.isNotEmpty()}.map{it.toLong()}
        
        nums.forEach{
            if(isPrime(it)){
                answer++
            }
        }
        
        return answer
    }
    
    
    fun isPrime(num: Long): Boolean {
        if (num <= 1) return false

        (2..sqrt(num.toDouble()).toLong()).forEach {
            if (num % it == 0L) return false
        }
        return true
    }
}