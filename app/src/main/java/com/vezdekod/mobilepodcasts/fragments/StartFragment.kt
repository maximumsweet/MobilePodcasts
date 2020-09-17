package com.vezdekod.mobilepodcasts.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import com.vezdekod.mobilepodcasts.OnFragmentInteractionListener
import com.vezdekod.mobilepodcasts.R
import com.vezdekod.mobilepodcasts.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private lateinit var viewBinding: FragmentStartBinding
    private var onFragmentInteractionListener: OnFragmentInteractionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onFragmentInteractionListener = context as OnFragmentInteractionListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentStartBinding.inflate(inflater, container, false).also {
        viewBinding = it
    }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.closeMessageButton.setOnClickListener {
            Toast.makeText(context, "Заглушка", Toast.LENGTH_SHORT).show()
        }
        viewBinding.createPodcastButton.setOnClickListener {
            val navDirections: NavDirections =
                StartFragmentDirections.actionNavFirstToNavMainPodcastData()
            onFragmentInteractionListener?.onFragmentInteraction(navDirections)
        }
    }
}