package cn.bproject.neteasynews.network

import androidx.annotation.Nullable
import retrofit2.Converter
import retrofit2.Retrofit
import okhttp3.ResponseBody
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.lang.reflect.Type


class JsonOrXmlConverterFactory : Converter.Factory(){
    private val xmlFactory = SimpleXmlConverterFactory.create()
    private val jsonFactory = GsonConverterFactory.create()

    fun create(): JsonOrXmlConverterFactory {
        return JsonOrXmlConverterFactory()
    }

    @Nullable
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        for (annotation in annotations) {
            if (annotation !is ResponseFormat) {
                continue
            }
            val value = annotation as ResponseFormat
            if (ResponseFormat.JSON.equals(value)) {
                return jsonFactory.responseBodyConverter(type, annotations, retrofit)
            } else if (ResponseFormat.XML.equals(value)) {
                return xmlFactory.responseBodyConverter(type, annotations, retrofit)
            }
        }

        return null
    }
}