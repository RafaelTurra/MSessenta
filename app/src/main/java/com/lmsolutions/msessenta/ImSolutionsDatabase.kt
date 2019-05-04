package com.lmsolutions.msessenta

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

// anotação define a lista de entidades e a versão do banco
@Database(entities = arrayOf(Cadastro::class), version = 1)
abstract class ImSolutionsDatabase: RoomDatabase() {
    abstract fun cadastroDAO(): CadastroDAO
}