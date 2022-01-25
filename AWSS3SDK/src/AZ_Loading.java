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

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.StringInputStream;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;

//import org.apache.log4j.Logger;

public class AZ_Loading {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		
		String S3_URI = setEndpoint();
        String S3_ACCESS_KEY_ID = setAccessKey();
        String S3_SECRET_KEY = setSecretKey();
        String S3_BUCKET = setBucket();
        String S3_ECS_NAMESPACE = null; // use default namespace
        
        AmazonS3 s3 = getS3Client(S3_ACCESS_KEY_ID, S3_SECRET_KEY, S3_URI, S3_BUCKET, S3_ECS_NAMESPACE);
		
		System.out.println( "Enter the site:" );
        String site = new BufferedReader( new InputStreamReader( System.in ) ).readLine();
        
        System.out.println( "Enter the instrument:" );
        String instrument = new BufferedReader( new InputStreamReader( System.in ) ).readLine();
		
		//String site = "Skondal";
		//String site = "Waltham";
		//String instrument = "instrument2";
		//String instrument = "instrument2";
		//String instrument = "instrument3";
		//String instrument = "instrument4";
		//String instrument = "instrument5";
		//String instrument = "instrument6";
		
		String prefix= site+"/"+instrument+"/";
		
		String value = "Data Place Holder";
		
		//set varibles for md
		//set vairbales for prefix (and endpoint?)
		
		
		//final Logger logger = Logger.getLogger(Log4JTest.class);
		
		
        
        //s3.setS3ClientOptions(arg0);

    	// retrieve object key/value from user
       // System.out.println( "Enter the object key:" );
       // String key = new BufferedReader( new InputStreamReader( System.in ) ).readLine();
       // System.out.println( "Enter the object content:" );
      //  String content = new BufferedReader( new InputStreamReader( System.in ) ).readLine();


		
        System.out.println( "Enter the path for the File repository:" );
        String Stringfile = new BufferedReader( new InputStreamReader( System.in ) ).readLine();
        
        //Get Array of Images with Path for upload
        File[] fileList = new File(Stringfile).listFiles();
        for (int i=0;i < fileList.length;i++) {
            System.out.println(fileList[i].getName());
            String key = prefix+""+fileList[i].getName();
            
            //add S3 put here
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.addUserMetadata("filename", fileList[i].getName());
            metadata.addUserMetadata("instrument", instrument);
            metadata.addUserMetadata("channel", value);
            metadata.addUserMetadata("objective", value);
            metadata.addUserMetadata("pixelsize", value);
            metadata.addUserMetadata("wavelength", value);
            metadata.addUserMetadata("plateid", value);
            metadata.addUserMetadata("wellid", value);
            metadata.addUserMetadata("site", site);
            metadata.addUserMetadata("zplane", value);
            metadata.addUserMetadata("imgtimepoint", value);
            metadata.addUserMetadata("binning", value);
            metadata.addUserMetadata("retentionstartdate", value);
            metadata.addUserMetadata("retentionenddatate", value);
            metadata.addUserMetadata("owner", value);
            metadata.addUserMetadata("contact", value);
            metadata.addUserMetadata("organization", value);
            metadata.addUserMetadata("domain", value);
            metadata.addUserMetadata("treatment", value);
            metadata.addUserMetadata("concentration", value);
            metadata.addUserMetadata("welltype", value);
            metadata.addUserMetadata("project", value);
            metadata.addUserMetadata("area", value);
            metadata.addUserMetadata("group", value);
            metadata.addUserMetadata("modality", value);
            metadata.addUserMetadata("comments", value);


            
            
            PutObjectRequest request = new PutObjectRequest(S3_BUCKET, key, fileList[i]).withMetadata(metadata);
            
            s3.putObject(request);
            
            System.out.println(key + "was written to bucket: " + S3_BUCKET);
            
        }
        


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
