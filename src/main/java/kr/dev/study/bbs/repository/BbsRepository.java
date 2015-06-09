package kr.dev.study.bbs.repository;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import kr.dev.study.bbs.domain.Bbs;

public class BbsRepository {
	
	private static List<Bbs> items;
	
	public BbsRepository(){
		createRepository();
	}
	
	private void createRepository() {
		if( items == null ){
			items = new ArrayList<Bbs>();
		}
	}

	public List<Bbs> findAll() {
		return items;
	}
	
	public Bbs findOne(final Integer id) {
		return Iterables.find(items, new Predicate<Bbs>() {

			@Override
			public boolean apply(Bbs input) {
				return input.getId() == id;
			}
			
		});
	}

	public void save(Bbs bbs) {
		items.add(bbs);
	}
}
