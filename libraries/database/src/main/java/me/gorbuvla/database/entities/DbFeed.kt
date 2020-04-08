package me.gorbuvla.database.entities

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import me.gorbuvla.core.domain.Feed
import java.net.URL

/**
 * Db entity description for [Feed]
 */
@Entity(tableName = "feed")
data class DbFeed(
    @PrimaryKey val id: String,
    val name: String,
    val url: String
) {

    fun toFeed(): Feed {
        return Feed(
            id = url,
            name = name,
            url = URL(url)
        )
    }
}

@Dao
interface FeedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(feed: DbFeed)

    @Query("SELECT * FROM feed")
    fun feeds(): Flow<List<DbFeed>>

    @Query("DELETE FROM feed WHERE id = :feedId")
    suspend fun delete(feedId: String)
}