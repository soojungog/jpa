package com.sujeong.jpa.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sujeong.jpa.project.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String>{

}
