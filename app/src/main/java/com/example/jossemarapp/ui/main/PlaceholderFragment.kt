package com.example.jossemarapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jossemarapp.R
import com.example.jossemarapp.model.Post
import kotlinx.android.synthetic.main.fragment_main.view.*
import android.content.Intent
import com.example.jossemarapp.ui.PageViewModel
import com.example.jossemarapp.ui.detail.PostActivity

class PlaceholderFragment : Fragment(), OnListFragmentInteractionListener {

    private lateinit var pageViewModel: PageViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        root.recycler_posts.layoutManager = LinearLayoutManager(context)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
        initAdapter(root)
        return root
    }

    private fun initAdapter(root: View) {
        val adapter = PostAdapter(this)
        root.recycler_posts.adapter = adapter
        pageViewModel.getPosts().observe(this, Observer {
            adapter.setData(it)
        })
    }

    override fun onListFragmentInteraction(item: Post) {
        val intent = Intent(activity, PostActivity::class.java)
        intent.putExtra("tab", arguments?.getInt(ARG_SECTION_NUMBER))
        intent.putExtra("id", item.id)
        startActivity(intent)
    }

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}