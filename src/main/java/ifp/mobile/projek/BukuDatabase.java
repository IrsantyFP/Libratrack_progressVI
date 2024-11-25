package ifp.mobile.projek;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {ListBuku.class}, version = 1)

public abstract class BukuDatabase extends RoomDatabase {


    public abstract BukuDao bukuDao();
}
