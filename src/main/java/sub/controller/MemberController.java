package sub.controller;

<<<<<<< HEAD
=======
import java.io.IOException;
>>>>>>> master
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

<<<<<<< HEAD
import model.domain.MeetingDTO;
import model.domain.MemberDTO;
import model.domain.PayDTO;
import model.service.MeetingService;
import model.service.MemberService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
=======
import model.domain.MemberDTO;
import model.service.MemberService;

>>>>>>> master
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

=======
import org.springframework.web.servlet.ModelAndView;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

>>>>>>> master

@Controller
public class MemberController {
	
	@Resource(name="memService")
    private MemberService memService;
<<<<<<< HEAD
	@Resource(name="meetingService")
    private MeetingService meetingService;
=======
	
>>>>>>> master
	
	@RequestMapping("/select.do")
	public ModelAndView select(){
		List<MemberDTO> list =  memService.memberSelect();
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", list);			
		mv.setViewName("jsonView");	//id=jsonView 객체를 찾아서 JsonView실행
		return mv;
	}
	
	@RequestMapping(value="login.do", method=RequestMethod.POST)
	public ModelAndView login(@RequestParam("email") String email, @RequestParam("mempw") String mempw,
							 HttpServletRequest req){
		ModelAndView mv=new ModelAndView("error");
		HttpSession session=req.getSession();
			MemberDTO vo = memService.isIdValid(email, mempw);
			if(vo != null){
<<<<<<< HEAD
				List<MeetingDTO> list = meetingService.meetingList(vo.getMemno());
				session.setAttribute("dto", vo);
				System.out.println(list);
				mv.addObject("list",list);
				mv.addObject("dto",vo );
				mv.setViewName("main");
=======
				session.setAttribute("dto", vo);
				mv.addObject("dto",vo );
				mv.setViewName("Test");
>>>>>>> master
				return mv;
			}
			return mv;
	}
	
	@RequestMapping(value="insertMember.do", method=RequestMethod.POST)
	public ModelAndView insertMember(@RequestParam("memname") String memname,
									@RequestParam("mempw") String mempw,
									@RequestParam("email") String email,
									@RequestParam("phonenumber") int phonenumber,
									HttpServletRequest req){
		MemberDTO dto = new MemberDTO(memname, mempw, email, phonenumber);
		dto.setDeviceid("82cfe0b7-b9b8-11e4-86a9-06a6fa0000b9");
		HttpSession session= req.getSession();
		ModelAndView mv=new ModelAndView();
		MemberDTO checkDto=memService.memJoinCheck(email);
			if(checkDto==null)
				{
<<<<<<< HEAD
					memService.insertMember(dto);
					session.setAttribute("dto", dto);
					mv.setViewName("main");
				}
			else
				{
					System.out.println("에러 발생");
					mv.setViewName("error");
				}
			return mv;
	}
	
	@RequestMapping(value="insertfacebook.do", method=RequestMethod.POST)
	public ModelAndView insertFacebook(@RequestParam("memname") String memname,
			@RequestParam("mempw") String mempw,
			@RequestParam("email") String email,
			@RequestParam("phonenumber") int phonenumber,
			HttpServletRequest req){
		MemberDTO dto = new MemberDTO(memname, mempw, email, phonenumber);
		HttpSession session= req.getSession();
		ModelAndView mv=new ModelAndView();
		MemberDTO checkDto=memService.memJoinCheck(email);
			if(checkDto==null)
				{
					memService.insertMember(dto);
					session.setAttribute("dto", dto);
					mv.setViewName("main");
				}
			else
				{
					System.out.println("에러 발생");
					mv.setViewName("error");
				}
		return mv;
	}
	
	/*@RequestMapping(value="updateMember.do", method=RequestMethod.POST)
=======
					
					memService.insertMember(dto);
					session.setAttribute("dto", dto);
					mv.setViewName("meeting");
				}
			/*if(dto==null)
			{
				mv.setViewName("joinError");
			}*/
			else
				{
					System.out.println("에러 발생");
					
					mv.setViewName("joinError");
				}
			return mv;
	}
	@RequestMapping(value="updateMember.do", method=RequestMethod.POST)
>>>>>>> master
	public ModelAndView updateMember(@RequestParam("email") String email,
			@RequestParam("memname") String memname,
			@RequestParam("mempw") String mempw,
			@RequestParam("phonenumber") int phonenumber,
			@RequestParam("cardpw") String cardpw,
			@RequestParam("account") String account,
			@RequestParam("deviceId") String deviceId,
			HttpServletRequest req){
		ModelAndView mv=new ModelAndView();
		HttpSession session = req.getSession();
		System.out.println("확인");
		
<<<<<<< HEAD
		MemberDTO dto = new MemberDTO(memname, mempw, cardpw, email, account, deviceId, phonenumber);
	
		dto.setEmail(req.getParameter("email").trim());
		dto.setMemname(req.getParameter("memname").trim());
		dto.setMempw(req.getParameter("mempw").trim());
		dto.setPhonenumber(Integer.parseInt(req.getParameter("phonenumber")));
		dto.setCardpw(req.getParameter("cardpw").trim());
		dto.setAccount(req.getParameter("account").trim());
		dto.setDeviceid(req.getParameter("deviceid").trim());
=======
		MemberDTO dto = new MemberDTO();
		dto.setEmail(email);
		dto.setMemname(memname);
		dto.setMempw(mempw);
		dto.setPhonenumber(phonenumber);
		dto.setCardpw(cardpw);
		dto.setAccount(account);
		dto.setDeviceid(deviceId);
>>>>>>> master
		System.out.println(dto);
		try{
			memService.updateMember(dto);
		}catch(Exception e){
			e.printStackTrace();
			req.getSession().setAttribute("error","에러났습니다.");
		}
		mv.setViewName("Test");
		return mv;
<<<<<<< HEAD
	}*/
=======
	}
	
	@RequestMapping(value="updatePhone.do", method=RequestMethod.POST)
	public ModelAndView updatePhone(
			@RequestParam("email") String email,
			@RequestParam("phonenumber") int phonenumber,
			HttpServletRequest req){
		System.out.println("이메일" + email);
		ModelAndView mv=new ModelAndView();
		HttpSession session = req.getSession();
		
		MemberDTO dto = new MemberDTO();
		dto.setEmail((String)((MemberDTO)session.getAttribute("dto")).getEmail());
		dto.setPhonenumber(phonenumber);
		System.out.println("dto:"+dto);
		try{
			memService.updatePhone(dto);
		}catch(Exception e){
			e.printStackTrace();
			req.getSession().setAttribute("error","에러났습니다.");
		}
		mv.setViewName("Test");
		return mv;
	}
	
	@RequestMapping(value="updateName.do", method=RequestMethod.POST)
	public ModelAndView updateName(
			@RequestParam("email") String email,
			@RequestParam("memname") String memname,
			HttpServletRequest req){
		System.out.println(email);
		ModelAndView mv=new ModelAndView();
		HttpSession session = req.getSession();
		
		MemberDTO dto = new MemberDTO();
		dto.setEmail((String)((MemberDTO)session.getAttribute("dto")).getEmail());
		dto.setMemname(memname);
		try{
			memService.updateName(dto);
		}catch(Exception e){
			e.printStackTrace();
			req.getSession().setAttribute("error","에러났습니다.");
		}
		mv.setViewName("Profile");
		return mv;
	}
>>>>>>> master
	
	
	@RequestMapping(value="isPwValid.do", method=RequestMethod.POST)
	public ModelAndView isPwValid(@RequestParam("mempw") String mempw,
				HttpServletRequest req)
			{
		ModelAndView mv=new ModelAndView();
		HttpSession session=req.getSession();
        
		MemberDTO dto = new MemberDTO();
		dto.setMempw(mempw);
		session.setAttribute("dto", memService.isPwValid(dto));
		
<<<<<<< HEAD
		mv.setViewName("main"); //id=test 객체를 찾아서 Test실행
		return mv;
	}
=======
		mv.setViewName("Test"); //id=test 객체를 찾아서 Test실행
		return mv;
	}
	@RequestMapping("/picEnroll.do")//upload
	public ModelAndView picEnroll(HttpServletRequest req){
		HttpSession session=req.getSession();
		String encType= "utf-8";
		ModelAndView mv=new ModelAndView();
		MemberDTO dto=new MemberDTO();
		String uploadFolder="image_storage";
		String saveFolder = req.getRealPath(uploadFolder);
		int maxSize=5*1024*1024;
		int result=0;
		MultipartRequest multi;
		
		try {
			multi = new MultipartRequest(req, saveFolder,maxSize, encType,new DefaultFileRenamePolicy());
			String profilePic= "./image_storage/"+multi.getFilesystemName("upload_bigfile");
			dto.setMemno((int)((MemberDTO)session.getAttribute("dto")).getMemno());
			dto.setMempic(profilePic);
			System.out.println(result);
			result=memService.updatePicture(dto);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(result);
		session.setAttribute("mempic", dto.getMempic());
	
		mv.addObject("mempic", dto.getMempic());
		
		mv.setViewName("Profile");
		return mv;
	}
	
	@RequestMapping(value="updatePassWord.do", method=RequestMethod.POST)
	public ModelAndView updatePassWord(
			@RequestParam("email") String email,
			@RequestParam("mempw") String mempw,
			HttpServletRequest req){
		ModelAndView mv=new ModelAndView();
		HttpSession session = req.getSession();
		MemberDTO dto = new MemberDTO();
		dto.setEmail((String)((MemberDTO)session.getAttribute("dto")).getEmail());
		dto.setMempw(mempw);
		try{
			memService.updatePassWord(dto);
		}catch(Exception e){
			e.printStackTrace();
			req.getSession().setAttribute("error","에러났습니다.");
		}
		mv.setViewName("Profile");
		return mv;
	}
	
	@RequestMapping(value="updateCardPassWord.do", method=RequestMethod.POST)
	public ModelAndView updateCardPassWord(
			@RequestParam("email") String email,
			@RequestParam("cardpw") String cardpw,
			HttpServletRequest req){
		ModelAndView mv=new ModelAndView();
		HttpSession session = req.getSession();
		
		MemberDTO dto = new MemberDTO();
		dto.setEmail((String)((MemberDTO)session.getAttribute("dto")).getEmail());
		dto.setCardpw(cardpw);
		try{
			memService.updateCardPassWord(dto);
		}catch(Exception e){
			e.printStackTrace();
			req.getSession().setAttribute("error","에러났습니다.");
		}
		mv.setViewName("Profile");
		return mv;
	}
	
	@RequestMapping(value="updateAccount.do", method=RequestMethod.POST)
	public ModelAndView updateAccount(
			@RequestParam("email") String email,
			@RequestParam("account") String account,
			HttpServletRequest req){
		System.out.println(email);
		ModelAndView mv=new ModelAndView();
		HttpSession session = req.getSession();
		
		MemberDTO dto = new MemberDTO();
		dto.setEmail((String)((MemberDTO)session.getAttribute("dto")).getEmail());
		dto.setAccount(account);
		try{
			memService.updateAccount(dto);
		}catch(Exception e){
			e.printStackTrace();
			req.getSession().setAttribute("error","에러났습니다.");
		}
		mv.setViewName("Profile");
		return mv;
	}
	
	@RequestMapping(value="updateDeviceId.do", method=RequestMethod.POST)
	public ModelAndView updateDeviceId(
			@RequestParam("email") String email,
			@RequestParam("deviceid") String deviceid,
			HttpServletRequest req){
		ModelAndView mv=new ModelAndView();
		HttpSession session = req.getSession();
		
		MemberDTO dto = new MemberDTO();
		dto.setEmail((String)((MemberDTO)session.getAttribute("dto")).getEmail());
		dto.setDeviceid(deviceid);
		try{
			memService.updateDeviceId(dto);
		}catch(Exception e){
			e.printStackTrace();
			req.getSession().setAttribute("error","에러났습니다.");
		}
		mv.setViewName("Profile");
		return mv;
	}
	
	
	@RequestMapping(value="deleteMember.do", method=RequestMethod.POST)
	public ModelAndView deleteMember(@RequestParam("email") String email,
				HttpServletRequest req)
			{
		ModelAndView mv=new ModelAndView();
		HttpSession session=req.getSession();
        
		MemberDTO dto = new MemberDTO();
		dto.setEmail((String)((MemberDTO)session.getAttribute("dto")).getEmail());
		session.setAttribute("dto", memService.deleteMember(dto));
		
		mv.setViewName("logout"); //id=test 객체를 찾아서 Test실행
		return mv;
	}
	
>>>>>>> master
}