package kr.dev.study.bbs.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import kr.dev.study.bbs.domain.Bbs;
import kr.dev.study.bbs.exception.BbsNotFoundException;
import kr.dev.study.bbs.service.BbsService;
import kr.dev.study.util.ValidationUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Bbs
 */
@Controller
@RequestMapping("bbs")
@SessionAttributes("bbs")
public class BbsController {
	
	private static final Logger logger = LoggerFactory.getLogger(BbsController.class);
	
	@Resource BbsService bbsService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String list(Model model) {
		
		model.addAttribute("items", bbsService.findAll());
		return "bbs/list";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String list(Model model, @PathVariable String id) throws BbsNotFoundException {
		
		logger.debug("Bbs findById : {}", id);
		
		model.addAttribute("item", bbsService.findById(id));
		return "bbs/view";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(Bbs bbs) {
		return "bbs/form";
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String create(@Valid Bbs bbs
			, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		logger.debug("Bbs : {}", bbs);
		
		if(bindingResult.hasErrors()){
			ValidationUtils.printLog(bindingResult, logger);
			return "bbs/form";
		}
		
		bbsService.create(bbs);
		
		redirectAttributes.addFlashAttribute("message", "등록 되었습니다.");
		logger.debug("Database : {}", bbs);
		
		return "redirect:/bbs";
	}
	
}
