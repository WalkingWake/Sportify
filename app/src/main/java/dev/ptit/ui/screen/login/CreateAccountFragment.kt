package dev.ptit.ui.screen.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.ptit.R
import dev.ptit.data.user.UserModel
import dev.ptit.databinding.FragmentCreateAccountBinding
import dev.ptit.setup.keys.Keys

@AndroidEntryPoint
class CreateAccountFragment : Fragment() {

    private var _binding: FragmentCreateAccountBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvCreateAccountButton.setOnClickListener {
            val signUpState = viewModel.getRegisterState(
                UserModel(
                    name = binding.etYourName.text.toString(),
                    email = binding.etYourEmail.text.toString(),
                    password = binding.etPassword.text.toString()
                ),
                confirmPassword = binding.etConfirmPassword.text.toString()
            )

            when (signUpState) {
                SignUpState.VERIFY -> {
                    binding.tvPasswordWarning.visibility = View.GONE
                    val bundle = Bundle()
                    viewModel.verifySignUp()
                    bundle.putString(Keys.NAVIGATE_FROM, Keys.CREATE_ACCOUNT_FRAGMENT)
                    findNavController().navigate(
                        R.id.action_createAccountFragment_to_verifyFragment,
                        bundle
                    )
                }

                SignUpState.EMAIL_EXIST -> {
                    binding.tvPasswordWarning.apply {
                        text = getString(R.string.email_exist)
                        setTextColor(ContextCompat.getColor(context, R.color.red))
                    }
                }

                SignUpState.PASSWORD_NOT_MATCH -> {
                    binding.tvPasswordWarning.apply {
                        text = getString(R.string.password_not_match)
                        setTextColor(ContextCompat.getColor(context, R.color.red))
                    }
                }

                SignUpState.EMPTY_FIELD -> {
                    binding.tvPasswordWarning.apply {
                        text = getString(R.string.please_fill_all_fields)
                        setTextColor(ContextCompat.getColor(context, R.color.red))
                    }
                }

                SignUpState.PASSWORD_TOO_SHORT -> {
                    binding.tvPasswordWarning.apply {
                        text = getString(R.string.password_warning)
                        setTextColor(ContextCompat.getColor(context, R.color.red))
                    }

                }

                SignUpState.EMAIL_INVALID -> {
                    binding.tvPasswordWarning.apply {
                        text = getString(R.string.please_enter_valid_email)
                        setTextColor(ContextCompat.getColor(context, R.color.red))
                    }
                }
            }


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}