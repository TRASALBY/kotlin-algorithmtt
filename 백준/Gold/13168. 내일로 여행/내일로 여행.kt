package solve_13168

private const val MAX = 1_000_000_000.0

private fun main() {
    val (n, r) = readln().split(" ").map { it.toInt() }
    val cities = readln().split(" ")
    val citiesHash = HashMap<String, Int>()

    var cityCnt = 0
    for (city in cities) {
        if (citiesHash.contains(city)) {
            continue
        }
        citiesHash[city] = cityCnt++
    }

    val m = readln().toInt()
    val destination = readln().split(" ").map { citiesHash[it] ?: -1 }

    val graphDefault = Array(cityCnt) {
        DoubleArray(cityCnt) {
            MAX
        }
    }

    val graphUseTicket = Array(cityCnt) {
        DoubleArray(cityCnt) {
            MAX
        }
    }
    
    for(i in 0 until cityCnt){
        graphDefault[i][i] = 0.0
        graphUseTicket[i][i] = 0.0
    }

    val k = readln().toInt()

    repeat(k) {
        val input = readln().split(" ")
        val edge = Edge(input[0], citiesHash[input[1]] ?: -1, citiesHash[input[2]] ?: -1, input[3].toDouble())
        when (edge.type) {
            "Mugunghwa", "ITX-Saemaeul", "ITX-Cheongchun" -> {
                graphUseTicket[edge.start][edge.end] = 0.0
                graphUseTicket[edge.end][edge.start] = 0.0
            }
            "S-Train", "V-Train" -> {
                graphUseTicket[edge.start][edge.end] = minOf(edge.cost / 2, graphUseTicket[edge.start][edge.end])
                graphUseTicket[edge.end][edge.start] = graphUseTicket[edge.start][edge.end]
            }
            else -> {
                graphUseTicket[edge.start][edge.end] = minOf(edge.cost, graphUseTicket[edge.start][edge.end])
                graphUseTicket[edge.end][edge.start] = graphUseTicket[edge.start][edge.end]
            }
        }

        graphDefault[edge.start][edge.end] = minOf(edge.cost,graphDefault[edge.start][edge.end])
        graphDefault[edge.end][edge.start] = graphDefault[edge.start][edge.end]
    }

    for (k in 0 until cityCnt) {
        for (i in 0 until cityCnt) {
            for (j in 0 until cityCnt) {
                graphDefault[i][j] = minOf(graphDefault[i][j], graphDefault[i][k] + graphDefault[k][j])
                graphUseTicket[i][j] = minOf(graphUseTicket[i][j], graphUseTicket[i][k] + graphUseTicket[k][j])
            }
        }
    }

    var cost1 = 0.0
    var cost2 = r.toDouble()

    for (i in 0 until m - 1) {
        cost1 += graphDefault[destination[i]][destination[i + 1]]
        cost2 += graphUseTicket[destination[i]][destination[i + 1]]
    }

    val answer = if (cost1 > cost2) {
        "Yes"
    } else {
        "No"
    }
    println(answer)

}

private data class Edge(
    val type: String,
    val start: Int,
    val end: Int,
    val cost: Double,
)
