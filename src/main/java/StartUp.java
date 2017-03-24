import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;

public class StartUp {
    public static void main(String[] args) throws IOException {
        AWSCredentials credentials = new BasicAWSCredentials("xxx",
                "xxx");
        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setProxyHost("tkyproxy.intra.tis.co.jp");
        clientConfig.setProxyPort(8080);
        AmazonS3Client client = new AmazonS3Client(credentials, clientConfig);

        String bucketName = "s3clienttest1";
        String key = "JP1-hearing.md";
        S3Object s3 = client.getObject(bucketName, key);
        InputStream is = s3.getObjectContent();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        System.out.println(sb.toString());

        br.close();
    }
}
