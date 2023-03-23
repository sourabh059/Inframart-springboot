package com.InfraMart.controllers;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.InfraMart.beans.Category;
import com.InfraMart.beans.ImageData;
import com.InfraMart.beans.Product;
import com.InfraMart.beans.User;
import com.InfraMart.config.ImageUtils;
import com.InfraMart.service.CategoryService;
import com.InfraMart.service.ProductService;
import com.InfraMart.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@CrossOrigin(origins="*")
@RestController
@RequestMapping("/")
public class AdminController 
{
	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	//admin can also add user but mainly Given this functionality to add Admin and manager
	@PostMapping("/registration")
	public ResponseEntity<User> addUser(@RequestBody User u)
	{
		User u1=userService.addNewUser(u);
		if(u1!=null)
		{
			return new ResponseEntity("Registration successful!!",HttpStatus.OK);
		}
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	//to get All Users,Admins,Managers
	@GetMapping("/getallusers")
	public ResponseEntity<List<User>> getAllUsers()
	{
		List<User> ulist=userService.getAll();

		return ResponseEntity.ok(ulist);
	}

	//to Delete User,Admin,managers
	@DeleteMapping("/deleteuser/{userId}") //Note in delete we have taken int here instead long because it was throwing some error but in service,dao long 
	public ResponseEntity<String> deleteById(@PathVariable int userId) 
	{
		userService.deleteById(userId);

		return new ResponseEntity("User Deleted Successfully"+userId,HttpStatus.OK);
	}


	//to update user role
	@PostMapping("/updateuser") 
	public ResponseEntity<String> updateById(@RequestBody User u)
	{
		int n=userService.updateUserById(u);
		if(n>0)
		{
			return new ResponseEntity("User updated successfully"+u.getUserId(),HttpStatus.CREATED);
		}
		else
			return new ResponseEntity("User not found"+u.getUserId(),HttpStatus.NOT_FOUND);
	}

	//category crud by admin
	//admin adds category 
	@PostMapping("/addcat")
	public ResponseEntity<Category> addCategory(@RequestBody Category cat)
	{
		Category ctg=categoryService.addCategory(cat);
		if(ctg!=null)
		{
			return new ResponseEntity("Category added successfuly!!",HttpStatus.OK);
		}
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	//to get All Users,Admins,Managers
	@GetMapping("/getcategories")
	public ResponseEntity<List<Category>> getAllCats()
	{
		List<Category> clist=categoryService.getAllCats();

		return ResponseEntity.ok(clist);
	}

	//to Delete User,Admin,managers
	@DeleteMapping("/deletecat/{categoryId}") //Note in delete we have taken int here instead long because it was throwing some error but in service,dao long
	public ResponseEntity<String> deleteByCatId(@PathVariable int categoryId)
	{
		categoryService.deleteByCatId(categoryId);

		return new ResponseEntity("Category Deleted Successfully"+categoryId,HttpStatus.OK);
	}


	//to update user role
	@PutMapping("/updatecat") 
	public ResponseEntity<String> updateById(@RequestBody Category cat)
	{
		int n=categoryService.updateCategoryById(cat);
		if(n>0)
		{
			return new ResponseEntity("User updated successfully"+cat.getCategoryId(),HttpStatus.CREATED);
		}
		else
			return new ResponseEntity("User not found"+cat.getCategoryId(),HttpStatus.NOT_FOUND);
	}

	//	@GetMapping("/product")
	//	public ResponseEntity<List<Product>> getAllProducts()
	//	{
	//		List<Product> plist=productService.displayAllProducts();
	//		return ResponseEntity.ok(plist);
	//	}

	@PostMapping("/addproduct")
	public ResponseEntity<Product> addProduct(@RequestParam("productName") String pn,@RequestParam("productDescription") String pd,@RequestParam("productPrice") long pp
			,@RequestParam("productUnit") int pu,@RequestParam("categoryId") String s1,@RequestParam("image") MultipartFile imageFile)
//	(@RequestParam("product") String product, @RequestParam("image") MultipartFile imageFile)
//	(@RequestBody Product product, @RequestParam("file") MultipartFile imageFile)
	{
//		Product product1 = null ;
//		try {
//			product1 = new ObjectMapper().readValue(product, Product.class);
//			
//			
//			
//		} catch (JsonProcessingException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		System.out.println(pn);
		System.out.println(pd);
		System.out.println(pp);
		System.out.println(pu);
		System.out.println(imageFile.getContentType());
		long l1=Long.parseLong(s1);
		Product p=new Product();  
		p.setProductName(pn);
		p.setProductDescription(pd);
		p.setProductPrice(pp);
		
		
		p.setProductUnit(pu);
		System.out.println(l1);
		Category c=new Category();
		c.setCategoryId(l1);
		p.setCategory(c);
		//		System.out.println(product.getProductName());
		//		@RequestBody Product product,
		//we have to give select tag in front end so we can select category and thereby categoryId
		
		System.out.println(imageFile.getName());
		System.out.println(imageFile.getOriginalFilename());
//		System.out.println(product1.getProductName());
//		System.out.println(product1.getProductDescription());
//		System.out.println(product1.getProductPrice());
//		System.out.println(product1.getProductUnit());
		System.out.println(imageFile.getContentType());

//		Product p=new Product();
//		p.setProductName(product1.getProductName());
//		p.setProductDescription(product1.getProductDescription());
//		p.setProductPrice(product1.getProductPrice());
//		p.setProductUnit(product1.getProductUnit());
//		System.out.println(product1.getProductUnit());
		
		try {
			ImageData img=(ImageData.builder()
					.name(imageFile.getOriginalFilename())
					.type(imageFile.getContentType())
					.imageData(ImageUtils.compressImage(imageFile.getBytes())).build());

			System.out.println(imageFile.getOriginalFilename());
			System.out.println(ImageUtils.compressImage(imageFile.getBytes()));
			p.setImage(img);
			Product prd=productService.addProduct(p);
			if(prd!=null)
			{
				return ResponseEntity.ok(prd);
			}


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}


	//get all products
	@GetMapping("/displayproduct")
	public ResponseEntity<List<Product>> getAllProducts()
	{

		List<Product>plist1=new ArrayList<>();

		List<Product> plist=productService.displayAllProducts();
		//		Product p1=new Product();

		for(Product p:plist)
		{

			ImageData img=p.getImage();
			ImageData img1=(ImageData.builder()
					.imgid(img.getImgid())
					.name(img.getName())
					.type(img.getType())
					.imageData(ImageUtils.decompressImage(img.getImageData())).build());
			p.setImage(img1);
			plist1.add(p);
		}

		return ResponseEntity.status(HttpStatus.OK)

				.body(plist1);
	}


	//to Delete product
	@DeleteMapping("/deleteproduct/{productId}") //Note in delete we have taken int here instead long because it was throwing some error but in service,dao long
	public ResponseEntity<String> deleteByProductId(@PathVariable int productId)
	{
		productService.deleteByProductId(productId);

		return new ResponseEntity("Category Deleted Successfully"+productId,HttpStatus.OK);
	}


	//to update product
	@PutMapping("/updateproduct") 
	public ResponseEntity<String> updateByProductId(@RequestBody Product product)
	{
		int n=productService.updateByProductId(product);
		if(n>0)
		{
			return new ResponseEntity("User updated successfully"+product.getProductId(),HttpStatus.CREATED);
		}
		else
			return new ResponseEntity("User not found"+product.getProductId(),HttpStatus.NOT_FOUND);
	}



}


