package com.jvjp.motivacional.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.jvjp.motivacional.R
import com.jvjp.motivacional.infra.MotivacaoContantes
import com.jvjp.motivacional.infra.SecurityPreferences
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mSharedPreferences: SecurityPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSharedPreferences = SecurityPreferences(this)
        setContentView(R.layout.activity_splash)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        buttonSave.setOnClickListener(this)

        verifyName()

    }

    override fun onClick(view: View?) {
        val id = view!!.id
        if (id == R.id.buttonSave) {
            handleSave()
        }
    }

    private fun verifyName(){
        val name = mSharedPreferences.getString(MotivacaoContantes.KEY.PERSON_NAME)
        if( name != ""){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun handleSave() {
        val name = editName.text.toString()

        if (name != "") {
            mSharedPreferences.storeString(MotivacaoContantes.KEY.PERSON_NAME, name)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        } else {
            Toast.makeText(
                this,
                getString(R.string.adc_toast),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}