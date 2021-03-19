package ulp.pt.yourspot.fragments.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.exercicio_customizado.view.*
import ulp.pt.yourspot.R
import ulp.pt.yourspot.data.modelo_de_dados.entidades.PublicarLivro

class ExplorarLivroAdapter(private val context: Context): RecyclerView.Adapter<ExplorarLivroAdapter.MinhaViewHolder>() {

    // Tem uma lista tipo nosso modelo de classes (utilizadores)
    private var lista_livro = mutableListOf<PublicarLivro>()
    private var lista_id = emptyList<Int>()
    private var lista_fragment = "teste"

    inner class MinhaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(livro:PublicarLivro){
            Glide.with(context).load(livro.imagem).into(itemView.imagem_exercicio_template)
            itemView.titulo_exercicio_template.text = livro.titulo
            itemView.descricao_exercicio_template.text = livro.descricao
            itemView.tema_exercicio_template.text = livro.genero
        }
    }

    // Vamos inflar essa view holder criada
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MinhaViewHolder {

        return MinhaViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.exercicio_customizado, parent, false))

    }

    override fun getItemCount(): Int {
        return if(lista_livro.size > 0) {
            lista_livro.size
        }else {
            0
        }
    }

    override fun onBindViewHolder(guardar_linha_customizada: MinhaViewHolder, posicao: Int) {
        val item_atual = lista_livro[posicao]
        guardar_linha_customizada.bindView(item_atual)
        /*
        val id_do_visualizador = lista_id[0]
        val pagina = lista_fragment
        // Encontrar o id
        guardar_linha_customizada.itemView.id_servico_txt.text = item_atual.ID_servico.toString()
        val id_do_anunciante = item_atual.ID_utilizador
        guardar_linha_customizada.itemView.id_utilizador_txt_servico.text = item_atual.ID_utilizador.toString()


        //guardar_linha_customizada.itemView.imagemServico_imageView.background = item_atual.imagem.toString()
        guardar_linha_customizada.itemView.tituloServico_txt.text = item_atual.titulo
        guardar_linha_customizada.itemView.precoServico_txt.text = item_atual.preco.toString()
        guardar_linha_customizada.itemView.cidadeServico_txt.text = item_atual.localizacao
        guardar_linha_customizada.itemView.horarioPublicacao_txt.text = item_atual.data.toString()
        guardar_linha_customizada.itemView.linha_servicos_layout.setOnClickListener {

            if (pagina == "ExplorarFragment") {
                val acao = explorarFragmentDirections.actionExplorarFragmentToAnuncioFragment(id_do_visualizador, item_atual.ID_servico, id_do_anunciante)
                guardar_linha_customizada.itemView.findNavController().navigate(acao)
            } else if (pagina == "ExplorarFiltradoFragment") {
                val acao = explorarFiltradoFragmentDirections.actionExplorarFiltradoFragmentToAnuncioFragment(id_do_visualizador, item_atual.ID_servico, id_do_anunciante)
                guardar_linha_customizada.itemView.findNavController().navigate(acao)
            }else if (pagina == "meusServicosFragment"){
                val acao = meuServicosFragmentDirections.actionMeuServicosFragmentToAnuncioFragment(id_do_visualizador,item_atual.ID_servico,id_do_anunciante)
                guardar_linha_customizada.itemView.findNavController().navigate(acao)
            }

        }*/
    }

    fun setData(data_livro: MutableList<PublicarLivro>, idutilizador: Int, fragment: String) {
        this.lista_livro = data_livro
        this.lista_id = listOf(idutilizador)
        this.lista_fragment = fragment
        notifyDataSetChanged()
    }
}