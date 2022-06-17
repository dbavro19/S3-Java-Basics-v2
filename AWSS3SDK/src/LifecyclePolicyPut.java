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

public class LifecyclePolicyPut {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AmazonS3 s3 = S3Factory.getS3Client();
		
		

		BucketLifecycleConfiguration.Rule rule1 = new BucketLifecycleConfiguration.Rule()
                .withId("Prefix expiration active 61")
                .withFilter(new LifecycleFilter(new LifecyclePrefixPredicate("nested/prefix/example/"))) //example of a lifecycle policy applying selectively to a prefix
                .withExpirationInDays(61) // would expire active data after 60 days
                .withStatus(BucketLifecycleConfiguration.ENABLED);
        
		BucketLifecycleConfiguration.Rule rule2 = new BucketLifecycleConfiguration.Rule()
                .withId("Tag non current expiration 365")
                .withFilter(new LifecycleFilter(new LifecycleTagPredicate(new Tag("exampletag", "examplevalue")))) // example of a lifecycle policy applying selectively to an object tag
                .withNoncurrentVersionExpirationInDays(364)
                .withStatus(BucketLifecycleConfiguration.ENABLED);
        
		BucketLifecycleConfiguration configuration = new BucketLifecycleConfiguration() // Add rules to BucketLifecycle COnfiguration via Array List (if adding multiple)
                .withRules(Arrays.asList(rule1, rule2));
		
		//BucketLifecycleConfiguration config = new BucketLifecycleConfiguration() Adding a single rule
		//		.withRules(rule1);

		s3.setBucketLifecycleConfiguration(S3Factory.S3_BUCKET, configuration); // Apply the configruation to the specified bucket
       
        System.out.println("Done");


	}
	
	public static void getLifecycleConfig()
	{

		
	}

}

