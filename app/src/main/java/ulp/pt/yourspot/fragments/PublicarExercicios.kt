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
import kotlinx.android.synthetic.main.fragment_publicar_exercicios.view.*
import ulp.pt.yourspot.fragments.PublicarExerciciosArgs
import ulp.pt.yourspot.fragments.PublicarExerciciosDirections
import ulp.pt.yourspot.R

class PublicarExercicios : Fragment() {
    // Variavel responsavel pelo armazenamento da variavel passada por argumento
    private val args by navArgs<PublicarExerciciosArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_publicar_exercicios, container, false)
        val ID_utilizador = args.IDUtilizador

        view.seta_retorno_publicar_exercicios.setOnClickListener{
            val acao = PublicarExerciciosDirections.actionPublicarExerciciosToExplorarExerciciosFragment(ID_utilizador)
            view.seta_retorno_publicar_exercicios.findNavController().navigate(acao)
        }

        // Barra de ações
        view.iconepesquisa_publicar_exercicios.setOnClickListener{
            val acao = PublicarExerciciosDirections.actionPublicarExerciciosToFeedConhecimentoFragment(ID_utilizador)
            view.iconepesquisa_publicar_exercicios.findNavController().navigate(acao)
        }

        view.iconecoracao_publicar_exercicios.setOnClickListener{
            val acao = PublicarExerciciosDirections.actionPublicarExerciciosToPostagensCurtidasFragment(ID_utilizador)
            view.iconecoracao_publicar_exercicios.findNavController().navigate(acao)
        }

        view.iconemensagem_publicar_exercicios.setOnClickListener{
            val acao = PublicarExerciciosDirections.actionPublicarExerciciosToMensagensFragment(ID_utilizador)
            view.iconemensagem_publicar_exercicios.findNavController().navigate(acao)
        }

        view.iconecasa_publicar_exercicios.setOnClickListener{
            val acao = PublicarExerciciosDirections.actionPublicarExerciciosToHomePageFragment(ID_utilizador)
            view.iconecasa_publicar_exercicios.findNavController().navigate(acao)
        }
        return view
    }

}