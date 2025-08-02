package com.rest.open.service

import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class NeopleService(
     private val myWebClient: WebClient,
) {

    @Value("\${common.neople.base-url}")
    lateinit var BASE_URL:String

    @Value("\${common.neople.key-cyphers}")
    lateinit var KEY:String

    suspend fun playerSearch(nickname:String): Map<String, Any>? {
        return myWebClient.get()
            .uri(BASE_URL + "/cy/players?nickname=${nickname}&apikey=${KEY}")
            .retrieve()
            .bodyToMono(Map::class.java as Class<Map<String, Any>>) // 응답 본문을 String으로 받음
            .awaitSingleOrNull()

    }


}