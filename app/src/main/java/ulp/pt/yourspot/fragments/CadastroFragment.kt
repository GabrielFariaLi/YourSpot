package ulp.pt.yourspot.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_cadastro.view.*
import ulp.pt.yourspot.R
import ulp.pt.yourspot.data.modelo_de_dados.entidades.Utilizador
import ulp.pt.yourspot.data.view_model.YourSpotViewModel
import java.text.SimpleDateFormat
import java.util.*


class CadastroFragment : Fragment() {

    // Seta o ID do utilizador atual, saberemos assim se o utilizador está logado ou não, e seu
    // ID correspondente a base de dados do YourSpot
    var ID_utilizador = 0

    // FireBaseDataBase
    val database = FirebaseDatabase.getInstance("https://yourspot-300e2-default-rtdb.europe-west1.firebasedatabase.app/")
    val myRef = database.reference

    // FireBaseAuthenticator
    lateinit var auth: FirebaseAuth
    var connected = false



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cadastro, container, false)


        // Inicializações de variaveis e argumentos
        lateinit var mYourSpotViewModel: YourSpotViewModel
        val date = Calendar.getInstance().time
        var formato_data_publicacao = SimpleDateFormat("EEE, d MMM yyyy H:m", Locale.getDefault())
        auth = FirebaseAuth.getInstance()



        view.botao_cadastrar.setOnClickListener{
            var nome_cadastro = view.inputnome.text.toString()
            var email_cadastro = view.inputemail.text.toString()
            var senha_cadastro = view.inputsenha.text.toString()
            var senha_confirmacao_cadastro = view.inputconfirmarsenha.text.toString()
            if(checarInput(
                    nome_cadastro,
                    email_cadastro,
                    senha_cadastro,
                    senha_confirmacao_cadastro
                )) {




                // YourSpotViewModel
                mYourSpotViewModel = ViewModelProvider(this).get(ulp.pt.yourspot.data.view_model.YourSpotViewModel::class.java)


                //inputs nao estão vazios podemos cadastrar os dados:
                var utilizador = Utilizador(
                    ID_utilizador,
                    nome_cadastro,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    email_cadastro,
                    senha_cadastro,
                    formato_data_publicacao.format(
                        date
                    )
                )

                mYourSpotViewModel.inserirUtilizador(utilizador)


                var ultimo_ID_utilizador = mYourSpotViewModel.getUltimoID_utilizador()
                var ID_utilizador = ultimo_ID_utilizador + 1
                utilizador = Utilizador(
                    ID_utilizador,
                    nome_cadastro,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    email_cadastro,
                    senha_cadastro,
                    formato_data_publicacao.format(
                        date
                    )
                )


                myRef.child("Utilizador").child(ID_utilizador.toString()).setValue(utilizador)
                auth.createUserWithEmailAndPassword(email_cadastro, senha_cadastro)
                Log.i("teste_Cadastro", ID_utilizador.toString()); //(information)
                if(ID_utilizador.toString().isEmpty()){
                    YourSpotFragmentsRepository.Alertas().alertaDeTexto(this.requireContext(), "Já existe alguem cadastrado com o email $email_cadastro")
                }else {
                    //iniciar session
                    //iniciar session
                    Log.i("teste_cadastro2", ID_utilizador.toString()); //(information)
                    var botaoacao = view.botao_cadastrar
                    var direcao = CadastroFragmentDirections.actionCadastroFragmentToHomePageFragment(ID_utilizador)
                    YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
                    YourSpotFragmentsRepository.Alertas().alertaDeTexto(this.requireContext(), "Cadastro efetuado com sucesso!")

                }
            }else {
                YourSpotFragmentsRepository.Alertas().alertaDeTexto(this.requireContext(), "Porfavor, preencha todos os campos!")
            }
        }

        view.texto_possuiconta.setOnClickListener{
            var botaoacao = view.texto_possuiconta
            var direcao = R.id.action_cadastroFragment_to_loginFragment
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        return view
    }

    //verifica se os editText não foram preenchidos
    private fun checarInput(nome: String, email: String, senha: String, senha_confirmacao: String): Boolean{
        return !(TextUtils.isEmpty(email) || TextUtils.isEmpty(senha) || TextUtils.isEmpty(nome) || TextUtils.isEmpty(
            senha_confirmacao
        ))
    }

}