package com.example.jossemarapp.ui.detail

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.jossemarapp.R
import com.example.jossemarapp.ui.PageViewModel
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.content_post.*

class PostActivity : AppCompatActivity() {

    private lateinit var pageViewModel: PageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            setIndex(intent.getIntExtra("tab",1))
        }
        pageViewModel.getPost(intent.getIntExtra("id",0)).observe(this, Observer {
            post_id.text = it.id.toString()
            post_title.text = it.title
            post_body.text = it.body
            post_user_id.text = it.userId.toString()
            fab.setOnClickListener { view ->
                pageViewModel.setPost(it)?.observe(this, Observer {
                    Toast.makeText(this, "Guardado!", Toast.LENGTH_SHORT).show()
                })
            }
        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
