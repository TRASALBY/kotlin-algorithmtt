package solve_17219

fun main() {
    val (n,m) = readln().split(" ").map{it.toInt()}

    val passwordHash = HashMap<String, String>()

    repeat(n){
        val (site, password) = readln().split(" ")
        passwordHash[site] = password
    }

    val sb = StringBuilder()

    repeat(m){
        sb.appendLine(passwordHash[readln()])
    }

    print(sb)
}