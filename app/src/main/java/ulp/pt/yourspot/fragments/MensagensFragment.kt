package ulp.pt.yourspot.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_pagina_mensagens.view.*
import kotlinx.android.synthetic.main.fragment_pagina_postagenscurtidas.view.*
import ulp.pt.yourspot.R

class MensagensFragment : Fragment()  {
    // Variavel responsavel pelo armazenamento da variavel passada por argumento
    private val args by navArgs<MensagensFragmentArgs>()

     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pagina_mensagens, container, false)

         // Seta o ID do utilizador atual, saberemos assim se o utilizador está logado ou não, e seu
         // ID correspondente a base de dados do YourSpot.
         var ID_utilizador = args.IDUtilizador

         view.seta_retorno_pagina_mensagens.setOnClickListener{
             view.seta_retorno_pagina_mensagens.findNavController().popBackStack()
         }

         // Barra de ações
         view.iconepesquisa_mensagens.setOnClickListener{
             val acao = MensagensFragmentDirections.actionMensagensFragmentToFeedConhecimentoFragment(ID_utilizador)
             view.iconepesquisa_mensagens.findNavController().navigate(acao)
         }

         view.iconecoracao_mensagens.setOnClickListener{
             val acao = MensagensFragmentDirections.actionMensagensFragmentToPostagensCurtidasFragment(ID_utilizador)
             view.iconecoracao_mensagens.findNavController().navigate(acao)
         }

         view.iconemensagem_mensagens.setOnClickListener{
             Toast.makeText(requireContext(),"Você já se encontra aqui :)", Toast.LENGTH_LONG).show()
         }

         view.iconecasa_mensagens.setOnClickListener{
             val acao = MensagensFragmentDirections.actionMensagensFragmentToHomePageFragment(ID_utilizador)
             view.iconecasa_mensagens.findNavController().navigate(acao)
         }

        return view
    }
}