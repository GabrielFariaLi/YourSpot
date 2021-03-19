package ulp.pt.yourspot.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_gestao_pessoal.view.*
import kotlinx.android.synthetic.main.fragment_pagina_postagenscurtidas.view.*
import ulp.pt.yourspot.R

class PostagensCurtidasFragment : Fragment() {

    // Variavel responsavel pelo armazenamento da variavel passada por argumento
    private val args by navArgs<PostagensCurtidasFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pagina_postagenscurtidas, container, false)

        // Seta o ID do utilizador atual, saberemos assim se o utilizador está logado ou não, e seu
        // ID correspondente a base de dados do YourSpot.
        var ID_utilizador = args.IDUtilizador

        view.seta_retorno_postagens_curtidas.setOnClickListener{
            var botaoacao = view.seta_retorno_postagens_curtidas
            YourSpotFragmentsRepository.Navegacao().paginaAnterior(botaoacao)
        }

        // Barra de ações
        view.iconepesquisa_postagens_curtidas.setOnClickListener{
            var botaoacao = view.iconepesquisa_postagens_curtidas
            var direcao = PostagensCurtidasFragmentDirections.actionPostagensCurtidasFragmentToFeedConhecimentoFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        view.iconecoracao_postagens_curtidas.setOnClickListener{
            YourSpotFragmentsRepository.Alertas().alertaPaginaAtual(this.requireContext())
        }

        view.iconemensagem_postagens_curtidas.setOnClickListener{
            var botaoacao = view.iconemensagem_postagens_curtidas
            var direcao = PostagensCurtidasFragmentDirections.actionPostagensCurtidasFragmentToMensagensFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        view.iconecasa_postagens_curtidas.setOnClickListener{
            var botaoacao = view.iconecasa_postagens_curtidas
            var direcao = PostagensCurtidasFragmentDirections.actionPostagensCurtidasFragmentToHomePageFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }
        return view
    }
}