package com.kristiania.lecture11_codealong.datastorage

import android.content.Context
import androidx.room.*

@Entity
data class RecordEntity(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "distance") var distance: Int,
    @ColumnInfo(name = "time") var time: Int
){
    @PrimaryKey(autoGenerate = true) var priId: Int? = null

    override fun toString(): String {
        return "$name ran distance of $distance in $time seconds"
    }
}

@Database(
    entities = [RecordEntity::class], version = 1, exportSchema = false
)
abstract class RaceDatabase : RoomDatabase() {
    abstract fun getDao(): RaceDao

    companion object {
        var DB_FILENAME = "myracedb"

        @Volatile
        private var INSTANCE: RaceDatabase? = null

        fun get(context: Context): RaceDatabase {
            val tmp = INSTANCE
            if (tmp != null) {
                return tmp
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RaceDatabase::class.java,
                    DB_FILENAME)
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}

@Dao
interface RaceDao {
    @Insert
    fun insertRecord(vararg item: RecordEntity)

    @Update
    fun updateRecord(vararg item: RecordEntity)

    @Query("SELECT * from RecordEntity")
    fun getAllRecords() : List<RecordEntity>
}