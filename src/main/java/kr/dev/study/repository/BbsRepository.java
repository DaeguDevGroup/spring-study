package kr.dev.study.repository;

import kr.dev.study.bbs.domain.Bbs;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BbsRepository extends MongoRepository<Bbs, String> {
}
