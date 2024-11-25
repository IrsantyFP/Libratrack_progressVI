package ifp.mobile.projek;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.List;

public class DetailBuku extends AppCompatActivity {

    private BukuDatabase db;
    private BukuDao BukuDao;
    private Handler handler;
    private List<ListBuku> dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_buku);

        Button likeButton = findViewById(R.id.likeButton);
        Button borrowButton = findViewById(R.id.borrowButton);

//        this.handler = new Handler(Looper.getMainLooper()){
//            @Override
//            public void handleMessage(@NonNull Message msg) {
//                super.handleMessage(msg);
//            }
//        };
//
//        this.db = Room.databaseBuilder(getApplicationContext(), BukuDatabase.class, "fren.db").build();
//        this.BukuDao = this.db.BukuDao();
        // Listener untuk likeButton
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailBuku.this, "Buku telah disimpan", Toast.LENGTH_SHORT).show();
//                Thread t = new Thread(() -> {
//                    //buat objek fren baru
//                    ListBuku b = new ListBuku();
//                    b.id = (int)(Math.random() * 1000000);
//                    b.judul = "Harry Potter";
//                    b.penulis = "JK. Rowling";
//                    b.tahun = "1997";
//
//                    // ambil semua data Fren dari dalam database
//                    this.BukuDao.insertAll(b);
//
//                    // Ambil semua data ListBuku dari database
//                    List<ListBuku> bukuList = this.BukuDao.getAllBooks();
//
//                    // Perbarui dataset (misalnya, untuk RecyclerView atau UI lainnya)
//                    this.dataset.clear();
//                    this.dataset.addAll(bukuList);
//
//                    // beritahu thread bahwa dataset sudah diubah
//                    Message m = this.handler.obtainMessage();
//                    this.handler.sendMessage(m);
//                });
//                t.start();
            }
        });


        // Listener untuk borrowButton
        borrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Membuat AlertDialog konfirmasi Yes/No
                new AlertDialog.Builder(DetailBuku.this)
                        .setTitle("Confirmation")
                        .setMessage("Apakah anda yakin ingin meminjam buku ini?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Aksi ketika tombol "Yes" ditekan
                                dialog.dismiss();
                                // Menampilkan toast ketika "Yes" ditekan
                                Toast.makeText(DetailBuku.this, "Buku berhasil ditambahkan!!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Menutup dialog ketika "No" ditekan
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert) // Optional: Menambahkan icon alert
                        .show(); // Menampilkan AlertDialog
            }
        });
    }

}
