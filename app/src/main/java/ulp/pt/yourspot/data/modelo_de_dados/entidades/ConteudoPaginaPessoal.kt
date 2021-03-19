package ulp.pt.yourspot.data.modelo_de_dados.entidades

import androidx.room.Entity
import java.util.*

@Entity(tableName = "ConteudoPaginaPessoal_tabela", primaryKeys = ["ID_utilizador", "ID_pagina_pessoal"])
data class ConteudoPaginaPessoal (
    val ID_utilizador: Int,
    val ID_pagina_pessoal: Int,
    val descricao_conteudo: String?,
    val tipo_conteudo: String?,
)
