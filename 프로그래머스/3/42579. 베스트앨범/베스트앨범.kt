class Solution {
    fun solution(genres: Array<String>, plays: IntArray): IntArray {
        var answer = intArrayOf()
        val genreHash = HashMap<String, ArrayList<Music>>()
        
        for(i in genres.indices){
            if(genreHash[genres[i]] == null){
                genreHash[genres[i]] = arrayListOf()
            }
            genreHash[genres[i]]?.add(Music(i, plays[i]))
        }
        
        val totalPlays = HashMap<String, Int>()
        
        genreHash.forEach{
            var sum = 0
            it.value.forEach{
                sum += it.play
            }
            totalPlays[it.key] = sum
        }
        
        
        val answerList : ArrayList<Int> = arrayListOf()
        totalPlays.map{it.key to it.value}.sortedBy{-it.second}.forEach{ 
            val musicList = (genreHash[it.first] ?: arrayListOf()).sortedBy{-it.play}
            var cnt = 0
            for(i in musicList.indices){
                if(cnt >= 2) break
                answerList.add(musicList[i].index)
                cnt++
            }
        }
        
        
        return answerList.toIntArray()
    }
}

data class Music(
    val index: Int,
    val play: Int
)