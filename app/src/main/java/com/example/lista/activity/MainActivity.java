
package com.example.lista.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.lista.model.MyItem;
import android.app.Activity;
import kessia.raissa.applista.R;
import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lista.adapter.MyAdapter;

// Declaração da classe MainActivity que herda de AppCompatActivity
public class MainActivity extends AppCompatActivity {

    // Constante para identificar a requisição de novo item
    static int NEW_ITEM_REQUEST = 1;

    // Lista que armazenará os itens
    List<MyItem> itens = new ArrayList<>();

    // Adaptador para o RecyclerView
    MyAdapter myAdapter;

    // Método chamado quando a atividade é criada
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Chama o método da superclasse e configura o layout da atividade
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Encontra o FloatingActionButton no layout
        FloatingActionButton fabAddItem = findViewById(R.id.fabAddNewItem);

        // Define um listener para o botão flutuante
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cria uma intenção para iniciar a NewItemActivity
                Intent i = new Intent(MainActivity.this, NewItemActivity.class);
                // Inicia a atividade esperando um resultado
                startActivityForResult(i, NEW_ITEM_REQUEST);
            }
        });

        // Encontra o RecyclerView no layout
        RecyclerView rvItens = findViewById(R.id.rvItens);

        // Inicializa o adaptador com o contexto atual e a lista de itens
        myAdapter = new MyAdapter(this, itens);

        // Define o adaptador no RecyclerView
        rvItens.setAdapter(myAdapter);

        // Configura o RecyclerView para ter tamanho fixo
        rvItens.setHasFixedSize(true);

        // Configura o layout manager do RecyclerView para ser linear
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvItens.setLayoutManager(layoutManager);

        // Adiciona uma linha divisória entre os itens do RecyclerView
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItens.getContext(), DividerItemDecoration.VERTICAL);
        rvItens.addItemDecoration(dividerItemDecoration);
    }

    // Método chamado quando a atividade chamada retorna um resultado
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Chama o método da superclasse
        super.onActivityResult(requestCode, resultCode, data);

        // Verifica se a requisição foi para adicionar um novo item
        if(requestCode == NEW_ITEM_REQUEST) {
            // Verifica se o resultado foi OK
            if(resultCode == Activity.RESULT_OK) {
                // Cria um novo item com os dados retornados
                MyItem myItem = new MyItem();
                myItem.title = data.getStringExtra("title");
                myItem.description = data.getStringExtra("description");
                myItem.photo = data.getData();

                // Adiciona o novo item à lista
                itens.add(myItem);

                // Notifica o adaptador que um novo item foi inserido
                myAdapter.notifyItemInserted(itens.size() - 1);
            }
        }
    }
}
