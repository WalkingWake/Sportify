package dev.ptit.ui.adapter.match

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import dev.ptit.R
import dev.ptit.databinding.ItemLineupsBinding


class LineupsLVAdapter(
    context: Context,
    private val lineupsList: List<Pair<Pair<Int, String>?, Pair<Int, String>?>>,
) : ArrayAdapter<Pair<Pair<Int, String>?, Pair<Int, String>?>>(context, R.layout.item_lineups, lineupsList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_lineups, parent, false)

        val binding = ItemLineupsBinding.bind(view)
        lineupsList[position].first?.let {
            binding.tvLineupId1.text = it.first.toString()
            binding.tvLineupName1.text = it.second
        }
        lineupsList[position].second?.let {
            binding.tvLineupId2.text = it.first.toString()
            binding.tvLineupName2.text = it.second
        }
        return view
    }


}