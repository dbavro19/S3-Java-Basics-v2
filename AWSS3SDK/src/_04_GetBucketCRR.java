import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.StringInputStream;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.EnumSet;

public class _04_GetBucketCRR {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		AmazonS3 s3 = S3Factory.getS3Client();

		
		BucketReplicationConfiguration crrsetting = s3.getBucketReplicationConfiguration(S3Factory.S3_BUCKET);
		
		String crroutput = crrsetting.toString();
		
		System.out.println(crroutput);
		
		
	}

}
