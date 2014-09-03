package controllers

/**
 * Created by Skylar on 8/25/2014.
 */

import play.api.mvc.{Action, Controller}
import models.{Note, User}

import scala.util.{Failure, Success}

object UserController extends Controller {
	def create = Action(parse.json) { request =>
		println(request.body.toString())
		Ok("")
	}

	def get(id: Int) = Action {
		val user = User.getById(id)
		user match {
			case Success(v) =>
				Ok(v.toString)
			case Failure(e) =>
				Ok(e.toString)
		}
	}
}
