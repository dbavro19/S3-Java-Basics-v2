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

public class _06_CopyObject {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		
		AmazonS3 s3 = S3Factory.getS3Client();
		
		final Logger logger = Logger.getLogger(Log4JTest.class);
		
		//File file = new File("C:\\Users\\bavard\\Downloads\\upload.txt");
        
        //s3.setS3ClientOptions(arg0);

    	// retrieve object key/value from user
        System.out.println( "Enter the object key:" );
        String key = new BufferedReader( new InputStreamReader( System.in ) ).readLine();
       // System.out.println( "Enter the object content:" );
      //  String content = new BufferedReader( new InputStreamReader( System.in ) ).readLine();


		
		
        //add content type
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.addUserMetadata("test", "testvalue");




        CopyObjectRequest copy = new CopyObjectRequest(S3Factory.S3_BUCKET, key, S3Factory.S3_BUCKET, key+"1");
        copy.setMetadataDirective("MERGE");
        
        s3.copyObject(copy);

        System.out.println(String.format("created object [%s/%s]",
        		S3Factory.S3_BUCKET, key));
        
        //System.out.println(java.lang.System.currentTimeMillis());

	}
	
}
