package me.gorbuvla.database.entities

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import me.gorbuvla.core.domain.Article
import org.threeten.bp.ZonedDateTime

/**
 * Db entity description for [Article]
 */
@Entity(tableName = "feed")
data class DbArticle(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String,
    val content: String
) {

    fun toArticle(): Article {
        return Article(
            id = id,
            title = title,
            content = content,
            createdAt = ZonedDateTime.now() // TODO: fixme
        )
    }
}

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg feeds: DbArticle)

    @Query("SELECT * FROM feed")
    fun feeds(): Flow<List<DbArticle>>

    @Query("SELECT * FROM feed WHERE id = :id LIMIT(1)")
    fun feed(id: Long): Flow<DbArticle>

    @Query("DELETE FROM feed")
    suspend fun clear()
}