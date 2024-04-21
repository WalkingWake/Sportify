package dev.ptit.ui.screen.login

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ptit.data.MailService
import dev.ptit.data.mail.MailModel
import dev.ptit.data.user.UserModel
import dev.ptit.data.user.UserRepository
import dev.ptit.setup.keys.Keys
import dev.ptit.setup.pref.LazyPref
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val lazyPref: LazyPref,
    private val userRepository: UserRepository,
    private val mailService: MailService
) : ViewModel() {

    private var forgotPasswordUserId: Int? = null
    private var verifyUser: UserModel? = null
    private var verifyCode: String? = null

    fun verifySignUp() {
        val code = (1000..9999).random().toString()
        verifyCode = code
        val call = mailService.sendMailRegister(
            MailModel(
                toEmail = verifyUser?.email ?: "",
                name = verifyUser?.name ?: "",
                code = verifyCode ?: ""
            )
        )

        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.d("TAG", "onResponse: ${response.body()}")
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("TAG", "onFailure: ${t.message}")
            }
        })
    }

    fun verifyResetPassword(email: String) {
        val code = (1000..9999).random().toString()
        verifyCode = code
        val call = mailService.sendMailReset(
            MailModel(
                toEmail = email,
                name = userRepository.getUserByEmail(email)?.name ?: "",
                code = verifyCode ?: ""
            )
        )
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.d("TAG", "onResponse: ${response.body()}")
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("TAG", "onFailure: ${t.message}")
            }
        })
    }

    fun verifyCode(code: String): Boolean {
        return code == verifyCode
    }

    fun login(
        email: String,
        password: String
    ): Boolean {
        val userId = userRepository.validateUser(email, password)
        userId?.let {
            lazyPref.put(Keys.USER_ID, it)
            return true
        }
        return false
    }

    fun registerUser() {
        verifyUser?.let {
            userRepository.registerUser(it)
        }
    }

    fun checkEmailForgotPassword(email: String): Boolean {
        val userId = userRepository.getUserIdByEmail(email)
        userId?.let {
            forgotPasswordUserId = it
            return true
        }
        return false
    }

    fun updatePassword(password: String) {
        forgotPasswordUserId?.let {
            userRepository.updatePassword(it, password)
        }
    }

    fun getRegisterState(
        userModel: UserModel,
        confirmPassword: String
    ): SignUpState {
        if (userModel.email.isEmpty() || userModel.password.isEmpty() || userModel.name.isEmpty()) {
            return SignUpState.EMPTY_FIELD
        }
        if (userRepository.checkUserExist(userModel.email)) {
            return SignUpState.EMAIL_EXIST
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(userModel.email).matches()) {
            return SignUpState.EMAIL_INVALID
        }
        if (userModel.password != confirmPassword) {
            return SignUpState.PASSWORD_NOT_MATCH
        }
        if (userModel.password.length < 6) {
            return SignUpState.PASSWORD_TOO_SHORT
        }
        verifyUser = userModel
        return SignUpState.VERIFY
    }
}

enum class SignUpState {
    VERIFY,
    EMAIL_EXIST,
    PASSWORD_NOT_MATCH,
    EMPTY_FIELD,
    PASSWORD_TOO_SHORT,
    EMAIL_INVALID
}