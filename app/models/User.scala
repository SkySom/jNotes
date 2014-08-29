package models

/**
 * Created by Skylar on 8/26/2014.
 */

import anorm._
import play.api.db._
import play.api.Play.current

class User(userId: String, username: String, email: String, password: String) {
	def save = {
		DB.withConnection { implicit c =>
			val result: Option[Long] = SQL("insert into \"user\"(username, email, password) values({username},{email},{password})")
				.on('username -> username, 'email -> email, 'password -> password).executeInsert()
		}
	}
}

object User {
	def getById(id: Int): User = {
		val res: SqlQueryResult = SQL("SELECT userid, username, email, password FROM user WHERE userid = {userid}").
				on("userid" -> id).apply().head
		val user: User = new User(res[Int]("userid"), res[String]("username"), res[String]("email"), res[String]("password"))
		return user
	}
}
