package kr.dev.study.bbs.service;

import java.util.List;

import kr.dev.study.bbs.domain.Bbs;

public interface BbsService {

	List<Bbs> getList();

	void save(Bbs bbs);

}
