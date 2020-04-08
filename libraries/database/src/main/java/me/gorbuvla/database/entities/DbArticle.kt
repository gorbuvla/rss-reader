package me.gorbuvla.database.entities

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import me.gorbuvla.core.domain.Article
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

/**
 * Db entity description for [Article]
 */
@Entity(tableName = "article")
data class DbArticle(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val content: String,
    val publishedAt: Long
) {

    fun toArticle(): Article {
        return Article(
            id = id,
            title = title,
            content = content,
            createdAt = ZonedDateTime.ofInstant(Instant.ofEpochMilli(publishedAt), ZoneId.systemDefault())
        )
    }
}

@Dao
interface ArticleDao {

    @Transaction
    suspend fun replace(vararg articles: DbArticle) {
        clear()
        insert(*articles)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg articles: DbArticle)

    @Query("SELECT * FROM article ORDER BY publishedAt DESC")
    fun articles(): Flow<List<DbArticle>>

    @Query("SELECT * FROM article WHERE id = :id LIMIT(1)")
    fun article(id: Int): Flow<List<DbArticle>>

    @Query("DELETE FROM article")
    suspend fun clear()
}