package com.example.gmail

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat

class EmailAdapter(private val context: Context, private val emails: List<Email>) : ArrayAdapter<Email>(context, 0, emails) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val email = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.email_item, parent, false)

        // Find views
        val avatarTextView = view.findViewById<TextView>(R.id.avatarTextView)
        val senderTextView = view.findViewById<TextView>(R.id.senderTextView)
        val titleTextView = view.findViewById<TextView>(R.id.titleTextView)
        val contentTextView = view.findViewById<TextView>(R.id.contentTextView)
        val timeTextView = view.findViewById<TextView>(R.id.timeTextView)
        val favoriteButton = view.findViewById<ImageButton>(R.id.favoriteButton)

        // Set content
        avatarTextView.text = email?.senderName?.firstOrNull()?.toString()
        senderTextView.text = email?.senderName
        titleTextView.text = email?.title
        contentTextView.text = email?.content
        timeTextView.text = email?.time

        // Set color for avatar background based on sender's initial
        avatarTextView.background.setColorFilter(getAvatarColor(email?.senderName), PorterDuff.Mode.SRC_IN)

        // Set color for title and content text
        titleTextView.setTextColor(ContextCompat.getColor(context, R.color.colorTitle))
        contentTextView.setTextColor(ContextCompat.getColor(context, R.color.colorContent))

        // Set favorite button icon and color based on favorite status
        favoriteButton.setImageResource(if (email?.isFavorite == true) R.drawable.ic_star else R.drawable.ic_star_border)
        favoriteButton.setColorFilter(
            if (email?.isFavorite == true) Color.parseColor("#FFD700") else Color.GRAY,
            PorterDuff.Mode.SRC_IN
        )

        // Handle favorite button click
        favoriteButton.setOnClickListener {
            email?.isFavorite = !(email?.isFavorite ?: false)
            notifyDataSetChanged()
        }

        return view
    }

    // Function to determine avatar color based on sender's initial
    private fun getAvatarColor(name: String?): Int {
        return when (name?.firstOrNull()?.uppercaseChar()) {
            'A', 'B', 'C' -> Color.parseColor("#FFB74D") // Orange
            'D', 'E', 'F' -> Color.parseColor("#4DB6AC") // Teal
            'G', 'H', 'I' -> Color.parseColor("#7986CB") // Indigo
            'J', 'K', 'L' -> Color.parseColor("#BA68C8") // Purple
            'M', 'N', 'O' -> Color.parseColor("#E57373") // Red
            'P', 'Q', 'R' -> Color.parseColor("#AED581") // Green
            else -> Color.parseColor("#90A4AE") // Gray for other cases
        }
    }
}
