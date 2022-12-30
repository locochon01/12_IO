package edu.kh.io.model.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IOService {

	
	//IO
	
	// Input (입력) : 외부에서 내부로 데이터를 들여오는 것
	// Output (출력) : 내부에서 외부로 데이터를 내보내는 것
	
	// Stream : 입/출력 통로 역할 (데이터가 흘러가는 통로)
	//			기본적으로 Stream은 단방향 ( 일방통행 )
	
	// 1) 파일 출력(내부 == 프로그램, 외부 == 파일)
	public void output1() {
		
		FileOutputStream fos = null;
		
		// FileOutputStream fos = new FileOutputStream("test1.txt");
		// Unhandled exception type FileNotFoundException 출력할 파일이 없을 수도 있다는 예외
		// => FileOutputStream 객체 생성 시 
		// FileNotFoundException 예외가 발생할 가능성이 있음 -> 예외처리 필요
		
		try {
			fos = new FileOutputStream("test1.txt");
			// 현재 프로그램에서 
			// test1.txt 파일로 출력하는 통로 객체 생성
			
			// 이 파일은 목적지가 필요함
			// -> 12_IO 지금 해당된 프로젝트 폴더가 기본 목적지로 설정되어있음
			
			// 파일에 "hello" 내보내기
			String str = "hello"; // 한 문자씩 끊어 보내기
			
			for(int i = 0; i < str.length(); i++) {
				
				// System.out.println( str.charAt(i));
				//"Hello"를 한 글자씩 끊어서 파일로 출력하기
				fos.write(str.charAt(i));
				// unhandled exception type IOException 
				// Write는 IOexception 발생 가능성이 있다.
				
				
			}	
			
		} catch (IOException e) {
			// FileNotFoundException은 IOException의 자식임
			// 다형성 적용으로 IOException 하나로 둘 다 잡을 수 있다
			System.out.println("예외 발생");
			e.printStackTrace(); // 예외 추적
			
		} finally {
			//예외 발생 유무에 상관없이 무조건 수행
			// 불필요한 통로 삭제
			//-> 자원 반환
			// 사용한 스트림 자원반환(통로없앰) --> 필수 작성!!!
			// -> 작성 안하면 문제점으로 꼽을 수 있음 ***
			
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	// 2) 파일 입력 (외부 -> 내부 프로그램 읽어오기)
	public void input1() {
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream("test1.txt");
			
			//FileInputStream은 1byte씩만 읽어올 수 있음
			while(true) {
				
				int data = fis.read(); // 다음 1 byte를 읽어 오는데 정수형
							// 다음 내용이 없으면 -1 반환
				
				if(data == -1) { // 다음 내용 없음 -> 종료
					break;
				}
				
				// 반복 종료가 안됐으면 char로 강제 형변환하여 문자로 출력
				System.out.println((char)data);
				
			}
			
		}catch(IOException e) {
			e.printStackTrace(); // 예외주척
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//----------------------------------
	
	//문자 기반 스트림
	
	// 3) 파일 출력 (문자기반 스트림)
	public void output2() {
		
		FileWriter fw = null; // 프로그램 -> 파일로 쓰는 문자기반 스트림
		
		try {
			
			fw = new FileWriter("test1.txt",true); // 외부파일과 연결하는 스트림 객체 생성
			// 매개변수에 , true를 쓰면 계속 이어쓰기 가능
			
			String str = "안녕하세요. Hello. 1234 !#"; // 통째로 보내기 위해 Buffer 를 사용
			
			fw.write(str); // 
			
		}catch(IOException e) {
			e.printStackTrace();
			
		}finally {
			try {
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	// 4) 파일 입력 
	public void input2() {
		FileReader fr = null;
		
		try {
			fr = new FileReader("test1.txt");
			
			while(true) {
				int data = fr.read();
				
				if (data == -1) {
					break;
				}
				
				System.out.print((char)data);
				
			}
		}catch(IOException e) {
			e.printStackTrace();
			
		}finally {
			try {
				fr.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
