package dev.ptit.ui.screen.matches

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.ptit.R
import dev.ptit.data.league.LeagueRepository
import dev.ptit.data.match.MatchRepository
import dev.ptit.databinding.FragmentMatchesBinding
import dev.ptit.ui.adapter.match.MatchAdapter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MatchesFragment : Fragment() {

    private var _binding: FragmentMatchesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MatchViewModel by viewModels()
    private var matchAdapter: MatchAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMatchesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        matchAdapter = MatchAdapter({
            viewModel.onLeagueClick(it.id)
        }, {
            findNavController().navigate(R.id.action_matchesFragment_to_matchDetailFragment)
        }, {
            viewModel.onUpcomingClick(it)
        })

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isUpcomingState.collect {
                matchAdapter?.setUpcomingState(it)
                if (it) {
                    matchAdapter?.setMatchList(MatchRepository().getUpcomingMatches())
                } else {
                    matchAdapter?.setMatchList(MatchRepository().getPastMatches())
                }
            }
        }

        binding.rvContent.adapter = matchAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.leagues.collect {
                Log.d("MatchesFragment", "onViewCreated: $it")
                matchAdapter?.setLeagueList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}