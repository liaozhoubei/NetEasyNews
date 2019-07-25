package cn.bproject.neteasynews.network

import java.lang.annotation.Retention

import java.lang.annotation.ElementType.METHOD
import java.lang.annotation.RetentionPolicy.RUNTIME

/**
 * 接口返回的数据格式，当前限定取值：[.JSON]或[.XML]
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(RUNTIME)
annotation class ResponseFormat(val value: String = "") {
    companion object {

        val JSON = "json"

        val XML = "xml"
    }
}