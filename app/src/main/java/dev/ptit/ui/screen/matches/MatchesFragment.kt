package dev.ptit.ui.screen.matches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.ptit.R
import dev.ptit.databinding.FragmentMatchesBinding
import dev.ptit.setup.extension.formattedDateToLong
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
            viewModel.onLeagueClick(it)
        }, {
            findNavController().navigate(R.id.action_matchesFragment_to_matchDetailFragment)
        }, {
            viewModel.onUpcomingClick(it)
        })

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isUpcomingState.collect {
                matchAdapter?.setUpcomingState(it)
//                matchAdapter?.setMatchList(viewModel.uiMatches.value)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiMatches.collect {
                matchAdapter?.setMatchList(viewModel.uiMatches.value)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.selectedLeagueId.collect {
                matchAdapter?.setSelectedLeague(it)
            }
        }

        binding.rvContent.adapter = matchAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.leagues.collect {
                matchAdapter?.setLeagueList(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.teams.collect {
                matchAdapter?.setTeamList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}