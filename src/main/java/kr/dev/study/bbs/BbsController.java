package kr.dev.study.bbs;

import javax.annotation.Resource;
import javax.validation.Valid;

import kr.dev.study.bbs.domain.Bbs;
import kr.dev.study.bbs.service.BbsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Bbs
 */
@Controller
@RequestMapping("bbs")
public class BbsController {
	
	private static final Logger logger = LoggerFactory.getLogger(BbsController.class);
	
	@Resource BbsService bbsService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(Model model) {
		
		model.addAttribute("items", bbsService.getList());
		
		return "bbs/list";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(@ModelAttribute("form") Bbs bbs) {
		
		return "bbs/form";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String form(@Valid @ModelAttribute("form") Bbs bbs
			, BindingResult result, RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()){
			logger.debug("result.hasErrors : {}", result.getFieldErrors());
			return form(bbs);
		}else{
			bbsService.save(bbs);
			redirectAttributes.addFlashAttribute("message", "등록 되었습니다.");
		}
		
		return "redirect:/bbs/";
	}
	
}
