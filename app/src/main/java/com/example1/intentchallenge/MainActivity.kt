package com.example1.intentchallenge

import android.Manifest.permission.WRITE_CONTACTS
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.google.android.material.tabs.TabLayout
import java.util.jar.Manifest

lateinit var message: ImageView
lateinit var place: ImageView
lateinit var github: ImageView
lateinit var contact: ImageView
private const val SELECT_PHONE_NUMBER = 111

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        message = findViewById(R.id.message)
        message.setOnClickListener() {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, arrayOf("ruqaiah.saif@gmail.com"))
                putExtra(Intent.EXTRA_SUBJECT, "Email Subject")
                putExtra(Intent.EXTRA_TEXT, "Email Message text")
            }
            if (sendIntent.resolveActivity(packageManager) != null) {
                startActivity(sendIntent)
            }
        }
        place = findViewById(R.id.place)
        place.setOnClickListener() {

            val data = Uri.parse("geo:33.5,-144.8")
            val mapIntent = Intent(Intent.ACTION_VIEW, data)

            startActivity(mapIntent)
            if (mapIntent.resolveActivity(packageManager) != null) {
                startActivity(mapIntent)
            }
        }
        github = findViewById(R.id.github)
        github.setOnClickListener() {
            val data = Uri.parse("https://github.com/RuqaiahSaif")
            val webIntent = Intent(Intent.ACTION_VIEW, data)


            if (webIntent.resolveActivity(packageManager) != null) {
                startActivity(webIntent)
            }
        }
        contact = findViewById(R.id.contact)
    }
}
