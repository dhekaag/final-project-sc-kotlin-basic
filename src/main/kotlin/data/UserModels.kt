package data

import com.beust.klaxon.Klaxon
import java.io.File

object UserModels {
    private val users = mutableListOf<Users>()

    fun addUser(user: Users) {
        users.add(user)
    }
    fun saveUsersToJson() {
        val jsonString = Klaxon().toJsonString(users)
        val jsonFile = File("users.json")
        jsonFile.writeText(jsonString)
    }
    fun loadUsersFromJson() {
        val jsonFile = File("users.json")
        if (jsonFile.exists()) {
            val jsonString = jsonFile.readText()
            val userList = Klaxon().parseArray<Users>(jsonString)
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
                Username: ${user.getUsername()}, 
                Password: ${user.getPassword()}, 
                Phone Number: ${user.getPhoneNumber()}
            """.trimIndent())
        }
    }

    fun findUser(username: String, password: String): Users? {
        return users.find { it.getUsername() == username && it.getPassword() == password }
    }

    fun deleteUsers(username: String) {
        val userToDelete = users.find { it.getUsername() == username }
        if (userToDelete != null) {
            users.remove(userToDelete)
            saveUsersToJson()
        } else {
            println("Pengguna dengan username $username tidak ditemukan.")
        }
    }
}