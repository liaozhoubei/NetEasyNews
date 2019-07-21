package cn.bproject.neteasynews.util

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.NonNull
import cn.bproject.neteasynews.base.SimpleBackActivity
import cn.bproject.neteasynews.base.SimpleBackPage

class ViewUtils {
    companion object{
        fun showSimpleBack(context: Context, page: SimpleBackPage) {
            showSimpleBack(context, page, null)
        }

        fun showSimpleBack(context: Context?, page: SimpleBackPage, args: Bundle?) {
            if (context == null)
                return
            val intent = getSimpleBackPageIntent(context, page, args)
            context.startActivity(intent)
        }

        @NonNull
        fun getSimpleBackPageIntent(context: Context, page: SimpleBackPage, args: Bundle?): Intent {
            val intent = Intent(context, SimpleBackActivity::class.java)
            intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.value)
            if (args != null)
                intent.putExtra(SimpleBackActivity.BUNDLE_KEY_ARGS, args)
            return intent
        }
    }
}

