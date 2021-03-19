package ulp.pt.yourspot.data.modelo_de_dados.relacoes

import androidx.room.Embedded
import androidx.room.Relation
import ulp.pt.yourspot.data.modelo_de_dados.entidades.Utilizador
import ulp.pt.yourspot.data.modelo_de_dados.entidades.Aluno

data class UtilizadoresAlunos (
    @Embedded val utilizador: Utilizador,
    @Relation(
                parentColumn = "ID_utilizador",
                entityColumn = "ID_aluno"
            )
            val alunos: List<Aluno>
)