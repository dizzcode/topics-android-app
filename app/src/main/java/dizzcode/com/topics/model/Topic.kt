package dizzcode.com.topics.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
   @StringRes
   val titleResId: Int,

   val label: Int,

   @DrawableRes val
   iconResId: Int
)
