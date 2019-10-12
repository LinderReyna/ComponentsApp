package com.example.jossemarapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jossemarapp.R
import com.example.jossemarapp.model.Post
import kotlinx.android.synthetic.main.fragment_post.view.*

class PostAdapter(private val mListener: OnListFragmentInteractionListener): RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    private var data: List<Post> = ArrayList()

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Post
            mListener.onListFragmentInteraction(item)
        }
    }

    fun setData(newData: List<Post>){
        data = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.mId.text = item.id.toString()
        holder.mTitle.text = item.title

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mId: TextView = mView.post_id
        val mTitle: TextView = mView.post_title
    }

}