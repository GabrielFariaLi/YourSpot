package ulp.pt.yourspot.data.modelo_de_dados.relacoes

import androidx.room.Embedded
import androidx.room.Relation
import ulp.pt.yourspot.data.modelo_de_dados.entidades.Utilizador
import ulp.pt.yourspot.data.modelo_de_dados.entidades.Aluno
import ulp.pt.yourspot.data.modelo_de_dados.entidades.PaginaPessoal

data class PaginasPessoaisDeUtilizadores (
    @Embedded val utilizador: Utilizador,
    @Relation(
                parentColumn = "ID_utilizador",
                entityColumn = "utilizador"
            )
            val paginasPessoais: List<PaginaPessoal>
)