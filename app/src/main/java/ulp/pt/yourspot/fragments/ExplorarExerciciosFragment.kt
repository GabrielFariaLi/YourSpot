package ulp.pt.yourspot.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import kotlinx.android.synthetic.main.fragment_explorar_exercicios.view.*
import kotlinx.android.synthetic.main.fragment_explorar_livros.view.*
import kotlinx.android.synthetic.main.fragment_feed_conhecimento.view.*
import kotlinx.android.synthetic.main.fragment_home_page.view.*
import ulp.pt.yourspot.fragments.ExplorarExerciciosFragmentArgs
import ulp.pt.yourspot.R
import ulp.pt.yourspot.data.modelo_de_dados.entidades.Exercicio
import ulp.pt.yourspot.data.modelo_de_dados.entidades.PublicarExercicio
import ulp.pt.yourspot.data.view_model.YourSpotViewModel
import ulp.pt.yourspot.fragments.adapters.ExplorarExercicioAdapter


class ExplorarExerciciosFragment : Fragment() {



    // Variavel responsavel pelo armazenamento da variavel passada por argumento
    private val args by navArgs<ExplorarExerciciosFragmentArgs>()

    // Variavel encarregada do view model
    lateinit var mYourSpotViewModel: YourSpotViewModel

    // FireBaseDataBase
    val database = FirebaseDatabase.getInstance("https://yourspot-300e2-default-rtdb.europe-west1.firebasedatabase.app/")
    val myRef = database.getReference("Exercicio")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_explorar_exercicios, container, false)
        var ID_utilizador = args.IDUtilizador

        // Seta Retorno
        view.seta_retorno_explorar_exercicios.setOnClickListener{
            var botaoacao = view.seta_retorno_explorar_exercicios
            YourSpotFragmentsRepository.Navegacao().paginaAnterior(botaoacao)
        }


        // RecyclerView
        val adapter = ExplorarExercicioAdapter(requireContext())
        val recyclerView = view.recyclerview_explorar_exercicios
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        // YourSpotViewModel
        mYourSpotViewModel = ViewModelProvider(this).get(YourSpotViewModel::class.java)
        mYourSpotViewModel.lerPublicarExercicioData().observe(viewLifecycleOwner, Observer {
            var ID_utilizador = args.IDUtilizador
            adapter.setData(it,ID_utilizador,"ExplorarExerciciosFragment")
            adapter.notifyDataSetChanged()
        })

        /*   val listTeste = mutableListOf<PublicarExercicio>()
             listTeste.add(PublicarExercicio(0,0,"11/03/2021","teste de titulo","teste de descricao","nenhum","https://st2.depositphotos.com/3725083/5484/i/600/depositphotos_54847433-stock-photo-magic-book-with-magic-lights.jpg","teste de tema"))
             listTeste.add(PublicarExercicio(0,0,"11/03/2021","teste de titulo2","teste de descricao","nenhum","https://st2.depositphotos.com/3725083/5484/i/600/depositphotos_54847433-stock-photo-magic-book-with-magic-lights.jpg","teste de tema"))
             */

        //adapter.setData(listTeste,ID_utilizador,"ExplorarExerciciosFragment")
        //adapter.notifyDataSetChanged()


        view.novo_exercicio.setOnClickListener{
            val acao = ExplorarExerciciosFragmentDirections.actionExplorarExerciciosFragmentToPublicarExercicios(ID_utilizador)
            view.novo_exercicio.findNavController().navigate(acao)
        }

        // Barra de ações
        view.iconepesquisa_explorar_exercicios.setOnClickListener{
            var botaoacao = view.iconepesquisa_explorar_exercicios
            var direcao = ExplorarExerciciosFragmentDirections.actionExplorarExerciciosFragmentToFeedConhecimentoFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        view.iconecoracao_explorar_exercicios.setOnClickListener{
            var botaoacao = view.iconecoracao_explorar_exercicios
            var direcao = ExplorarExerciciosFragmentDirections.actionExplorarExerciciosFragmentToPostagensCurtidasFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        view.iconemensagem_explorar_exercicios.setOnClickListener{
            var botaoacao = view.iconemensagem_explorar_exercicios
            var direcao = ExplorarExerciciosFragmentDirections.actionExplorarExerciciosFragmentToMensagensFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        view.iconecasa_explorar_exercicios.setOnClickListener{
            var botaoacao = view.iconecasa_explorar_exercicios
            var direcao = ExplorarExerciciosFragmentDirections.actionExplorarExerciciosFragmentToMensagensFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        return view
    }

}