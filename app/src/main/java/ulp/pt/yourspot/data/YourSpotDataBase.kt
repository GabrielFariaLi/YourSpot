package ulp.pt.yourspot.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ulp.pt.yourspot.data.modelo_de_dados.entidades.*

@Database(
    entities = [
        Aluno::class,
        ConteudoPaginaPessoal::class,
        Exercicio::class,
        Livro::class,
        PaginaPessoal::class,
        PesquisarExercicio::class,
        PesquisarLivro::class,
        PesquisarTexto::class,
        Professor::class,
        PublicarExercicio::class,
        PublicarLivro::class,
        PublicarTexto::class,
        Texto::class,
        Utilizador::class,
    ],
    version = 5
)
abstract class YourSpotDataBase : RoomDatabase() {

    abstract fun yourSpotDao(): YourSpotDao
    // abstract val yourSpotDao: YourSpotDao

    companion object {
        @Volatile
        private var INSTANCE: YourSpotDataBase? = null

        fun getDataBase(context: Context): YourSpotDataBase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    YourSpotDataBase::class.java,
                    "yourSpot_DataBase"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build().also {
                    INSTANCE = it
                }
            }
        }
    }
}
