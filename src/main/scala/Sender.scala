import java.io._

class Sender(connectionClass: ConnectionClass) {
  val reader = new BufferedReader(new InputStreamReader(System.in))
  val QUEUE_NAME: String = "Client2Server"
  connectionClass.channel.queueDeclare(QUEUE_NAME, false, false, false, null)

  def messageSender(message: String): Unit = {
    connectionClass.channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"))
    println(" [x] Sent '" + message + "'")
  }

}
