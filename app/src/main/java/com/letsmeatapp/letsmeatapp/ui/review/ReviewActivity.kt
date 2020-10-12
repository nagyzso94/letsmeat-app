package com.letsmeatapp.letsmeatapp.ui.review

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.letsmeatapp.letsmeatapp.R

class ReviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.review_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ReviewFragment.newInstance())
                .commitNow()
        }
    }
}