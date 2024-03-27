package com.codewithrajan.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codewithrajan.blog.repository.IUserRepo;

@SpringBootTest
class BlogAppApplicationTests {

	@Autowired
	private IUserRepo userRepo;
	
	
	@Test
	void contextLoads() {
	}
	
//	@Test
	public void repoTest() {
		
		// it will create 
		// during starting of our spring application 
		// repository classes/interfaces is being scanned
		// and during runtime all implementation is done dynamically at runtime by spring
		// and that implementation class is called proxy classes
		// It is kept in one package
		// and it an implementation class so spring create object of these implementation interfaces.
		// When we Autowired it then this class object is injected in that variable 
		
		// implementation class of object come from that UserRepo(class)
		// and that class is created dynamically
		
		String className = this.userRepo.getClass().getName();
		String packageName = this.userRepo.getClass().getPackageName();
		
		System.out.println("Class Name: " + className);
		System.out.println("Package Name: "  + packageName);
	}

}
