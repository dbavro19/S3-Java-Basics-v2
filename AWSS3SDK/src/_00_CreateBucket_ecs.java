import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


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


public class _00_CreateBucket_ecs {

	public static void main(String[] args) throws Exception {
    	// create the ECS S3 Client
		AmazonS3 s3 = S3Factory.getS3Client();
		
		System.out.println( "Enter the new Bucket name:" );
        String key = new BufferedReader( new InputStreamReader( System.in ) ).readLine();

        
        
        s3.createBucket(key);
        

    	System.out.println( String.format("Successfully created bucket:" + key ));
    }
}


