/*
import android.content.Context
import com.ali_sajjadi.frenchpastry.wrapper.DeviceInfo
import okhttp3.Interceptor
import okhttp3.Response

class CustomHeadersInterceptor(
    private val context: Context,
    private val apiKey: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        // استفاده از request.url() برای دسترسی به URL
        val url = request.url.toString() // به جای `request.url()`

        val deviceID = DeviceInfo.getDeviceID(context)
        val publicKey = DeviceInfo.getPublicKey(context)

        // بررسی کنید که آیا URL خاصی نیاز به هدر دارد
        if (url.contains("someEndpoint")) {
            // اعمال هدرهای خاص
            val newRequest = request.newBuilder()
                .addHeader("app-api-key", apiKey)
                .addHeader("app-device-uid", deviceID)
                .addHeader("app-public-key", publicKey)
                .build()

            return chain.proceed(newRequest)
        }

        // در غیر اینصورت، درخواست را بدون تغییر ارسال کنید
        return chain.proceed(request)
    }
}
*/
