package com.jpure.rickandmorty.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.jpure.rickandmorty.common.DATABASE_NAME
import com.jpure.rickandmorty.data.entities.Episode
import com.jpure.rickandmorty.data.entities.Locations
import com.jpure.rickandmorty.data.entities.RemoteKeys
import com.jpure.rickandmorty.data.entities.Role
import com.jpure.rickandmorty.data.local.*

/**
 * Room
 * @author Jp
 * @date 2021/2/26.
 */
//建表、版本
@Database(entities = [Role::class, RemoteKeys::class, Locations::class, Episode::class], version = 2, exportSchema = false)
//设置转换器
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun roleDao(): RoleDao
    abstract fun locationsDao():LocationsDao
    abstract fun episodeDao(): EpisodeDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {

        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration() //数据库更新时删除数据重新创建
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
//                            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
//                            WorkManager.getInstance(context).enqueue(request)
                        }
                    }
                ).build()
        }
    }
}