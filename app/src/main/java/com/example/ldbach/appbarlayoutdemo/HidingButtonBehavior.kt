package com.example.ldbach.appbarlayoutdemo

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View
import android.widget.Button

class HidingButtonBehavior(context: Context?, attrs: AttributeSet?) : CoordinatorLayout.Behavior<Button>(context, attrs) {

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: Button?, dependency: View?): Boolean {
        return dependency?.id == R.id.nestedScrollView
    }

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: Button, directTargetChild: View, target: View, axes: Int): Boolean {
        return true
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: Button, target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {
        if (dyConsumed > 0 || dyConsumed == 0 && dyUnconsumed > 0) {
            child.visibility = View.INVISIBLE
        } else {
            child.visibility = View.VISIBLE
        }
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type)
    }
}