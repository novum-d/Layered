package io.novumd.core.network

interface NetworkDataSource {
  suspend fun getTopics(ids: List<String>? = null): List<NetworkTopic>

  suspend fun getNewsResources(ids: List<String>? = null): List<NetworkNewsResource>

  suspend fun getTopicChangeList(after: Int? = null): List<NetworkChangeList>

  suspend fun getNewsResourceChangeList(after: Int? = null): List<NetworkChangeList>
}
