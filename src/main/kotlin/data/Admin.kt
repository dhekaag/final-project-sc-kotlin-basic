package data

import data.UserModels.showUsers

class Admin: Users(){


    override fun notification(username: String) {
        println("akun $username, berhasil ditambahkan oleh admin\n")
    }

    fun showUsersAsAdmin(){
        showUsers()
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