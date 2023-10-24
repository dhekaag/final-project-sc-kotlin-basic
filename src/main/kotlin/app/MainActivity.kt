package app

import controllers.LoginController
import data.UserModels
import data.Users
fun main(){
    val users = Users()
    val loginC = LoginController()
    UserModels.loadUsersFromJson()

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
}
fun registerForm(user: Users) {
    while (true) {
        print("Masukkan username: ")
        val tUsername = readln()
        print("Masukkan password: ")
        val tPassword = readln()
        print("Masukkan nomor telepon: ")
        val tPhoneNumber = readln()

        if (tUsername.isNotEmpty() && tPassword.isNotEmpty() && tPhoneNumber.isNotEmpty()) {
            user.register(tUsername, tPassword, tPhoneNumber)
            user.notification(tUsername)
            UserModels.saveUsersToJson()
            break
        } else {
            println("Username, password, dan nomor telepon tidak boleh kosong.\n")
        }
    }
}








