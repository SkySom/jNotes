package models

/**
 * Created by Skylar on 8/26/2014.
 */

import anorm._
import java.lang.IllegalArgumentException
import org.postgresql.util.PSQLException
import play.api.db._
import play.api.Play.current

import scala.util.Try

class User(userIdValue: Long, username: String, email: String, password: String, hash: String) {
	def userId = userIdValue

	def save = {
		DB.withConnection { implicit c =>
			val result: Int = SQL("insert into users(username, email, password, hash) " +
				"values({username},{email},{password}, {hash}) where userid = {userid}")
				.on('userid -> userId, 'username -> username, 'email -> email, 'password -> password, 'hash -> hash)
				.executeUpdate()
		}
	}

	override def toString: String = {
		val userString = "Username: " + username + "\nEmail: " + email + "\nPassword: " + password + "\nHash: " + hash
		userString
	}
}

object User {
	def getById(id: Long): Try[User] = {
		Try {
			val res: Row = DB.withConnection(implicit c =>
				SQL("SELECT userid, username, email, password, hash FROM users WHERE userid = {userid}").
				on("userid" -> id).apply().head
			)

			val user: User = new User(res[Long]("userid"), res[String]("username"), res[String]("email"),
				res[String]("password"), res[String]("hash"))

			user
		}
	}

	def create(username: String, email: String, password: String, hash: String)= {
		try {
			val result: Option[Long] = DB.withConnection { implicit c =>
				SQL("insert into users(username, email, password, hash) " +
					"values({username},{email},{password}, {hash})")
					.on('username -> username, 'email -> email, 'password -> password, 'hash -> hash).executeInsert()
			}
		} catch {
			case e: PSQLException => throw new IllegalArgumentException("Username or Email already taken")
		}
	}
}
