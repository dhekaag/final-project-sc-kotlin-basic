package controllers


import app.homeScreen
import app.registerForm
import data.Admin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import models.UserModels
import constant.isLoading


class LoginController{
    private val admin = Admin()

    fun login()= runBlocking {
        first@ while (true){
            print("\nusername : ")
            val lUsername = readln()
            print("password : ")
            val lPassword = readln()

            if (lUsername == "admin" && lPassword == "admin"){
                adminDashboard()
                break@first
            }
            val user = UserModels.findUser(lUsername, lPassword)
            if (user != null) {
                println(isLoading)
                launch {
                    delay(2_000)
                    homeScreen(username = user.username.toString())
                }
                break@first
            } else {
                println("username atau password salah, silahkan masukan yang benar!!!")
            }
        }
    }

    private fun adminDashboard(){
        while (true){
            println("")
            print("""
                        Menu:
                        1. Create user
                        2. Show user
                        3. delete user
                        4. Exit
                        Pilih menu:
                    """.trimIndent())
            val userInput = readln()
            when(userInput){
                "1" -> registerForm(admin)
                "2" -> admin.showUsersAsAdmin()
                "3" -> admin.deleteUsersAsAdmin()
                "4" -> {
                    println("user logout\n")
                    break
                }
                else -> println("nomor tidak tersedia, silahkan masukan nomor yang tersedia!\n")
            }
        }
    }
}