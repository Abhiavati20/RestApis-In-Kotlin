package io.communitygaming.taskOne.repositories

import io.communitygaming.taskOne.models.Student
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository:MongoRepository<Student, String> {
    fun findByAge(age:Int):List<Student>
}