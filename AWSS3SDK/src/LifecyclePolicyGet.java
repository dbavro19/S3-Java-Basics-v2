import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.BucketLifecycleConfiguration;
import com.amazonaws.services.s3.model.BucketPolicy;
import com.amazonaws.services.s3.model.Tag;
import com.amazonaws.services.s3.model.lifecycle.LifecycleFilter;
import com.amazonaws.services.s3.model.lifecycle.LifecycleFilterPredicate;
import com.amazonaws.services.s3.model.lifecycle.LifecyclePrefixPredicate;
import com.amazonaws.services.s3.model.lifecycle.LifecycleTagPredicate;

public class LifecyclePolicyGet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AmazonS3 s3 = S3Factory.getS3Client();
		
		
		
		BucketLifecycleConfiguration configuration = s3.getBucketLifecycleConfiguration(S3Factory.S3_BUCKET);
		
		System.out.println("Number of Rules Found: " + configuration.getRules().size());
		
		for (int x = 0; x < configuration.getRules().size(); x++ )
		{
			

			
			System.out.println();
			System.out.println("Policy ID: " + configuration.getRules().get(x).getId());
			System.out.println("Status: " + configuration.getRules().get(x).getStatus());
			System.out.println("Non CUrrent Version Expiration in Days: " + configuration.getRules().get(x).getNoncurrentVersionExpirationInDays());
			System.out.println("Current Object Expiration in Days: " + configuration.getRules().get(x).getExpirationInDays());

			try {
				LifecyclePrefixPredicate prefix = (LifecyclePrefixPredicate) configuration.getRules().get(x).getFilter().getPredicate();
				System.out.println("Prefix: " + prefix.getPrefix());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Prefix: ");
			}
			
			try {
				LifecycleTagPredicate tagfilter = (LifecycleTagPredicate) configuration.getRules().get(x).getFilter().getPredicate();
				Tag tag = tagfilter.getTag();
				System.out.println("Tag Key: " + tag.getKey() + " | Tag Value: " + tag.getValue());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Tag: ");
			}
		
			
			
			
		}
		
		
		
	}

}
