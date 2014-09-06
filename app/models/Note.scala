package models

/**
 * Created by Skylar on 8/26/2014.
 */

import anorm._
import org.postgresql.util.PSQLException
import play.api.db._
import play.api.Play.current

import scala.util.Try

class Note(noteId: Long, name: String, message: String, user: User) {

	def save = {
		DB.withConnection { implicit c =>
			val result: Option[Long] = SQL("insert into notes(name, message, userid) values({name},{message},{userid})" +
				"where noteid = {noteid}")
				.on('noteid -> noteId, 'name -> name, 'message -> message, 'userid -> user.userId).executeInsert()
		}
	}
}

object Note {
	def getById(id: Long) = {
		Try {
			val res: Row = DB.withConnection(implicit c =>
				SQL("SELECT noteid, name, message, userid FROM notes WHERE noteid = {noteid}").
					on("noteid" -> id).apply().head
			)
			val user: Try[User] = User.getById(res[Long]("userid"))

			if (user.isSuccess) {
				val noteOwner: User = user.asInstanceOf[User]
				val note: Note = new Note(res[Long]("noteid"), res[String]("name"), res[String]("message"), noteOwner)
				note
			}
		}
	}

	def create(name: String, message: String, user: User)= {
		try {
			val result: Option[Long] = DB.withConnection { implicit c =>
				SQL("insert into notes(name, message, userid) values({name},{message},{userid})")
					.on('name -> name, 'message -> message, 'userid -> user.userId).executeInsert()
			}
		} catch {
			case e: PSQLException => throw new Exception("Note not created")
		}
	}
}
