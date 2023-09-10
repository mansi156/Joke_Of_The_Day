package com.mansi.jokesapplication.presentation.jokes

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.mansi.jokesapplication.R
import com.mansi.jokesapplication.databinding.ActivityJokesBinding
import com.mansi.jokesapplication.utils.isNetworkAvailable
import org.koin.androidx.viewmodel.ext.android.viewModel

class JokesActivity : AppCompatActivity() {

    private lateinit var activityJokesBinding: ActivityJokesBinding
    private var mAdapter: JokesAdapter? = JokesAdapter()
    private val jokeViewModel: JokesViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityJokesBinding = ActivityJokesBinding.inflate(layoutInflater)
        setContentView(activityJokesBinding.root)
        activityJokesBinding.jokesRecyclerView.adapter = mAdapter


        with(jokeViewModel) {

            jokeList.observe(this@JokesActivity) {
                activityJokesBinding.jokesProgressBar.visibility = GONE
                mAdapter?.mJokeList = it
            }

            messageData.observe(this@JokesActivity) {
                Toast.makeText(this@JokesActivity, it, LENGTH_LONG).show()
            }

            showProgressBar.observe(this@JokesActivity) { isVisible ->
                activityJokesBinding.jokesProgressBar.visibility = if (isVisible) VISIBLE else GONE
            }
        }
    }


    override fun onDestroy() {
        mAdapter = null
        super.onDestroy()
    }

    companion object {
        private val TAG = JokesActivity::class.java.name
    }
}
