package tfip.pck.day25.day25_redisqueuedemo;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import tfip.pck.day25.day25_redisqueuedemo.services.MessagePublish;


@SpringBootApplication
@EnableAsync
public class Day25RedisqueuedemoApplication implements CommandLineRunner{

	@Autowired
	MessagePublish pub;

	public static void main(String[] args) {
		SpringApplication.run(Day25RedisqueuedemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String userID;
		String message;
		Scanner scan = new Scanner(System.in);

		while (true){
			System.out.println(">>> Enter ID for Queue: ");
			userID = scan.nextLine();

			System.out.println(">>> Enter Message for Queue: ");
			message = scan.nextLine();

			pub.queue(userID, message);
		}
		
	}

}
