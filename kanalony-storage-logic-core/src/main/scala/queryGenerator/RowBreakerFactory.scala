package queryGenerator

import kanalony.storage.generator.ColumnNames

/**
 * Created by elad.benedict on 3/22/2016.
 */
object RowBreakerFactory {
  def getBreaker(columnName : ColumnNames.Value) : IRowBreaker = columnName match {
    case ColumnNames.year => YearPartitioner()
    case ColumnNames.month => MonthPartitioner()
    case ColumnNames.day => DayPartitioner()
    case _ => throw new IllegalArgumentException(s"Unsupported column name for partitioning ${columnName.toString}")
  }
}
