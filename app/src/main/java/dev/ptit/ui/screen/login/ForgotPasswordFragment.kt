package dev.ptit.ui.screen.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.ptit.R
import dev.ptit.databinding.FragmentForgotPasswordBinding
import dev.ptit.setup.keys.Keys


@AndroidEntryPoint
class ForgotPasswordFragment : Fragment() {

    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvNextButton.setOnClickListener {
            if (viewModel.checkEmailForgotPassword(binding.etYourEmail.text.toString())) {
                binding.tvEnterYourEmail.visibility = View.INVISIBLE

                val bundle = Bundle()
                bundle.putString(Keys.NAVIGATE_FROM, Keys.FORGOT_PASSWORD_FRAGMENT)
                findNavController().navigate(
                    R.id.action_forgotPasswordFragment_to_verifyFragment,
                    bundle
                )
            } else {
                binding.tvEnterYourEmail.apply {
                    text = getString(R.string.email_not_found)
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}