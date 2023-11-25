package data

import models.UserModels

open class Users(
    private val username:String?,
    private val password:String?,
    private val phoneNumber: String?
) : Authentication {
    constructor():this("","","")

    data class User(
        val username: String?,
        val password: String?,
        val phoneNumber: String?
    )

    override fun register(username: String, password: String, phoneNumber: String) {
        UserModels.addUser(User(username, password, phoneNumber))
        UserModels.saveUsersToJson()
    }
    override fun notification(username: String) {
        println("akun $username, berhasil ditambahkan\n")
    }
}