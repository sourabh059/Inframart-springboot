package com.InfraMart.controllers;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.InfraMart.beans.Otp;
import com.InfraMart.beans.User;
import com.InfraMart.service.IEmailSenderService;
import com.InfraMart.service.UserService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping(value="/forgotpass")
public class ForgotPasswordController 
{
	@Autowired
	private PasswordEncoder passwordEncoder;
	//	Random random = new Random(1000);
	Random random = new Random();
	//	@Autowired 
	//	private UserDao udao; 

	@Autowired
	private UserService userService;

	@Autowired
	private IEmailSenderService emailService;


	static int otp;
	@PostMapping("/verifymail")
	public ResponseEntity<User> verifyEmailSendOTP(@RequestBody User u1)
	{
		System.out.println("at verity email"+u1);
		User user=userService.findByEmail(u1.getEmail());
		//		request.getSession().setAttribute("user", user);
		System.out.println("user"+user);
		if(user!=null)
		{ 
			otp = random.nextInt(999999);

			//			System.out.println("OTP "+otp);

			String subject = "OTP From InfraMart";
			String message ="OTP to reset your InfraMart password is : "+otp;


			String to=u1.getEmail();
			String msg = null;
			try {
				msg=emailService.sendOTPEmail(subject,message,to);
				//				return new ResponseEntity(""+msg+" "+user,HttpStatus.OK);  ///this ok enough to redirect user to enter otp page
				return ResponseEntity.ok(user);
			} catch (MailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return new ResponseEntity("Email not found!..check email and retry",HttpStatus.NOT_FOUND);
	}

	@PostMapping("/otpvfn")
	public ResponseEntity<User> verifyOTP(@RequestBody Otp newotp)
	{
		System.out.println(newotp);
		//		User user=(User)request.getSession().getAttribute("user");
		//		System.out.println("inside otpvfn"+user)
		System.out.println(newotp);
		System.out.println(newotp.getEmail());
		//As at front end we are storing object so we will we will redirect user here and if vfn success redirect to new password page
		if(otp==newotp.getNewotp())
		{
			User user=userService.findByEmail(newotp.getEmail());
			//			u.setPassword(null);
			return  ResponseEntity.ok(user);
		}
		return new ResponseEntity("please enter correct otp",HttpStatus.EXPECTATION_FAILED);
	}

	@PostMapping("/enternewpass")
	public ResponseEntity<String> newPassword(@RequestBody User user)
	{
		User user1=userService.findByEmail(user.getEmail());
		
//		System.out.println("user"+user);
//		System.out.println("user1"+user1);
		

	     user1.setPassword(passwordEncoder.encode(user.getPassword()));

//		System.out.println(user.getPassword());
//		System.out.println(user.getEmail());
		//As at front end we are storing object so we will we will use stored user and update password
		//updateUserById this method of AdminController but can't use as we are updating full user there
		int u=userService.updateById(user1);
		if(u>0)
		{
			return new ResponseEntity("Password updated Successfully",HttpStatus.OK);
		}
		return new ResponseEntity("user not found",HttpStatus.NOT_FOUND);

	}


}
