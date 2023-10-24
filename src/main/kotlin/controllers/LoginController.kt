package controllers

import data.UserModels.findUser
import app.registerForm
import data.Admin


class LoginController{
    val admin = Admin()
    fun login() {
        first@ while (true){
            print("username : ")
            val lUsername = readln()
            print("password : ")
            val lPassword = readln()


            if (lUsername == "admin" && lPassword == "admin"){
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
                            break@first
                        }

                        else -> println("nomor tidak tersedia, silahkan masukan nomor yang tersedia!\n")
                    }
                }
            }
            val user = findUser(lUsername, lPassword)
            if (user != null) {
                println("Login berhasil sebagai pengguna dengan username: ${user.getUsername()}")
                break
            } else {
                println("username atau password salah, silahkan masukan yang benar!!!")
            }
        }
    }
}