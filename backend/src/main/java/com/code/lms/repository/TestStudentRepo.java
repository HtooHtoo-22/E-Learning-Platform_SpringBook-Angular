package com.code.lms.repository;

import com.code.lms.model.entity.TestStudent;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestStudentRepo extends JpaRepository<TestStudent,Integer> {
}
