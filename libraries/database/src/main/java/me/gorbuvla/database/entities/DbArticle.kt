package me.gorbuvla.database.entities

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import me.gorbuvla.core.domain.Article
import org.threeten.bp.ZonedDateTime

/**
 * Db entity description for [Article]
 */
@Entity(tableName = "article")
data class DbArticle(
    @PrimaryKey val id: String,
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
    suspend fun insert(vararg articles: DbArticle)

    @Query("SELECT * FROM article")
    fun articles(): Flow<List<DbArticle>>

    @Query("SELECT * FROM article WHERE id = :id LIMIT(1)")
    fun article(id: String): Flow<List<DbArticle>>

    @Query("DELETE FROM article")
    suspend fun clear()
}