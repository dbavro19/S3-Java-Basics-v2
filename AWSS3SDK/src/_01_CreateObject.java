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

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.CanonicalGrantee;
import com.amazonaws.services.s3.model.ObjectLockConfiguration;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.StringInputStream;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import org.apache.log4j.Logger;

public class _01_CreateObject {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		
		AmazonS3 s3 = S3Factory.getS3Client();
		
		//final Logger logger = Logger.getLogger(Log4JTest.class);
		
		File file = new File("C:\\Users\\bavard\\Downloads\\lifecycle.json");
        
        //s3.setS3ClientOptions(arg0);

    	// retrieve object key/value from user
        System.out.println( "Enter the object key:" );
        String key = new BufferedReader( new InputStreamReader( System.in ) ).readLine();
       // System.out.println( "Enter the object content:" );
      //  String content = new BufferedReader( new InputStreamReader( System.in ) ).readLine();

//lifecycle.json
		
		
		  String metaKey = "hashvalue";
	        
	        //get hash
		  
		  MessageDigest SHAmd = MessageDigest.getInstance("SHA-256");
		  try (InputStream SHAis = Files.newInputStream(Paths.get("C:\\Users\\bavard\\Downloads\\lifecycle.json"));
		       DigestInputStream SHAdis = new DigestInputStream(SHAis, SHAmd)) 
		  {
		    /* Read decorated stream (dis) to EOF as normal... */
		  }
		  byte[] SHAdigest = SHAmd.digest();
		  
		  String SHAmetaValue = bin2hex(SHAdigest);
		  
		  MessageDigest MD5md = MessageDigest.getInstance("MD5");
		  try (InputStream MD5is = Files.newInputStream(Paths.get("C:\\Users\\bavard\\Downloads\\lifecycle.json"));
		       DigestInputStream MD5dis = new DigestInputStream(MD5is, MD5md)) 
		  {
		    /* Read decorated stream (dis) to EOF as normal... */
		  }
		  byte[] MD5digest = MD5md.digest();
		  
		  String MD5metaValue = bin2hex(MD5digest);

	        
	     //   MessageDigest sha256digest = MessageDigest.getInstance("SHA-256");
	     //   byte[] sha256hash = sha256digest.digest(content.getBytes(StandardCharsets.UTF_8));
	        
	        //convert to hex
	    //    String shametaValue = bin2hex(sha256hash);
	        
	    //    MessageDigest md5digest = MessageDigest.getInstance("MD5");
	   //     byte[] md5hash = md5digest.digest(content.getBytes(StandardCharsets.UTF_8));
	        
	        //convert to hex
	   //     String md5metaValue = bin2hex(md5hash);
	        
	        
	        
	        System.out.println("SHA256 HASH VALUE IS: " + SHAmetaValue);
	        System.out.println("MD5 HASH VALUE IS: " + MD5metaValue);
        
        //add content type
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentMD5(MD5metaValue);
        metadata.addUserMetadata("md5hash", MD5metaValue);
        metadata.addUserMetadata("sha256hash", SHAmetaValue);


        //PutObjectRequest put = new PutObjectRequest(S3Factory.S3_BUCKET, key, content)
        //		.withMetadata(metadata);
      

        
       // s3.putObject(put);
        		
       // s3.putObject(S3Factory.S3_BUCKET, key, content);
        
        
        //s3.putObject("testtest",key,content);
        
        PutObjectRequest request = new PutObjectRequest(S3Factory.S3_BUCKET, key, file).withMetadata(metadata);
        
        s3.putObject(request);

        

        System.out.println(String.format("created object [%s/%s]",
        		S3Factory.S3_BUCKET, key));
        
        //System.out.println(java.lang.System.currentTimeMillis());

	}
	
	
	static String bin2hex(byte[] data) {
	    StringBuilder hex = new StringBuilder(data.length * 2);
	    for (byte b : data)
	        hex.append(String.format("%02x", b & 0xFF));
	    return hex.toString();
	}

}
