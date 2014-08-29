package controllers

/**
 * Created by Skylar on 8/25/2014.
 */
import play.api.mvc.{Action, Controller}
import models.{Note, User}

object UserController extends Controller {
	def index = Action {
		Ok("Things are working")
	}

	def get(id: Int) = Action {
		val user = User.getById(id)
		Ok(user.toString)
	}

	def create = Action {
		Ok("Things are working")
	}
}
