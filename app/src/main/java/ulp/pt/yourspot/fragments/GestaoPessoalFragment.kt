package ulp.pt.yourspot.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_area_academica.view.*
import kotlinx.android.synthetic.main.fragment_gestao_pessoal.view.*
import kotlinx.android.synthetic.main.fragment_home_page.view.*
import ulp.pt.yourspot.R

class GestaoPessoalFragment: Fragment() {

    // Variavel responsavel pelo armazenamento da variavel passada por argumento
    private val args by navArgs<GestaoPessoalFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_gestao_pessoal, container, false)

        // Seta o ID do utilizador atual, saberemos assim se o utilizador está logado ou não, e seu
        // ID correspondente a base de dados do YourSpot.
        var ID_utilizador = args.IDUtilizador

        view.seta_retorno_gestaopessoal.setOnClickListener{
            var botaoacao = view.seta_retorno_gestaopessoal
            YourSpotFragmentsRepository.Navegacao().paginaAnterior(botaoacao)
        }

        view.nova_pag.setOnClickListener{
            YourSpotFragmentsRepository.Alertas().alertaNaoImplementado(this.requireContext());
        }

        // Barra de ações
        view.iconepesquisa_gestaopessoal.setOnClickListener{
            var botaoacao = view.iconepesquisa_gestaopessoal
            var direcao = GestaoPessoalFragmentDirections.actionGestaoPessoalFragmentToFeedConhecimentoFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        view.iconecoracao_gestaopessoal.setOnClickListener{
            var botaoacao = view.iconecoracao_gestaopessoal
            var direcao = GestaoPessoalFragmentDirections.actionGestaoPessoalFragmentToPostagensCurtidasFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        view.iconemensagem_gestaopessoal.setOnClickListener{
            var botaoacao = view.iconemensagem_gestaopessoal
            var direcao = GestaoPessoalFragmentDirections.actionGestaoPessoalFragmentToMensagensFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        view.iconecasa_gestaopessoal.setOnClickListener{
            var botaoacao = view.iconecasa_gestaopessoal
            var direcao = GestaoPessoalFragmentDirections.actionGestaoPessoalFragmentToHomePageFragment(ID_utilizador)
            YourSpotFragmentsRepository.Navegacao().navegarPara(botaoacao, direcao)
        }

        return view
    }
}