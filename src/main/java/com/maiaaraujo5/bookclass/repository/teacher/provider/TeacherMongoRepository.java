package com.maiaaraujo5.bookclass.repository.teacher.provider;

import com.maiaaraujo5.bookclass.repository.teacher.model.TeacherDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherMongoRepository extends MongoRepository<TeacherDocument, String> {
    TeacherDocument findTeacherByEmail(String email);
}
