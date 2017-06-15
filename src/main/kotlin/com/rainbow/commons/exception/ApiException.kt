package com.rainbow.commons.exception

/**
 * Created by rainbow on 2017/6/15.
 *一事专注，便是动人；一生坚守，便是深邃！
 */
open class ApiException(message: String? = null, var code: Int = 0) : RuntimeException(message)