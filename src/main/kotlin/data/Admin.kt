package data

import models.UserModels

class Admin: Users(){

    override fun notification(username: String) {
        println("akun $username, berhasil ditambahkan oleh admin\n")
    }

    fun showUsersAsAdmin(){
        UserModels.showUsers()
    }

    fun deleteUsersAsAdmin(){
        print("""
            Masukan username akun yang ingin dihapus!
            username : 
        """.trimIndent())
        val userInput = readln()
        UserModels.deleteUsers(userInput)
        println("Akun berhasil dihapus!!!\n")
    }
}