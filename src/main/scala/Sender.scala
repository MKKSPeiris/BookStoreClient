import java.io._

import com.rabbitmq.client.ConnectionFactory

class Sender {
  val reader = new BufferedReader(new InputStreamReader(System.in))
  val factory = new ConnectionFactory()
  factory.setHost("localhost")
  val connection = factory.newConnection()
  val channel = connection.createChannel()
  val QUEUE_NAME: String = "Client2Server"
  channel.queueDeclare(QUEUE_NAME, false, false, false, null)

  def messageSender(message: String): Unit = {
    channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"))
    println(" [x] Sent '" + message + "'")
  }

}