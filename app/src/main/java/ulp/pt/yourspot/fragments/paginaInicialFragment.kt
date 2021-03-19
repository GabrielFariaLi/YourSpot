package ulp.pt.yourspot.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_pagina_inicial.*
import kotlinx.android.synthetic.main.fragment_pagina_inicial.view.*
import ulp.pt.yourspot.R


class paginaInicialFragment : Fragment() {

    var ID_utilizador = 0;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pagina_inicial, container, false)
        

        view.criar_conta_button.setOnClickListener{
            var botaoacao = view.criar_conta_button
            var direcao = R.id.action_paginaInicialFragment_to_cadastroFragment
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        view.ja_tem_uma_conta.setOnClickListener{
            var botaoacao = view.ja_tem_uma_conta
            var direcao = R.id.action_paginaInicialFragment_to_loginFragment
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        return view
    }

}