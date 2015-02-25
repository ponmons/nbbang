package sub.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.domain.MeetingDTO;
import model.domain.MemberDTO;
import model.service.MeetingService;
import model.service.MemberService;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sun.org.mozilla.javascript.internal.json.JsonParser;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@Controller
public class MemberController {
	
	@Resource(name="memService")
    private MemberService memService;
	@Resource(name="meetingService")
    private MeetingService meetingService;
	
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
		 MemberDTO checkDto = memService.memJoinCheck(email);
			if(checkDto != null){
				List<MeetingDTO> list = meetingService.meetingList(checkDto.getMemno());
				session.setAttribute("dto", checkDto);
				System.out.println(list);
				mv.addObject("list",list);
				mv.addObject("dto",checkDto );
				mv.setViewName("main");
				return mv;
			}
			return mv;
	}
	@RequestMapping(value="insertfacebook.do", method=RequestMethod.GET)
	public ModelAndView insertFacebook(@RequestParam("data") String data,
													HttpServletRequest req){
		JSONObject obj = JSONObject.fromObject(JSONSerializer.toJSON(data));
		System.out.println("req" +obj );
		MemberDTO dto = null;
		 MemberDTO checkDto = memService.memJoinCheck((String)obj.get("email"));
		HttpSession session = req.getSession();
		ModelAndView mv = new ModelAndView();
			if(checkDto == null)
				{
					dto = new MemberDTO((String)obj.get("name"), (String)obj.get("id"), (String)obj.get("email"), 0);
					dto.setMempic("https://graph.facebook.com/"+(String)obj.get("id")+"/picture");
					System.out.println(dto);
					memService.insertMember(dto);
					session.setAttribute("dto", dto);
					mv.addObject("dto", dto);
					mv.setViewName("main");
				}
			else
				{
					System.out.println("페이스북으로 로그인");
					session.setAttribute("dto", checkDto);
					mv.addObject("dto", checkDto);
					mv.setViewName("main");
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
					mv.setViewName("main");
				}
			return mv;
	}
	@RequestMapping(value="profile.do", method=RequestMethod.POST)
	public ModelAndView moveProfile(HttpServletRequest req){
		ModelAndView mv=new ModelAndView();
		HttpSession session = req.getSession();
		mv.setViewName("Profile");
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
		
		MemberDTO dto = new MemberDTO(memname, mempw, cardpw, email, account, deviceId, phonenumber);
	
		dto.setEmail(req.getParameter("email").trim());
		dto.setMemname(req.getParameter("memname").trim());
		dto.setMempw(req.getParameter("mempw").trim());
		dto.setPhonenumber(Integer.parseInt(req.getParameter("phonenumber")));
		dto.setCardpw(req.getParameter("cardpw").trim());
		dto.setAccount(req.getParameter("account").trim());
		dto.setDeviceid(req.getParameter("deviceid").trim());
		System.out.println(dto);
		try{
			memService.updateMember(dto);
		}catch(Exception e){
			e.printStackTrace();
			req.getSession().setAttribute("error","에러났습니다.");
		}
		mv.setViewName("Test");
		return mv;
	}*/
	
	
	@RequestMapping(value="isPwValid.do", method=RequestMethod.POST)
	public ModelAndView isPwValid(@RequestParam("mempw") String mempw,
				HttpServletRequest req)
			{
		ModelAndView mv=new ModelAndView();
		HttpSession session=req.getSession();
        
		MemberDTO dto = new MemberDTO();
		dto.setMempw(mempw);
		session.setAttribute("dto", memService.isPwValid(dto));
		
		mv.setViewName("main"); //id=test 객체를 찾아서 Test실행
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
		mv.setViewName("main");
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
	
	
}
