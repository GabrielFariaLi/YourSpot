package ulp.pt.yourspot.data.modelo_de_dados.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Professor_tabela")
data class Professor (
    @PrimaryKey(autoGenerate = true)
    val ID_professor: Int,
    val cargo: String?,
)
