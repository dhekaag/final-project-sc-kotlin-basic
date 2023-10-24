package data

open class Users(
    private var username:String?,
    private var password:String?,
    private var phoneNumber: String?
) : Authentication {
    constructor():this("","","")
    fun getUsername(): String? {
        return username
    }
    fun getPassword():String?{
        return password
    }
    fun getPhoneNumber():String?{
        return phoneNumber
    }
    override fun register(username: String, password: String, phoneNumber: String) {
        UserModels.addUser(Users(username, password, phoneNumber))
        UserModels.saveUsersToJson()
    }
    override fun notification(username: String) {
        println("akun $username, berhasil ditambahkan\n")
    }
}