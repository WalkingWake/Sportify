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
import dev.ptit.databinding.FragmentCreateNewPasswordBinding
import dev.ptit.ui.dialog.DialogCongratulations

@AndroidEntryPoint
class CreateNewPasswordFragment : Fragment() {

    private var _binding: FragmentCreateNewPasswordBinding? = null
    private val binding get() = _binding!!

    private val viewModel : LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateNewPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvCreateButton.setOnClickListener {

            if(binding.etPassword.text.toString() != binding.etConfirmPassword.text.toString()){
                binding.tvPasswordWarning.apply {
                    text = getString(R.string.password_not_match)
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
                }
            }
            else if(binding.etPassword.text.toString().length < 6){
                binding.tvPasswordWarning.apply {
                    text = getString(R.string.password_warning)
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
                }
            }
            else{
                binding.tvPasswordWarning.visibility = View.INVISIBLE
                viewModel.updatePassword(binding.etPassword.text.toString())
                context?.let { context ->
                    DialogCongratulations(
                        context,
                        context.getString(R.string.successful_reset_password)
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