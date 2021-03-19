package ulp.pt.yourspot.data.modelo_de_dados.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Exercicio_tabela")
data class Exercicio (
    @PrimaryKey(autoGenerate = true)
    val ID_exercicio: Int,
    val professor: Int,
)
