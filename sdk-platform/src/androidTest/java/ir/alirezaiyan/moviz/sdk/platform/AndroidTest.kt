package ir.alirezaiyan.moviz.sdk.platform

import android.app.Application
import android.content.Context
import ir.alirezaiyan.moviz.sdk.base.test.InjectMocksRule
import ir.alirezaiyan.moviz.sdk.platform.platform.BaseActivity
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * Base class for Android tests. Inherit from it to create test cases which contain android
 * framework dependencies or components.
 *
 * @see ir.alirezaiyan.moviz.sdk.base.test.UnitTest
 */
@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE,
        application = AndroidTest.ApplicationStub::class,
        sdk = [19])
abstract class AndroidTest {

    @Suppress("LeakingThis")
    @Rule @JvmField val injectMocks = InjectMocksRule.create(this@AndroidTest)

    fun context(): Context = RuntimeEnvironment.application

    fun activityContext(): Context = mock(BaseActivity::class.java)

    internal class ApplicationStub : Application()
}
