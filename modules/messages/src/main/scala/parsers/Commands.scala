package parsers

object Commands {

  val START = "start"
  val GUILD_LIST = "list"

  def makeInlineCommand(command: String) = s"/c_$command"

}
