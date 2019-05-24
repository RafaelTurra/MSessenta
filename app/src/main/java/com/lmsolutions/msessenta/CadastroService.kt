package com.lmsolutions.msessenta

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object CadastroService {

    //TROQUE PELA URL DE ONDE ESTÁ O WS
    // Veja um exemplo no repositório https://github.com/fesousa/aula-android-kotlin-api
    val host = "http://RafaelTurra.pythonanywhere.com"
    //val host = "http://fesousa.pythonanywhere.com"
    val TAG = "WS_LMSApp"

    fun getCadastros (context: Context): List<Cadastro> {
        var cadastros = ArrayList<Cadastro>()
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/cadastros"
            val json = HttpHelper.get(url)
            cadastros = parserJson(json)
            // salvar offline
            for (d in cadastros) {
                saveOffline(d)
            }
            return cadastros
        } else {
            val dao = DatabaseManager.getCadastroDAO()
            val cadastros = dao.findAll()
            return cadastros
        }

    }

    fun getCadastro (context: Context, id: Long): Cadastro? {

        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/cadastros/${id}"
            val json = HttpHelper.get(url)
            val cadastro = parserJson<Cadastro>(json)

            return cadastro
        } else {
            val dao = DatabaseManager.getCadastroDAO()
            val cadastro = dao.getById(id)
            return cadastro
        }

    }

    fun save(cadastro: Cadastro): Response {
        val json = HttpHelper.post("$host/cadastros", cadastro.toJson())
        return parserJson<Response>(json)
    }

    fun saveOffline(cadastro: Cadastro) : Boolean {
        val dao = DatabaseManager.getCadastroDAO()

        if (! existeCadastro(cadastro)) {
            dao.insert(cadastro)
        }

        return true

    }

    fun existeCadastro(cadastro: Cadastro): Boolean {
        val dao = DatabaseManager.getCadastroDAO()
        return dao.getById(cadastro.id) != null
    }

    fun delete(cadastro: Cadastro): Response {
        if (AndroidUtils.isInternetDisponivel(ImSolutions.getInstance().applicationContext)) {
            val url = "$host/cadastros/${cadastro.id}"
            val json = HttpHelper.delete(url)

            return parserJson<Response>(json)
        } else {
            val dao = DatabaseManager.getCadastroDAO()
            dao.delete(cadastro)
            return Response(status = "OK", msg = "Dados salvos localmente")
        }

    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}