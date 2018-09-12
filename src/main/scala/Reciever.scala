import com.rabbitmq.client._

class Reciever {

  val factory = new ConnectionFactory()
  factory.setHost("localhost")
  val connection: Connection = factory.newConnection()
  val channel: Channel = connection.createChannel()
  val QUEUE_NAME: String = "Server2Client"
  channel.queueDeclare(QUEUE_NAME, false, false, false, null)
  val consumer: DefaultConsumer = new DefaultConsumer(channel) {

    override def handleDelivery(consumerTag: String,
                                envelope: Envelope,
                                properties: AMQP.BasicProperties,
                                body: Array[Byte]) {
      val message = new String(body, "UTF-8")
      println(" [x] Received '" + message + "'" + "\n------------------------------------------------------------\n")
    }
  }
  channel.basicConsume(QUEUE_NAME, true, consumer)
}
