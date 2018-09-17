object Client {

  def main(argv: Array[String]) {
    val connectionClass = new ConnectionClass()
    new Reciever(connectionClass)
    new SendHandler(connectionClass)
  }

}
