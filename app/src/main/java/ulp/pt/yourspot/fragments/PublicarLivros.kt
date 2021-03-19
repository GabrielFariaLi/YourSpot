package ulp.pt.yourspot.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_explorar_livros.view.*
import kotlinx.android.synthetic.main.fragment_feed_conhecimento.view.*
import kotlinx.android.synthetic.main.fragment_publicar_livros.view.*
import ulp.pt.yourspot.fragments.PublicarExerciciosArgs
import ulp.pt.yourspot.fragments.PublicarLivrosArgs
import ulp.pt.yourspot.fragments.PublicarLivrosDirections
import ulp.pt.yourspot.R

class PublicarLivros : Fragment() {
    // Variavel responsavel pelo armazenamento da variavel passada por argumento
    private val args by navArgs<PublicarLivrosArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_publicar_livros, container, false)
        val ID_utilizador = args.IDUtilizador

        view.seta_retorno_publicar_livros.setOnClickListener{
            val acao = PublicarLivrosDirections.actionPublicarLivrosToExplorarLivrosFragment(ID_utilizador)
            view.seta_retorno_publicar_livros.findNavController().navigate(acao)
        }


        // Barra de ações
        view.iconepesquisa_publicar_livros.setOnClickListener{
            val acao = PublicarLivrosDirections.actionPublicarLivrosToFeedConhecimentoFragment(ID_utilizador)
            view.iconepesquisa_publicar_livros.findNavController().navigate(acao)
        }

        view.iconecoracao_publicar_livros.setOnClickListener{
            val acao = PublicarLivrosDirections.actionPublicarLivrosToPostagensCurtidasFragment(ID_utilizador)
            view.iconecoracao_publicar_livros.findNavController().navigate(acao)
        }

        view.iconemensagem_publicar_livros.setOnClickListener{
            val acao = PublicarLivrosDirections.actionPublicarLivrosToMensagensFragment(ID_utilizador)
            view.iconemensagem_publicar_livros.findNavController().navigate(acao)
        }

        view.iconecasa_publicar_livros.setOnClickListener{
            val acao = PublicarLivrosDirections.actionPublicarLivrosToHomePageFragment(ID_utilizador)
            view.iconecasa_publicar_livros.findNavController().navigate(acao)
        }
        return view
    }

}