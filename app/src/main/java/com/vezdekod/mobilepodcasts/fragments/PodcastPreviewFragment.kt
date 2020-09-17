package com.vezdekod.mobilepodcasts.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vezdekod.mobilepodcasts.OnFragmentInteractionListener
import com.vezdekod.mobilepodcasts.databinding.FragmentPodcastPreviewBinding
import com.vezdekod.mobilepodcasts.databinding.PreviewTimecodeItemBinding
import com.vezdekod.mobilepodcasts.model.PodcastViewModel
import java.util.*

class PodcastPreviewFragment : Fragment() {

    private lateinit var viewBinding: FragmentPodcastPreviewBinding
    private val viewModel: PodcastViewModel by viewModels({requireActivity()})

    private var onFragmentInteractionListener: OnFragmentInteractionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onFragmentInteractionListener = context as? OnFragmentInteractionListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentPodcastPreviewBinding.inflate(inflater, container, false).also {
        viewBinding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.nextButton.setOnClickListener {
            val navDirections: NavDirections =
                PodcastPreviewFragmentDirections.actionNavPodcastPreviewToNavEnd()
            onFragmentInteractionListener?.onFragmentInteraction(navDirections)
        }
        Objects.requireNonNull((requireActivity() as AppCompatActivity).supportActionBar)
            ?.setDisplayHomeAsUpEnabled(true)
        Objects.requireNonNull((requireActivity() as AppCompatActivity).supportActionBar)
            ?.setTitle("Редактирование")

        val adapter = object : RecyclerView.Adapter<TimecodeViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimecodeViewHolder {
                return TimecodeViewHolder(
                    PreviewTimecodeItemBinding.inflate(
                    LayoutInflater.from(requireContext()), parent, false))
            }

            override fun onBindViewHolder(holder: TimecodeViewHolder, position: Int) {
                holder.viewBinding.timecodeTimeTv.text = viewModel.timecodes[position].time
                holder.viewBinding.timecodeDescriptionTv.text = viewModel.timecodes[position].name
            }

            override fun getItemCount(): Int {
                return viewModel.timecodes.size
            }

        }

        viewBinding.timecodeListRecycler.adapter = adapter
        viewBinding.timecodeListRecycler.layoutManager = LinearLayoutManager(requireContext())
    }

    private inner class TimecodeViewHolder(val viewBinding: PreviewTimecodeItemBinding) : RecyclerView.ViewHolder(viewBinding.root)
}