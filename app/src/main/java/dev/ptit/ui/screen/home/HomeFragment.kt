package dev.ptit.ui.screen.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.ptit.R
import dev.ptit.databinding.FragmentHomeBinding
import dev.ptit.setup.extension.formattedDateToLong
import dev.ptit.ui.adapter.home.HomeAdapter
import dev.ptit.ui.screen.matches.MatchViewModel
import dev.ptit.ui.screen.news.NewsViewModel
import kotlinx.coroutines.launch
import kotlin.math.min


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var homeAdapter: HomeAdapter? = null
    private val viewModel: HomeViewModel by viewModels()

    private val newsViewModel: NewsViewModel by activityViewModels()
    private val matchViewModel: MatchViewModel by activityViewModels()

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
                findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
            },
            onViewAllMatchesClick = {
                findNavController().navigate(R.id.action_homeFragment_to_matchesFragment)
            },
            onNewsItemClick = {
                newsViewModel.setSelectedNews(it)
                findNavController().navigate(R.id.action_homeFragment_to_matchesFragment)
            },
            onLeagueClick = {
                matchViewModel.onLeagueClick(it.remoteId)
                findNavController().navigate(R.id.action_homeFragment_to_matchesFragment)
            }
        )
        binding.rvContent.adapter = homeAdapter
    }

    private fun setUpListener() {

        //on text change for edittext
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d("TAG", "beforeTextChanged: $s")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("TAG", "onTextChanged: $s")
            }

            override fun afterTextChanged(s: Editable?) {
                Log.d("TAG", "afterTextChanged: $s")
                if (s.isNullOrEmpty()) {
                    homeAdapter?.setIsSearching(false)
                } else {
                    homeAdapter?.setIsSearching(true)
                    viewModel.searchMatch(s.toString())
//                    homeAdapter?.setSearchText(s.toString())
                }
            }
        })
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
            viewModel.uiMatches.collect { list ->
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