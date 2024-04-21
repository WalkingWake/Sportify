package dev.ptit.data

import dev.ptit.data.mail.MailModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface MailService {

    @POST("api/mail/register")
    fun sendMailRegister(@Body mailModel: MailModel): Call<String>

    @POST("api/mail/reset")
    fun sendMailReset(@Body mailModel: MailModel): Call<String>

}