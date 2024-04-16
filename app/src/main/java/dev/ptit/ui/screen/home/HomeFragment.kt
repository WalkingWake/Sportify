package dev.ptit.ui.screen.home

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
import dev.ptit.databinding.FragmentHomeBinding
import dev.ptit.setup.extension.formattedDateToLong
import dev.ptit.ui.adapter.home.HomeAdapter
import kotlinx.coroutines.launch
import kotlin.math.min


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var homeAdapter: HomeAdapter? = null
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView()
        setUpListener()
        setUpData()


    }

    private fun setUpView() {
        homeAdapter = HomeAdapter(
            onViewAllNewsClick = {
                findNavController().navigate(R.id.action_homeFragment_to_newsFragment)
            },
            onViewAllLeaguesClick = {
                Log.d("HomeFragment", "onViewAllLeaguesClick")
            },
            onViewAllMatchesClick = {
                findNavController().navigate(R.id.action_homeFragment_to_matchesFragment)
            }
        )
        binding.rvContent.adapter = homeAdapter
    }

    private fun setUpListener() {
    }

    private fun setUpData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.news.collect {
                homeAdapter?.setNewsList(it.subList(0, min(10, it.size)))
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.leagues.collect {
                homeAdapter?.setLeagueList(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.matches.collect { list ->
                homeAdapter?.setMatchList(
                    list
                        .filter { match ->
                            val currentTime = System.currentTimeMillis()
                            val matchTime = match.startTime.formattedDateToLong()
                            matchTime > currentTime
                        }
                        .sortedBy {
                            it.startTime.formattedDateToLong()
                        }
                )
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.teams.collect {
                homeAdapter?.setTeamList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}