package dev.ptit.ui.screen.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import dev.ptit.R
import dev.ptit.databinding.FragmentNewsDetailsBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsDetailsFragment : Fragment() {

    private var _binding: FragmentNewsDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.selectedNews.collect{
                binding.tvNewsDate.text = it.date
                binding.tvNewsTitle.text = it.title
                binding.tvNewsContent.text = it.description.replace("\\n", "\n")
                Glide.with(this@NewsDetailsFragment).load(it.image).into(binding.ivNewsImage)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}