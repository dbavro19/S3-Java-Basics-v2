import java.util.Arrays;
import java.util.List;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.BucketLifecycleConfiguration;
import com.amazonaws.services.s3.model.BucketLifecycleConfiguration.Rule;
import com.amazonaws.services.s3.model.BucketPolicy;
import com.amazonaws.services.s3.model.Tag;
import com.amazonaws.services.s3.model.lifecycle.LifecycleFilter;
import com.amazonaws.services.s3.model.lifecycle.LifecyclePrefixPredicate;
import com.amazonaws.services.s3.model.lifecycle.LifecycleTagPredicate;

public class LifecyclePolicyDelete {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AmazonS3 s3 = S3Factory.getS3Client();
		
		
		

			try {
				BucketLifecycleConfiguration configuration = s3.getBucketLifecycleConfiguration(S3Factory.S3_BUCKET);
				System.out.println("Number of Rules Found: " + configuration.getRules().size());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("No Rules Found");
			}
			
			
    		
    		try {
				s3.deleteBucketLifecycleConfiguration(S3Factory.S3_BUCKET);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("No Configuration Found");;
			}

    		
    		
			try {
				BucketLifecycleConfiguration configuration2 = s3.getBucketLifecycleConfiguration(S3Factory.S3_BUCKET);
				System.out.println("Number of Rules Found: " + configuration2.getRules().size());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("No Rules Found");
			}
	}

}

