package com.rainbow.commons.exception

import org.springframework.http.HttpStatus

/**
 * Created by rainbow on 2017/6/15.
 *一事专注，便是动人；一生坚守，便是深邃！
 */
open class AppException(message: String? = null, var status: HttpStatus = HttpStatus.BAD_REQUEST) : RuntimeException(message)
