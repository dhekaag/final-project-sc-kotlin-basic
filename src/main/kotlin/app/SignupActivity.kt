package app

import data.Users
import models.UserModels

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