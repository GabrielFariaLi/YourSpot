package ulp.pt.yourspot.data.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ulp.pt.yourspot.data.YourSpotDao
import ulp.pt.yourspot.data.YourSpotDataBase
import ulp.pt.yourspot.data.modelo_de_dados.entidades.PublicarExercicio
import ulp.pt.yourspot.data.modelo_de_dados.entidades.PublicarLivro
import ulp.pt.yourspot.data.modelo_de_dados.entidades.PublicarTexto
import ulp.pt.yourspot.data.modelo_de_dados.entidades.Utilizador
import ulp.pt.yourspot.data.repository.YourSpotRepository


// Provir data para a interface Grafíca e sobreviver a mudança de configurações
// Serve como meio de comunicação entre o Repository e a interface grafíca
public class YourSpotViewModel(application: Application): AndroidViewModel(application) {
    // Repository do YourSpot
    val repository: YourSpotRepository

    // Sempre a RRIMEIRA EXECUÇÃO quando o view model do utilizador for chamado
    init {
        val mYourSpotDao = YourSpotDataBase.getDataBase(application).yourSpotDao()
        repository = YourSpotRepository(mYourSpotDao)
    }


    fun getAutenticacao(email: String, senha: String): List<Utilizador>  {
    return repository.getAutenticacao(email,senha)
    }

    fun getID_utilizador(email: String, senha: String): Int {
        return repository.getID_utilizador(email,senha)

    }

    fun getUltimoID_utilizador(): Int {
        return repository.getUltimoID_utilizador()
    }

    fun lerPublicarExercicioData(): LiveData<MutableList<PublicarExercicio>>{
        val mutableData = MutableLiveData<MutableList<PublicarExercicio>>()
        repository.lerPublicarExercicioData().observeForever{ lista_exercicio ->
            mutableData.value = lista_exercicio
        }
        return mutableData
    }

    fun lerPublicarLivroData(): LiveData<MutableList<PublicarLivro>>{
        val mutableData = MutableLiveData<MutableList<PublicarLivro>>()
        repository.lerPublicarLivroData().observeForever{ lista_livro ->
            mutableData.value = lista_livro
        }
        return mutableData
    }

    fun lerPublicarTextoData(): LiveData<MutableList<PublicarTexto>> {
        val mutableData = MutableLiveData<MutableList<PublicarTexto>>()
        repository.lerPublicarTextoData().observeForever{ lista_texto ->
            mutableData.value = lista_texto
        }
        return mutableData
    }



    // Metodo de adicionar utilizador
    fun inserirUtilizador(utilizador: Utilizador){
        // co-rotina
        //dispatchers.io = rodar em uma thread de plano de fundo
        viewModelScope.launch(Dispatchers.IO) {
            // Chama a função do repository dos utilizadores
            repository.inserirUtilizador(utilizador)
        }
    }




}
