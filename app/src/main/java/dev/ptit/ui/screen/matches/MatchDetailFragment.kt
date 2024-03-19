package dev.ptit.ui.screen.matches

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import dev.ptit.R
import dev.ptit.databinding.FragmentMatchDetailBinding
import dev.ptit.ui.adapter.match.MatchDetailAdapter

class MatchDetailFragment : Fragment() {

    private var _binding: FragmentMatchDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMatchDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vpMatchDetails.adapter = MatchDetailAdapter()

        TabLayoutMediator(binding.tlMatchDetails, binding.vpMatchDetails) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.timeline)
                1 -> getString(R.string.stats)
                2 -> getString(R.string.lineups)
                3 -> getString(R.string.news)
                4 -> getString(R.string.comment)
                else -> ""
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}