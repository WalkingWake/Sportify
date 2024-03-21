package dev.ptit.ui.screen.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dev.ptit.R
import dev.ptit.databinding.FragmentVerifyBinding
import dev.ptit.setup.keys.Keys
import dev.ptit.ui.dialog.DialogCongratulations

class VerifyFragment : Fragment() {

    private var _binding: FragmentVerifyBinding? = null
    private val binding get() = _binding!!

    private var navigateFrom: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVerifyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigateFrom = arguments?.getString(Keys.NAVIGATE_FROM)

        binding.tvVerifyButton.setOnClickListener {
            if (navigateFrom == Keys.FORGOT_PASSWORD_FRAGMENT) {
                findNavController().navigate(R.id.action_verifyFragment_to_createNewPasswordFragment)
            } else if (navigateFrom == Keys.CREATE_ACCOUNT_FRAGMENT) {
                context?.let { context ->
                    DialogCongratulations(
                        context,
                        context.getString(R.string.successful_registration)
                    ) {
                        findNavController().popBackStack(R.id.signInFragment, false)
                    }.show()
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}