import com.rabbitmq.client._

class Reciever(connectionClass: ConnectionClass) {

  val QUEUE_NAME: String = "Server2Client"
  connectionClass.channel.queueDeclare(QUEUE_NAME, false, false, false, null)
  val consumer: DefaultConsumer = new DefaultConsumer(connectionClass.channel) {

    override def handleDelivery(consumerTag: String,
                                envelope: Envelope,
                                properties: AMQP.BasicProperties,
                                body: Array[Byte]) {
      val message = new String(body, "UTF-8")
      println(" [x] Received '" + message + "'" + "\n------------------------------------------------------------\n")
    }
  }
  connectionClass.channel.basicConsume(QUEUE_NAME, true, consumer)
}
