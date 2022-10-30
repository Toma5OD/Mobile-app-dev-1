package org.wit.placemark.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar

import org.wit.placemark.R

import org.wit.placemark.databinding.ActivityLoginBinding
import org.wit.placemark.main.MainApp
import org.wit.placemark.models.UserModel
import timber.log.Timber


class LoginActivity : AppCompatActivity() {
    lateinit var app: MainApp

    private lateinit var binding: ActivityLoginBinding

    private lateinit var placemarkIntentLauncher: 
ActivityResultLauncher<Intent>
    private lateinit var signUpIntentLauncher: 
ActivityResultLauncher<Intent>




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        app = application as MainApp


        //create a temporary user
       if (app.users.findAll().firstOrNull { it.email == 
"james@geraghty.ie" } == null)
            app.users.create(UserModel("homer@simpson.com", "Homer",
"Simpson", "secret"))

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signIn.title = title
        setSupportActionBar(binding.signIn)

        binding.btnLogin.setOnClickListener() {
            var email = binding.email.text.toString()
            Timber.i("Email: $email")

            var user: UserModel? = app.users.findAll().firstOrNull { 
it.email == email }
            if (user != null) {
                if (binding.password.text.toString() == user.password) {
                    val launcherIntent = Intent(this, 
PlacemarkListActivity::class.java)
                    launcherIntent.putExtra("user", user)
                    placemarkIntentLauncher.launch(launcherIntent)
                } else {
                    Snackbar.make(it, R.string.invalid_password, 
Snackbar.LENGTH_LONG)
                        .show()
                }
            } else {
                Snackbar.make(it, R.string.invalid_username, 
Snackbar.LENGTH_LONG)
                    .show()
            }

        }
        registerPlacemarkCallback()
        registerSignUpCallback()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_login, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_signUp -> {
                val launcherIntent = Intent(this, 
RegisterActivity::class.java)
                signUpIntentLauncher.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun registerPlacemarkCallback() {
        placemarkIntentLauncher =
            
registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { }
    }

    private fun registerSignUpCallback() {
        signUpIntentLauncher =
            
registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { }
    }


}
