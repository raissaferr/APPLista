
package com.example.lista.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;
import kessia.raissa.applista.R;

// Declaração da classe NewItemActivity que estende AppCompatActivity
public class NewItemActivity extends AppCompatActivity {

    // Constante para identificar a requisição de seleção de foto
    static int PHOTO_PICKER_REQUEST = 1;
    // Variável para armazenar o URI da foto selecionada
    Uri photoSelected = null;

    // Método chamado quando a atividade é criada
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Chama o método da superclasse e configura o layout da atividade
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        // Encontra o ImageButton no layout
        ImageButton imgCI = findViewById(R.id.imbCI);
        // Define um listener para o ImageButton para selecionar uma imagem
        imgCI.setOnClickListener(v -> {
            // Cria uma intenção para abrir o seletor de documentos
            Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            // Define o tipo de documento para imagens
            photoPickerIntent.setType("image/*");
            // Inicia a atividade de seleção de foto esperando um resultado
            startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST);
        });

        // Encontra o botão de adicionar item no layout
        Button btnAddItem = findViewById(R.id.btnAddItem);
        // Define um listener para o botão de adicionar item
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verifica se uma foto foi selecionada
                if (photoSelected == null) {
                    // Mostra uma mensagem de erro se nenhuma foto foi selecionada
                    Toast.makeText(NewItemActivity.this, "É necessário selecionar uma imagem!", Toast.LENGTH_LONG).show();
                    return;
                }

                // Encontra o EditText do título no layout
                EditText etTitle = findViewById(R.id.etTitle);
                // Obtém o texto do título
                String title = etTitle.getText().toString();
                // Verifica se o título está vazio
                if (title.isEmpty()) {
                    // Mostra uma mensagem de erro se o título está vazio
                    Toast.makeText(NewItemActivity.this, "É necessário inserir um título", Toast.LENGTH_LONG).show();
                    return;
                }

                // Encontra o EditText da descrição no layout
                EditText etDesc = findViewById(R.id.etDesc);
                // Obtém o texto da descrição
                String description = etDesc.getText().toString();
                // Verifica se a descrição está vazia
                if (description.isEmpty()) {
                    // Mostra uma mensagem de erro se a descrição está vazia
                    Toast.makeText(NewItemActivity.this, "É necessário inserir uma descrição", Toast.LENGTH_LONG).show();
                    return;
                }

                // Cria uma nova intenção para retornar os dados
                Intent i = new Intent();
                // Define o URI da foto selecionada na intenção
                i.setData(photoSelected);
                // Adiciona o título e a descrição como extras na intenção
                i.putExtra("title", title);
                i.putExtra("description", description);
                // Define o resultado da atividade como OK e adiciona a intenção
                setResult(Activity.RESULT_OK, i);
                // Encerra a atividade
                finish();
            }
        });
    }

    // Método chamado quando uma atividade chamada retorna um resultado
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Chama o método da superclasse
        super.onActivityResult(requestCode, resultCode, data);

        // Verifica se a requisição foi para selecionar uma foto
        if (requestCode == PHOTO_PICKER_REQUEST) {
            // Verifica se o resultado foi OK
            if (resultCode == Activity.RESULT_OK) {
                // Obtém o URI da foto selecionada dos dados retornados
                photoSelected = data.getData();
                // Encontra o ImageView de pré-visualização da foto no layout
                ImageView imvfotoPreview = findViewById(R.id.imvPhotoPreview);
                // Define o URI da foto no ImageView para exibir a foto selecionada
                imvfotoPreview.setImageURI(photoSelected);
            }
        }
    }
}
