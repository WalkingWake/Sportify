package dev.ptit.ui.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.ptit.data.league.LeagueModel
import dev.ptit.data.league.LeagueRepository
import dev.ptit.data.match.MatchModel
import dev.ptit.data.match.MatchRepository
import dev.ptit.data.news.NewsModel
import dev.ptit.data.news.NewsRepository
import dev.ptit.databinding.FragmentHomeBinding
import dev.ptit.ui.adapter.home.HomeAdapter


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvContent.adapter = HomeAdapter().apply {
            setNewsList(
                NewsRepository().getAllNews()
            )
            setLeagueList(
                LeagueRepository().getAllLeagues()
            )
            setMatchList(
                MatchRepository().getAllMatches()
            )
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}