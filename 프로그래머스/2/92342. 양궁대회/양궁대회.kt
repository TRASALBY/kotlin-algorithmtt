class Solution {
    var max = 0
    var gap =  0
    var count = 0
    var info : IntArray = intArrayOf()
    private var answer = intArrayOf()
    
    fun solution(n: Int, info: IntArray): IntArray {

         answer = IntArray(11)

        count = n
        this.info = info


        search(0, IntArray(11))
        if(gap==0){
            answer = intArrayOf(-1)
        }

        return answer
    }

    fun search(num: Int, arr: IntArray){
        if(num==count){
            var mySum=0
            var youSum=0
            for(i in arr.indices){
                if(info[i] != 0 || arr[i] != 0) {
                    if (info[i] < arr[i]) {
                        mySum += (10 - i)
                    } else if (info[i] != 0 && info[i] >= arr[i]) {
                        youSum += (10 - i)
                    }
                }

            }

            if(mySum-youSum>gap){
                gap = mySum-youSum

                answer = arr.copyOf()
                max = mySum
            }else if(mySum-youSum==gap){
                for(i in 10 downTo 0){
                    if(answer[i]<arr[i]){
                        answer = arr.copyOf()
                    }
                }
            }


        }else{
            for(i in arr.indices){

                if(info[i]!=0){
                    if(arr[i]<info[i]){

                        if(i==10){
                            arr[i]+=(count-num)
                            search(count, arr)
                            arr[i]-=(count-num)
                        }else{
                            var temp = info[i]-arr[i]
                            if(num+temp+1<=count){

                                arr[i]+=(temp+1)
                                search(num + temp + 1, arr)
                                arr[i]-=(temp+1)
                            }
                        }

                    }

                }else{

                    if(arr[i]==info[i]){

                            arr[i]++
                            search(num + 1, arr)
                            arr[i]--

                    }

                }

            }
        }
        
    }
}