package com.rainbow.feign

import com.rainbow.commons.ApiUtils
import com.rainbow.commons.exception.ApiException
import com.rainbow.commons.exception.AppException
import com.rainbow.commons.exception.ErrorEntity
import feign.Feign
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.http.HttpStatus

/**
 * Created by rainbow on 2017/6/15.
 *一事专注，便是动人；一生坚守，便是深邃！
 */
@Configuration
class FeignConfig {

    @Value("\${rainbow.api.user.header}")
    lateinit private var header: String

    @Autowired
    lateinit private var utils: ApiUtils

    @Bean
    @Scope("prototype")
    fun feignBuilder() = Feign.builder().decode404().requestInterceptor {
        it.header("R-APP-ID", header)
    }.errorDecoder { s, response ->
        if (response.status() in 400..499) {
            if (response.body() != null) {
                val error = utils.mapper.readValue(response.body().asInputStream(), ErrorEntity::class.java)
                throw AppException(error.message, HttpStatus.valueOf(error.status))
            }
            val status = HttpStatus.valueOf(response.status())
            throw AppException(status.reasonPhrase, status)
        } else {
            throw ApiException(response.body().asReader().readText())
        }
    }!!

}