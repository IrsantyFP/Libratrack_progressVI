package ifp.mobile.projek;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.List;

public class Adapter extends RecyclerView.Adapter{

    private final Context ctx;
    private final List<ListBuku> data;

    public Adapter(Context ctx, List<ListBuku> data) {
        this.ctx = ctx;
        this.data = data;
    }

    public class bukuVH extends ViewHolder {
        private final TextView tvJudul;
        private final TextView tvPenulis;
        private final TextView tvTahun;

        public bukuVH(@NonNull View itemView) {
            super(itemView);
            this.tvJudul = itemView.findViewById(R.id.tvJudul);
            this.tvPenulis = itemView.findViewById(R.id.tvPenulis);
            this.tvTahun = itemView.findViewById(R.id.tvTahun);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(this.ctx).inflate(R.layout.rowview, parent, false);
        ViewHolder vh = new bukuVH(rowView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ListBuku b = this.data.get(position);
        bukuVH vh = (bukuVH) holder;
        vh.tvJudul.setText(b.judul);
        vh.tvPenulis.setText(b.penulis);
        vh.tvTahun.setText(b.tahun);
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }
}
