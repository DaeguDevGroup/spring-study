package kr.dev.study.bbs.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import kr.dev.study.bbs.domain.Bbs;
import kr.dev.study.bbs.service.BbsService;

@Service
public class BbsServiceImpl implements BbsService {
	
	private static List<Bbs> bbsRepository;

	@Override
	public List<Bbs> getList() {
		return bbsRepository;
	}

	@Override
	public void save(Bbs bbs) {
		
		if( bbsRepository == null ){
			bbsRepository = new ArrayList<Bbs>();
		}
		bbsRepository.add(bbs);

	}

}
