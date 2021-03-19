package ulp.pt.yourspot.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_login.view.*
import ulp.pt.yourspot.R
import ulp.pt.yourspot.data.view_model.YourSpotViewModel



class YourSpotFragmentsRepository {
    class Navegacao() {

        public fun navegarPara(viewacao: View, acaonavegar: Int){
            viewacao.findNavController().navigate(acaonavegar)
        }

        //Função navegar com assinatura diferente (Usada quando passamos parâmetros)
        public fun navegarPara(viewacao: View, acaonavegar: NavDirections){
            viewacao.findNavController().navigate(acaonavegar)
        }

        public fun paginaAnterior(viewacao: View){
            viewacao.findNavController().popBackStack()
        }

    }

    class Alertas() {

        public fun alertaDeTexto(fragmentContexto: Context, mensagem: String){
            var alerta = mensagem
            Toast.makeText(fragmentContexto,alerta, Toast.LENGTH_LONG).show()
        }

        public fun alertaPaginaAtual(fragmentContexto: Context){
            var alerta = "Você já se encontra aqui :)"
            Toast.makeText(fragmentContexto,alerta, Toast.LENGTH_LONG).show()
        }

        public fun alertaNaoImplementado(fragmentContexto: Context){
            var alerta = "Pedimos desculpa mas parece que essa funcionalidade ainda não existe :("
            Toast.makeText(fragmentContexto,alerta, Toast.LENGTH_LONG).show()
        }

    }

    class Login() {

        fun SistemadeLogin() {

            // Inicializações de variaveis e argumentos
            var mYourSpotViewModel: YourSpotViewModel

            val view = loginFragment().view

            //verifica se os editText não foram preenchidos
            fun checarInput(email: String, senha: String): Boolean{
                return !(TextUtils.isEmpty(email) && TextUtils.isEmpty(senha))
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

            fun Navegar(mStringBuilder:StringBuilder,view: View,id_utilizador:Int) {
                var teste = mStringBuilder
                Log.d("teste_login1", mStringBuilder.toString())
                Log.d("teste_login5", view.id_utilizador_placeholder.text.toString())
                Log.d("teste_login6", id_utilizador.toString())
                val user = loginFragment().auth.currentUser
                var botaoacao = view.bota_entrar_login
                var direcao = loginFragmentDirections.actionLoginFragmentToHomePageFragment(loginFragment().ID_utilizador)
                YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
                YourSpotFragmentsRepository.Alertas().alertaDeTexto(loginFragment().requireContext(), "Login efetuado com sucesso! Você está no modo ONLINE!")
            }

            // FireBase associada ao LoginFragment
            loginFragment().auth = FirebaseAuth.getInstance()

            view?.logo_login?.setOnClickListener {
                var botaoacao = view.logo_login
                var direcao = R.id.action_loginFragment_to_paginaInicialFragment
                Navegacao().navegarPara(botaoacao, direcao)
            }

            view?.n_o_tem_uma?.setOnClickListener {
                var botaoacao = view.n_o_tem_uma
                var direcao = R.id.action_loginFragment_to_cadastroFragment
                Navegacao().navegarPara(botaoacao, direcao)
            }

            view?.bota_entrar_login?.setOnClickListener {
                var email = view.inputemail_login.text.toString()
                var senha = view.inputsenha_login.text.toString()
                if (checarInput(email, senha)) {
                    var mSB_ID_utilizador = mutableListOf<Int>()
                    var idzin: Int
                    var mStringBuilder = StringBuilder()


                    //inputs nao estão vazios podemos verificar se o utilizador possui internet ou não para a autenticação dos dados
                    if (isOnline(loginFragment().requireContext())) {

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
                                        loginFragment().ID_utilizador = i.key.toString().toInt()
                                        view.id_utilizador_placeholder.text = loginFragment().ID_utilizador.toString()
                                        mSB_ID_utilizador.add(loginFragment().ID_utilizador)
                                        Log.i(
                                            "teste_login4",
                                            loginFragment().ID_utilizador.toString()
                                        ); //(information)
                                        Log.i(
                                            "teste_login7",
                                            mSB_ID_utilizador.toString()
                                        ); //(information)
                                        Log.i(
                                            "teste_login8",
                                            view.id_utilizador_placeholder.text.toString()
                                        ); //(information)
                                        Navegar(mStringBuilder,view,loginFragment().ID_utilizador)
                                    }
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                            }
                        }
                        loginFragment().myRef.addListenerForSingleValueEvent(get_data_firebase)
                        // ...
                    }else if (!isOnline(loginFragment().requireContext())) {

                        // YourSpotViewModel
                        mYourSpotViewModel =
                            ViewModelProvider(loginFragment()).get(ulp.pt.yourspot.data.view_model.YourSpotViewModel::class.java)
                        var resultadoLogin = mYourSpotViewModel.getAutenticacao(email, senha)
                        if (resultadoLogin.isEmpty()) {
                            Alertas().alertaDeTexto(loginFragment().requireContext(), "Você ainda não está cadastrado!")
                        } else {

                            //iniciar session
                            var ID_utilizador = mYourSpotViewModel.getID_utilizador(email, senha)
                            Log.i("teste_login1", ID_utilizador.toString()); //(information)
                            var botaoacao = view.bota_entrar_login
                            var direcao = loginFragmentDirections.actionLoginFragmentToHomePageFragment(ID_utilizador)
                            Navegacao().navegarPara(botaoacao, direcao)

                            Alertas().alertaDeTexto(loginFragment().requireContext(), "Login efetuado com sucesso! Você está no modo OFFLINE!")
                        }
                    }

                }else if(!checarInput(email, senha)){
                    Alertas().alertaDeTexto(loginFragment().requireContext(), "Porfavor, preencha todos os campos!")
                }
            }

        }


    }


///// FIM REPOSITORIO ///////
}