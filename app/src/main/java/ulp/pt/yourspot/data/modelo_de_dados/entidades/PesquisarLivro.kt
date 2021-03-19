package ulp.pt.yourspot.data.modelo_de_dados.entidades

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(primaryKeys = ["ID_utilizador", "ID_livro"])
data class PesquisarLivro(
        val ID_utilizador: Int,
        val ID_livro: Int,
        val data: String?,
        val favoritado: Boolean?,
        val titulo_livro: String?,
): Parcelable
