package kr.dev.study.bbs.service;

import java.util.List;

import kr.dev.study.bbs.domain.Bbs;
import kr.dev.study.bbs.exception.BbsNotFoundException;

public interface BbsService {

	List<Bbs> getList();

	Bbs findById(Integer id) throws BbsNotFoundException;

	void create(Bbs bbs);

}
