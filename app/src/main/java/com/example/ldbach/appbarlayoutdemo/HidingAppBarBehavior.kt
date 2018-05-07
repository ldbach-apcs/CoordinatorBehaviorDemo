package com.example.ldbach.appbarlayoutdemo

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.v4.widget.NestedScrollView
import android.util.AttributeSet
import android.util.Log
import android.view.View

class HidingAppBarBehavior(context: Context?, attrs: AttributeSet?) : CoordinatorLayout.Behavior<AppBarLayout>(context, attrs) {

    private var isAppBarHidden = false
    private var isAppBarFullyShown = true
    private var firstTime = true

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: AppBarLayout?, dependency: View?): Boolean {
        return dependency?.id == R.id.nestedScrollView
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout?, child: AppBarLayout?, dependency: View?): Boolean {
        if (dependency?.id == R.id.nestedScrollView) {
            if (firstTime) {
                dependency.translationY = child?.height!!.toFloat()
                firstTime = false
            }
        }
        return false
    }

    override fun onLayoutChild(parent: CoordinatorLayout?, child: AppBarLayout?, layoutDirection: Int): Boolean {
        // parent?.findViewById<NestedScrollView>(R.id.nestedScrollView)?.translationY = child?.height!!.toFloat()
        return super.onLayoutChild(parent, child, layoutDirection)
    }



    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: AppBarLayout, directTargetChild: View, target: View, axes: Int, type: Int): Boolean {
        return true
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: AppBarLayout, target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {
        if (target.id != R.id.nestedScrollView)
            return
        isAppBarHidden = child.translationY + child.height.toFloat() <= 0.0005f
        isAppBarFullyShown = child.translationY == 0f

        if (dyConsumed > 0 || dyUnconsumed > 0) {
            // Hide appbar
            if (!isAppBarHidden) {
                // only offset the scroll when app bar is not hidden
                val counterScroll = Math.min(dyConsumed - child.translationY, child.height.toFloat())
                target.scrollY -= counterScroll.toInt()
                child.translationY = -counterScroll
                target.translationY = -counterScroll + child.height
            } else {
                target.translationY = 0f
            }
        } else {
            if (isAppBarFullyShown)
                return
            if (dyConsumed < 0) {
                val totalDisplacement = Math.min(0f, child.translationY - dyConsumed)
                child.translationY = totalDisplacement
                target.translationY = totalDisplacement + child.height
            } else {
                val totalDisplacement = Math.min(0f, child.translationY - dyUnconsumed)
                child.translationY = totalDisplacement
                target.translationY = totalDisplacement + child.height
            }
        }
    }
}