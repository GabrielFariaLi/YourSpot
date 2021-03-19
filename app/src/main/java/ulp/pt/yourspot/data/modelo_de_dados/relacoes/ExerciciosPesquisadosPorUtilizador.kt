package ulp.pt.yourspot.data.modelo_de_dados.relacoes

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import ulp.pt.yourspot.data.modelo_de_dados.entidades.*

data class ExerciciosPesquisadosPorUtilizador (
    @Embedded val utilizador: Utilizador,
    @Relation(
                parentColumn = "ID_utilizador",
                entityColumn = "ID_utilizador",
                associateBy = Junction(PesquisarExercicio::class)
            )
            val lista_exericicos: List<Exercicio>
)