import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.StringInputStream;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;

import org.apache.log4j.Logger;

public class _99_HashTesting {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		
		AmazonS3 s3 = S3Factory.getS3Client();
		
		
		
		
		//final Logger logger = Logger.getLogger(Log4JTest.class);
		
		File file = new File("C:\\Users\\bavard\\Downloads\\lifecycle.json");
		File badfile = new File ("C:\\Users\\bavard\\Downloads\\lifecycle.json");
        
        //s3.setS3ClientOptions(arg0);

    	// retrieve object key/value from user
        System.out.println( "Enter the object key:" );
        String key = new BufferedReader( new InputStreamReader( System.in ) ).readLine();
       // System.out.println( "Enter the object content:" );
      //  String content = new BufferedReader( new InputStreamReader( System.in ) ).readLine();


        
      
		//SHA Hashing

        byte[] shaHash = hashFile("SHA-256", file);
		
        String shaValue = bin2hex(shaHash);
		
        
        //MD5 Hashing


        byte[] mdHash = hashFile("MD5", file);
        
	        
        String mdValue = bin2hex(mdHash);
        
        System.out.println("SHA = " + shaValue);
        
        System.out.println("MD5 = " + mdValue);
		  
        System.out.println("TESTING BASE 64 for MD5");
        
        String base64hash = bin2Base64(mdHash);
        
        System.out.println("MD5 (base64) = " + base64hash);
        
        
        
        
        
      //S3
        //Create Metadata
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentMD5(base64hash);
        metadata.addUserMetadata("md5hash", mdValue);
        metadata.addUserMetadata("sha256hash", shaValue);

        //Create Put Request
        PutObjectRequest request = new PutObjectRequest(S3Factory.S3_BUCKET, key, file).withMetadata(metadata);
        //Put Object
        s3.putObject(request);

     // Fix this (encoding)   DatatypeConverter.
       // DatatypeConverter
  
        
        System.out.println(String.format("created object [%s/%s]",
        		S3Factory.S3_BUCKET, key));
		  

	}
	
	
	static String bin2hex(byte[] data) {
	    StringBuilder hex = new StringBuilder(data.length * 2);
	    for (byte b : data)
	        hex.append(String.format("%02x", b & 0xFF));
	    return hex.toString();
	}
	
	static byte[] hashFile (String s, File file) throws IOException, NoSuchAlgorithmException
	{
		
        byte[] buffer= new byte[8192];
        int count;
        MessageDigest digest = MessageDigest.getInstance(s);
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        while ((count = bis.read(buffer)) > 0) {
            digest.update(buffer, 0, count);
        }
        bis.close();

       byte[] hash = digest.digest();
       
       return hash;
	}
	
	static String hash2String (byte[] data)
	{
		String s = new String(data, StandardCharsets.UTF_8);
        System.out.println("Output : " + s);
		
		return "s";
	}
	
	static String bin2Base64 (byte[] data)
	{
		
		String base64 = Base64.getEncoder().encodeToString(data);
		
		return base64;
	}

}
