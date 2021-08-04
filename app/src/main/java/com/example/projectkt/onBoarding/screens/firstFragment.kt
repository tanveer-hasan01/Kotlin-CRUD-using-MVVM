package com.example.projectkt.onBoarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.projectkt.R
import com.example.projectkt.databinding.FragmentFirstBinding
import kotlinx.android.synthetic.main.fragment_view_pager.*


class firstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewPager =  activity?.findViewById<ViewPager2>(R.id.viewPager)
        binding.next.setOnClickListener(View.OnClickListener {

           viewPager?.currentItem = 1
        })


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}