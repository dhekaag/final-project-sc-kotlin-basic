package data

interface Authentication {
    fun register(username: String,password:String,phoneNumber:String)
    fun notification(username: String)
}