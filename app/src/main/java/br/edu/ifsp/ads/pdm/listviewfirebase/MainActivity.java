package br.edu.ifsp.ads.pdm.listviewfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    //Atributos representando os objetos gráficos da interface
    private EditText txtNome;
    private EditText txtEmail;
    private Button btnAdicionar;
    private ListView listaPessoas;


    // Atributo que é a referência para o nó raiz do BD no Firebase:
    private DatabaseReference BD = FirebaseDatabase.getInstance().getReference();


    // Atributo que é a referência para o adapter do Firebase:
    private ContatosAdapter adapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ligando os atributos com os objetos na interface gráfica

        txtNome = findViewById(R.id.txtNome);
        txtEmail = findViewById(R.id.txtEmail);
        btnAdicionar = findViewById(R.id.btnAdicionar);
        listaPessoas = findViewById(R.id.listaPessoas);

        btnAdicionar.setOnClickListener(new EscutadorBotaoAdicionar());

        DatabaseReference conteudo26 = BD.child("cont26listacontatos");

        FirebaseListOptions<Contato> options = new FirebaseListOptions.Builder<Contato>().setLayout(R.layout.item_lista).setQuery(conteudo26, Contato.class).setLifecycleOwner(this).build();


        adapter = new ContatosAdapter(options);

        listaPessoas.setAdapter(adapter);

        listaPessoas.setOnItemClickListener(new EscutadorCliqueLista());
    }

    private class EscutadorBotaoAdicionar implements View.OnClickListener{
        @Override
fgd
        public void onClick(View view){
            String nome, email;

            DatabaseReference conteudo26 = BD.child("cont26listacontatos");

            nome = txtNome.getText().toString();
            email = txtEmail.getText().toString();

            String chave = conteudo26.push().getKey();

            Contato c = new Contato(chave, nome, email );

            conteudo26.child(chave).setValue(c);



            txtNome.setText("");
            txtEmail.setText("");

            txtNome.requestFocus();

        }

    }


    private class ContatosAdapter extends FirebaseListAdapter<Contato> {


        public ContatosAdapter(FirebaseListOptions options) {
            super( options );
        }



        @Override
        protected void populateView(View v, Contato c, int position) {


            TextView lblNome  = v.findViewById( R.id.lblNome );
            TextView lblEmail = v.findViewById( R.id.lblEmail );


            lblNome.setText(  c.getNome()  );
            lblEmail.setText( c.getEmail() );
        }
    }

    private class EscutadorCliqueLista implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Contato c = adapter.getItem(i);


            Toast.makeText(getApplicationContext(), "Nome: " + c.getNome() + "\nEmail: " + c.getEmail(), Toast.LENGTH_SHORT).show();
        }
    }










}