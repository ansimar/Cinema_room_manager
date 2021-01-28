package cinema

import java.util.*

var room: Array<Array<String>> = arrayOf(
        arrayOf("S", "S", "S", "S", "S", "S", "S", "S"),
        arrayOf("S", "S", "S", "S", "S", "S", "S", "S"),
        arrayOf("S", "S", "S", "S", "S", "S", "S", "S"),
        arrayOf("S", "S", "S", "S", "S", "S", "S", "S"),
        arrayOf("S", "S", "S", "S", "S", "S", "S", "S"),
        arrayOf("S", "S", "S", "S", "S", "S", "S", "S"),
        arrayOf("S", "S", "S", "S", "S", "S", "S", "S"))

fun main() {
    //printRoom()
    //calculateProfit()

    val scanner = Scanner(System.`in`)
    println()
    println("Enter the number of rows:")
    val numberOfRows = scanner.nextInt()
    println("Enter the number of seats in each row:")
    val numberOfSeatsPerRows = scanner.nextInt()

    populateRoom(numberOfRows, numberOfSeatsPerRows)
    printVariableRoom(numberOfRows, numberOfSeatsPerRows)
    println()
    println("Enter a row number:")
    val numberOfRow = scanner.nextInt()
    println("Enter a seat number in that row:")
    val numberOfSeat = scanner.nextInt()
    println()
    println("Ticket price: $${getTicketPrize(numberOfRow, numberOfSeat, numberOfRows, numberOfSeatsPerRows)}")
    println()
    bookSeat(numberOfRow, numberOfSeat)
    printVariableRoom(numberOfRows, numberOfSeatsPerRows)

}

fun bookSeat(numberOfRow: Int, numberOfSeat: Int) {
    room[numberOfRow - 1][numberOfSeat - 1] = "B"
}

fun getTicketPrize(numberOfRow: Int, numberOfSeat: Int, numberOfRows: Int, numberOfSeatsPerRows: Int): Int {
    if ((numberOfRows * numberOfSeatsPerRows) <= 60) return 10
    val rowsDivided = room.size / 2
    if ((room.size % 2) == 0){
        return if (numberOfRow <= rowsDivided) 10 else 8
    }else{
        return if (numberOfRow < (rowsDivided + 1)) 10 else 8
    }
}

fun calculateProfit() {
    val scanner = Scanner(System.`in`)
    println("Enter the number of rows:")
    val numberOfRows = scanner.nextInt()
    println("Enter the number of seats in each row:")
    val numberOfSeatsPerRows = scanner.nextInt()
    val profit = getMoneyObtained(numberOfRows, numberOfSeatsPerRows)
    println("Total income:")
    println("$${profit}")
}

fun getMoneyObtained(numberOfRows: Int, numberOfSeatsPerRows: Int):Int {
    val normalPrize = 10
    val reducedPrize = 8
    val rowsDivided = numberOfRows / 2

     if ((numberOfRows * numberOfSeatsPerRows) <= 60){
         return numberOfRows * numberOfSeatsPerRows * normalPrize
     }else{
         if ((numberOfRows % 2) == 0){
             return (rowsDivided * numberOfSeatsPerRows * normalPrize) + (rowsDivided * numberOfSeatsPerRows * reducedPrize)
         }else{
             return (rowsDivided * numberOfSeatsPerRows * normalPrize) + ((rowsDivided + 1) * numberOfSeatsPerRows * reducedPrize)
         }
     }
}

fun printRoom() {
    println("Cinema:")
    println("  1 2 3 4 5 6 7 8")
    room.forEachIndexed { index, seats ->
        print("${index + 1} ")
        seats.forEach { seat -> print("${seat} ") }
        println()
    }
}

fun printVariableRoom(rows: Int, seats: Int) {
    println("Cinema:")
    //populateRoom(rows, seats)
    print("  ")
    room.get(0).forEachIndexed { index, seat ->
        print("${index + 1} ")
    }
    room.forEachIndexed { index, seats ->
        println()
        print("${index + 1} ")
        seats.forEach { seat -> print("${seat} ") }
    6}
}

fun populateRoom(rows: Int, seats: Int) {
    room = emptyArray()
    val list = room.toMutableList()
    for(i in 1..rows){
       var row = mutableListOf<String>()
       for(j in 1..seats){
           row.add("S")
       }
        list.add(row.toTypedArray())
    }
    room = list.toTypedArray()
}
