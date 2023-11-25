package models

import com.beust.klaxon.Klaxon
import data.Users
import java.io.File

object UserModels {
    private val users = mutableListOf<Users.User>()
    private val klaxon = Klaxon()
    private val jsonFile = File("users.json")

    fun addUser(user: Users.User) {
        users.add(user)
    }
    fun saveUsersToJson() {
        val jsonString = klaxon.toJsonString(users)
        jsonFile.writeText(jsonString)
    }
    fun loadUsersFromJson() {
        if (jsonFile.exists()) {
            val jsonString = jsonFile.readText()
            val userList = klaxon.parseArray<Users.User>(jsonString)
            if (userList != null) {
                users.clear()
                users.addAll(userList)
            }
        }
    }
    fun showUsers() {
        for (user in users) {
            println("")
            println("""
                akun :
                Username: ${user.username}, 
                Password: ${user.password}, 
                Phone Number: ${user.phoneNumber}
            """.trimIndent())
        }
    }

    fun findUser(username: String, password: String): Users.User? {
        return users.find { it.username == username && it.password == password }
    }

    fun deleteUsers(username: String) {
        val userToDelete = users.find { it.username == username }
        if (userToDelete != null) {
            users.remove(userToDelete)
            saveUsersToJson()
        } else {
            println("Pengguna dengan username $username tidak ditemukan.")
        }
    }
}