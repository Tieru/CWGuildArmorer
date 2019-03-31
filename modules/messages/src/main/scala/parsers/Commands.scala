package parsers

object Commands {

  val START = "start"
  val GUILD_LIST = "list"
  val MEMBER_INFO = "info"

  def makeInlineCommand(command: String) = s"/c_$command"

}
