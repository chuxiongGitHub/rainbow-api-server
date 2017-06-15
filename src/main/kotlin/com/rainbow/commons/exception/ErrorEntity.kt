package com.rainbow.commons.exception

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.http.HttpStatus
import java.util.*
import javax.servlet.http.HttpServletRequest

/**
 * Created by rainbow on 2017/6/15.
 *一事专注，便是动人；一生坚守，便是深邃！
 */
class ErrorEntity() {

    var message: String? = null

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val timestamp = Date()

    var status = HttpStatus.BAD_REQUEST.value()

    var error: String? = null

    var path: String? = null

    constructor(message: String, status: HttpStatus, request: HttpServletRequest) : this() {
        this.message = message
        this.status = status.value()
        this.error = status.reasonPhrase
        this.path = request.requestURI
    }
}