package com.rest.open.controller

import com.rest.open.service.NeopleService
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient

@RequestMapping("/api/cyphers")
@RestController
class CyphersController(
    private var neopleService: NeopleService,
) {



    @GetMapping("/user")
   suspend fun userSearch(
       @RequestParam(value = "nickname") nickname: String
   ): Map<String, Any>? {
       return neopleService.playerSearch(nickname)
   }

}