package com.rainbow.mapper

import com.rainbow.entity.Address
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

/**
 * Created by Administrator on 2017/7/12.
 */
@Mapper
interface AddressMapper : BaseMapper<Address> {

    fun delByMobile(@Param("mobile") mobile: String)

}