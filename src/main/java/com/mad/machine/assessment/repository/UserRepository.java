package com.mad.machine.assessment.repository;

import com.mad.machine.assessment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
