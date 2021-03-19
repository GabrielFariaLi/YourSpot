package ulp.pt.yourspot.data.modelo_de_dados.entidades

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(primaryKeys = ["ID_utilizador", "ID_texto"])
data class PesquisarTexto(
        val ID_utilizador: Int,
        val ID_texto: Int, 
        val data: String?,
        val favoritado: Boolean?,
        val titulo_texto: String?,
): Parcelable
