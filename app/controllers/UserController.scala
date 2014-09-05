package controllers

/**
 * Created by Skylar on 8/25/2014.
 */

import play.api.data.validation
import play.api.libs.json.{JsValue, JsObject}
import play.api.mvc.{Action, Controller}
import play.api.libs.json._

import com.github.t3hnar.bcrypt._
import scala.util.Try
import models.{Note, User}


import scala.util.{Failure, Success}

object UserController extends Controller {
	def create = Action(parse.json) { request =>
		val userJson: JsValue = request.body.as[JsValue]
		val username = userJson.\("username").as[String]
		val email = userJson.\("email").as[String]
		val password = userJson.\("password").as[String]
		val confirm = userJson.\("confirmpassword").as[String]
		if(!validation.Constraints.nonEmpty(username).toString.equals("Valid")) {
			BadRequest(Json.obj(
				"message" -> "Username is required"
			))
		} else if(!validation.Constraints.emailAddress(email).toString.equals("Valid")) {
			if(!validation.Constraints.nonEmpty(email).toString.equals("Valid")) {
				BadRequest(Json.obj(
					"message" -> "Email is required"
				))
			} else {
				BadRequest(Json.obj(
					"message" -> "Email is not valid"
				))
			}
		} else if(!validation.Constraints.nonEmpty(password).toString.equals("Valid")) {
			BadRequest(Json.obj(
				"message" -> "Password is required"
			))
		} else if(!password.equals(confirm)) {
			BadRequest(Json.obj(
				"message" -> "Passwords do not match"
			))
		} else {
			val salt = generateSalt
			val hashedPassword = password.bcrypt(salt)
			val user = User.create(username, email, hashedPassword, salt)
			println(user.toString)
			Ok(Json.obj(
				"message" -> "Account Created"
			))
		}
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
