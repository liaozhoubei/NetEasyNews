package cn.bproject.neteasynews.base

import cn.bproject.neteasynews.R
import cn.bproject.neteasynews.fragment.AboutFragment
import cn.bproject.neteasynews.fragment.NewsFragment
import cn.bproject.neteasynews.fragment.PhotoFragment
import cn.bproject.neteasynews.fragment.VideoFragment

enum class SimpleBackPage private constructor(var value: Int, var title: Int, var clz: Class<*>?) {


    NEWS(1, R.string.news_fragment, NewsFragment::class.java),
    PHOTO(2, R.string.photo_fragment, PhotoFragment::class.java),
    VIDEO(3, R.string.video_fragment, VideoFragment::class.java),
    SETTING(4, R.string.about_fragment, AboutFragment::class.java);

    companion object {

        fun getPageByValue(value: Int): SimpleBackPage? {
            for (p in values()) {
                if (p.value == value)
                    return p
            }
            return null
        }
    }
}
