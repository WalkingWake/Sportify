package dev.ptit.ui.screen.favorite

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
import dev.ptit.databinding.FragmentFavoriteBinding
import dev.ptit.ui.adapter.club.ClubAdapter
import dev.ptit.ui.adapter.match.LeagueAdapter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private var leagueAdapter: LeagueAdapter? = null
    private var clubAdapter: ClubAdapter? = null
    private val viewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        leagueAdapter = LeagueAdapter {
            viewModel.setSelectedLeague(it)
        }
        clubAdapter = ClubAdapter {
            findNavController().navigate(R.id.action_favoriteFragment_to_clubFragment)
        }
        binding.rvLeagues.adapter = leagueAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.leagues.collect {
                leagueAdapter?.setList(it)
            }
        }

        binding.rvClub.adapter = clubAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.teams.collect {
                clubAdapter?.setList(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.selectedLeague.collect {
                leagueAdapter?.setSelectedLeague(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}