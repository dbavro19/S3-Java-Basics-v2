import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class _08_ListObjects {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		AmazonS3 s3 = S3Factory.getS3Client();
		
		ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
			    .withBucketName(S3Factory.S3_BUCKET)
			    .withPrefix("")
			    ;
			ObjectListing objectListing;

			do {
			        objectListing = s3.listObjects(listObjectsRequest);
			        for (S3ObjectSummary objectSummary : 
			            objectListing.getObjectSummaries()) {
			            System.out.println( " - " + objectSummary.getKey() + "  " +
			                    "(size = " + objectSummary.getSize() + 
			                    ")");
			        }
			        listObjectsRequest.setMarker(objectListing.getNextMarker());
			} while (objectListing.isTruncated());

	}

}
