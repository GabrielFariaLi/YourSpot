package ulp.pt.yourspot.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_cadastro.view.*
import kotlinx.android.synthetic.main.fragment_home_page.view.*
import ulp.pt.yourspot.R



class HomePageFragment : Fragment() {

    // Variavel responsavel pelo armazenamento da variavel passada por argumento
    private val args by navArgs<HomePageFragmentArgs>()

    lateinit var navigationView: NavigationView

    // FireBaseDataBase
    val database = FirebaseDatabase.getInstance("https://yourspot-300e2-default-rtdb.europe-west1.firebasedatabase.app/")
    val myRef = database.getReference("Utilizador")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Seta o ID do utilizador atual, saberemos assim se o utilizador está logado ou não, e seu
        // ID correspondente a base de dados do YourSpot.
        var ID_utilizador = args.IDUtilizador
        /*navigationView = findViewById(R.id.hamburger_menu_yourspot)
        navigationView.menu.findItem(R.id.perfil_nav).isVisible = false;*/

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_page, container, false)
        /*//get data from firebase
        var get_data_firebase = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
             var mStringBuilder = StringBuilder()
                for (i in snapshot.children){

                        var primeiro_nome = i.child("senha").value
                        var email = i.child("email").getValue()
                        mStringBuilder.append("\n${i.key}\n$primeiro_nome\n$email")
                        Log.i("primeiro_nome", primeiro_nome.toString()); //(information)


                }
                view.onde_todas_.text = mStringBuilder
            }

            override fun onCancelled(error: DatabaseError) {

            }

        }
        myRef.addValueEventListener(get_data_firebase)
        myRef.addListenerForSingleValueEvent(get_data_firebase)
        */
        //view.onde_todas_.text = myRef.child("Utilizador").child("3").get().toString()
        Log.i("teste_homepage1", ID_utilizador.toString()); //(information)


        /*-------------------------------Area Gestão pessoal--------------------------------------*/
        view.frame_area_gestao_pessoal.setOnClickListener{
            var botaoacao = view.frame_area_gestao_pessoal
            var direcao = HomePageFragmentDirections.actionHomePageFragmentToGestaoPessoalFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        /*-----------------------------------Area Academica--------------------------------------*/
        view.frame_area_academica.setOnClickListener{
            var botaoacao = view.frame_area_academica
            var direcao = HomePageFragmentDirections.actionHomePageFragmentToAreaAcademicaFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }


        // Barra de ações
        view.iconepesquisa_homepage.setOnClickListener{
            var botaoacao = view.iconepesquisa_homepage
            var direcao = HomePageFragmentDirections.actionHomePageFragmentToFeedConhecimentoFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        view.iconecoracao_homepage.setOnClickListener{
            var botaoacao = view.iconepesquisa_homepage
            var direcao = HomePageFragmentDirections.actionHomePageFragmentToPostagensCurtidasFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        view.iconecasa_homepage.setOnClickListener{
            YourSpotFragmentsRepository.Alertas().alertaPaginaAtual(this.requireContext())
        }

        view.iconemensagem_homepage.setOnClickListener{
            var botaoacao = view.iconemensagem_homepage
            var direcao = HomePageFragmentDirections.actionHomePageFragmentToMensagensFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        return view
    }

    /*fun navegarFragment(view_des:View,action:Int) {
        view_des.findNavController().navigate(action)
    }*/


}
