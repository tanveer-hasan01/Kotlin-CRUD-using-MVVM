package com.example.projectkt.onBoarding.screens

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectkt.MySharedPreference
import com.example.projectkt.activity.ListActivity
import com.example.projectkt.databinding.FragmentLastscreenBinding
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_lastscreen.*
import kotlinx.android.synthetic.main.fragment_view_pager.*
import javax.inject.Inject


class Lastscreen : Fragment() {


    @Inject
    lateinit var sharedPreferences: MySharedPreference
    private var _binding: FragmentLastscreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentLastscreenBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.finish.setOnClickListener(View.OnClickListener {

            val intent = Intent(getActivity(), ListActivity::class.java)
            startActivity(intent)

            sharedPreferences.setGetStarted(true)

            activity?.finish()

        })


        return view
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }


}