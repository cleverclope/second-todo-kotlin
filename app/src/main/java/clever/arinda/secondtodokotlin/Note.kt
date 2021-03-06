package clever.arinda.secondtodokotlin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable")
class Note(
    @ColumnInfo(name = "tittle") val noteTitle: String,
    @ColumnInfo(name = "description") val noteDescription : String,
    @ColumnInfo(name = "timeStamp") val timeStamp: String
) {
@PrimaryKey(autoGenerate = true)
var id = 0
}