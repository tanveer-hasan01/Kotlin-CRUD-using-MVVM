package com.example.projectkt.onBoarding.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.projectkt.MySharedPreference
import com.example.projectkt.R
import com.example.projectkt.activity.ListActivity
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class SplashFragment : Fragment() {


    @Inject
    lateinit var sharedPreference: MySharedPreference




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


      //  sharedPreference = MySharedPreference.getPreferencesInstance(requireActivity().applicationContext)
        Handler().postDelayed({
            if (sharedPreference.getGetStarted()==true) {

                val intent = Intent(getActivity(), ListActivity::class.java)
                startActivity(intent)
                activity?.finish()

            } else {
                findNavController().navigate(R.id.action_splashFragment2_to_viewPagerFragment)
            }
        }, 2000)


        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }


}