import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListMultipartUploadsRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.MultipartUpload;
import com.amazonaws.services.s3.model.MultipartUploadListing;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class _08_ListMPUs {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		
		
		
    	
        String S3_URI = setEndpoint();
        String S3_ACCESS_KEY_ID = setAccessKey();
        String S3_SECRET_KEY = setSecretKey();
        String S3_BUCKET = setBucket();
        String S3_ECS_NAMESPACE = null; // use default namespace
        
        AmazonS3 s3 = getS3Client(S3_ACCESS_KEY_ID, S3_SECRET_KEY, S3_URI, S3_BUCKET, S3_ECS_NAMESPACE);
		
		ListMultipartUploadsRequest listRequest = new ListMultipartUploadsRequest(S3_BUCKET);
		MultipartUploadListing listing = s3.listMultipartUploads(listRequest);
		
		List<MultipartUpload> mpulist = listing.getMultipartUploads();
		
		boolean test = mpulist.isEmpty();
		
		for (int x =0; mpulist.size() > x; x++)
		{
			
			System.out.println("Number of incomplete MPU's: " + mpulist.size());
			System.out.println("Upload ID = " + mpulist.get(x).getUploadId());
		}
		
		System.out.println("Is bucket empty of MPUs: " + test);
		
		
		
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

}
