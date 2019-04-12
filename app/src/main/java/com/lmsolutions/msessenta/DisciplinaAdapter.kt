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
import kotlinx.android.synthetic.main.adapter_disciplina.view.*

// define o construtor que recebe a lista de disciplinas e o evento de clique
class DisciplinaAdapter (
    val disciplinas: List<Disciplina>,
    val onClick: (Disciplina) -> Unit): RecyclerView.Adapter<DisciplinaAdapter.DisciplinasViewHolder>() {

    // ViewHolder com os elemetos da tela
    class DisciplinasViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cardNome: TextView
        val cardImg : ImageView
        val cardProgress: ProgressBar
        val cardView: CardView

        init {
            cardNome = view.cardNome //view.findViewById<TextView>(R.id.cardNome)
            cardImg = view.cardImg //view.findViewById<ImageView>(R.id.cardImg)
            cardProgress = view.cardProgress // view.findViewById<ProgressBar>(R.id.cardProgress)
            cardView = view.card_disciplinas // view.findViewById<CardView>(R.id.card_disciplinas)

        }

    }

    // Quantidade de disciplinas na lista

    override fun getItemCount() = this.disciplinas.size

    // inflar layout do adapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisciplinasViewHolder {
        // infla view no adapter
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_disciplina, parent, false)

        // retornar ViewHolder
        val holder = DisciplinasViewHolder(view)
        return holder
    }

    // bind para atualizar Views com os dados

    override fun onBindViewHolder(holder: DisciplinasViewHolder, position: Int) {
        val context = holder.itemView.context

        // recuperar objeto disciplina
        val disciplina = disciplinas[position]

        // atualizar dados de disciplina

        holder.cardNome.text = disciplina.nome
        holder.cardProgress.visibility = View.VISIBLE

        // download da imagem
        Picasso.with(context).load(disciplina.foto).fit().into(holder.cardImg,
                object: com.squareup.picasso.Callback{
                    override fun onSuccess() {
                        holder.cardProgress.visibility = View.INVISIBLE
                    }

                    override fun onError() {
                        holder.cardProgress.visibility = View.INVISIBLE
                    }
                })

        // adiciona evento de clique
        holder.itemView.setOnClickListener {onClick(disciplina)}
    }
}