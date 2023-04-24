class Solution {
    fun solution(enroll: Array<String>, referral: Array<String>, seller: Array<String>, amount: IntArray): IntArray {
        var answer= IntArray(enroll.size)

        val treeMap = HashMap<String, TreeNode>()

        val root = TreeNode("-")
        treeMap["-"] = root

        for(i in referral.indices){
            val name = enroll[i]
            val parent = referral[i]
            val parentNode = treeMap[parent]!!
            val nowNode = TreeNode(name,parent)
            parentNode.children.add(nowNode)
            treeMap[name] = nowNode
        }

        for(i in seller.indices){
            val name = seller[i]
            var money = amount[i] * 100
            var nowNode = treeMap[name]!!
            var parent = nowNode.parent
            var parentNode = treeMap[parent]
            var rebate = 0
            while(parentNode != null && money > 0){
                rebate = nowNode.getMoney(money)
                nowNode = parentNode
                parent = parentNode.parent
                parentNode = treeMap[parent]
                money = rebate
            }   
        }

        for (i in answer.indices){
            answer[i] = treeMap[enroll[i]]!!.money
        }


        return answer
    }
}

data class TreeNode(
    val name: String,
    val parent: String = "",
    var money : Int = 0,
    val children: MutableList<TreeNode> = mutableListOf()
){
    fun getMoney(money: Int): Int{
        val rebate = money / 10
        this.money += money - rebate
        return rebate
    }
}