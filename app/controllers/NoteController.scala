package controllers

/**
 * Created by Skylar on 8/25/2014.
 */
import play.api.mvc.{Action, Controller}
import models.{Note, User}

object NoteController extends Controller {
	def index = Action {
		Ok("Things are working")
	}

	def create = Action {
		var user = new User("Play", "Play", "Play");
		user.save
		var note = new Note("Play", "Play", user);
		note.save
		Ok("Note created")
	}
}
