package models

/**
 * Created by Skylar on 8/26/2014.
 */

import anorm._
import play.api.db._
import play.api.Play.current

class User(usernameValue: String, emailValue: String, passwordValue: String) {
	def userId = 0
	def username = usernameValue
	def email = emailValue
	def password = passwordValue

	def save = {
		DB.withConnection { implicit c =>
			val result: Option[Long] = SQL("insert into User(userId, username, email, password) values({{username},{email},{password})")
				.on('username -> username, 'email -> email, 'password -> password).executeInsert()
		}
	}
}
