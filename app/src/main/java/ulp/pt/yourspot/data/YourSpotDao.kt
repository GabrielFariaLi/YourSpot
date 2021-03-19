package ulp.pt.yourspot.data

import androidx.room.*
import ulp.pt.yourspot.data.modelo_de_dados.entidades.Utilizador
import ulp.pt.yourspot.data.modelo_de_dados.relacoes.UtilizadoresAlunos
import ulp.pt.yourspot.data.modelo_de_dados.relacoes.UtilizadoresProfessores


@Dao
interface YourSpotDao {

    /* Inserts,Updates,Deletes */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun inserirUtilizador(utilizador: Utilizador)

    /* Querys SELECT */
    @Query("SELECT email, senha, ID_utilizador FROM utilizador_tabela where email=:email and senha=:senha")
    fun getAutenticacao(email: String, senha: String):List<Utilizador>

    @Query("SELECT ID_utilizador FROM utilizador_tabela where email=:email and senha=:senha")
    fun getID_utilizador(email: String, senha: String): Int

    @Query("SELECT MAX(ID_utilizador) FROM utilizador_tabela")
    abstract fun getUltimoID_utilizador(): Int
    /* Querys Com JUNCTION (Association) */
    @Transaction
    @Query("SELECT * FROM utilizador_tabela")
    fun getUtilizadoresAlunos(): List<UtilizadoresAlunos>

    @Transaction
    @Query("SELECT * FROM utilizador_tabela")
    fun getUtilizadoresProfessores(): List<UtilizadoresProfessores>



}
