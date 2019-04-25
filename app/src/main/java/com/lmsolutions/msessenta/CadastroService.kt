package br.com.fernandosousa.lmsapp

import android.content.Context
import com.lmsolutions.msessenta.Cadastro
//import com.lmsolutions.msessenta.Disciplina

object CadastroService {


    fun getCadastro (context: Context): List<Cadastro> {

        val cadastro = mutableListOf<Cadastro>()

        // criar 10 Cadastro
        for (i in 1..1) {
            val d = Cadastro()
            d.nome = "Parceiro $i"
            d.endereco = "Endereco Parceiro $i"
            d.profissao = "Profissao Parceiro $i"
            d.foto = "https://www.impacta.com.br/landing-page/excel-show-2-edicao/res/img/logo.png"
            cadastro.add(d)
        }

        return cadastro
    }

}