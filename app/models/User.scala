package models

/**
 * Created by Skylar on 8/26/2014.
 */

import anorm._
import play.api.db._
import play.api.Play.current

import scala.util.Try

class User(userIdValue: Int, username: String, email: String, password: String) {
	def userId = userIdValue

	def save = {
		DB.withConnection { implicit c =>
			val result: Option[Long] = SQL("insert into \"user\"(username, email, password) values({username},{email},{password})")
				.on('username -> username, 'email -> email, 'password -> password).executeInsert()
		}
	}
}

object User {
	def getById(id: Int): Try[User] = {
		//This seems wrong on so many levels
		Try {
			val res: Row = DB.withConnection(implicit c =>
				SQL("SELECT userid, username, email, password FROM \"user\" WHERE userid = {userid}").
				on("userid" -> id).apply().head
			)

			val user: User = new User(res[Int]("userid"), res[String]("username"), res[String]("email"), res[String]("password"))
			return Try(user)
		}
	}
}
