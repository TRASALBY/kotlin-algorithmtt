import java.util.*

const val WOLF = 1
const val SHEEP = 0

class Solution {
    var answer = 0
    val route : ArrayList<Int> = arrayListOf()
    var maxSheep = 0
    lateinit var nodeArr : Array<Node>
    lateinit var visited : BooleanArray
    
    fun solution(info: IntArray, edges: Array<IntArray>): Int {
        nodeArr = Array(info.size){ idx -> 
            if(info[idx] == SHEEP){
                maxSheep++
            }
            Node(info[idx], arrayListOf())
        }
        
        visited = BooleanArray(info.size)
        
        edges.forEach{
            nodeArr[it[0]].children.add(it[1])
        }
        
        var now = 0
        var sheep = 1
        var wolf = 0
        route.add(0)
        
        dfs(sheep, wolf)
        
        return answer
    }
    
    fun getCanGoNode(): ArrayList<Int>{
        val next = ArrayList<Int>()
        
        route.forEach{ idx ->
            nodeArr[idx].children.forEach{ child -> 
                if(visited[child].not()){
                    next.add(child)
                }
            }
        }
        return next
    }
    
    fun dfs(sheep: Int, wolf: Int){
        if(sheep == maxSheep){
            answer = sheep
            return
        }
        if(sheep <= wolf){
            if(sheep > answer){
                answer = sheep
            }
            return
        }
        
        val nextNodes = getCanGoNode()

        nextNodes.forEach{
            when(nodeArr[it].type){
                WOLF -> {
                    route.add(it)
                    visited[it] = true
                    dfs(sheep, wolf+1)
                    visited[it] = false
                    route.removeLast()
                }
                SHEEP -> {
                    route.add(it)
                    visited[it] = true
                    dfs(sheep+1, wolf)
                    visited[it] = false
                    route.removeLast()
                }
                else -> {
                    
                }
            }
        }
    }
}

data class Node(val type: Int, val children: ArrayList<Int>)