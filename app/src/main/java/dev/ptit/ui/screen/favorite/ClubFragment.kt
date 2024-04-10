package dev.ptit.ui.screen.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import dev.ptit.R
import dev.ptit.databinding.FragmentClubBinding
import dev.ptit.ui.adapter.club.ClubVPAdapter


class ClubFragment : Fragment() {
    private var _binding: FragmentClubBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClubBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vpClubInformation.adapter = ClubVPAdapter()

        TabLayoutMediator(binding.tlClubInformation, binding.vpClubInformation) { tab, position ->
            tab.text = when (position) {
                0 -> "Match"
                1 -> "Table"
                2 -> "Player"
                3 -> "Information"
                else -> ""
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}