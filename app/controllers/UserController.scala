package controllers

/**
 * Created by Skylar on 8/25/2014.
 */

import play.api.libs.json.{JsValue, JsObject}
import play.api.mvc.{Action, Controller}
import play.api.libs.json._
import models.{Note, User}

import scala.util.{Failure, Success}

object UserController extends Controller {
	def create = Action(parse.json) { request =>
		val userJson: JsValue = request.body.as[JsValue]
		val username = userJson.\("username")
		val email = userJson.\("email")
		val password = userJson.\("password")
		if(userJson.\("confirmpassword") != password) {
			Ok(Json.obj(
				"success" -> "false",
				"message" -> "passwords must match"
			))
		}
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
