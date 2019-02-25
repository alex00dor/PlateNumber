package com.kiko.ukraine.platenumber.presentation.base

import android.databinding.BindingAdapter
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.support.v4.widget.CircularProgressDrawable
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso


@set:BindingAdapter("visibleOrGone")
var View.visibleOrGone
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

@set:BindingAdapter("visible")
var View.visible
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.INVISIBLE
    }

@BindingAdapter("layout_width")
fun View.setLayoutWidth(width: Int) {
    layoutParams.width = width
}

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

    Picasso.get().load(url).placeholder(circularProgressDrawable).into(this)

}

@BindingAdapter("circleBackgroundColor")
fun ImageView.setCircleBackgroundColor(color : String?){
    if (background is ShapeDrawable) {
        (background as ShapeDrawable).paint.color = Color.parseColor(color)
    } else if (background is GradientDrawable) {
        (background as GradientDrawable).setColor(Color.parseColor(color))
    } else if (background is ColorDrawable) {
        (background as ColorDrawable).color = Color.parseColor(color)
    }
}
