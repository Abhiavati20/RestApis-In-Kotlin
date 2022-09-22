package io.communitygaming.taskOne.controllers

import io.communitygaming.taskOne.models.Student
import io.communitygaming.taskOne.repositories.StudentRepository
import org.bson.types.ObjectId
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/student")
class StudentController(
    private val studentRepository: StudentRepository
) {

    @GetMapping("/")
    fun getStudents():ResponseEntity<List<Student>> {
        return ResponseEntity.ok(this.studentRepository.findAll())
    }

    @GetMapping("/{id}")
    fun getStudentsById(@PathVariable id:String):ResponseEntity<Student> {
        return ResponseEntity.ok(this.studentRepository.findByIdOrNull(id))
    }

    //    task important points

    //  1. A POST Request endpoint which accepts a Path Variable and uses it to return something in response.
    @PostMapping("/{name}/{age}/{dept}")
    fun addStudentByPathVar(@PathVariable name:String,@PathVariable age:String,@PathVariable dept:String) : ResponseEntity<Student> {
        val _id = ObjectId.get().timestamp.toString()
        val student = this.studentRepository.save(Student(_id,name,age.toInt(),dept))
        return ResponseEntity.ok(student)
    }

    //  2. A GET Request endpoint which accepts a Query Param Variable and uses it to return something in response.
    @GetMapping("/query")
    fun getStudentsByQuery(
        @RequestParam(name="age",required = false) age:String,
    )
    :ResponseEntity<List<Student>>{
        return ResponseEntity.ok(this.studentRepository.findByAge(age.toInt()))
    }


    // 3. A POST Request endpoint which accepts a Body Variable and uses it to return something in response.
    @PostMapping("")
    fun addStudent(@RequestBody student: Student):ResponseEntity<Student> {
        return ResponseEntity.ok(this.studentRepository.save(student))
    }

    @PutMapping("/{id}")
    fun updateStudent(@PathVariable id: String, @RequestBody student: Student):ResponseEntity<Student> {
        var oldStudent = this.studentRepository.findById(id).orElse(null)
        oldStudent.name = student.name
        oldStudent.age = student.age
        oldStudent.dept = student.dept
        return ResponseEntity.ok(this.studentRepository.save(oldStudent))
    }

    @DeleteMapping("/{id}")
    fun deleteStudent(@PathVariable id: String): ResponseEntity<String> {
        this.studentRepository.deleteById(id)
        return ResponseEntity.ok("Student with " + id + " is deleted")
    }
}