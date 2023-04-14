class Solution {
    fun solution(book_time: Array<Array<String>>): Int {
        var answer: Int = 0
        val roomList = ArrayList<ArrayList<Book>>()
        
        book_time.sortBy{it.first()}
        book_time.forEach{
            val (sh, sm) = it.first().split(":").map{it.toInt()}
            val (eh,em) = it.last().split(":").map{it.toInt()}
            val nowBook = Book(Time(sh,sm), Time(eh,em))
            var flag = false
            
            for (room in roomList){
                if(flag){
                    break
                }
                for (i in room.indices){
                    if(i == 0){
                       if(nowBook.addMinute(10).end <= room[i].start){
                           flag = true
                           break
                       }
                    }else {
                        val afterCleanTIme = room[i-1].addMinute(10)
                        if(nowBook.start >= afterCleanTIme.end && nowBook.end <= room[i].start ){
                           flag = true
                           break
                        }
                    }
                }
                if(flag.not()){
                    if(nowBook.start >= room.last().addMinute(10).end){
                       flag = true
                    }
                }
                if(flag){
                    room.add(nowBook)
                    break
                }
            }
            if(flag.not()){
                roomList.add(arrayListOf(nowBook))
            }
            
        }
        println(roomList)
        return roomList.size
    }
    
}

data class Book(val start:Time, val end : Time){
        
    fun addMinute(minute: Int): Book{
        var newHour = end.hour
        var newMinute = end.minute
        newMinute += minute
        if(newMinute >= 60){
            newHour +=1
            newMinute -= 60
        }
        return Book(start, Time(newHour, newMinute))
    }
    
    override fun toString() = "$start ~ $end"
}
data class Time(val hour: Int, val minute: Int) : Comparable<Time> {
    override fun compareTo(other: Time) = compareValuesBy(this, other, {it.hour}, {it.minute})
    override fun toString() = "${hour}:${minute}"
} 