package com.rainbow.commons

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.stereotype.Component
import com.fasterxml.jackson.dataformat.xml.XmlMapper

/**
 * Created by rainbow on 2017/6/15.
 *一事专注，便是动人；一生坚守，便是深邃！
 */
@Component
open class ApiUtils {

    val restTemplate by lazy {
        RestTemplateBuilder().additionalMessageConverters(
                StringHttpMessageConverter(Charsets.UTF_8),
                MappingJackson2HttpMessageConverter()
        ).build()!!
    }

    val mapper by lazy { ObjectMapper() }

    val objectMapper by lazy { ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)!! }

    val xmlMapper by lazy { XmlMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)!! }

    fun buildUri(url: String, params: Map<String, Any?> = emptyMap()): String {
        val query = params.filterValues { it != null }.map { "${it.key}=${it.value}" }.joinToString("&")
        val sep = if (url.contains("?")) "&" else "?"
        return "$url$sep$query"
    }

    fun <T> mapToBean(map: Map<*,*>, clazz: Class<T>) = mapper.readValue(mapper.writeValueAsString(map), clazz)!!

}