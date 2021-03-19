package ulp.pt.yourspot.data.modelo_de_dados.entidades

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Texto_tabela")
data class Texto(
    @PrimaryKey(autoGenerate = true)
    val ID_texto: Int,
    val professor: Int,

): Parcelable
