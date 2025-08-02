package com.rest.open.util

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import java.time.Duration
import reactor.netty.http.client.HttpClient
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import lombok.extern.slf4j.Slf4j
import org.springframework.http.client.reactive.ReactorClientHttpConnector

@Configuration
class WebClientConfig {

    @Bean
    fun myWebClient(): WebClient {
        // HTTP 클라이언트 타임아웃 설정 (선택 사항)
        val httpClient = HttpClient.create()
            .responseTimeout(Duration.ofSeconds(60)) // 응답 타임아웃
            .doOnConnected { connection ->
                connection.addHandlerLast(ReadTimeoutHandler(60)) // 읽기 타임아웃
                    .addHandlerLast(WriteTimeoutHandler(60)) // 쓰기 타임아웃
            }

        return WebClient.builder()
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) // 기본 콘텐츠 타입
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE) // 기본 허용 타입
            .clientConnector(ReactorClientHttpConnector(httpClient)) // 타임아웃 설정 적용
            .filter { request, next ->
                next.exchange(request)
            }
            .build()
    }

}