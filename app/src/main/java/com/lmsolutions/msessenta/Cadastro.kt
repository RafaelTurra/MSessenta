package com.lmsolutions.msessenta

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName = "cadastro")

class Cadastro: Serializable {

    @PrimaryKey
    var id:Long = 0
    var nome = ""
    var endereco = ""
    var foto = ""
    var profissao = ""

    override fun toString(): String {
        return "Cadastro(endereco='$endereco')"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}