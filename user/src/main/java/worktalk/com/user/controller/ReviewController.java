package worktalk.com.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import worktalk.com.user.domain.Review;
import worktalk.com.user.repository.ReviewDAO;
import worktalk.com.user.service.ReviewFileService;
import worktalk.com.user.service.ReviewService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ReviewController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	
	@Autowired
	ReviewService service;

	@Autowired
	ReviewFileService service_file;

	@Autowired
	ReviewDAO rv_dao;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
//	//1대1문의 페이지
//	@RequestMapping(value = "/mypage/customercenter.do", method = RequestMethod.GET)
//	public String selectAll(Model model) {
//		logger.info("Welcome selectAll");
//		
//		List<Customer_center> cc_boardlist = service.selectAll();
//		logger.info("cc_boardlist.size() : {}",cc_boardlist.size());
//		
//		model.addAttribute("cc_boardlist",cc_boardlist);
//
//		return "customerCenter/customerCenter";
//	}
	
	//문의 작성
	@RequestMapping(value = "/mypage/review_insert.do", method = RequestMethod.POST)
	public String insert(Review review) {
		logger.info("Welcome insertOK...");
		logger.info("{}", review);
		// vo에 맵핑을 위한 빈 등록-root-context.xml << multipartResolver필요
		review = service_file.getVO(review);
		
		int result = service.insert(review);
		logger.info("result : {}", result);
			return "redirect:reviewqna.do";
		
	}
	
	//문의 수정
//	@RequestMapping(value = "/mypage/customercenter_update.do", method = RequestMethod.POST)
//	public String update(Customer_center customer_center) {
//		logger.info("Welcome updateOK");
//		
//		customer_center = service_file.getVO(customer_center);
//		logger.info("{}",customer_center);
//		
//		int result = service.update(customer_center);
//		logger.info("result : {}", result);
//		if (result == 1) {
//			return "redirect:customercenter.do";
//		} else {
//			return "customercenter_update.do";
//		}
//
//	}
//	
//	//문의 삭제
//	@RequestMapping(value = "/mypage/customercenter_delete.do", method = RequestMethod.GET)
//	public String delete(Customer_center customer_center) {
//		logger.info("Welcome deleteOK");
//		int result = service.delete(customer_center);
//		logger.info("result : {}", result);
//
//		return "redirect:customercenter.do";
//	}
//	
//	@RequestMapping(value = "/mypage/customercenter_searchList.do", method = RequestMethod.GET)
//	public String searchList(Model model, String searchKey, String searchWord) {
//		logger.info("Welcome searchList");
//		logger.info("searchKey:{}", searchKey);
//		logger.info("searchWord:{}", searchWord);
//
//		List<Customer_center> cc_boardlist = service.searchList(searchKey, searchWord);
//		logger.info("result() : {}", cc_boardlist.size());
//		model.addAttribute("cc_boardlist", cc_boardlist);
//		
//		return "customerCenter/customerCenter";
////		return "mypage/selectAll";
//	}
//
//	//qna관리 페이지
//			@RequestMapping(value = "/mypage/reviewqna.do", method = RequestMethod.GET)
//			public String selectAll(Model model, String writer) {
//				logger.info("Welcome selectAll");
//				
//				List<Review> review_list = rv_dao.findByName(writer);
//				logger.info("review_list.size() : {}",review_list.size());
//				
//				model.addAttribute("review_list",review_list);
//				
//				return "reviewqna/reviewqna";
//			}
}
