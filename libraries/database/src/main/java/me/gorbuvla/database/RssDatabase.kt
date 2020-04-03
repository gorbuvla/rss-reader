package me.gorbuvla.database

import androidx.room.Database
import androidx.room.RoomDatabase
import me.gorbuvla.database.entities.ArticleDao
import me.gorbuvla.database.entities.DbArticle

/**
 * Room database description and DAO providers
 */
@Database(entities = [DbArticle::class], version = 1, exportSchema = false)
abstract class RssDatabase : RoomDatabase() {

    abstract fun feedDao(): ArticleDao
}