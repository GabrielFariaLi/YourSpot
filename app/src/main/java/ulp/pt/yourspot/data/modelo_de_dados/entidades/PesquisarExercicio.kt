package ulp.pt.yourspot.data.modelo_de_dados.entidades

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(primaryKeys = ["ID_utilizador", "ID_exercicio"])
data class PesquisarExercicio(
        val ID_utilizador: Int,
        val ID_exercicio: Int,
        val data: String?,
        val favoritado: Boolean?,
        val titulo_exercicio: String?,
): Parcelable
