package com.example1.intentchallenge
import android.content.Context
import android.Manifest.permission.READ_CONTACTS
import android.Manifest.permission.WRITE_CONTACTS
import android.content.ContentResolver;
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
      //////////////// for check contact/////////////////
        fun contactExists(context: Context, number: String): Boolean {
          if (number != null) {
              val cr: ContentResolver? = context.getContentResolver()
              val contatUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
              val queryField = arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER)

              var cursor = cr?.query(contatUri, queryField, null, null, null)

              while (cursor?.moveToNext() != null) {
                  var contactNumber: String =
                      (cursor?.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)).toString()

                  if (number.equals(contactNumber)) {
                      return true
                  }
              }
              return false
          } else {
              return false
          }
      }





        contact = findViewById(R.id.contact)
        contact.setOnClickListener() {
            val name = "roqia saif"
            val phone = "770695605"

            if (ContextCompat.checkSelfPermission(this@MainActivity,android.Manifest.permission.WRITE_CONTACTS) !==  PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this@MainActivity,android.Manifest.permission.WRITE_CONTACTS)) {
                    ActivityCompat.requestPermissions(this@MainActivity, arrayOf(android.Manifest.permission.WRITE_CONTACTS), 1)
                } else {
                    ActivityCompat.requestPermissions(this@MainActivity,
                        arrayOf(android.Manifest.permission.WRITE_CONTACTS), 1)
                }
            }



            if(contactExists(this,phone)){

                val callIntent = Intent().apply {
                    action =Intent.ACTION_DIAL
                    data = Uri.parse("tel:$phone")
                }
                if (callIntent.resolveActivity(packageManager) != null) {
                    startActivity(callIntent)
                }

            }

            val cotactIntent = Intent(ContactsContract.Intents.Insert.ACTION)
            cotactIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE)
            cotactIntent.putExtra(ContactsContract.Intents.Insert.NAME, name)
            cotactIntent.putExtra(ContactsContract.Intents.Insert.PHONE, phone)
            if (cotactIntent.resolveActivity(packageManager) != null) {
                startActivity(cotactIntent)
            }
        }

            }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if ((ContextCompat.checkSelfPermission(
                            this@MainActivity,
                            android.Manifest.permission.WRITE_CONTACTS
                        ) ===
                                PackageManager.PERMISSION_GRANTED)
                    ) {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }
        }






