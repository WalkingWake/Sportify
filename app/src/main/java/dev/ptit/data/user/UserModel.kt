package dev.ptit.data.user

data class UserModel(
    var remoteId: Int = 0,
    val email: String = "",
    var password: String = "",
    val name: String = "",
)