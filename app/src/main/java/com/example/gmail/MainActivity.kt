package com.example.gmail

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Sample email data
        val emails = listOf(
            Email("Alice", "Meeting Tomorrow", "Don't forget our meeting...", "12:30 PM"),
            Email("Bob", "Project Update", "Here is the latest update...", "10:15 AM"),
            Email("Charlie", "Lunch Plans?", "Are we still on for lunch?", "9:45 AM")
        )

        // Find ListView and set Adapter
        val emailListView = findViewById<ListView>(R.id.emailListView)
        val adapter = EmailAdapter(this, emails)
        emailListView.adapter = adapter
    }
}
