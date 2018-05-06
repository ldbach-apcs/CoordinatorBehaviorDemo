package com.example.ldbach.appbarlayoutdemo

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.v4.widget.NestedScrollView
import android.util.AttributeSet
import android.util.Log
import android.view.View

class HidingAppBarBehavior(context: Context?, attrs: AttributeSet?) : CoordinatorLayout.Behavior<AppBarLayout>(context, attrs) {

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: AppBarLayout?, dependency: View?): Boolean {
        return dependency?.id == R.id.nestedScrollView
    }

    override fun onLayoutChild(parent: CoordinatorLayout?, child: AppBarLayout?, layoutDirection: Int): Boolean {
        parent?.findViewById<NestedScrollView>(R.id.nestedScrollView)?.translationY = child?.height!!.toFloat()
        return super.onLayoutChild(parent, child, layoutDirection)
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout?, child: AppBarLayout?, dependency: View?): Boolean {
        if (dependency?.id == R.id.nestedScrollView) {
            if (dependency.scrollY  > child?.height!!) {
                child.visibility = View.INVISIBLE
            } else {
                child.visibility = View.VISIBLE
            }
        }
        return false
    }
}