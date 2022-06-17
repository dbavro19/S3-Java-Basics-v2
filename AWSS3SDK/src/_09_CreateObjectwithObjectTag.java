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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;

import javax.crypto.KeyGenerator;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.log4j.Logger;

public class _09_CreateObjectwithObjectTag {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		
		AmazonS3 s3 = S3Factory.getS3Client();
		
		//final Logger logger = Logger.getLogger(Log4JTest.class);
		
        
        //s3.setS3ClientOptions(arg0);

    	// retrieve object key/value from user
        System.out.println( "Enter the object key:" );
        String key = new BufferedReader( new InputStreamReader( System.in ) ).readLine();
        System.out.println( "Enter the object content:" );
        String content = new BufferedReader( new InputStreamReader( System.in ) ).readLine();



        PutObjectRequest putRequest = new PutObjectRequest(S3Factory.S3_BUCKET, key, content); // create object request we can add md and tags to
        
        //add metadata (which is different than an Object Tag)
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.addUserMetadata("mdkey", "mdvalue"); // add MD
        
        //Add Tagging set (policy aware metadata...basically)
        List<Tag> tag = new ArrayList<Tag>();
        tag.add(new Tag("exampletag", "examplevalue"));
        putRequest.setMetadata(metadata);
        putRequest.setTagging(new ObjectTagging(tag));


 
        
      //  s3.putObject(S3Factory.S3_BUCKET, key, content) //I dont know a way to add tags to the putObjectMethod
      //  	.setMetadata(metadata); // But i can put regular old MD
        
        s3.putObject(putRequest);
        

        System.out.println(String.format("created object [%s/%s]",
        		S3Factory.S3_BUCKET, key));
        
        //System.out.println(java.lang.System.currentTimeMillis());

	}	

}
