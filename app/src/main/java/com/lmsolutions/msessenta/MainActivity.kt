package com.lmsolutions.msessenta

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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

        botaoLogin.setOnClickListener {onClickLogin() }

        progressBar.visibility = View.INVISIBLE

        var lembrar = Prefs.getBoolean("lembrar")
        if (lembrar) {
            var lembrarNome  = Prefs.getString("lembrarNome")
            var lembrarSenha  = Prefs.getString("lembrarSenha")
            campo_usuario.setText(lembrarNome)
            campo_senha.setText(lembrarSenha)
            checkBoxLogin.isChecked = lembrar

        }
    }



    override fun onResume() {
        super.onResume()
        // abrir a disciplina caso clique na notificação com o aplicativo fechado
        abrirCadastro()
        // mostrar no log o tokem do firebase
        Log.d("firebase", "Firebase Token: ${Prefs.getString("FB_TOKEN")}")
    }

    fun onClickLogin() {
        //val campoUsuario = findViewById<EditText>(R.id.campo_usuario)
        //val campoSenha = findViewById<EditText>(R.id.campo_senha)
        val valorUsuario = campo_usuario.text.toString()
        val valorSenha = campo_senha.text.toString()

        // armazenar valor do checkbox
        Prefs.setBoolean("lembrar", checkBoxLogin.isChecked)

        // verificar se é para lembrar nome e senha
        if (checkBoxLogin.isChecked){
            Prefs.setString("lembrarNome", valorUsuario )
            Prefs.setString("lembrarSenha", valorSenha )

        } else {
            Prefs.setString("lembrarNome", "" )
            Prefs.setString("lembrarSenha", "" )
        }





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

    fun abrirCadastro() {
        // verificar se existe  id da disciplina na intent
        if (intent.hasExtra("cadastroId")) {
            Thread {
                var cadastroId = intent.getStringExtra("disciplinaId")?.toLong()!!
                val cadastro = CadastroService.getCadastro(this, cadastroId)
                runOnUiThread {
                    val intentDisciplina = Intent(this, CadastroActivity::class.java)
                    intentDisciplina.putExtra("disciplina", cadastro)
                    startActivity(intentDisciplina)
                }
            }.start()
        }

    }
}
