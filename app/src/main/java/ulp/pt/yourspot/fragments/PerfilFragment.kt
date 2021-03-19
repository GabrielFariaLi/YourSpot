package ulp.pt.yourspot.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_home_page.view.*
import kotlinx.android.synthetic.main.fragment_pagina_perfil.view.*
import ulp.pt.yourspot.R

class PerfilFragment: Fragment() {

    // Variavel responsavel pelo armazenamento da variavel passada por argumento
    private val args by navArgs<PerfilFragmentArgs>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pagina_perfil, container, false)

        //val ID_utilizador = args.IDUtilizador

        view.seta_retorno_perfil.setOnClickListener{
            var botaoacao = view.seta_retorno_perfil
            YourSpotFragmentsRepository.Navegacao().paginaAnterior(botaoacao)
        }

        // Barra de ações
        view.iconepesquisa_perfil.setOnClickListener{
            var botaoacao = view.iconepesquisa_perfil
            var direcao = PerfilFragmentDirections.actionPerfilFragmentToFeedConhecimentoFragment(0)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        view.iconecoracao_perfil.setOnClickListener{
            var botaoacao = view.iconecoracao_perfil
            var direcao = PerfilFragmentDirections.actionPerfilFragmentToPostagensCurtidasFragment(0)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        view.iconemensagem_perfil.setOnClickListener{
            var botaoacao = view.iconemensagem_perfil
            var direcao = PerfilFragmentDirections.actionPerfilFragmentToMensagensFragment(0)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }
        view.iconecasa_perfil.setOnClickListener{
            var botaoacao = view.iconecasa_perfil
            var direcao = PerfilFragmentDirections.actionPerfilFragmentToHomePageFragment(0)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }
        return view
    }
}