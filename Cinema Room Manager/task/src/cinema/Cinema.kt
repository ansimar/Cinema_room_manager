package cinema

import java.text.DecimalFormat
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
    val scanner = Scanner(System.`in`)
    println()
    println("Enter the number of rows:")
    val numberOfRows = scanner.nextInt()
    println("Enter the number of seats in each row:")
    val numberOfSeatsPerRows = scanner.nextInt()
    populateRoom(numberOfRows, numberOfSeatsPerRows)

    var income = 0
    // Print menu
    do {
        printMenu()
        when(scanner.nextInt()){
            1 -> printVariableRoom(numberOfRows, numberOfSeatsPerRows)
            2 -> income = buyASeat(scanner, numberOfRows, numberOfSeatsPerRows, income)
            3 -> printStatistics(numberOfRows, numberOfSeatsPerRows, income)
            0 -> return
        }
    }while (true)

}

fun printStatistics(numberOfRows: Int, numberOfSeatsPerRows: Int, income: Int) {
    val purchased = getNumberOfPurchasedTickets()
    println("Number of purchased tickets: ${purchased}")
    val totalIncome = getMoneyObtained(numberOfRows, numberOfSeatsPerRows)
    val percentage: Double = (purchased * 100).toDouble()/(numberOfRows * numberOfSeatsPerRows).toDouble()
    val dec = DecimalFormat("#,##0.00")
    println("Percentage: ${dec.format(percentage)}%")
    println("Current income: $${income}")
    println("Total income: $${totalIncome}")
}

fun getNumberOfPurchasedTickets(): Int {
    var purchased = 0
    room.forEach { row ->
        row.forEach { seat -> if (seat.equals("B")) purchased++ }
    }
    return purchased
}

private fun buyASeat(scanner: Scanner, numberOfRows: Int, numberOfSeatsPerRows: Int, income: Int): Int {
    var purchased = false
    var prize = 0
    do {
        println("Enter a row number:")
        val numberOfRow = scanner.nextInt()
        println("Enter a seat number in that row:")
        val numberOfSeat = scanner.nextInt()
        if (validSeat(numberOfRow, numberOfSeat)){
            prize = getTicketPrize(numberOfRow, numberOfSeat, numberOfRows, numberOfSeatsPerRows)
            println()
            println("Ticket price: $${prize}")
            println()
            bookSeat(numberOfRow, numberOfSeat)
            purchased = true
        }
    }while(!purchased)

    return income + prize
}

fun validSeat(numberOfRow: Int, numberOfSeat: Int): Boolean {
    try {
        val seat = room[numberOfRow - 1][numberOfSeat - 1]
        if (seat.equals("B")){
            println("That ticket has already been purchased!")
            return false
        }else return true
    }catch (e: IndexOutOfBoundsException){
        println("Wrong input!")
        return false
    }
}

private fun printMenu() {
    println()
    println("1. Show the seats")
    println("2. Buy a ticket")
    println("3. Statistics")
    println("0. Exit")
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

fun printVariableRoom(rows: Int, seats: Int) {
    println()
    println("Cinema:")
    print("  ")
    room.get(0).forEachIndexed { index, seat ->
        print("${index + 1} ")
    }
    room.forEachIndexed { index, seats ->
        println()
        print("${index + 1} ")
        seats.forEach { seat -> print("${seat} ") }
    }
    println()
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
