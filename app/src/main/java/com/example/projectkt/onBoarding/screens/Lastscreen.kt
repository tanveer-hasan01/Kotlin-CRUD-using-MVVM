package com.example.projectkt.onBoarding.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectkt.activity.ListActivity
import com.example.projectkt.databinding.FragmentLastscreenBinding
import kotlinx.android.synthetic.main.fragment_lastscreen.*
import kotlinx.android.synthetic.main.fragment_view_pager.*


class Lastscreen : Fragment() {


    private var _binding: FragmentLastscreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       // _binding = FragmentLastscreenBinding.bind().inflate(inflater, container, false)
        _binding = FragmentLastscreenBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.finish.setOnClickListener(View.OnClickListener {

            val intent = Intent(getActivity(), ListActivity::class.java)
            startActivity(intent)
            onBoardingFinished()
            activity?.finish()

        })


        return view
    }


    private fun onBoardingFinished(){
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }

}