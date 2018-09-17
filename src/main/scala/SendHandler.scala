import java.io.{BufferedReader, InputStreamReader}

class SendHandler(connectionClass: ConnectionClass) {

  println("Press 1 to get allbooks names" +
    "\nPress 2 to get Book Details" +
    "\nPress 3 to Add a New book" +
    "\nPress 4 to Remove a Book")

  val reader = new BufferedReader(new InputStreamReader(System.in))
  val sender = new Sender(connectionClass)
  while (true) {
    val userCommand = reader.readLine().toInt
    if (userCommand == 1) {
      val message: String = "AllBooks"
      sender.messageSender(message)
    }
    else if (userCommand == 2) {
      println("Enter Book Name :")
      val message: String = "BookDetail" + "+" + reader.readLine()
      sender.messageSender(message)
    }
    else if (userCommand == 3) {

      println("Enter Book Name :")
      val bookName: String = reader.readLine()
      println("Enter Authers Name :")
      val authorName = reader.readLine()
      print("Enter Price :")
      val price = reader.readLine()
      val message: String = "BookAdd" + "+" + bookName + "+" + authorName + "+" + price
      sender.messageSender(message)
    }
    else if (userCommand == 4) {
      println("Enter Book Name")
      val message: String = "BookRemove" + "+" + reader.readLine()
      sender.messageSender(message)
    }
    else {
      println("try again")
    }
  }
}
