package com.lmsolutions.msessenta

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.drm.DrmStore
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.*
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import br.com.fernandosousa.lmsapp.DisciplinaService
import kotlinx.android.synthetic.main.activity_tela_inicial.*

class TelaInicialActivity() : DebugActivity(),
    NavigationView.OnNavigationItemSelectedListener {

    private val context: Context get() = this
    private var disciplinas = listOf<Disciplina>()
    var recyclerDisc: RecyclerView? = null
    constructor(context: Context) : this() {
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        //var params = intent.extras
        //val nome = intent.getStringExtra("nomeUsuario")

        // acessar parametros da intnet
        // intent é um atributo herdado de Activity
            val args = intent.extras
        // recuperar o parâmetro do tipo String
        val nome = args.getString("nome")

        // recuperar parâmetro simplificado
        val numero = intent.getIntExtra("nome", 0)

        Toast.makeText(context, "Parâmetro: $nome", Toast.LENGTH_LONG).show()
        Toast.makeText(context, "Numero: $numero", Toast.LENGTH_LONG).show()

       // val mensagem = findViewById<TextView>(R.id.mensagemInicial)
        //mensagem.text = "Bem vindo $nome"

        //val botaoSair = findViewById<Button>(R.id.botaoSair)
        //botaoSair.setOnClickListener { cliqueSair() }

        // colocar toolbar
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // alterar título da ActionBar
        supportActionBar?.title = "Disciplinas"

        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        configuraMenuLateral()

        // configurar cardview
        recyclerDisc = recyclerDisciplinas // findViewById<RecyclerView>(R.id.recyclerDisciplinas)
        recyclerDisc?.layoutManager = LinearLayoutManager(context)
        recyclerDisc?.itemAnimator = DefaultItemAnimator()
        recyclerDisc?.setHasFixedSize(true)

    }

    override fun onResume() {
        super.onResume()
        // task para recuperar as disciplinas
        taskDisciplinas()
    }

    // configurar as disciplinas
    fun taskDisciplinas() {
        this.disciplinas = DisciplinaService.getDisciplinas(context)
        // atualizar lista
        recyclerDisciplinas?.adapter = DisciplinaAdapter(disciplinas) {onClickDisciplina(it)}
    }

    fun cliqueSair() {
        val returnIntent = Intent();
        returnIntent.putExtra("result", "Saída do BrewerApp");
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    // tratamento do evento de clicar em uma disciplina
    fun onClickDisciplina(disciplina: Disciplina) {
        Toast.makeText(context, "Clicou disciplina ${disciplina.nome}", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, DisciplinaActivity::class.java)
        intent.putExtra("disciplina", disciplina)
        startActivity(intent)
    }

    // configuraçao do navigation Drawer com a toolbar
    private fun configuraMenuLateral() {
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        var menuLateral = findViewById<DrawerLayout>(R.id.layourMenuLateral)

        // ícone de menu (hamburger) para mostrar o menu
        var toogle = ActionBarDrawerToggle(this, menuLateral, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        menuLateral.addDrawerListener(toogle)
        toogle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.menu_lateral)
        navigationView.setNavigationItemSelectedListener(this)
    }

    // método sobrescrito para inflar o menu na Actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da Acti   onBar
        menuInflater.inflate(R.menu.menu_main, menu)
        // vincular evento de buscar
        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView).setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                // ação enquanto está digitando
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                // ação  quando terminou de buscar e enviou
                return false
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // id do item clicado
        val id = item?.itemId
        // verificar qual item foi clicado e mostrar a mensagem Toast na tela
        // a comparação é feita com o recurso de id definido no xml
        if (id == R.id.action_buscar) {
            Toast.makeText(context, "Botão de buscar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_atualizar) {
            Toast.makeText(context, "Botão de atualizar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_config) {
            Toast.makeText(context, "Botão de configuracoes", Toast.LENGTH_LONG).show()
        }
        // botão up navigation
        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
    // método que deve ser implementado quando a activity implementa a interface NavigationView.OnNavigationItemSelectedListener
    // para tratar os eventos de clique no menu lateral
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_diciplinas -> {
                Toast.makeText(this, "Clicou Disciplinas", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_mensagens -> {
                Toast.makeText(this, "Clicou Mensagens", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_forum -> {
                Toast.makeText(this, "Clicou Forum", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_localizacao -> {
                Toast.makeText(this, "Clicou Localização", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_config -> {
                Toast.makeText(this, "Clicou Config", Toast.LENGTH_SHORT).show()
            }
        }

        // fecha menu depois de tratar o evento
        val drawer = findViewById<DrawerLayout>(R.id.layourMenuLateral)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    //override fun writeToParcel(parcel: Parcel, flags: Int) {

    //}

    //override fun describeContents(): Int {
    //    return 0
    //}

    //companion object CREATOR : Parcelable.Creator<TelaInicialActivity> {
    //    override fun createFromParcel(parcel: Parcel): TelaInicialActivity {
    //        return TelaInicialActivity(parcel)
    //   }

    ///   override fun newArray(size: Int): Array<TelaInicialActivity?> {
    //       return arrayOfNulls(size)
    //   }


    //private fun configuraMenuLateral() {
    //    var toogle = ActionBarDrawerToggle(
    //        this,
    //        layoutMenuLateral,
    //        toobar,
    //        R.string.navigation_drawer_open,
    //        R.string.navigation_drawer_close
//
     //   )
      //  layout.menu_lateral_cabecalho.addDrawerListener(toogle)
//

     //   toogle.syncState()
     //   view_menu_lateral.setNavigationItemSelectedListener(this)
   // }
}
