package com.rest.open.service

import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class NeopleService(
     private val myWebClient: WebClient,
) {

    val BASE_URL:String = "https://api.neople.co.kr/"

    @Value("\${common.neople.key-cyphers}")
    lateinit var KEY:String


    suspend fun playerSearch(): Map<String, Any>? {
        return myWebClient.get()
            .uri(BASE_URL + "cy/players?nickname=모지랑이&apikey=${KEY}")
            .retrieve()
            .bodyToMono(Map::class.java as Class<Map<String, Any>>) // 응답 본문을 String으로 받음
            .awaitSingleOrNull()

    }


}