package com.vezdekod.mobilepodcasts.ui.audioedit

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vezdekod.mobilepodcasts.OnFragmentInteractionListener
import com.vezdekod.mobilepodcasts.R
import com.vezdekod.mobilepodcasts.databinding.FragmentAudioEditingBinding
import com.vezdekod.mobilepodcasts.model.PodcastViewModel
import com.vezdekod.mobilepodcasts.ui.data.TimecodeDataSource

class AudioEditingFragment : Fragment() {
    private var timecodesRecycler: RecyclerView? = null
    private lateinit var viewBinding: FragmentAudioEditingBinding

    private var onFragmentInteractionListener: OnFragmentInteractionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onFragmentInteractionListener = context as? OnFragmentInteractionListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentAudioEditingBinding.inflate(inflater, container, false).also {
        viewBinding = it
    }.root

    private val timecodeViewModel: AudioEditingViewModel by viewModels()
    private val globalViewModel: PodcastViewModel by viewModels({ requireActivity() })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.addTimecodeButton.setOnClickListener {
            timecodeViewModel.addTimecode()
        }

        viewBinding.fragmentAudioEditingButtonFinishEditing.setOnClickListener {
            val navDirections: NavDirections =
                AudioEditingFragmentDirections.actionNavAudioEditingToNavPodcastPreview()
            onFragmentInteractionListener?.onFragmentInteraction(navDirections)
        }

        initList(view)

        timecodeViewModel.timecodesLiveData.observe(viewLifecycleOwner, {
            updateList(it)
            globalViewModel.timecodes.apply {
                clear()
                addAll(it.timecodes)
            }
        })
    }

    private fun initList(root: View) {
        timecodesRecycler = root.findViewById(R.id.timecode_list_recycler)
        updateList(TimecodeDataSource())
    }

    private fun updateList(dataSource: TimecodeDataSource) {
        val linearLayoutManager = LinearLayoutManager(requireContext())
        val adapter = TimecodeAdapter(dataSource)
        timecodesRecycler?.layoutManager = linearLayoutManager
        timecodesRecycler?.adapter = adapter
    }
}