package com.alicethais.aplicai.models

import java.io.Serializable

class Job: Serializable {
    var id: Int = 0
    var title: String = ""
    var description: String = ""
    var date_post: String = ""
    var mail: String = ""
    var is_remote: Boolean = false
    var salary: Int? = 0
}