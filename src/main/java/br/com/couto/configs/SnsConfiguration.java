package br.com.couto.configs;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

public class SnsConfiguration {
	@Value("${cloud.aws.credentials.access-key}")
	private String accessKey;

	@Value("${cloud.aws.credentials.secret-key}")
	private String secretKey;
	
	@Value("${cloud.aws.region.static}")
	private String region;
	
	@Bean
	@Primary
	public AmazonSNSClient amazonSNSClient() {
		
		BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		
		return (AmazonSNSClient) AmazonSNSClientBuilder
				.standard()
				.withRegion(region)
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.build();
	}
	
}
