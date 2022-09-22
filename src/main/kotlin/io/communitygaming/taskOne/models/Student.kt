package io.communitygaming.taskOne.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "Students")
data class Student(
    @Id
    var _id: String = ObjectId.get().timestamp.toString(),
    var name:String = "",
    var age:Int = 0,
    var dept:String=""
) {

}