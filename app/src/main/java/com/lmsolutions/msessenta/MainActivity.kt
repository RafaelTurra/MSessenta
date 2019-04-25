package com.lmsolutions.msessenta

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.login.*

class MainActivity : DebugActivity() {

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        // encontra objeto pelo id
        val imagem = findViewById<ImageView>(R.id.campo_imagem)
        imagem.setImageResource(R.drawable.msessenta2)

        val texto = findViewById<TextView>(R.id.texto_login)
        texto.text = getString(R.string.mensagem_inicial)


        val botaoLogin = findViewById<Button>(R.id.botao_login)

        botao_login.setOnClickListener { onClickLogin() }
    }

    //private val context: Context get() = this

    fun onClickLogin() {
        val campoUsuario = findViewById<EditText>(R.id.campo_usuario)
        val campoSenha = findViewById<EditText>(R.id.campo_senha)
        val valorUsuario = campoUsuario.text.toString()
        val valorSenha = campoSenha.text.toString()

        val testeUsu = "Rafael"
        val testeSenha = 123



        //Toast.makeText( this,  "Nome do usuario: ${campo_usuario.text}", Toast.LENGTH_SHORT).show()

        // criação da intent para navegar entre telas/activities
        val intent = Intent(context, TelaInicialActivity::class.java)

        // criação da variavel para colocar parametros
        val params = Bundle()
        // colocar parametros na intent
        params.putString("nome", "Rafael")
        intent.putExtras(params)

        // colocar parametros do tipo string
        //intent.putExtra("nomeUsuario", campo_usuario.text.toString())
        intent.putExtra("numero", 10)

        // fazer a chamada esperando resultado
        startActivityForResult(intent, 1)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            val result = data?.getStringExtra("result")
            Toast.makeText(context, "$result", Toast.LENGTH_LONG).show()

           // Toast.makeText(context, "Saiu do MSessentaApp", Toast.LENGTH_SHORT).show()
        }
    }
}
