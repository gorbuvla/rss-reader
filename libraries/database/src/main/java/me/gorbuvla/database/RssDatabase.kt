package me.gorbuvla.database

import androidx.room.Database
import androidx.room.RoomDatabase
import me.gorbuvla.database.entities.ArticleDao
import me.gorbuvla.database.entities.DbArticle
import me.gorbuvla.database.entities.DbFeed
import me.gorbuvla.database.entities.FeedDao

/**
 * Room database description and DAO providers
 */
@Database(entities = [DbArticle::class, DbFeed::class], version = 1, exportSchema = false)
abstract class RssDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    abstract fun feedDao(): FeedDao
}