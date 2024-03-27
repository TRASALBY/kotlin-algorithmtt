class Solution {
    lateinit var map : Array<IntArray>
    
    fun solution(board: Array<IntArray>, skill: Array<IntArray>): Int {
        
        var answer: Int = 0
        
        val y = board.size + 1
        val x = board.first().size + 1
        
        map = Array(y){
            IntArray(x)
        }
        
        
        
        
        
        
        skill.forEach{
            var type = it[0]
            var r1 = it[1]
            var c1 = it[2]
            var r2 = it[3]+ 1
            var c2 = it[4] + 1
            var degree = it[5]
            if(type == 1){
                degree = -degree
            }     
            map[r1][c1] += degree
            map[r1][c2] -= degree
            map[r2][c1] -= degree
            map[r2][c2] += degree
            
        
        }
        

        
        
        
        for(i in 0 until y -1){
            for(j in 0 until x -1){
                map[i+1][j] += map[i][j]
            }
        }
        
        
        for(i in 0 until y-1){
            for(j in 0 until x -1){
                map[i][j+1] += map[i][j]
            }
        }
        
        
        
        var cnt = 0
        for(i in 0 until y - 1){
            for(j in 0 until x - 1){
                if(board[i][j] + map[i][j] > 0) cnt++
                
                board[i][j] += map[i][j] 
            }
        }
        return cnt
    }
    
}