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
import kotlinx.android.synthetic.main.fragment_explorar_livros.view.*
import kotlinx.android.synthetic.main.fragment_explorar_textos.view.*
import ulp.pt.yourspot.R
import ulp.pt.yourspot.data.modelo_de_dados.entidades.PublicarLivro
import ulp.pt.yourspot.data.view_model.YourSpotViewModel
import ulp.pt.yourspot.fragments.adapters.ExplorarLivroAdapter

class ExplorarLivrosFragment : Fragment() {

    // Variavel responsavel pelo armazenamento da variavel passada por argumento
    private val args by navArgs<ExplorarLivrosFragmentArgs>()

    // Variavel encarregada do view model
    lateinit var mYourSpotViewModel: YourSpotViewModel
 
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_explorar_livros, container, false)

        // Variavel responsavel pelo armazenamento da variavel passada por argumento
        var ID_utilizador = args.IDUtilizador

        // Seta Retorno
        view.seta_retorno_explorar_livros.setOnClickListener{
            var botaoacao = view.seta_retorno_explorar_livros
            YourSpotFragmentsRepository.Navegacao().paginaAnterior(botaoacao)
        }

        // RecyclerView
        val adapter = ExplorarLivroAdapter(requireContext())
        val recyclerView = view.recyclerview_explorar_livros
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

       // YourSpotViewModel
        mYourSpotViewModel = ViewModelProvider(this).get(YourSpotViewModel::class.java)
        mYourSpotViewModel.lerPublicarLivroData().observe(viewLifecycleOwner, Observer {
            var ID_utilizador = args.IDUtilizador
            adapter.setData(it,ID_utilizador,"ExplorarLivrosFragment")
            adapter.notifyDataSetChanged()
        })

        view.novo_livro.setOnClickListener{
            var botaoacao = view.novo_livro
            val direcao = ExplorarLivrosFragmentDirections.actionExplorarLivrosFragmentToPublicarLivros(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }


        // Barra de ações
        view.iconepesquisa_explorar_livros.setOnClickListener{
            var botaoacao = view.iconepesquisa_explorar_livros
            var direcao = ExplorarLivrosFragmentDirections.actionExplorarLivrosFragmentToFeedConhecimentoFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        view.iconecoracao_explorar_livros.setOnClickListener{
            var botaoacao = view.iconecoracao_explorar_livros
            var direcao = ExplorarLivrosFragmentDirections.actionExplorarLivrosFragmentToPostagensCurtidasFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        view.iconemensagem_explorar_livros.setOnClickListener{
            var botaoacao = view.iconemensagem_explorar_livros
            var direcao = ExplorarLivrosFragmentDirections.actionExplorarLivrosFragmentToMensagensFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        view.iconecasa_explorar_livros.setOnClickListener{
            var botaoacao = view.iconecasa_explorar_livros
            var direcao = ExplorarLivrosFragmentDirections.actionExplorarLivrosFragmentToHomePageFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)

        }

        return view
    }

}