package com.lmsolutions.msessenta

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_cadastro.view.*

// define o construtor que recebe a lista de cadastro e o evento de clique
class CadastroAdapter (
    val cadastro: List<Cadastro>,
    val onClick: (Cadastro) -> Unit): RecyclerView.Adapter<CadastroAdapter.CadastroViewHolder>() {

    // ViewHolder com os elemetos da tela
    class CadastroViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cardNome: TextView
        val cardImg : ImageView
        val cardProgress: ProgressBar
        val cardView: CardView

        init {
            cardNome = view.cardNome //view.findViewById<TextView>(R.id.cardNome)
            cardImg = view.cardImg //view.findViewById<ImageView>(R.id.cardImg)
            cardProgress = view.cardProgress // view.findViewById<ProgressBar>(R.id.cardProgress)
            cardView = view.card_cadastro // view.findViewById<CardView>(R.id.card_cadastro)

        }

    }

    // Quantidade de cadastro na lista

    override fun getItemCount() = this.cadastro.size

    // inflar layout do adapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CadastroViewHolder {
        // infla view no adapter
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_cadastro, parent, false)

        // retornar ViewHolder
        val holder = CadastroViewHolder(view)
        return holder
    }

    // bind para atualizar Views com os dados

    override fun onBindViewHolder(holder: CadastroViewHolder, position: Int) {
        val context = holder.itemView.context

        // recuperar objeto cadastro
        val cadastro = cadastro[position]

        // atualizar dados de cadastro

        holder.cardNome.text = cadastro.nome
        holder.cardNome.text = cadastro.endereco
        holder.cardProgress.visibility = View.VISIBLE

        // download da imagem
        Picasso.with(context).load(cadastro.foto).fit().into(holder.cardImg,
                object: com.squareup.picasso.Callback{
                    override fun onSuccess() {
                        holder.cardProgress.visibility = View.INVISIBLE
                    }

                    override fun onError() {
                        holder.cardProgress.visibility = View.INVISIBLE
                    }
                })

        // adiciona evento de clique
        holder.itemView.setOnClickListener {onClick(cadastro)}
    }
}