package com.shvants.runninglife.ui.model

import com.shvants.runninglife.R

data class UiUserModel(var id: Long = 0,
                       var avatar: Int = R.drawable.ic_avatar_stub,
                       var fullName: String = "Full name",
                       var location: String = "Location"
        /*var moves: List<UiMoveModel>?*/)