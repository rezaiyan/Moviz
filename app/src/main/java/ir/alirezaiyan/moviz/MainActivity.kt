package ir.alirezaiyan.moviz

import ir.alirezaiyan.moviz.feature.search.SearchFragment
import ir.alirezaiyan.moviz.sdk.platform.platform.BaseActivity


class MainActivity : BaseActivity() {
    override fun fragment() = SearchFragment()
}
