package ulp.pt.yourspot.fragments

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat.recreate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.*
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_home_page.view.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import ulp.pt.yourspot.R
import ulp.pt.yourspot.data.view_model.YourSpotViewModel



class loginFragment : Fragment() {

    // FireBaseAuthenticator
    lateinit var auth: FirebaseAuth
    var connected = false

    // FireBaseDataBase
    val database =
        FirebaseDatabase.getInstance("https://yourspot-300e2-default-rtdb.europe-west1.firebasedatabase.app/")
    val myRef = database.getReference("Utilizador")

    var ID_utilizador: Int = 63


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        // Inicializações de variaveis e argumentos
        lateinit var mYourSpotViewModel: YourSpotViewModel

        // FireBase
        auth = FirebaseAuth.getInstance()

        view.logo_login.setOnClickListener {
            var botaoacao = view.logo_login
            var direcao = R.id.action_loginFragment_to_paginaInicialFragment
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        view.n_o_tem_uma.setOnClickListener {
            var botaoacao = view.n_o_tem_uma
            var direcao = R.id.action_loginFragment_to_cadastroFragment
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        view.bota_entrar_login.setOnClickListener {
            var email = view.inputemail_login.text.toString()
            var senha = view.inputsenha_login.text.toString()
            if (checarInput(email, senha)) {
                var mSB_ID_utilizador = mutableListOf<Int>()
                var idzin: Int
                var mStringBuilder = StringBuilder()


                //inputs nao estão vazios podemos verificar se o utilizador possui internet ou não para a autenticação dos dados
                if (isOnline(requireContext())) {

                    //get data from firebase
                    var get_data_firebase = object : ValueEventListener {

                        override fun onDataChange(snapshot: DataSnapshot) {
                            for (i in snapshot.children) {
                                var senha_firebase = i.child("senha").value
                                var email_firebase = i.child("email").value
                                mStringBuilder.append("${i.key}\n$senha_firebase $email")
                                Log.i("teste_login2", mStringBuilder.toString())
                                if (senha_firebase == senha && email_firebase == email) {
                                    mStringBuilder.append("\n${i.key}\n$email $senha_firebase ")
                                    Log.i(
                                        "teste_login3",
                                        mStringBuilder.toString()
                                    ); //(information)
                                    // Pegar o ID do utilizador referente ao email e a senha, da base de dados
                                    ID_utilizador = i.key.toString().toInt()
                                    view.id_utilizador_placeholder.text = ID_utilizador.toString()
                                    mSB_ID_utilizador.add(ID_utilizador)
                                    Log.i(
                                        "teste_login4",
                                        ID_utilizador.toString()
                                    ); //(information)
                                    Log.i(
                                        "teste_login7",
                                        mSB_ID_utilizador.toString()
                                    ); //(information)
                                    Log.i(
                                        "teste_login8",
                                        view.id_utilizador_placeholder.text.toString()
                                    ); //(information)
                                    Navegar(mStringBuilder,view,ID_utilizador)
                                }
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                        }
                    }
                    myRef.addListenerForSingleValueEvent(get_data_firebase)
                // ...
            }else if (!isOnline(requireContext())) {

                // YourSpotViewModel
                mYourSpotViewModel =
                    ViewModelProvider(this).get(ulp.pt.yourspot.data.view_model.YourSpotViewModel::class.java)
                var resultadoLogin = mYourSpotViewModel.getAutenticacao(email, senha)
                if (resultadoLogin.isEmpty()) {
                    YourSpotFragmentsRepository.Alertas().alertaDeTexto(this.requireContext(), "Você ainda não está cadastrado!")
                } else {

                    //iniciar session
                    var ID_utilizador = mYourSpotViewModel.getID_utilizador(email, senha)
                    Log.i("teste_login1", ID_utilizador.toString()); //(information)
                    var botaoacao = view.bota_entrar_login
                    var direcao = loginFragmentDirections.actionLoginFragmentToHomePageFragment(ID_utilizador)
                    YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)

                    YourSpotFragmentsRepository.Alertas().alertaDeTexto(this.requireContext(), "Login efetuado com sucesso! Você está no modo OFFLINE!")
                }
            }

        }else if(!checarInput(email, senha)){
                YourSpotFragmentsRepository.Alertas().alertaDeTexto(this.requireContext(), "Porfavor, preencha todos os campos!")
        }
    }




        return view
    }

    private fun Navegar(mStringBuilder:StringBuilder,view: View,id_utilizador:Int) {
        Log.d("teste_login1", mStringBuilder.toString())
        Log.d("teste_login5", view.id_utilizador_placeholder.text.toString())
        Log.d("teste_login6", id_utilizador.toString())
        var botaoacao = view.bota_entrar_login
        var direcao = loginFragmentDirections.actionLoginFragmentToHomePageFragment(ID_utilizador)
        YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        YourSpotFragmentsRepository.Alertas().alertaDeTexto(this.requireContext(), "Login efetuado com sucesso! Você está no modo ONLINE!")
    }


    //verifica se os editText não foram preenchidos
    private fun checarInput(email: String, senha: String): Boolean{
        return !(TextUtils.isEmpty(email) || TextUtils.isEmpty(senha))
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

}