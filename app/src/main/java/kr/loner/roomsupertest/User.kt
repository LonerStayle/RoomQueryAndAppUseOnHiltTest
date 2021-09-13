package kr.loner.roomsupertest

import android.graphics.Bitmap
import androidx.room.*

@Entity
data class User(

    @PrimaryKey
    val uid: Int,
    @ColumnInfo(name = "first_name") val firstName: String? = null,
    @ColumnInfo(name = "last_name") val lastName: String? = null,
    val age:Int = 10,
    val test:Int = 0,
    val test2:Int = 0,
    val test3:Int = 0
)

open class BaseUser {
    var picture: Bitmap? = null
}

@Entity(ignoredColumns = ["picture"])
data class RemoteUser(
    @PrimaryKey val id: Int,
    val hasVpn: Boolean
) : BaseUser()


@Fts4
@Entity(tableName = "users")
data class Fts4UseByUser(
    //Fts4에서 rowid 칼럼은 필수임
    @PrimaryKey @ColumnInfo(name = "rowid") val id: Int,
    @ColumnInfo(name = "first_name") val firstName: String?
)


