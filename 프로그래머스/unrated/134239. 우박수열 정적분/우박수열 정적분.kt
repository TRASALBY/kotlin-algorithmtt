class Solution {
    fun solution(k: Int, ranges: Array<IntArray>): DoubleArray {
        val answer= DoubleArray(ranges.size)
        
        val nums = ArrayList<Int>()
        var n = k
        nums.add(n)
        while(n !=1) {
            if(n % 2 == 0) {
                n /= 2
            } else {
                n = n * 3 + 1
            }
            nums.add(n)
        }
        
        for(i in ranges.indices){
            val r = ranges[i]
            var start = r.first()
            var end = nums.lastIndex + r.last()
            println("$start $end")
            if(start > end) {
                answer[i] = -1.0
            } else{
                var size = 0.0
                for(j in start until end){
                    size += getSize(nums[j],nums[j+1])
                }
                answer[i] = size
            }
        }
        
        return answer
    }
    
    fun getSize(a: Int, b: Int) : Double{
        val aa = maxOf(a,b)
        val bb = minOf(a,b)
        val heightDiff = (aa - bb).toDouble()
        
        val size = heightDiff / 2.0 + bb
        return size
    }
}