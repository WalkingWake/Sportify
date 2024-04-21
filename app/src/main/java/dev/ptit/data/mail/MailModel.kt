package dev.ptit.data.mail

import com.google.gson.annotations.SerializedName

data class MailModel(
    @SerializedName("toEmail")
    val toEmail: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("code")
    val code: String = ""
)
