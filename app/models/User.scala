package models

/**
 * Created by Skylar on 8/26/2014.
 */

import anorm._
import play.api.db._
import play.api.Play.current
import play.api.libs.json._

import scala.util.Try

class User(userIdValue: Long, username: String, email: String, password: String) {
	def userId = userIdValue
	def hash: String = ""

	def create = {
		DB.withConnection { implicit c =>
			val result: Option[Long] = SQL("insert into \"user\"(username, email, password, hash) " +
				"values({username},{email},{password}, {hash})")
				.on('username -> username, 'email -> email, 'password -> password, 'hash -> hash).executeInsert()
		}
	}

	def save = {
		DB.withConnection { implicit c =>
			val result: Int = SQL("insert into \"user\"(username, email, password, hash) " +
				"values({username},{email},{password}, {hash})")
				.on('username -> username, 'email -> email, 'password -> password, 'hash -> hash).executeUpdate()
		}
	}
}

object User {
	def getById(id: Int): Try[User] = {
		//This seems wrong on so many levels
		Try {
			val res: Row = DB.withConnection(implicit c =>
				SQL("SELECT userid, username, email, password, hash FROM \"user\" WHERE userid = {userid}").
				on("userid" -> id).apply().head
			)

			val user: User = new User(res[Int]("userid"), res[String]("username"), res[String]("email"),
				res[String]("password"))
			return Try(user)
		}
	}
}
