package com.vezdekod.mobilepodcasts.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import com.google.android.material.snackbar.Snackbar
import com.vezdekod.mobilepodcasts.OnFragmentInteractionListener
import com.vezdekod.mobilepodcasts.databinding.FragmentLastBinding

class LastFragment : Fragment() {

    private lateinit var viewBinding: FragmentLastBinding

    private var onFragmentInteractionListener: OnFragmentInteractionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onFragmentInteractionListener = context as? OnFragmentInteractionListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentLastBinding.inflate(inflater, container, false).also {
        viewBinding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.closeMessageButton.setOnClickListener {
            //TODO return to first screen
            val navDirections: NavDirections =
                LastFragmentDirections.actionNavEndToNavFirst()
            onFragmentInteractionListener?.onFragmentInteraction(navDirections)
        }

        viewBinding.sharePodcastButton.setOnClickListener {
//            Snackbar.make(it, "Заглушка", Snackbar.LENGTH_SHORT)
            Toast.makeText(context, "Заглушка", Toast.LENGTH_SHORT).show()
        }
    }

}