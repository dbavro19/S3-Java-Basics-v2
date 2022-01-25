import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.CanonicalGrantee;
import com.amazonaws.services.s3.model.ObjectLockConfiguration;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.StringInputStream;
//import com.emc.object.s3.S3Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class AZ_Parse_and_Upload_Log {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		
		

		
	//ADD CLient Configuration 
    	
    	//------------ Enviornmentals-------------
        
		//String S3_ACCESS_KEY_ID = "dom";
        //String S3_SECRET_KEY = "dtomdFshXb9bWelb4GZ7FhhQCnYxYYpsg1hzvYaE";
        //String S3_URI = "10.246.22.181";
        //String S3_BUCKET = "demobucket";
        String S3_ECS_NAMESPACE = null; // use default namespace
        //String S3_HOST1 = "http://10.237.83.101";
        
        //TEst Drive
        //--------------------------------------------
    	
        String S3_URI = setEndpoint();
        String S3_ACCESS_KEY_ID = setAccessKey();
        String S3_SECRET_KEY = setSecretKey();
        String S3_BUCKET = setBucket();
		
        
        Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
        String strDate = dateFormat.format(date);  
 
    		
        
        
        
    	// create the ECS S3 Client
        AmazonS3 s3 = getS3Client(S3_ACCESS_KEY_ID, S3_SECRET_KEY, S3_URI, S3_BUCKET, S3_ECS_NAMESPACE);

    	
    
		//Parse the Log
		//Get orig Log location
    	String origLog ="/opt/emc/caspian/fabric/agent/services/object/main/log/dataheadsvc-access.log";
    	String parsedLog="/home/admin/dataheadsvc-access"+ S3_URI + ""+ strDate +".log";
    	File file = new File("/opt/emc/caspian/fabric/agent/services/object/main/log/dataheadsvc-access.log");
    	
	//Run the command
		//cat origLog |grep {bucketname} > parsedLog
	//Upload File to ECS
    
    	
    	

    	// retrieve the key value from user
        System.out.println( "Enter the object key:" );
        String key = new BufferedReader( new InputStreamReader( System.in ) ).readLine();
        

        
        PutObjectRequest request = new PutObjectRequest(S3_BUCKET, key, file);
        
        s3.putObject(request);

        

        System.out.println(String.format("created object [%s/%s]",
        		S3_BUCKET, key));
        
        //System.out.println(java.lang.System.currentTimeMillis());

	}
	
	
	 public static AmazonS3 getS3Client(String S3_ACCESS_KEY_ID, String S3_SECRET_KEY, String S3_ENDPOINT, String S3_BUCKET, String S3_ECS_NAMESPACE) {
	        BasicAWSCredentials creds = new BasicAWSCredentials(S3_ACCESS_KEY_ID, S3_SECRET_KEY); 


	        AwsClientBuilder.EndpointConfiguration ec = new AwsClientBuilder.EndpointConfiguration(S3_ENDPOINT,"us-east-1"); //ECS endpoint config
	        //AwsClientBuilder.EndpointConfiguration ec = new AwsClientBuilder.EndpointConfiguration("s3.us-east-1.amazonaws.com", "us-east-1"); //AWS config endpoint
	        AmazonS3 client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(creds))
	                .withEndpointConfiguration(ec)
	                .withPathStyleAccessEnabled(true)
	                .disableChunkedEncoding()
	                .build();
	        
	        
	        


			return client;
	    }
	 
	 
		public static String setEndpoint() throws IOException {
			
	        System.out.println( "Enter ECS Endpoint:" );
	        String key = new BufferedReader( new InputStreamReader( System.in ) ).readLine();
	        
	        return key;
		}
		
		public static String setAccessKey() throws IOException {
			
	        System.out.println( "Enter User Key (Access Key):" );
	        String key = new BufferedReader( new InputStreamReader( System.in ) ).readLine();
	        
	        return key;
		}
		
		public static String setSecretKey() throws IOException {
			
	        System.out.println( "Enter Secret Key:" );
	        String key = new BufferedReader( new InputStreamReader( System.in ) ).readLine();
	        
	        return key;
		}
		
		public static String setBucket() throws IOException {
			
	        System.out.println( "Enter Bucket Name:" );
	        String key = new BufferedReader( new InputStreamReader( System.in ) ).readLine();
	        
	        return key;
		}
		
		public static void runCmd(String origLog, String parsedLog, String S3_BUCKET) throws IOException
		{

			String[] cmd = {"cat", origLog, "|", "grep", S3_BUCKET, ">", parsedLog };
			
			ProcessBuilder pb = new ProcessBuilder(cmd);
			Process p = pb.start();
			BufferedReader reader =  new BufferedReader(new InputStreamReader(p.getErrorStream()));
			
		}
}
