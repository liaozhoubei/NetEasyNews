package cn.bproject.neteasynews.network

import retrofit2.Converter

import java.lang.annotation.Documented
import java.lang.annotation.Retention

import java.lang.annotation.ElementType.METHOD
import java.lang.annotation.RetentionPolicy.RUNTIME
import kotlin.reflect.KClass

@Documented
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(RUNTIME)
annotation class ResponseConverter(val value: KClass<out Converter.Factory>)