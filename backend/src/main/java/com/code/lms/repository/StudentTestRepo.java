package com.code.lms.repository;

import com.code.lms.model.entity.StudentTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentTestRepo extends JpaRepository<StudentTest,Integer> {
}
