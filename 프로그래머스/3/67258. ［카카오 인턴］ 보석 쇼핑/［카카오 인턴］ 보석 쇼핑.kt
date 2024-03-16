import java.util.*
class Solution {
    
    fun solution(gems: Array<String>): IntArray {
                
        val jewelSet = HashSet<String>()
        gems.forEach{
            jewelSet.add(it)
        }
        val jewelMap = HashMap<String, Int>()
        
        val basketSet = HashSet<String>()
        
        var answer = intArrayOf(0, Int.MAX_VALUE)
        
        fun checkGems(): Boolean{
            for(gem in jewelSet){
                if(jewelMap[gem]!! < 1){
                    return false
                }
            }
            return true
        }

        
        jewelSet.forEach{
            jewelMap[it] = 0
        }
        
        var start = 0
        var end = 0

        val gem = gems[start]
        jewelMap[gem] = jewelMap.getOrDefault(gem, 0) + 1
        basketSet.add(gem)
        var find = checkGems()
        
        // println("find $find")
        // println("$start $end")
        // println("${answer.joinToString(" ")}")
        // println("$jewelMap \n")
        while(start < end || end != gems.size - 1){
            // if(end > gems.size - 1 || start > end) break
            
            if(find){
                if(answer[1] - answer[0] > end - start){
                    answer[0] = start + 1
                    answer[1] = end + 1
                }
                val gem = gems[start]
                start++
                jewelMap[gem] = jewelMap.getOrDefault(gem, 0) - 1
                if(jewelMap[gem]!! == 0){
                    find = false
                    basketSet.remove(gem)
                } else{
                    find = true
                }
            } else {
                end++
                if(end > gems.size - 1) break
                val gem = gems[end]
                jewelMap[gem] = jewelMap.getOrDefault(gem, 0) + 1
                basketSet.add(gem)
                find = jewelSet.size == basketSet.size
            }
            // println("find $find")
            // println("$start $end")
            // println("${answer.joinToString(" ")}")
            // println("$jewelMap \n")
        }
        
        return answer
    }

}