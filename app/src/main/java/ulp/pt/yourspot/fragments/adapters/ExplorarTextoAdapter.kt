package ulp.pt.yourspot.fragments.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.exercicio_customizado.view.*
import ulp.pt.yourspot.R
import ulp.pt.yourspot.data.modelo_de_dados.entidades.PublicarTexto

class ExplorarTextoAdapter(private val context: Context): RecyclerView.Adapter<ExplorarTextoAdapter.MinhaViewHolder>() {

    // Tem uma lista tipo nosso modelo de classes (utilizadores)
    private var lista_texto = mutableListOf<PublicarTexto>()
    private var lista_id = emptyList<Int>()
    private var lista_fragment = "teste"

    inner class MinhaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        
        fun bindView(texto:PublicarTexto){

            Glide.with(context).load(texto.imagem).into(itemView.imagem_exercicio_template)
            itemView.titulo_exercicio_template.text = texto.titulo
            itemView.descricao_exercicio_template.text = texto.descricao
            itemView.tema_exercicio_template.text = texto.genero

        }
    }

    // Vamos inflar essa view holder criada
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MinhaViewHolder {

        return MinhaViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.exercicio_customizado, parent, false))

    }

    override fun getItemCount(): Int {
        
        return if(lista_texto.size > 0) {
            lista_texto.size
        }else {
            0
        }

    }

    override fun onBindViewHolder(guardar_linha_customizada: MinhaViewHolder, posicao: Int) {

        val item_atual = lista_texto[posicao]
        guardar_linha_customizada.bindView(item_atual)

    }

    fun setData(data_texto: MutableList<PublicarTexto>, idutilizador: Int, fragment: String) {

        this.lista_texto = data_texto

        this.lista_id = listOf(idutilizador)
        
        this.lista_fragment = fragment
        
        notifyDataSetChanged()
    }
}