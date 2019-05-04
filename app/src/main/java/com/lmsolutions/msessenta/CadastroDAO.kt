package com.lmsolutions.msessenta

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface CadastroDAO {
    @Query("SELECT * FROM cadastro where id = :id")
    fun getById(id: Long) : Cadastro?

    @Query("SELECT * FROM cadastro")
    fun findAll(): List<Cadastro>

    @Insert
    fun insert(cadastro: Cadastro)

    @Delete
    fun delete(cadastro: Cadastro)

}