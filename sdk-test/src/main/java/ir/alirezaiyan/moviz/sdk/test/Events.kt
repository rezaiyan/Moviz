package ir.alirezaiyan.moviz.sdk.test


import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId

class Events {
    fun clickOnView(@IdRes viewId: Int) {
        onView(withId(viewId)).perform(click())
    }
}
