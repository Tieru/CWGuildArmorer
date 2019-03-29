package service.profile
import entity.guild.GuildEntity
import entity.player.HeroEntity
import entity.user.UserEntity
import entity.{GuildForwardAction, HeroForwardAction}
import javax.inject.Inject

import scala.concurrent.{ExecutionContext, Future}

class ProfileServiceImpl @Inject()()(implicit ec: ExecutionContext) extends ProfileService {

  override def onStart(userId: Int): Future[UserEntity] = ???

  override def onProfileUpdate(userId: Int, forwardFrom: Option[Int], forwardDate: Option[Int], info: HeroForwardAction): Future[HeroEntity] = ???

  override def onGuildUpdate(userId: Int, forwardFrom: Option[Int], forwardDate: Option[Int], info: GuildForwardAction): Future[GuildEntity] = ???

}
