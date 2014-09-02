package models

/**
 * Created by Skylar on 8/26/2014.
 */

import anorm._
import play.api.db._
import play.api.Play.current
import models.User

class Note(nameValue:String, messageValue: String, userValue: User) {
	def noteId = 1
	def name = nameValue
	def message = messageValue
	def user = userValue

	def save = {
		DB.withConnection { implicit c =>
			val result: Option[Long] = SQL("insert into \"note\"(name, message, userid) values({name},{message},{userid})")
				.on('name -> name, 'message -> message, 'userid -> user.userId).executeInsert()
		}
	}
}
