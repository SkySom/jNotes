package models

/**
 * Created by Skylar on 8/26/2014.
 */

import anorm._
import play.api.db._
import play.api.Play.current

class User(username: String, email: String, password: String) {
	def userId

	def save = {
		DB.withConnection { implicit c =>
			val result: Option[Long] = SQL("insert into \"user\"(username, email, password) values({username},{email},{password})")
				.on('username -> username, 'email -> email, 'password -> password).executeInsert()
		}
	}
}
