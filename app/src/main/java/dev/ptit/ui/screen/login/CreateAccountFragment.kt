package dev.ptit.ui.screen.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dev.ptit.R
import dev.ptit.databinding.FragmentCreateAccountBinding
import dev.ptit.setup.keys.Keys
import dev.ptit.ui.dialog.DialogCongratulations
import java.security.Key


class CreateAccountFragment : Fragment() {

    private var _binding: FragmentCreateAccountBinding? = null
    private val binding get() = _binding!!

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
            val bundle = Bundle()
            bundle.putString(Keys.NAVIGATE_FROM, Keys.CREATE_ACCOUNT_FRAGMENT)
            findNavController().navigate(R.id.action_createAccountFragment_to_verifyFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}