package parsers

object Commands {

  val START = "start"

  def makeInlineCommand(command: String) = s"/c_$command"

}
