


package com.InfraMart.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.InfraMart.beans.ImageData;
import com.InfraMart.beans.Product;
import com.InfraMart.beans.User;
import com.InfraMart.config.ImageUtils;
import com.InfraMart.dao.UserDao;
import com.InfraMart.service.UserService;


@CrossOrigin(origins="*")
@RestController
@RequestMapping("/infraprofile")
public class InfraProfileController 
{
	@Autowired
	private UserService userService;
	
//	@Autowired
//	private UserDao udao;
	
	@PostMapping("/addtocart")
	public ResponseEntity<User> addToCart(@RequestBody List<Product> plist, @RequestParam("email") String email)
	{
		long total=0;
		long ptotal=0;
		List<Product>plist1=new ArrayList<>();
		for(Product p:plist)
		{

			ImageData img=p.getImage();
			ImageData img1=(ImageData.builder()
					.imgid(img.getImgid())
					.name(img.getName())
					.type(img.getType())
					.imageData(ImageUtils.compressImage(img.getImageData())).build());
			p.setImage(img1);
			plist1.add(p);
		}
		System.out.println(total);
//		User user=userService.findByEmail(email);
		
		User u = userService.addTocart(plist1,email);
		if(u!=null)
		{
			return ResponseEntity.ok(u);
		}
		return new ResponseEntity("User session ended",HttpStatus.NOT_FOUND);
	}
  
	@PostMapping("/getuserbyemail")
	public ResponseEntity<User> getUserByEmail(@RequestBody User user)
	{
		User u=userService.findByEmail(user.getEmail());
		System.out.println(u);
		return ResponseEntity.ok(u);
	}
	
	
}
