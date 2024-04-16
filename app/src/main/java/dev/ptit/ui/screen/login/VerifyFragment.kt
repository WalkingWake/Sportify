package dev.ptit.ui.screen.login

import android.app.Activity
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.ptit.R
import dev.ptit.databinding.FragmentVerifyBinding
import dev.ptit.setup.keys.Keys
import dev.ptit.ui.dialog.DialogCongratulations


@AndroidEntryPoint
class VerifyFragment : Fragment() {

    private var _binding: FragmentVerifyBinding? = null
    private val binding get() = _binding!!

    private var navigateFrom: String? = null

    private val viewModel: LoginViewModel by activityViewModels()

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
            validateCode()

        }

        binding.etVerify1.requestFocus()
        moveFocus(binding.etVerify1, null, binding.etVerify2)
        moveFocus(binding.etVerify2, binding.etVerify1, binding.etVerify3)
        moveFocus(binding.etVerify3, binding.etVerify2, binding.etVerify4)
        moveFocus(binding.etVerify4, binding.etVerify3, null)
    }

    private fun moveFocus(
        currentEditText: EditText,
        previousEditText: EditText?,
        nextEditText: EditText?
    ) {
        currentEditText.addTextChangedListener {
            if (currentEditText.text.toString().isNotEmpty()) {
                if (nextEditText == null) {
                    currentEditText.clearFocus()
                    hideKeyboard()
                    validateCode()
                } else {
                    nextEditText.requestFocus()
                }
            }
        }

        currentEditText.setOnKeyListener { _, _, event ->
            if (event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_DEL && currentEditText.text.toString()
                    .isEmpty()
            ) {
                previousEditText?.requestFocus()
                previousEditText?.setText("")
            }
            false
        }
    }

    private fun validateCode() {
        hideKeyboard()
        if (viewModel.verifyCode(
                binding.etVerify1.text.toString() +
                        binding.etVerify2.text.toString() +
                        binding.etVerify3.text.toString() +
                        binding.etVerify4.text.toString()
            )
        ) {
            binding.tvVerifyDescription.visibility = View.INVISIBLE

            if (navigateFrom == Keys.FORGOT_PASSWORD_FRAGMENT) {
                findNavController().navigate(R.id.action_verifyFragment_to_createNewPasswordFragment)
            } else if (navigateFrom == Keys.CREATE_ACCOUNT_FRAGMENT) {
                viewModel.registerUser()
                context?.let { context ->
                    DialogCongratulations(
                        context,
                        context.getString(R.string.successful_registration)
                    ) {
                        findNavController().popBackStack(R.id.signInFragment, false)
                    }.show()
                }
            }

        } else {
            binding.tvVerifyDescription.apply {
                text = getString(R.string.wrong_code)
                setTextColor(ContextCompat.getColor(context, R.color.red))
            }
            clearEditText(
                binding.etVerify1,
                binding.etVerify2,
                binding.etVerify3,
                binding.etVerify4
            )
        }
    }

    private fun clearEditText(vararg editTexts: EditText) {
        for (editText in editTexts) {
            editText.setText("")
            editText.clearFocus()
        }
    }

    @Suppress("DEPRECATION")
    private fun hideKeyboard() {
        val imm =
            activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm!!.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}