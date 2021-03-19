package ulp.pt.yourspot.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_area_academica.view.*
import kotlinx.android.synthetic.main.fragment_home_page.view.*
import ulp.pt.yourspot.R

class AreaAcademicaFragment : Fragment() {


    // Variavel responsavel pelo armazenamento da variavel passada por argumento
    private val args by navArgs<AreaAcademicaFragmentArgs>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_area_academica, container, false)

        // Seta o ID do utilizador atual, saberemos assim se o utilizador está logado ou não, e seu
        // ID correspondente a base de dados do YourSpot.
        var ID_utilizador = args.IDUtilizador

        view.seta_retorno_area_academica.setOnClickListener{
            var botaoacao = view.seta_retorno_area_academica
            YourSpotFragmentsRepository.Navegacao().paginaAnterior(botaoacao)
        }
        /* Navegação entre o feed, explorar exercicios,textos ou livros */
        view.explorar_feed_button.setOnClickListener{
            var botaoacao = view.explorar_feed_button
            var direcao = AreaAcademicaFragmentDirections.actionAreaAcademicaFragmentToFeedConhecimentoFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }
        view.frame_exerc.setOnClickListener{
            var botaoacao = view.frame_exerc
            var direcao = AreaAcademicaFragmentDirections.actionAreaAcademicaFragmentToExplorarExerciciosFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        view.frame_livro.setOnClickListener{
            var botaoacao = view.frame_livro
            var direcao = AreaAcademicaFragmentDirections.actionAreaAcademicaFragmentToExplorarLivrosFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }
        view.frame_texto.setOnClickListener{
            var botaoacao = view.frame_texto
            var direcao = AreaAcademicaFragmentDirections.actionAreaAcademicaFragmentToExplorarTextosFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        /* Barra de ações*/
        view.iconepesquisa_area_academica.setOnClickListener{
            var botaoacao = view.iconepesquisa_area_academica
            var direcao = AreaAcademicaFragmentDirections.actionAreaAcademicaFragmentToFeedConhecimentoFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        view.iconecoracao_area_academica.setOnClickListener{
            var botaoacao = view.iconecoracao_area_academica
            var direcao = AreaAcademicaFragmentDirections.actionAreaAcademicaFragmentToPostagensCurtidasFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        view.iconemensagem_area_academica.setOnClickListener{
            var botaoacao = view.iconemensagem_area_academica
            var direcao = AreaAcademicaFragmentDirections.actionAreaAcademicaFragmentToMensagensFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        view.iconecasa_area_academica.setOnClickListener{
            var botaoacao = view.iconecasa_area_academica
            var direcao = AreaAcademicaFragmentDirections.actionAreaAcademicaFragmentToHomePageFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }


        return view
    }

}