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
import java.util.EnumSet;

public class _02_PutObjectNotification {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		AmazonS3 s3 = S3Factory.getS3Client();

		String ARN = "urn:osc:webhook::osaidc15b2543bcaa0a8:DomAWS";
		
		BucketNotificationConfiguration notificationConfiguration = new BucketNotificationConfiguration();
		
		notificationConfiguration.addConfiguration("EventingToLambda",
                new TopicConfiguration(ARN, EnumSet.of(S3Event.ObjectCreated)));
		

		SetBucketNotificationConfigurationRequest request = new SetBucketNotificationConfigurationRequest(
				S3Factory.S3_BUCKET, notificationConfiguration);
		
		 s3.setBucketNotificationConfiguration(request);
		
	}

}
