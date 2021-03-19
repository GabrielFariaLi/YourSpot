package ulp.pt.yourspot.data.modelo_de_dados.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Pagina_pessoal_tabela")
data class PaginaPessoal (
    @PrimaryKey(autoGenerate = true)
    val ID_pagina_pessoal: Int,
    val utilizador: Int,
    val tipo: String?,
    val titulo: String?,
    val descricao: String?,
)
