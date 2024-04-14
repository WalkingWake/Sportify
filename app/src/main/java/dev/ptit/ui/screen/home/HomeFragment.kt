package dev.ptit.ui.screen.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dev.ptit.data.league.LeagueRepository
import dev.ptit.data.match.MatchRepository
import dev.ptit.databinding.FragmentHomeBinding
import dev.ptit.ui.adapter.home.HomeAdapter
import kotlinx.coroutines.launch
import kotlin.math.min


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeAdapter = HomeAdapter()
    private val viewModel : HomeViewModel  by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvContent.adapter = homeAdapter.apply {
//            setNewsList(
//                viewModel.getAllNews()
//            )
            setLeagueList(
                LeagueRepository().getAllLeagues()
            )
            setMatchList(
                MatchRepository().getAllMatches()
            )
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.news.collect{
                homeAdapter.setNewsList(it.subList(0, min(10, it.size)))
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}