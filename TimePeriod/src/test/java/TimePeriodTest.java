import java.util.Date;

import org.junit.Assert;                                                   
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;                                                         
                                                                               
                                                                               
public class TimePeriodTest {                                                  
	static TimePeriod periodA;                                                        
	static Date startDateA;                                                    
	static Date endDateA;                                                      
	                                                                           
	@Before                                                                   
	public void setUp(){                                                       
		periodA = new TimePeriod();                                            
		startDateA = TimePeriod.setDate(8, 1, 2015);                              
		endDateA = TimePeriod.setDate(25, 3, 2015);                               
		periodA.setStartDate(startDateA);
		periodA.setEndDate(endDateA);
		System.out.println("Period A ==> " + startDateA + " " + endDateA);
		
	}                                                                          
	                                                                           
	@Test 
	public void testPeriodABeforePeriodB(){                                    
		TimePeriod periodB = new TimePeriod();                                 
		Date startDateB = TimePeriod.setDate(20, 4, 2015);                  
		Date endDateB = TimePeriod.setDate(15, 5, 2015);                   
		periodB.setStartDate(startDateB);
		periodB.setEndDate(endDateB);
		System.out.println("Period B ==> " + startDateB + " " + endDateB);
		System.out.println("test-periodA before periodB \n");
		Assert.assertFalse(periodA.overlapsWith(periodB));
		                                                                                                                                            
	}  
	
	@Test
	public void testPeriodAEqualsPeriodB(){
		TimePeriod periodB = new TimePeriod();                                 
		Date startDateB = TimePeriod.setDate(8, 1, 2015);                
		Date endDateB = TimePeriod.setDate(25, 3, 2015);                  
		periodB.setStartDate(startDateB); 
		periodB.setEndDate(endDateB);
		System.out.println("Period B ==> " + startDateB + " " + endDateB);
		System.out.println("test-periodA equals periodB \n");
		Assert.assertTrue(periodA.overlapsWith(periodB));
	}
	  
	@Test
	public void testPeriodAAfterPeriodB(){
		TimePeriod periodB = new TimePeriod();                                 
		Date startDateB = TimePeriod.setDate(15, 10, 2014);               
		Date endDateB = TimePeriod.setDate(1, 1, 2015);                   
		periodB.setStartDate(startDateB); 
		periodB.setEndDate(endDateB);
		System.out.println("Period B ==> " + startDateB + " " + endDateB);
		System.out.println("test-periodA after periodB \n");
		Assert.assertFalse(periodA.overlapsWith(periodB));
	}
	
	
	@Test
	public void testPeriodAInsidePeriodB(){
		TimePeriod periodB = new TimePeriod();                                 
		Date startDateB = TimePeriod.setDate(1, 1, 2015);                
		Date endDateB = TimePeriod.setDate(26, 3, 2015);                   
		periodB.setStartDate(startDateB); 
		periodB.setEndDate(endDateB);
		System.out.println("Period B ==> " + startDateB + " " + endDateB);
		System.out.println("test-periodA inside periodB \n");
		Assert.assertTrue(periodA.overlapsWith(periodB));
	}
	
	
	@Test
	public void testPeriodBInsidePeriodA(){
		TimePeriod periodB = new TimePeriod();                                 
		Date startDateB = TimePeriod.setDate(10, 1, 2015);                
		Date endDateB = TimePeriod.setDate(20, 3, 2015);                   
		periodB.setStartDate(startDateB); 
		periodB.setEndDate(endDateB);
		System.out.println("Period B ==> " + startDateB + " " + endDateB);
		System.out.println("test-periodB inside periodA \n");
		Assert.assertTrue(periodA.overlapsWith(periodB));
	}
	
	
	@Test
	public void testEndBAfterStartA(){
		TimePeriod periodB = new TimePeriod();                                 
		Date startDateB = TimePeriod.setDate(10, 10, 2014);                
		Date endDateB = TimePeriod.setDate(20, 1, 2015);                  
		periodB.setStartDate(startDateB); 
		periodB.setEndDate(endDateB);
		System.out.println("Period B ==> " + startDateB + " " + endDateB);
		System.out.println("test-endB after startA \n");
		Assert.assertTrue(periodA.overlapsWith(periodB));
	}
	
	
	@Test
	public void testStartBBeforeEndA(){
		TimePeriod periodB = new TimePeriod();                                 
		Date startDateB = TimePeriod.setDate(20, 1, 2015);                 
		Date endDateB = TimePeriod.setDate(20, 6, 2015);                   
		periodB.setStartDate(startDateB); 
		periodB.setEndDate(endDateB);
		System.out.println("Period B ==> " + startDateB + " " + endDateB);
		System.out.println("test-startB before endA \n");
		Assert.assertTrue(periodA.overlapsWith(periodB));
	}
	
	@Test
	public void testStartBEqualsEndA(){
		TimePeriod periodB = new TimePeriod();                                 
		Date startDateB = TimePeriod.setDate(25, 3, 2015);                 
		Date endDateB = TimePeriod.setDate(20, 6, 2015);                   
		periodB.setStartDate(startDateB); 
		periodB.setEndDate(endDateB);
		System.out.println("Period B ==> " + startDateB + " " + endDateB);
		System.out.println("test-startB equals endA \n");
		Assert.assertFalse(periodA.overlapsWith(periodB));
	}
}                                                                              
                                                                               