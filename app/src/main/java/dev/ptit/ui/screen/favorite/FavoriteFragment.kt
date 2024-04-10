package dev.ptit.ui.screen.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dev.ptit.R
import dev.ptit.data.league.LeagueRepository
import dev.ptit.databinding.FragmentFavoriteBinding
import dev.ptit.ui.adapter.club.ClubAdapter
import dev.ptit.ui.adapter.match.LeagueAdapter


class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvLeagues.adapter = LeagueAdapter {}.apply {
            setList(LeagueRepository().getAllLeagues())
        }

        binding.rvClub.adapter = ClubAdapter{
            findNavController().navigate(R.id.action_favoriteFragment_to_clubFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}