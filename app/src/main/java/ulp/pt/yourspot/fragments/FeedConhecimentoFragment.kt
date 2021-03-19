package ulp.pt.yourspot.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_area_academica.view.*
import kotlinx.android.synthetic.main.fragment_explorar_exercicios.view.*
import kotlinx.android.synthetic.main.fragment_explorar_livros.view.*
import kotlinx.android.synthetic.main.fragment_feed_conhecimento.view.*
import kotlinx.android.synthetic.main.fragment_home_page.view.*
import ulp.pt.yourspot.R
import ulp.pt.yourspot.data.view_model.YourSpotViewModel
import ulp.pt.yourspot.fragments.adapters.ExplorarExercicioAdapter
import ulp.pt.yourspot.fragments.adapters.ExplorarLivroAdapter
import ulp.pt.yourspot.fragments.adapters.ExplorarTextoAdapter

class FeedConhecimentoFragment : Fragment() {

    // Variavel responsavel pelo armazenamento da variavel passada por argumento
    private val args by navArgs<FeedConhecimentoFragmentArgs>()

    // Variavel encarregada do view model
    lateinit var mYourSpotViewModel: YourSpotViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_feed_conhecimento, container, false)

        // Seta o ID do utilizador atual, saberemos assim se o utilizador está logado ou não, e seu
        // ID correspondente a base de dados do YourSpot.
        var ID_utilizador = args.IDUtilizador

        view.seta_retorno_feedconhecimento.setOnClickListener{
            var botaoacao = view.seta_retorno_feedconhecimento
            YourSpotFragmentsRepository.Navegacao().paginaAnterior(botaoacao)
        }

        /*-------------------------------FEED CONHECIMENTO---------------------------------------*/
        /*-------------------------------Exercicio---------------------------------------*/
        // RecyclerView
        val adapter_exercicio = ExplorarExercicioAdapter(requireContext())
        val recyclerView_exercicio = view.recyclerview_feed_exercicio
        recyclerView_exercicio.adapter = adapter_exercicio
        recyclerView_exercicio.layoutManager = LinearLayoutManager(requireContext())

        // YourSpotViewModel
        mYourSpotViewModel = ViewModelProvider(this).get(YourSpotViewModel::class.java)
        mYourSpotViewModel.lerPublicarExercicioData().observe(viewLifecycleOwner, Observer {
            var ID_utilizador = args.IDUtilizador
            adapter_exercicio.setData(it,ID_utilizador,"FeedConhecimento")
            adapter_exercicio.notifyDataSetChanged()
        })

        /*-------------------------------Livro---------------------------------------*/
        // RecyclerView
        val adapter_livro = ExplorarLivroAdapter(requireContext())
        val recyclerView_livro = view.recyclerview_feed_livro
        recyclerView_livro.adapter = adapter_livro
        recyclerView_livro.layoutManager = LinearLayoutManager(requireContext())

        // YourSpotViewModel
        mYourSpotViewModel = ViewModelProvider(this).get(YourSpotViewModel::class.java)
        mYourSpotViewModel.lerPublicarLivroData()                                        .observe(viewLifecycleOwner, Observer {
            var ID_utilizador = args.IDUtilizador
            adapter_livro.setData(it,ID_utilizador,"FeedConhecimento")
            adapter_livro.notifyDataSetChanged()
        })


        /*-------------------------------Texto---------------------------------------*/
        // RecyclerView
        val adapter_texto = ExplorarTextoAdapter(requireContext())
        val recyclerView_texto = view.recyclerview_feed_texto
        recyclerView_texto.adapter = adapter_texto
        recyclerView_texto.layoutManager = LinearLayoutManager(requireContext())

        // YourSpotViewModel
        mYourSpotViewModel = ViewModelProvider(this).get(YourSpotViewModel::class.java)
        mYourSpotViewModel.lerPublicarTextoData().observe(viewLifecycleOwner, Observer {
            var ID_utilizador = args.IDUtilizador
            adapter_texto.setData(it,ID_utilizador,"FeedConhecimento")
            adapter_texto.notifyDataSetChanged()
        })


        // Barra de ações
        view.iconepesquisa_feed.setOnClickListener{
            Toast.makeText(requireContext(),"Você já se encontra aqui :)", Toast.LENGTH_LONG).show()
            YourSpotFragmentsRepository.Alertas().alertaPaginaAtual(this.requireContext())
        }

        view.iconecoracao_feed.setOnClickListener{
            var botaoacao = view.iconecoracao_feed
            var direcao = FeedConhecimentoFragmentDirections.actionFeedConhecimentoFragmentToPostagensCurtidasFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        view.iconemensagem_feed.setOnClickListener{
            var botaoacao = view.iconemensagem_feed
            var direcao = FeedConhecimentoFragmentDirections.actionFeedConhecimentoFragmentToMensagensFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        view.iconecasa_feed.setOnClickListener{
            var botaoacao = view.iconecasa_feed
            var direcao = FeedConhecimentoFragmentDirections.actionFeedConhecimentoFragmentToHomePageFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }


        return view
    }

}