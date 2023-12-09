package performance;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * @author Martin Gonzalez-Rodriguez
 * 
 * This class is able to execute algorithms from external classes using Java's reflection capabilities.
 * 
 * The goal is to measure the execution time of those algorithms executing them using increasing amounts of workload (n).
 * 
 * The resuts are stored in CSV files able to be later processed by spreadsheet software 
 */


public class TestBench {
	
	public static final int SLEEP_TIME = 2; // amount of time consumed by every execution cycle

	
	/**
	 * Executes a method in an external class a number of times calculating the average execution time for all the runs, which
	 * is stored in a CSV file (one entry per workload).
	 * 
	 * The workload for each execution set can be defined including the initial and final workload. 
	 * 
	 * 
	 * @param output name for the CSV file
	 * @param samples number of times to execute the method
	 * @param startN initial workload
	 * @param endN final workload
	 * @param className Name of the class containing the method.
	 * @param methodName Name of the method
	 */
	public static void test (String output, int samples, int startN, int endN, String className, String methodName) {
		FileWriter file = null;
	    PrintWriter pw = null;
	    
	    System.out.println();
	    
	    System.out.println("*** " + output + "***"); 
	    try
	    {
	        file = new FileWriter(output);
	        pw = new PrintWriter(file);

	        if (startN > endN)
	        	pw.println ("workload empty!");
	        else
	        {
	        	for (int n = startN; n < endN; n++)
	        	{
	        		long avgTime = 0;
	        		for (int i = 0; i < samples; i++)
		        	{
	        			long timeStart = System.currentTimeMillis();
	        			
	        			testAlgorithm(className, methodName, n);

	        			avgTime += (System.currentTimeMillis() - timeStart);
	        		}
	        		
	        		avgTime /= samples;
	        		pw.println(avgTime);
	        	}
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	       try {
	       // Make yourself sure the file is closed!
	       if (file != null)
	          file.close();
	       } catch (Exception e2) {
	          e2.printStackTrace();
	       }
	    }  
	    
	    System.out.println();
	}
	
	    
	/**
	 * Executes a method in an external class making use of the Java's reflection capabilities.
	 * 
	 * @param className Name of the class
	 * @param methodName Name of the method
	 * @param n Workload for the method (called methods MUST accept a single int value as a parameter
	 */
	
	public void testAlgorithmFull (String className, String methodName, int n)
	{    
	    Class<?> myClass = null;
	    Object myObject = null;
   
	    try {
	         //loading class dynamically using reflection
	    	 myClass = Class.forName(className);
	         myObject = myClass.newInstance();

	        } catch (InstantiationException ex) {
	            System.err.println("Not able to create Instance of Class");
	        } catch (IllegalAccessException ex) {
	            System.err.println("Not able to access Class");
	        } catch (ClassNotFoundException ex) {
	            System.err.println("Not able to find Class");
	        }
	    
	       try {
	            //getting method instance reflectively
	    	    Class<?>[] params=new Class[1];
	            params[0]=Integer.TYPE;
	            
	            Method m = myClass.getMethod(methodName, params);
	          	                 
	            //calling method in java using reflection dynamically
	            m.invoke(myObject, n);

	        } catch (NoSuchMethodException ex) {
	            System.err.println("Not able to find Method on class");
	            ex.printStackTrace();
	        } catch (SecurityException ex) {
	            System.err.println("Security Exception raised");
	            ex.printStackTrace();
	        } catch (IllegalAccessException ex) {
	            System.err.println("Not able to access method ");
	            ex.printStackTrace();
	        } catch (IllegalArgumentException ex) {
	            System.err.println("Incorrect supplied arguments");
	            ex.printStackTrace();
	        } catch (InvocationTargetException ex) {
	            System.err.println("Not able to invoke method by String in Java");
	            ex.printStackTrace();
	        }
	    }
	
	
	/**
	 * Executes a method in an external class making use of the Java's reflection capabilities.
	 * 
	 * @param className Name of the class
	 * @param methodName Name of the method
	 * @param n Workload for the method (called methods MUST accept a single int value as a parameter
	 */
	public static void testAlgorithmReduced (String className, String methodName, int n)
	{    
	    Class<?> myClass = null;
	    Object myObject = null;
   
	    try {
	         //loading class dynamically using reflection
	    	 myClass = Class.forName(className);
	         myObject = myClass.newInstance();

	        } catch (Exception ex) {
	            System.err.println("Error loading the class ");
	        } 
	    
	       try {
	            //getting method instance reflectively
	    	    Class<?>[] params=new Class[1];
	            params[0]=Long.TYPE;
	            
	            Method m = myClass.getMethod(methodName, params);
	          	                 
	            //calling method in java using reflection dynamically
	            m.invoke(myObject, n);

	        } catch (Exception ex) {
	            System.err.println("Error loading the class ");
	        } 
	}
	
	/**
	 * Executes a method in an external class making use of the Java's reflection capabilities.
	 * 
	 * @param className Name of the class
	 * @param methodName Name of the method
	 * @param n Workload for the method (called methods MUST accept a single long  value as a parameter
	 */
	public static void testAlgorithm (String className, String methodName, long n) throws Exception
	{    
	    Class<?> myClass = null;
	    Object myObject = null;
   
	    //Loads the class dynamically using reflection
	    myClass = Class.forName(className);
	    myObject = myClass.newInstance();

	       
	    //Gets a method instance 
	    Class<?>[] params=new Class[1];
	    params[0]=Long.TYPE;
	            
	    Method m = myClass.getMethod(methodName, params);
	          	                 
	    //Calls the method in java using reflection
	    m.invoke(myObject, n);
	}
 
	
	/**
	 * Slows Down the execution of an algorithm
	 * 
	 * @param i Prints out the iteration number in the console (information to the user)
	 * @return
	 */
     
	/*public static void doNothing(long i)
	{
	   System.out.println ("Doing nothing at step... ("+i+")");
		
		long endTime = System.currentTimeMillis() + SLEEP_TIME;
	    while (System.currentTimeMillis() < endTime) 
	    {
	        // do nothing
	    }
	}*/
			public static void doNothing(long n) {
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try
		{

//			System.out.println("TESTING COMPUTATIONAL REFLECTION...");
//			TestBench.testAlgorithm("Algorithms", "linear", 50);
//			System.out.println();			
			
			System.out.println("RECORDING EXECUTION TIME FOR ITERATIVE ALGORITHMS...");
			//System.out.println("<LINEAR>");
			
			//to store mediations, sample, initial value of n, last value of n, package.Class, method to test
			//O(n)- n=50, O(n^2) lower, O(log(n)) higher
			//time in ms
			
			TestBench.test("01_Graph_Floyd.txt", 3, 100, 300, "performance.GraphPerformanceTest", "runFloyd"); 
			System.out.println();
			
//			TestBench.test("02_Graph_Dijkstra.txt", 3, 100, 300, "performance.GraphPerformanceTest", "runDijkstra"); 
//			System.out.println();
//			
//			TestBench.test("03_Graph_Build.txt", 3, 100, 300, "erformance.GraphPerformanceTest", "initGraph"); 
//			System.out.println();
			
			System.out.println();
			System.out.println("EXECUTION ENDS");
			System.out.println();
		}
		catch (Exception ex) 
		{
            System.err.println("Exceptin Detected: " + ex);
        } 
		
	}

}