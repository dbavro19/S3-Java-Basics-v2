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


import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.DeleteObjectsRequest.KeyVersion;
import com.amazonaws.services.s3.model.DeleteObjectsResult;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

public class BulkDelete {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		AmazonS3 s3 = S3Factory.getS3Client();
        
    	// retrieve the object value from user
        System.out.println( "Enter the object key1:" );
        String key1 = new BufferedReader( new InputStreamReader( System.in ) ).readLine();
        
        System.out.println( "Enter the object key2:" );
        String key2 = new BufferedReader( new InputStreamReader( System.in ) ).readLine();
        
        
    	
        DeleteObjectsRequest multiObjectDeleteRequest = new DeleteObjectsRequest(S3Factory.S3_BUCKET).withKeys(key1, key2);
        
        DeleteObjectsResult delObjRes = s3.deleteObjects(multiObjectDeleteRequest);
        int numDeletes = delObjRes.getDeletedObjects().size();

        System.out.println(numDeletes + " objects successfully deleted.");
        
	}

}
