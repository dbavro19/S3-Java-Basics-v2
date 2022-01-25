import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class _07_GetObject {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		AmazonS3 s3 = S3Factory.getS3Client();
		
    	// retrieve the key value from user
        System.out.println( "Enter the object key:" );
        String key = new BufferedReader( new InputStreamReader( System.in ) ).readLine();
        
        
        // read the object from the demo bucket
        	//offset read test
        	//byte testbyte = 1;
        	//Range bytetest = new Range(0 , 10);

        	//String content1 = s3.readObject(ECSS3Factory.S3_BUCKET, key, S);
        	
        
        
 GetObjectRequest request = new GetObjectRequest(S3Factory.S3_BUCKET, key);
        
        
        s3.getObject(request);
        
        //Pasre Input stream stuff
        
       // BufferedReader reader = new BufferedReader(new InputStreamReader(bytecontent));
      //  StringBuilder out = new StringBuilder();
      //  String line;
      //  while ((line = reader.readLine()) != null) {
      //      out.append(line);
      //  }
      //  System.out.println("THis is the byte range stuff: " + out.toString());   //Prints the string content read from input stream
     //   reader.close();
    

        
        
        // print object key/value and content for validation
    	System.out.println( String.format("object [%s/%s] content: [%s]",
                S3Factory.S3_BUCKET, key ));

	}

}
