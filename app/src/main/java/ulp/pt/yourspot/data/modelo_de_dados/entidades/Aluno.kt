package ulp.pt.yourspot.data.modelo_de_dados.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Aluno_tabela")
data class Aluno (
    @PrimaryKey(autoGenerate = true)
    val ID_aluno: Int,
)
