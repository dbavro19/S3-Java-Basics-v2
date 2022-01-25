


import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider; //no 1.11-21
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.S3ClientOptions;
import org.apache.commons.codec.binary.Base64;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

/**
 * Factory class to create the ViPR S3 client.  The client will be used in the examples for the
 * Java ViPR S3 interface.
 */
public class S3Factory {


    //public static final String S3_ENDPOINT = "http://10.246.22.164:9020";
    //public static final String S3_ENDPOINT = "http://131351993663399907.public.ecstestdrive.com"; //TEST DRIVE
    //public static final String S3_ACCESS_KEY_ID = "131351993663399907@ecstestdrive.emc.com";
    //public static final String S3_SECRET_KEY = "Qgr64jxhpWBjsL1EWp1xK0u8lGTB/ZuojmGHTvfp";
    //public static final String S3_BUCKET = "metadatabucket"; 
    
    //AWS
	//public static final String S3_ENDPOINT = "http://s3.amazonaws.com"; // endpoint AWS
    //public static final String S3_ACCESS_KEY_ID = "AKIAUOSPVP5C2T6RXN6L"; // AWS
    //public static final String S3_SECRET_KEY = "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"; //AWS Secret Key
    //public static final String S3_BUCKET = "domslockedbucket";
    
    //ECS
    public static final String S3_ENDPOINT = "http://10.246.22.121:9020"; // endpoint ECS
    //public static final String S3_ACCESS_KEY_ID = "AKIAA4B217833F7A599A";
    //public static final String S3_SECRET_KEY = "dtomdFshXb9bWelb4GZ7FhhQCnYxYYpsg1hzvYaE"; //dom
    //public static final String S3_SECRET_KEY = "ge+fX9o6rm7hrM22GhV+fss3yW27xOGOtUQulGHA"; // CDVR
    //public static final String S3_BUCKET = "testtest";
    //public static final String S3_NAMESPACE = "demo";
    
    //Object Scale
    //public static final String S3_ENDPOINT = "http://10.246.152.175:80"; // endpoint ECS
    public static final String S3_ACCESS_KEY_ID = "dom";
    //public static final String S3_SECRET_KEY = "hRdqauOJiwpY6kbx782FFediQJJ89Op8T12ahK9M"; //dom
    public static final String S3_SECRET_KEY = "hRdqauOJiwpY6kbx782FFediQJJ89Op8T12ahK9M"; // 3.6.2 IAM
    public static final String S3_BUCKET = "bucket1";
    //public static final String S3_NAMESPACE = "demo";
    

    public static final String S3_VERSIONBUCKET = "lockingbucket";


    /*
     * The end point of the ViPR S3 REST interface - this should take the form of
     * http://ecs-address:9020 or https://ecs-address:9021
     * or
     * https://object.ecstestdrive.com
     * if you're using ECS Test Drive.  Note that you'll need to install the RSA root certificate in Java's
     * cacerts file if you're ECS Test Drive:
     * https://portal.ecstestdrive.com/Content/ECS%20Test%20Drive%20SSL%20issues%20with%20Java.docx
     * or run the InstallCert program in the tools directory:
     * java -jar installcert-usn-20140115.jar object.ecstestdrive.com:443
     */
    //public static AmazonS3Client getS3Client() {
    public static AmazonS3 getS3Client() {
        BasicAWSCredentials creds = new BasicAWSCredentials(S3_ACCESS_KEY_ID, S3_SECRET_KEY); 

  /*
  //old v1.10 client
        ClientConfiguration cc = new ClientConfiguration();
        //cc.setProxyHost("localhost");
        //cc.setProxyPort(8888);
        // Force use of v2 Signer.
        //cc.setSignerOverride("S3SignerType");
		AmazonS3Client client = new AmazonS3Client(creds, cc);
        client.setEndpoint(S3_ENDPOINT);
*/


  // 1.11-100 good standard/basic with v4 auth doesn't work with 1.11-21 though  ADDED PATH STLYE
        AwsClientBuilder.EndpointConfiguration ec = new AwsClientBuilder.EndpointConfiguration(S3_ENDPOINT,"us-east-1"); //ECS endpoint config
        //AwsClientBuilder.EndpointConfiguration ec = new AwsClientBuilder.EndpointConfiguration("s3.us-east-1.amazonaws.com", "us-east-1"); //AWS config endpoint
        AmazonS3 client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(creds))
                .withEndpointConfiguration(ec)
                .withPathStyleAccessEnabled(true)
                .disableChunkedEncoding()
                .build();
        
        
        
        //AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1)
        //		.withCredentials(credentialsProvider)
        //		.withCredentials(creds)
        //		.withPathStyleAccessEnabled(true)
        //		.build();


/*
// new client,but using v2 auth
        ClientConfiguration cc = new ClientConfiguration();
        cc.setSignerOverride("S3SignerType");

        AmazonS3ClientBuilder.EndpointConfiguration ec = new AmazonS3ClientBuilder.EndpointConfiguration(S3_ENDPOINT,"us-east-1");
        AmazonS3 client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(creds))
                .withClientConfiguration(cc)
                .withEndpointConfiguration(ec).withPathStyleAccessEnabled(true)
                .build();
*/




/*
//new client but deprecated way of using path style bucket naming
        S3ClientOptions opts = new S3ClientOptions();
        opts.setPathStyleAccess(true);
        client.setS3ClientOptions(opts);
  */

		return client;
    }

    // Generates a RSA key pair for testing.
    public static void main(String[] args) {
        try {
            KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
            keyGenerator.initialize(1024, new SecureRandom());
            KeyPair myKeyPair = keyGenerator.generateKeyPair();

            // Serialize.
            byte[] pubKeyBytes = myKeyPair.getPublic().getEncoded();
            byte[] privKeyBytes = myKeyPair.getPrivate().getEncoded();

            String pubKeyStr = new String(Base64.encodeBase64(pubKeyBytes, false), "US-ASCII");
            String privKeyStr = new String(Base64.encodeBase64(privKeyBytes, false), "US-ASCII");

            System.out.println("Public Key: " + pubKeyStr);
            System.out.println("Private Key: " + privKeyStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
