package com.example.bottomnavigationbar

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LifeCycleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LifeCycleFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    @Deprecated("Deprecated in Java")
    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
        Log.e("LEfyCylce_Frag ", "onAttachFragment")

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("LEfyCylce_Frag ", "onAttach")

    }

    @Deprecated("Deprecated in Java")
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        Log.e("LEfyCylce_Frag ", "onAttach Activity")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        Log.e("LEfyCylce_Frag ", "onCreate")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.e("LEfyCylce_Frag ", "onCreateView")

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_life_cycle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("LEfyCylce_Frag ", "onViewCreated")

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("LEfyCylce_Frag ", "onActivityCreated")

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.e("LEfyCylce_Frag ", "onViewStateRestored")

    }

    override fun onStart() {
        super.onStart()
        Log.e("LEfyCylce_Frag ", "onStart")

    }

    override fun onResume() {
        super.onResume()
        Log.e("LEfyCylce_Frag ", "onResume")

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.e("LEfyCylce_Frag ", "onSaveInstanceState")

    }

    override fun onPause() {
        super.onPause()
        Log.e("LEfyCylce_Frag ", "onPause")

    }

    override fun onStop() {
        super.onStop()
        Log.e("LEfyCylce_Frag ", "onStop")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("LEfyCylce_Frag ", "onDestroyView")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("LEfyCylce_Frag ", "onDestroy")

    }

    override fun onDetach() {
        super.onDetach()
        Log.e("LEfyCylce_Frag ", "onDetach")

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LifeCycleFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LifeCycleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}