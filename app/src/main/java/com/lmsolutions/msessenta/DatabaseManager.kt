package com.lmsolutions.msessenta

import android.arch.persistence.room.Room

object DatabaseManager {

    // singleton
    private var dbInstance: ImSolutionsDatabase
    init {
        val appContext = ImSolutions.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(
                appContext, // contexto global
                ImSolutionsDatabase::class.java, // Referência da classe do banco
                "lms.sqlite" // nome do arquivo do banco
        ).build()
    }

    fun getCadastroDAO(): CadastroDAO {
        return dbInstance.cadastroDAO()
    }
}