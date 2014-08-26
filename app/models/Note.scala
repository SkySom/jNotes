package models

/**
 * Created by Skylar on 8/26/2014.
 */
class Note(nameValue:String, messageValue: String, userValue: User) {
	def noteId = 0
	def name = nameValue
	def message = messageValue
	def user = userValue
}
