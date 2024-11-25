package ifp.mobile.projek;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;

public class TambahBuku extends AppCompatActivity implements View.OnClickListener {

    private Button btSimpan;
    private EditText etJudul;
    private EditText etPenulis;
    private EditText etTahun;
    private BukuDatabase db;
    private BukuDao bukuDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_buku);

        this.btSimpan = findViewById(R.id.btSimpan);
        this.etJudul = findViewById(R.id.etJudul);
        this.etPenulis = findViewById(R.id.etPenulis);
        this.etTahun = findViewById(R.id.etTahun);
        this.btSimpan.setOnClickListener(this);

        this.db = Room.databaseBuilder(getApplicationContext(), BukuDatabase.class, "buku.db").build();
        this.bukuDao = this.db.bukuDao();

    }

    @Override
    public void onClick(View view) {
        Thread t = new Thread(() -> {
            //buat objek fren baru
            ListBuku b = new ListBuku();
            b.id = (int)(Math.random() * 1000000);
            b.judul = this.etJudul.getText().toString();
            b.penulis = this.etPenulis.getText().toString();
            b.tahun = this.etTahun.getText().toString();

            // ambil semua data Fren dari dalam database
            this.bukuDao.insertAll(b);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("id", b.id);
            resultIntent.putExtra("judul", b.judul);
            resultIntent.putExtra("penulis", b.penulis);
            resultIntent.putExtra("tahun", b.tahun);
            setResult(RESULT_OK, resultIntent);

            finish();

//            List<ListBuku> bukus = this.bukuDao.getAllListBuku();
//            this.dataset.clear();
//            this.dataset.addAll(bukus);

//            // beritahu thread bahwa dataset sudah diubah
//            Message m = this.handler.obtainMessage();
//            this.handler.sendMessage(m);
        });
        t.start();
    }
}