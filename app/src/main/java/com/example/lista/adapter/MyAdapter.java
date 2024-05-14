// Declaração do pacote da aplicação
package com.example.lista.adapter;

// Importação das classes necessárias
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lista.activity.MainActivity;
import com.example.lista.model.MyItem;
import java.util.List;
import kessia.raissa.applista.R;

// Declaração da classe MyAdapter que estende RecyclerView.Adapter
public class MyAdapter extends RecyclerView.Adapter {

    // Referência à atividade principal e à lista de itens
    MainActivity mainActivity;
    List<MyItem> itens;

    // Construtor da classe que recebe a atividade principal e a lista de itens
    public MyAdapter(MainActivity mainActivity, List<MyItem> itens) {
        this.mainActivity = mainActivity;
        this.itens = itens;
    }

    // Método chamado para criar um novo ViewHolder
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Obtém o LayoutInflater a partir do contexto da atividade principal
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        // Infla o layout do item da lista
        View v = inflater.inflate(R.layout.item_list, parent, false);
        // Retorna um novo MyViewHolder com a view inflada
        return new MyViewHolder(v);
    }

    // Método chamado para vincular os dados a um ViewHolder
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // Obtém o item da lista na posição fornecida
        MyItem myItem = itens.get(position);

        // Obtém a view associada ao ViewHolder
        View v = holder.itemView;

        // Obtém o ImageView do layout do item
        ImageView imvPhoto = v.findViewById(R.id.imvPhoto);
        // Define a imagem do ImageView a partir do URI da foto do item
        imvPhoto.setImageURI(myItem.photo);

        // Obtém o TextView do título do layout do item
        TextView tvTitle = v.findViewById(R.id.tvTitle);
        // Define o texto do título a partir do título do item
        tvTitle.setText(myItem.title);

        // Obtém o TextView da descrição do layout do item
        TextView tvdesc = v.findViewById(R.id.tvDesc);
        // Define o texto da descrição a partir da descrição do item
        tvdesc.setText(myItem.description);
    }

    // Método que retorna o número total de itens na lista
    @Override
    public int getItemCount() {
        return itens.size();
    }

    // Classe ViewHolder interna para armazenar a view de cada item
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
