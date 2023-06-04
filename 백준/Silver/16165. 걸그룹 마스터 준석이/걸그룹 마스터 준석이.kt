package solve_16165

private val sb = StringBuilder()
private fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }

    val groupHash = HashMap<String, List<String>>()
    val memberHash = HashMap<String, String>()
    repeat(n) {
        val groupName = readln()
        val group = List(readln().toInt()) {
            readln()
        }.sorted()
        groupHash[groupName] = group

        group.forEach{
            memberHash[it] = groupName
        }

    }

    repeat(m){
        val q = readln()

        when(readln().toInt()){
            0 -> {
                groupHash[q]?.forEach{
                    sb.appendLine(it)
                }
            }
            1 -> {
                sb.appendLine(memberHash[q])
            }
        }
    }

    println(sb)
}

private data class Group(
    val member: List<String>
)