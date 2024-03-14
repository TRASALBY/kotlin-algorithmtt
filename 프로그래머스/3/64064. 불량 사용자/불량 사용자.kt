class Solution {
    lateinit var userId : Array<String>
    lateinit var bannedId : Array<String>
    lateinit var visited : BooleanArray
    var answer = 0
    
    val answerSet = HashSet<String>()
    
    fun solution(user_id: Array<String>, banned_id: Array<String>): Int {
        userId = user_id
        bannedId = banned_id
        visited = BooleanArray(userId.size)
        
        backTrack(0)
        
        
        
        return answerSet.size
    }
    
    fun backTrack(idx: Int){
        if(idx == bannedId.size){
            if(visited.count{it} == bannedId.size){
                val sb = StringBuilder()
                for(i in visited.indices){
                    if(visited[i]){
                        sb.append("${userId[i]} ")
                    }
                }
                answerSet.add(sb.toString())
            }
            return
        }
        
        
        for(i in userId.indices){
            if(visited[i]) continue
            val name = userId[i]
            val bannedName = bannedId[idx]
            if(name.length == bannedName.length){
                var find = true
                for(j in 0 until name.length){
                    if(bannedName[j] == '*') continue
                    if(name[j] != bannedName[j]){
                        find = false
                        break
                    }
                }
                if(find){
                    visited[i] = true
                    backTrack(idx+1)
                    visited[i] = false
                }
            }
        }
    }
}