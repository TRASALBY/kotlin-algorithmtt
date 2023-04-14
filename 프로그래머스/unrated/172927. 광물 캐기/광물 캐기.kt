class Solution {
    val DIA = "dia"
    val IRON = "iron"
    val STONE = "stone"
    
    fun solution(picks: IntArray, minerals: Array<String>): Int {
        var answer: Int = 0
        
        var count = 0
        var d= 0
        var i= 0
        var s= 0
        
        val diaHash = hashMapOf(DIA to 1, IRON to 1 , STONE to 1)
        val ironHash = hashMapOf(DIA to 5, IRON to 1 , STONE to 1)
        val stoneHash = hashMapOf(DIA to 25, IRON to 5 , STONE to 1)
        
        var mineralPickList = ArrayList<MineralPick>()
        
        val pickCnt = picks.sum()
        
        for(mine in minerals){
            when(mine) {
                "diamond" -> d += 1
                "iron" -> i += 1
                else -> s += 1
            }
            count += 1
            if(count == 5) {
                mineralPickList.add(MineralPick(d,i,s))
                d = 0
                i = 0
                s = 0
                count = 0
            }
        }
        if(count != 0){
            mineralPickList.add(MineralPick(d,i,s))
        }
        if(mineralPickList.size > pickCnt){
            mineralPickList = ArrayList(mineralPickList.subList(0,pickCnt))
        }
        mineralPickList.sortDescending()
        
        var (diaPick, ironPick, stonePick) = picks
        
        for(mineral in mineralPickList){
            if(diaPick > 0) {
                diaPick -= 1
                answer += pickMine(diaHash, mineral)
            } else if(ironPick > 0) {
                ironPick -= 1
                answer += pickMine(ironHash, mineral)
            } else if(stonePick > 0) {
                stonePick -= 1
                answer += pickMine(stoneHash, mineral)
            } else {
                break
            }
        }
        
        return answer
        
    }
    
    
    fun pickMine(hash: HashMap<String, Int>, mineral: MineralPick) : Int{
        var cost = 0
        repeat(mineral.diamond){
            cost += hash[DIA] ?: 0
        }
        repeat(mineral.iron) {
            cost += hash[IRON] ?: 0
        }
        repeat(mineral.stone) {
            cost += hash[STONE] ?: 0
        }
        println(cost)
        return cost
    }
}

data class MineralPick(val diamond: Int, val iron: Int, val stone: Int) : Comparable<MineralPick> {
    override fun compareTo(other: MineralPick) = compareValuesBy(this, other, {it.diamond}, {it.iron}, {it.stone})
}