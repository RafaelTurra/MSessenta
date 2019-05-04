package com.lmsolutions.msessenta

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cadastro_cliente.*

class ClienteCadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_cliente)
        setTitle("Novo Cliente")

        salvarCadastro.setOnClickListener {
            val cadastro = Cadastro()
            cadastro.nome = nomeDisciplina.text.toString()
            cadastro.endereco = enderecoCliente.text.toString()
            cadastro.profissao = profissaoCliente.text.toString()
            cadastro.foto = urlFoto.text.toString()

            taskAtualizar(cadastro)
        }
    }

    private fun taskAtualizar(cadastro: Cadastro) {
        // Thread para salvar a discilpina
        Thread {
            CadastroService.save(cadastro)
            runOnUiThread {
                // após cadastrar, voltar para activity anterior
                finish()
            }
        }.start()
    }
}
