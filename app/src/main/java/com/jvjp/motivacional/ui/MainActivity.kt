package com.jvjp.motivacional.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.jvjp.motivacional.R
import com.jvjp.motivacional.infra.MotivacaoContantes
import com.jvjp.motivacional.infra.SecurityPreferences
import com.jvjp.motivacional.repository.Mock
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mSharedPreferences: SecurityPreferences
    private var mPhraseFilter: Int = MotivacaoContantes.PHRASEFLITER.ALL

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportActionBar != null){
            supportActionBar!!.hide()
        }
        mSharedPreferences = SecurityPreferences(this)
        val name = mSharedPreferences.getString(MotivacaoContantes.KEY.PERSON_NAME)
        textName.text = "Óla, $name!"
        //Logica inicial de seleção
        imageAll.setColorFilter(resources.getColor(R.color.colorAccent))
        handleNewPhrase()


        buttoNewPhrase.setOnClickListener(this)
        imageAll.setOnClickListener(this)
        imageHappy.setOnClickListener(this)
        imageMorning.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        val id = v!!.id
        val listFilter = listOf(R.id.imageAll, R.id.imageHappy, R.id.imageMorning)
        if (id == R.id.buttoNewPhrase) {
            handleNewPhrase()
        } else if (id in listFilter) {
            handleFliter(id)
        }
    }

    private fun handleFliter(id: Int) {
        imageAll.setColorFilter(resources.getColor(R.color.white))
        imageHappy.setColorFilter(resources.getColor(R.color.white))
        imageMorning.setColorFilter(resources.getColor(R.color.white))

        when (id) {
            R.id.imageAll -> {
                imageAll.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = MotivacaoContantes.PHRASEFLITER.ALL
            }
            R.id.imageHappy -> {
                imageHappy.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = MotivacaoContantes.PHRASEFLITER.HAPPY
            }
            R.id.imageMorning -> {
                imageMorning.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = MotivacaoContantes.PHRASEFLITER.MORMING
            }
        }
    }

    private fun handleNewPhrase() {
        textPhrase.text = Mock().getPhrase(mPhraseFilter)
    }

}