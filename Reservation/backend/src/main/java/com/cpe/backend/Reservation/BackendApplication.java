package com.cpe;
import com.cpe.backend.Reservation.entity.Reservation;
import com.cpe.backend.Reservation.entity.Fieldtype;
import com.cpe.backend.Reservation.entity.Fielduse;
import com.cpe.backend.Reservation.entity.Member;
import com.cpe.backend.Reservation.entity.Employee;
import com.cpe.backend.Reservation.repository.ReservationRepository;
import com.cpe.backend.Reservation.repository.FieldtypeRepository;
import com.cpe.backend.Reservation.repository.FielduseRepository;
import com.cpe.backend.Reservation.repository.MemberRepository;
import com.cpe.backend.Reservation.repository.EmployeeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import java.util.stream.Stream;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	ApplicationRunner init(ReservationRepository reservationRepository, FieldtypeRepository fieldtypeRepository, FielduseRepository fielduseRepository, MemberRepository memberRepository,EmployeeRepository employeeRepository) {
		return args -> {
			
			Stream.of("สนามกรีฑา", "สนามว่ายน้ำ", "สนามฟุตบอล","สนามวอลเล่ย์บอล","สนามเซปักตะกร้อ","สนามเทนนิส","สนามเทเบิลเทนนิส","สนามแบตนิสตัน","สนามเปตอง"
			,"สนามวอลเล่ย์บอลชายหาด","สนามฟุตซอล").forEach(name -> {
				Fieldtype fieldtype = new Fieldtype(); // สร้าง Object Customer
				fieldtype.setName(name); // set ชื่อ (name) ให้ Object ชื่อ Customer
				fieldtypeRepository.save(fieldtype); // บันทึก Objcet ชื่อ Customer
			});
			Stream.of("แข่งขัน", "ปกติ").forEach(name -> {
				Fielduse fielduse = new Fielduse(); // สร้าง Object Video
				fielduse.setName(name); // set ชื่อ (name) ให้ Object ชื่อ Video
				fielduseRepository.save(fielduse); // บันทึก Objcet ชื่อ Video
			});

			Stream.of("Nutthnicha", "Watthana", "Sasiprapa").forEach(name -> {
				Employee employee = new Employee(); // สร้าง Object Video
				employee.setName(name); // set ชื่อ (name) ให้ Object ชื่อ Video
				employeeRepository.save(employee); // บันทึก Objcet ชื่อ Video
			});

			Stream.of("Nutthnicha", "Watthana", "Sasiprapa").forEach(name -> {
				Member member = new Member(); // สร้าง Object Video
				member.setName(name); // set ชื่อ (name) ให้ Object ชื่อ Video
				memberRepository.save(member); // บันทึก Objcet ชื่อ Video
			});

			memberRepository.findAll().forEach(System.out::println); // แสดง ข้อมูลทั้งหมดใน Entity Customer บน Terminal
			fieldtypeRepository.findAll().forEach(System.out::println); // แสดง ข้อมูลทั้งหมดใน Entity RentalType บน Terminal
			fielduseRepository.findAll().forEach(System.out::println); // แสดง ข้อมูลทั้งหมดใน Entity Video บน Terminal
			employeeRepository.findAll().forEach(System.out::println); // แสดง ข้อมูลทั้งหมดใน Entity Customer บน Terminal
		};
	}

}