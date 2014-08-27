package models

/**
 * Created by Skylar on 8/26/2014.
 */

import anorm._
import play.api.db._
import play.api.Play.current

class Note(nameValue:String, messageValue: String, userValue: User) {
	def noteId = 0
	def name = nameValue
	def message = messageValue
	def user = userValue

	def save = {
		DB.withConnection { implicit c =>
			val result: Option[Long] = SQL("insert into Note(name, message, userId) values({name},{message},{userId})")
				.on('name -> name, 'message -> message, 'userId -> user.userId).executeInsert()
		}
	}
}
