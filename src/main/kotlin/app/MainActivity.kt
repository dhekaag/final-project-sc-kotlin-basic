package app

import controllers.LoginController
import models.UserModels
import data.Users

fun main(){
    val users = Users()
    val loginC = LoginController()
    UserModels.loadUsersFromJson()

    try {
        main@while (true){
            print("""
            Dashboard:
            1. Login
            2. SignUp
            3. Exit
            masukan pilihan menu :
        """.trimIndent())
            val userInput = readln()

            when(userInput){
                "1" -> {
                    loginC.login()
                    break
                }
                "2" -> registerForm(users)
                "3" -> {
                    println("Terima kasih")
                    break
                }
                else -> println("nomor tidak tersedia, silahkan masukan nomor yang tersedia!")
            }

        }
    }catch (e: Exception){
        println("terjadi error : ${e.message.toString()}")
    }
}











