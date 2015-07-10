package kr.dev.study.bbs.service;

import java.util.List;

import kr.dev.study.bbs.domain.Bbs;
import kr.dev.study.bbs.exception.BbsNotFoundException;

public interface BbsService {

	List<Bbs> findAll();

	Bbs findById(String id) throws BbsNotFoundException;

	void create(Bbs bbs);

}
