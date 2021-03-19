package ulp.pt.yourspot.data.modelo_de_dados.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Livro_tabela")
data class Livro (
    @PrimaryKey(autoGenerate = true)
    val ID_livro: Int,
    val professor: Int,

)
