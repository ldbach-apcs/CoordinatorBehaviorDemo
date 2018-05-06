package com.example.ldbach.appbarlayoutdemo

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View

class TranslateNestedScrollViewBehavior(context: Context?, attrs: AttributeSet?) : CoordinatorLayout.Behavior<NestedScrollView>(context, attrs) {
    override fun layoutDependsOn(parent: CoordinatorLayout?, child: NestedScrollView?, dependency: View?): Boolean {
        return dependency?.id == R.id.myAppBar
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout?, child: NestedScrollView
    ?, dependency: View?): Boolean {
        if (dependency?.id == R.id.myAppBar) {
            child?.translationY = dependency.translationY + dependency.height
        }
        return super.onDependentViewChanged(parent, child, dependency)
    }
}