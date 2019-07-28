package cn.bproject.neteasynews.network

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.lang.reflect.Type
import kotlin.reflect.KClass

// https://blog.csdn.net/cheng545/article/details/90550165
class CompositeConverterFactory private constructor(private val mFactory: Converter.Factory?) : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type?,
        annotations: Array<Annotation>?,
        retrofit: Retrofit?
    ): Converter<ResponseBody, *>? {
        var factoryClass: KClass<*>? = null
        for (annotation in annotations!!) {
            if (annotation is ResponseConverter) {
                factoryClass = annotation.value
                break
            }
        }

        var factory: Converter.Factory? = null
        if (factoryClass != null) {
            try {
                val createMethod = factoryClass.java.getMethod("create")
                factory = createMethod.invoke(null) as Converter.Factory
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: InvocationTargetException) {
                e.printStackTrace()
            }

        }

        if (factory == null && mFactory != null) {
            factory = mFactory
        }

        return if (factory != null) factory.responseBodyConverter(
            type,
            annotations,
            retrofit
        ) else super.responseBodyConverter(type, annotations, retrofit)

    }

    override fun requestBodyConverter(
        type: Type?,
        parameterAnnotations: Array<Annotation>?,
        methodAnnotations: Array<Annotation>?,
        retrofit: Retrofit?
    ): Converter<*, RequestBody>? {
        var factoryClass: KClass<*>? = null
        for (paramAnno in methodAnnotations!!) {
            if (paramAnno is RequestConverter) {
                factoryClass = paramAnno.value
                break
            }
        }

        var factory: Converter.Factory? = null
        if (factoryClass != null) {
            try {
                val createMethod = factoryClass.java.getMethod("create")
                factory = createMethod.invoke(null) as Converter.Factory
                return factory.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit)
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: InvocationTargetException) {
                e.printStackTrace()
            }

        }

        if (factory == null && mFactory != null) {
            factory = mFactory
        }

        return if (factory != null) factory.requestBodyConverter(
            type,
            parameterAnnotations,
            methodAnnotations,
            retrofit
        ) else super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit)

    }

    companion object {

        fun create(factory: Converter.Factory?): CompositeConverterFactory {
            return factory?.let { CompositeConverterFactory(it) } ?: throw NullPointerException("parameter is null")
        }
    }
}
