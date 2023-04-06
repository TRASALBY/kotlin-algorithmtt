package solve_13144

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun input(): Int{
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val arr = IntArray(n){
        input()
    }

    val numSet = HashSet<Int>()
    var cnt = 0
    var right = 0
    for(left in 0 until n){
        while(right < n){
            if(numSet.contains(arr[right])){
                break
            }
            numSet.add(arr[right++])
        }
        cnt += right - left
        numSet.remove(arr[left])
    }

    println(cnt)
}
