package com.lmsolutions.msessenta

import java.io.Serializable

class Cadastro: Serializable {

    var id:Long = 0
    var nome = ""
    var endereco = ""
    var foto = ""
    var profissao = ""

    override fun toString(): String {
        return "Cadastro(endereco='$endereco')"
    }
}