package dev.ptit.ui.screen.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.ptit.R
import dev.ptit.databinding.FragmentSignInBinding
import dev.ptit.ui.MainActivity


@AndroidEntryPoint
class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvSignUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_createAccountFragment)
        }

        binding.tvSignInButton.setOnClickListener {
            if (viewModel.login(
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString()
                )
            ) {
                binding.tvError.visibility = View.INVISIBLE
                activity?.apply {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            } else {
                binding.tvError.visibility = View.VISIBLE
            }
        }

        binding.tvForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_forgotPasswordFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}