package ulp.pt.yourspot.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_explorar_exercicios.view.*
import kotlinx.android.synthetic.main.fragment_explorar_textos.*
import kotlinx.android.synthetic.main.fragment_explorar_textos.view.*
import ulp.pt.yourspot.R
import ulp.pt.yourspot.data.modelo_de_dados.entidades.PublicarTexto
import ulp.pt.yourspot.data.view_model.YourSpotViewModel
import ulp.pt.yourspot.fragments.adapters.ExplorarExercicioAdapter
import ulp.pt.yourspot.fragments.adapters.ExplorarTextoAdapter

class ExplorarTextosFragment : Fragment() {

    // Variavel responsavel pelo armazenamento da variavel passada por argumento
    private val args by navArgs<ExplorarTextosFragmentArgs>()


    // Variavel encarregada do view model
    lateinit var mYourSpotViewModel: YourSpotViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_explorar_textos, container, false)

        // Variavel responsavel pelo armazenamento da variavel passada por argumento
        var ID_utilizador = args.IDUtilizador

        // Seta Retorno
        view.seta_retorno_explorar_textos.setOnClickListener{
            var botaoacao = view.seta_retorno_explorar_textos
            YourSpotFragmentsRepository.Navegacao().paginaAnterior(botaoacao)
        }

        // RecyclerView
        val adapter = ExplorarTextoAdapter(requireContext())
        val recyclerView = view.recyclerview_explorar_textos
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // YourSpotViewModel
        mYourSpotViewModel = ViewModelProvider(this).get(YourSpotViewModel::class.java)
        mYourSpotViewModel.lerPublicarTextoData().observe(viewLifecycleOwner, Observer {
            var ID_utilizador = args.IDUtilizador
            adapter.setData(it,ID_utilizador,"ExplorarTextosFragment")
            adapter.notifyDataSetChanged()
        })

        view.novo_texto.setOnClickListener{
            var botaoacao = novo_texto
            val direcao = ExplorarTextosFragmentDirections.actionExplorarTextosFragmentToPublicarTextos(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        // Barra de ações
        view.iconepesquisa_explorar_textos.setOnClickListener{
            var botaoacao = view.iconepesquisa_explorar_textos
            var direcao = ExplorarTextosFragmentDirections.actionExplorarTextosFragmentToFeedConhecimentoFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        view.iconecoracao_explorar_textos.setOnClickListener{
            var botaoacao = view.iconecoracao_explorar_textos
            var direcao = ExplorarTextosFragmentDirections.actionExplorarTextosFragmentToPostagensCurtidasFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        view.iconemensagem_explorar_textos.setOnClickListener{
            var botaoacao = view.iconemensagem_explorar_textos
            var direcao = ExplorarTextosFragmentDirections.actionExplorarTextosFragmentToMensagensFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        view.iconecasa_explorar_textos.setOnClickListener{
            var botaoacao = view.iconecasa_explorar_textos
            var direcao = ExplorarTextosFragmentDirections.actionExplorarTextosFragmentToHomePageFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }



        return view
    }

}