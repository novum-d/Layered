package io.novumd.layered.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
  @PrimaryKey
  val id: String,
  val name: String,
)

fun UserEntity.asExternalModel() = {
}
