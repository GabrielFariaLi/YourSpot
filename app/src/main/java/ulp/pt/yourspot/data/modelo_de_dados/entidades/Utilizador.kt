package ulp.pt.yourspot.data.modelo_de_dados.entidades

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Utilizador_tabela")
data class Utilizador(
    @PrimaryKey(autoGenerate = true)
    val ID_utilizador: Int,
    val primeiro_nome: String?,
    val ultimo_nome: String?,
    val entidade_ensino: String?,
    val nivel_de_acesso: String?,
    val foto_perfil: String?,
    val pais: String?,
    val contacto: String?,
    val email: String?,
    val senha: String?,
    val ultimo_login: String?
): Parcelable
