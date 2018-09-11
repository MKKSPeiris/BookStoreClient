import com.rabbitmq.client._
import com.rabbitmq.client.ConnectionFactory
import java.io._

import jdk.nashorn.internal.ir.WhileNode

object Client {

  def main(argv: Array[String]) {

    println("Press 1 to get allbooks names")
    println("Press 2 to get Book Details ")
    println("Press 3 to Add a New book")
    println("Press 4 to get Remove a Book")
    var reader = new BufferedReader(new InputStreamReader(System.in))

    val factory = new ConnectionFactory()
    factory.setHost("localhost")
    val connection = factory.newConnection()
    val channel = connection.createChannel()
    val QUEUE_NAME: String = "Client2Server"
    channel.queueDeclare(QUEUE_NAME, false, false, false, null)

    val QUEUE_NAME2: String = "Server2Client"
    channel.queueDeclare(QUEUE_NAME2, false, false, false, null)
    val consumer = new DefaultConsumer(channel) {

      override def handleDelivery(consumerTag: String,
                                  envelope: Envelope,
                                  properties: AMQP.BasicProperties,
                                  body: Array[Byte]) {
        var message = new String(body, "UTF-8")
        println(" [x] Received '" + message + "'")
        println("\n------------------------------------------------------------\n")
      }
    }
    channel.basicConsume(QUEUE_NAME2, true, consumer)

    while (true) {
      var userCommand = reader.readLine().toInt
      if (userCommand == 1) {
        var message: String = "AllBooks"
        MessageSender(message)
      }
      else if (userCommand == 2) {
        var message: String = "BookDetail"
        println("Enter Book Name :")
        message = message + "+" + reader.readLine().toString
        MessageSender(message)
      }
      else if (userCommand == 3) {
        var message: String = "BookAdd"
        println("Enter Book Name :")
        message = message + "+" + reader.readLine().toString
        println("Enter Authers Name :")
        message = message + "+" + reader.readLine().toString
        print("Enter Price :")
        message = message + "+" + reader.readLine().toString
        MessageSender(message)
      }
      else if (userCommand == 4) {
        var message: String = "BookRemove"
        println("Enter Book Name")
        message = message + "+" + reader.readLine().toString
        MessageSender(message)
      }
      else {
        println("try again")
      }
    }

    def MessageSender(message: String): Unit = {
      channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"))
      println(" [x] Sent '" + message + "'")
      //channel.close()
      //connection.close()
    }


  }

}
