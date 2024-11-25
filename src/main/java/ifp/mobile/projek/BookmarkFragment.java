package ifp.mobile.projek;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class BookmarkFragment extends Fragment {

    private RecyclerView rvListBuku;
    private List<ListBuku> data;
    private Adapter adapter;
    private Handler handler;

    public BookmarkFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_bookmark, container, false);
        this.rvListBuku = v.findViewById(R.id.rvListBuku);
        FloatingActionButton fab = v.findViewById(R.id.addBuku);

        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), TambahBuku.class);
                startActivityForResult(intent, 1);
            }
        });

//        List<LikedBooks> data = new ArrayList<>();
//        data.add(new LikedBooks("Hujan", "Tere Liye", "2016"));
//        data.add(new LikedBooks("Harry Potter", "JK. Rowling", "1997"));
//        data.add(new LikedBooks("Hunger Games", "Suzanne Collins", "2008"));
//        data.add(new LikedBooks("Matahari", "Tere Liye", "2016"));
//        data.add(new LikedBooks("Bumi", "Tere Liye", "2014"));

        this.handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                adapter.notifyDataSetChanged();
            }
        };

        this.data = new ArrayList<>();
        this.adapter = new Adapter(requireContext(), this.data);
        this.rvListBuku.setAdapter(this.adapter);
        this.rvListBuku.setLayoutManager(new LinearLayoutManager(requireContext()));

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == AppCompatActivity.RESULT_OK) {
            // Ambil data buku yang ditambahkan
            int id = data.getIntExtra("id", 0);
            String judul = data.getStringExtra("judul");
            String penulis = data.getStringExtra("penulis");
            String tahun = data.getStringExtra("tahun");

            // Tambahkan buku ke dataset dan perbarui RecyclerView
            ListBuku buku = new ListBuku();
            buku.id = id;
            buku.judul = judul;
            buku.penulis = penulis;
            buku.tahun = tahun;

            onBukuAdded(buku); // Panggil metode untuk memperbarui data
        }
    }

    private void onBukuAdded(ListBuku buku) {
        this.data.add(buku);
        requireActivity().runOnUiThread(() -> adapter.notifyDataSetChanged());
    }

}