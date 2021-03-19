package ulp.pt.yourspot.data.modelo_de_dados.entidades

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(primaryKeys = ["ID_utilizador", "ID_exercicio"])
data class PublicarExercicio(
        val ID_utilizador: Int,
        val ID_exercicio: Int,
        val data: String?,
        val titulo: String?,
        val descricao: String?,
        val arquivo_anexado: String?,
        val imagem: String?,
        val tema: String?,
): Parcelable
