package com.serdararici.signinwithfirebase.Model

import java.io.Serializable

data class User(val email: String="",
                val password: String=""): Serializable {

}