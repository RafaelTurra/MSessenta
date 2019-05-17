package com.lmsolutions.msessenta

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class ActivityProduto : AppCompatActivity() {

    private val context: Context get() = this
    var cadastro: Cadastro? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produto)

        val a = findViewById<EditText>(R.id.edtNomeProduto)



    }



}
