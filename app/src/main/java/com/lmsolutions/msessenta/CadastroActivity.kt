package com.lmsolutions.msessenta

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class CadastroActivity : DebugActivity() {

    var disciplina: Cadastro? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        // recuperar onjeto de Disciplina da Intent
        disciplina = intent.getSerializableExtra("disciplina") as Cadastro

        // configurar título com nome da Disciplina e botão de voltar da Toobar
        // colocar toolbar
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // alterar título da ActionBar
        supportActionBar?.title = disciplina?.nome

        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // atualizar dados do carro
        var texto = findViewById<TextView>(R.id.nomeCadastro)
        texto.text = disciplina?.nome
        var imagem = findViewById<ImageView>(R.id.imagemCadastro)
        Picasso.with(this).load(disciplina?.foto).fit().into(imagem,
                object: com.squareup.picasso.Callback{
                    override fun onSuccess() {}

                    override fun onError() { }
                })
    }
}
