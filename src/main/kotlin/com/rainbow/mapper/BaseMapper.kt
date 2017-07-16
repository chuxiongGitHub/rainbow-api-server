/**
 *作者 陈彩红 创建时间： 2017/6/14.
 */
package com.rainbow.mapper

import org.apache.ibatis.annotations.Param

/**
 * 创建者：陈彩红 on 2017/6/14
 *每日进步一小点.
 */
interface BaseMapper<T> {

    fun save(t:T)

    fun modify(@Param("ownerId") ownerId: String, @Param("entity") t: T)

    fun list(): List<Any>

    fun delete(id: Long)

    fun deleteByOwnerId(ownerId: String)

    fun update(@Param("entity") t: T)

    fun findById(id: Long)

}