package com.rainbow.service

import com.rainbow.commons.exception.ApiException
import com.rainbow.entity.Address
import com.rainbow.entity.Position
import com.rainbow.mapper.AddressMapper
import com.rainbow.mapper.PositionMapper
import org.apache.ibatis.jdbc.SQL
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.sql.SQLException

/**
 * Created by Administrator on 2017/7/12.
 */
@Service
class AddressService {

    @Autowired
    lateinit var addressMapper: AddressMapper
    @Autowired
    lateinit var positionMapper: PositionMapper

    private val logger by lazy { LoggerFactory.getLogger(AddressService::class.java) }

    fun save(params: Map<String, Any>) {
        requireNotNull(params["mobile"], { "手机号码不能为空" })
        requireNotNull(params["username"], { "用户名不能为空" })
        requireNotNull(params["longitude"], { "经度不能为空" })
        requireNotNull(params["latitude"], { "纬度不能为空" })
        val address = Address()
        val position = Position()

        try {
            address.mobile = params["mobile"] as String
            address.username = params["username"] as String

            position.latitude = params["latitude"] as Double
            position.longitude = params["longitude"] as Double
            position.mobile = params["mobile"] as String

            addressMapper.save(address)
            positionMapper.save(position)
        } catch (e: SQLException) {
            logger.error(e.message)
        }
    }

    fun list():Any?{
        return addressMapper.list()
    }


    fun delByMobile(mobile: String) = addressMapper.delByMobile(mobile)

}