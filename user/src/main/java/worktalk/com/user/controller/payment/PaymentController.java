package worktalk.com.user.controller.payment;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import worktalk.com.user.domain.Pay;
import worktalk.com.user.domain.Reservation;
import worktalk.com.user.service.PayService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class PaymentController {

	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

	@Autowired
	HttpSession session;
	@Autowired
	PayService service;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = { "/payment/prepaid_test.do" }, method = RequestMethod.GET)
	public String prepaid_test() {
		logger.info("Welcome prepaid_test!");

		return "payment/prepaid";
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = { "payment/payment_test_getResult.do" }, method = RequestMethod.POST)
	public Object payment_test_getResult(Pay pay, Reservation reservation) {
		logger.info("Welcome payment_test_getResult!");
		logger.info("Reservation: {}", reservation);
		logger.info("Pay: {}", pay);

		return "payment/prepaid";
	}
	
	@ResponseBody
//	@RequestMapping(value = { "/payment/verify/{imp_uid}" }, method = RequestMethod.POST)
	@RequestMapping(value = { "payment/verify.do" }, method = RequestMethod.POST)
	public IamportResponse<Payment> paymentByImpUid(Model model
			, Locale locale
			, HttpSession session,
			Pay pay)
			throws IamportResponseException, IOException {

		// REST API 키와 REST API secret 를 아래처럼 순서대로 입력한다.

		logger.info("Welcome paymentByImpUid!");
		logger.info("pay: {}", pay);

		return service.verifyByUid(pay.getImp_uid());
	}
	
//	@ResponseBody
//	@RequestMapping(value = { "/payment/findByName" }, method = RequestMethod.GET)
//	public IamportResponse<Payment> paymentFindByName(
//			Model model, Locale locale ,HttpSession session, List<String> id_list)
//			throws IamportResponseException, IOException {
//
//		// REST API 키와 REST API secret 를 아래처럼 순서대로 입력한다.
//
//		logger.info("Welcome findByName!");
//		String id = "id";
//		return null;
////		return api.paymentByImpUid(id_list);
//	}
	
	@ResponseBody
	@RequestMapping(value = { "payment/cancel.do" }, method = RequestMethod.GET)
	public IamportResponse<Payment> paymentCancel(
			Model model, Locale locale ,HttpSession session, Pay pay)
					throws IamportResponseException, IOException {
		
		// REST API 키와 REST API secret 를 아래처럼 순서대로 입력한다.
		
		logger.info("Welcome cancel!");
		
		return service.cancelByUid(pay.getImp_uid());
	}
	
	@RequestMapping(value = { "payment/cancel_page.do" }, method = RequestMethod.GET)
	public String paymentCancel_page(
			Model model, Locale locale ,HttpSession session, Pay pay, long space_num)
					throws IamportResponseException, IOException {
		
		// REST API 키와 REST API secret 를 아래처럼 순서대로 입력한다.
		
		logger.info("Welcome cancel_page!");
		logger.info("pay: {}", pay);
		
		service.cancelByUid(pay.getImp_uid());
		
		if (space_num == 0) {
			logger.info("redirecting to selectAll....");
			return "redirect:http://localhost:8100/user/space_selectAll.do";
		} else {
			logger.info("redirecting to selectOne....space_num : {}", space_num);
			return "redirect:http://localhost:8100/user/space_selectOne.do?space_num="+space_num;
		}
		
	}
	
	@RequestMapping(value = { "payment/findHistoryByName.do" }, method = RequestMethod.GET)
	public String paymentCancel_page(Pay pay, Model model) {
		logger.info("Welcome findHistoryByName!");
		logger.info("pay: {}", pay);
		
		pay.setP_name((String)session.getAttribute("user_name"));
		pay.setP_name("사용자");
		List<Pay> payment_list =  service.findHistoryByName(pay);
		logger.info("size: {}", payment_list.size());
		logger.info("{}", payment_list);
		
		model.addAttribute("payment_list", payment_list);
		
		return "home";
	}
}
