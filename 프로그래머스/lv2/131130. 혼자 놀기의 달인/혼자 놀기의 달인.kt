class Solution {
    fun solution(cards: IntArray): Int {
        var answer: Int = 0
        val visited = BooleanArray(cards.size + 1)
        visited[0] = true
        
        var cardsList = ArrayList<Int>()
        cards.forEach{
            cardsList.add(it)
        }
        
        val boxList = ArrayList<ArrayList<Int>>()
        
        while(cardsList.isNotEmpty()){
            val box = ArrayList<Int>()
            var nowNum = cardsList.first()
            box.add(nowNum)
            visited[nowNum] = true
            cardsList.remove(nowNum)
            while (true){
                nowNum = cards[nowNum - 1]
                if(visited[nowNum]){
                    break
                } else {
                    visited[nowNum] = true
                    box.add(nowNum)
                    cardsList.remove(nowNum)
                }
            }
            boxList.add(box)
        }
        
        if(boxList.size == 1){
            answer = 0
        } else {
            val countList = boxList.map{it.size}.sorted().reversed()
            answer = countList[0] * countList[1]
        }
        
        
        return answer
    }
}