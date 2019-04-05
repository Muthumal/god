package lk.janiru.greenlocator.db;

/*
 *
 * Project Name : ${PROJECT}
 * Created by Janiru on 3/27/2019 1:43 PM.
 *
 */

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import lk.janiru.greenlocator.db.dao.UserDao;
import lk.janiru.greenlocator.db.entiity.User;

@Database(entities = {User.class},version = 3,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "database")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
