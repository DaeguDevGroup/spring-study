package kr.dev.study.bbs.service.impl;

import java.util.List;

import kr.dev.study.bbs.domain.Bbs;
import kr.dev.study.bbs.exception.BbsNotFoundException;
import kr.dev.study.bbs.service.BbsService;
import kr.dev.study.repository.BbsRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepositoryBbsService implements BbsService {
	
	private static final Logger logger = LoggerFactory.getLogger(RepositoryBbsService.class);
	
	private final BbsRepository repository;

    @Autowired
    public RepositoryBbsService(BbsRepository repository) {
        this.repository = repository;
    }

	@Override
	public List<Bbs> findAll() {
		return (List<Bbs>) repository.findAll();
	}

	@Override
	public void create(Bbs bbs) {
		repository.save(bbs);
	}

	@Override
	public Bbs findById(final String id) throws BbsNotFoundException {

		logger.debug("Finding a bbs entry with id: {}", id);
		
		Bbs found = repository.findOne(id);
		logger.debug("Found bbs entry: {}", found);
		
		if (found == null) {
            throw new BbsNotFoundException("No bbs entry found with id: " + id);
        }
		
		return found;
	}

}
