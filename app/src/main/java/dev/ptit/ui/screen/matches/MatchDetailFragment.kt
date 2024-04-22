package dev.ptit.ui.screen.matches

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import dev.ptit.R
import dev.ptit.data.comments.CommentEntity
import dev.ptit.databinding.FragmentMatchDetailBinding
import dev.ptit.setup.extension.formattedDateToLong
import dev.ptit.setup.extension.longToFormattedDate
import dev.ptit.ui.adapter.match.MatchDetailAdapter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MatchDetailFragment : Fragment() {

    private var _binding: FragmentMatchDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MatchViewModel by activityViewModels()
    private val matchDetailAdapter = MatchDetailAdapter {
        viewModel.addComment(
            CommentEntity(
                comment = it,
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMatchDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vpMatchDetails.adapter = matchDetailAdapter

        TabLayoutMediator(binding.tlMatchDetails, binding.vpMatchDetails) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.timeline)
                1 -> getString(R.string.stats)
                2 -> getString(R.string.news)
                3 -> getString(R.string.comment)
                else -> ""
            }
        }.attach()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiComments.collect {
                matchDetailAdapter.setComments(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.currentMatchData.collect {
                matchDetailAdapter.setMatchData(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.team1.collect {
                Glide.with(this@MatchDetailFragment)
                    .load(it.logo)
                    .into(binding.ivClub1Image)
                binding.tvClub1Name.text = it.name
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.team2.collect {
                Glide.with(this@MatchDetailFragment)
                    .load(it.logo)
                    .into(binding.ivClub2Image)
                binding.tvClub2Name.text = it.name
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.currentMatch.collect {
                binding.tvMatchTime.text = it.startTime.formattedDateToLong().longToFormattedDate("HH:mm, EE dd/MM")
                binding.tvMatchScore.text = "${it.team1Score} - ${it.team2Score}"
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.timelines.collect {
                matchDetailAdapter.setTimeline(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiNews.collect {
                matchDetailAdapter.setNews(it)
            }
        }

        matchDetailAdapter.setUser(viewModel.getAllUsers())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}