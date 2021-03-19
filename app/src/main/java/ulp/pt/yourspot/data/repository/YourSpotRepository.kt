package ulp.pt.yourspot.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_home_page.view.*
import ulp.pt.yourspot.data.YourSpotDao
import ulp.pt.yourspot.data.modelo_de_dados.entidades.PublicarExercicio
import ulp.pt.yourspot.data.modelo_de_dados.entidades.PublicarLivro
import ulp.pt.yourspot.data.modelo_de_dados.entidades.PublicarTexto
import ulp.pt.yourspot.data.modelo_de_dados.entidades.Utilizador
import java.text.SimpleDateFormat
import java.util.*

//import com.example.depot.explorarFiltradoFragmentArgs

// Classe abstrata que acessa multiplas fontes de data
// Dao necessário como parametro


class YourSpotRepository(private val yourSpotDao: YourSpotDao) {

    val date = Calendar.getInstance().time
    var formato_data_publicacao = SimpleDateFormat("EEE, d MMM yyyy H:m", Locale.getDefault())

    fun getAutenticacao(email: String, senha: String): List<Utilizador> {
        return yourSpotDao.getAutenticacao(email,senha)

    }

    fun getID_utilizador(email: String, senha: String): Int {
        return yourSpotDao.getID_utilizador(email,senha)

    }

    fun inserirUtilizador(utilizador: Utilizador) {
        return yourSpotDao.inserirUtilizador(utilizador)

    }

    fun getUltimoID_utilizador(): Int {
        return yourSpotDao.getUltimoID_utilizador()
    }

    fun lerPublicarExercicioData(): LiveData<MutableList<PublicarExercicio>> {
        val mutableData = MutableLiveData<MutableList<PublicarExercicio>>()
        val database = FirebaseDatabase.getInstance("https://yourspot-300e2-default-rtdb.europe-west1.firebasedatabase.app/")
        val myRef = database.getReference("PublicarExercicio")
        var get_data_firebase = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var mStringBuilder = StringBuilder()
                val listData = mutableListOf<PublicarExercicio>()
                for (i in snapshot.children){

                    var titulo = i.child("titulo").value
                    var tema = i.child("tema").value
                    var descricao = i.child("descricao").value
                    var imagem = i.child("imagem").value
                    val exercicio_publicado = PublicarExercicio(0,0,formato_data_publicacao.format(date),titulo.toString(),descricao.toString(),"nenhum",imagem.toString(),tema.toString())
                    listData.add(exercicio_publicado)
                }
                mutableData.value = listData
            }

            override fun onCancelled(error: DatabaseError) {

            }

        }
        myRef.addValueEventListener(get_data_firebase)
        return mutableData
    }

    fun lerPublicarLivroData(): LiveData<MutableList<PublicarLivro>> {
        val mutableData = MutableLiveData<MutableList<PublicarLivro>>()
        val database = FirebaseDatabase.getInstance("https://yourspot-300e2-default-rtdb.europe-west1.firebasedatabase.app/")
        val myRef = database.getReference("PublicarLivro")
        var get_data_firebase = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var mStringBuilder = StringBuilder()
                val listData = mutableListOf<PublicarLivro>()
                for (i in snapshot.children){

                    var titulo = i.child("titulo").value
                    var edicao = i.child("edicao").value
                    var autor = i.child("autor").value
                    var genero = i.child("genero").value
                    var disciplina = i.child("disciplina").value
                    var descricao = i.child("descricao").value
                    var imagem = i.child("imagem").value
                    val exercicio_publicado = PublicarLivro(0,0,formato_data_publicacao.format(date),titulo.toString(),descricao.toString(),"nenhum",imagem.toString(),edicao.toString(),autor.toString(),genero.toString(),disciplina.toString())
                    listData.add(exercicio_publicado)
                }
                mutableData.value = listData
            }

            override fun onCancelled(error: DatabaseError) {

            }

        }
        myRef.addValueEventListener(get_data_firebase)
        return mutableData
    }

    fun lerPublicarTextoData(): LiveData<MutableList<PublicarTexto>> {
        val mutableData = MutableLiveData<MutableList<PublicarTexto>>()
        val database = FirebaseDatabase.getInstance("https://yourspot-300e2-default-rtdb.europe-west1.firebasedatabase.app/")
        val myRef = database.getReference("PublicarTexto")
        var get_data_firebase = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var mStringBuilder = StringBuilder()
                val listData = mutableListOf<PublicarTexto>()
                for (i in snapshot.children){
                    var titulo = i.child("titulo").value
                    var fonte = i.child("fonte").value
                    var autor = i.child("autor").value
                    var genero = i.child("genero").value
                    var disciplina = i.child("disciplina").value
                    var descricao = i.child("descricao").value
                    var imagem = i.child("imagem").value
                    val exercicio_publicado = PublicarTexto(0,0,formato_data_publicacao.format(date),titulo.toString(),descricao.toString(),"nenhum",imagem.toString(),fonte.toString(),autor.toString(),genero.toString(),disciplina.toString())
                    listData.add(exercicio_publicado)
                }
                mutableData.value = listData
            }

            override fun onCancelled(error: DatabaseError) {

            }

        }
        myRef.addValueEventListener(get_data_firebase)
        return mutableData
    }




}
