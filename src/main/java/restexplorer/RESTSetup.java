package restexplorer;


import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

public class RESTSetup
{

	public record Todo(String item,int priority) {}
	
	private record RDesc(String name,String description,String location) {};
	RestClient client;
	
	String uriBase = "http://localhost:9000/v1";
	RDesc teamDesc = new RDesc("MKB","Michael's Team",uriBase+"/MKB");
	RDesc addDesc = new RDesc("address","Personal Address",teamDesc.location()+"/address");
	RDesc todoDesc = new RDesc("todo","Todo List Items",teamDesc.location()+"/todo");
	
	RDesc A = new RDesc("A","A Team",uriBase+"/A");
	RDesc B = new RDesc("B","B Team",uriBase+"/B");
	RDesc C = new RDesc("C","C Team",uriBase+"/C");
	
	
	
	RDesc t1 = new RDesc("t1","todo",uriBase+"/MKB/todo/t1");
	RDesc t2 = new RDesc("t2","todo",uriBase+"/MKB/todo/t2");
	RDesc t3 = new RDesc("t3","todo",uriBase+"/MKB/todo/t3");

	Todo t1Data = new Todo("sleep",10);
	Todo t2Data = new Todo("eat",7);
	Todo t3Data = new Todo("crow",5);
	
	private void create(RDesc data)
	{
		
		client.post()
				.uri(data.location())
				.contentType(MediaType.APPLICATION_JSON)
				.body(data)
				.retrieve();	
	}
	
	
	private void createObject(RDesc desc, Object data)
	{
		client.post()
				.uri(desc.location())
				.contentType(MediaType.APPLICATION_JSON)
				.body(data)
				.retrieve();
		
		
	}
	
	public RESTSetup(RestClient client)
	{
		this.client = client;
		
		create(teamDesc);
		create(A);
		create(B);
		create(C);
		create(todoDesc);
		create(addDesc);
		createObject(t1,t1Data);
		createObject(t2,t2Data);
		createObject(t3,t3Data);
		
		String response = client.get()
		.uri(uriBase)
		.retrieve()
		.body(String.class);

		
		System.out.println(response);
		
		response = client.get()
				.uri(teamDesc.location())
				.retrieve()
				.body(String.class);

				
				System.out.println(response);
				
				
		response = client.get()
				.uri(todoDesc.location())
						.retrieve()
						.body(String.class);

						
		System.out.println(response);

		response = client.get()
				.uri(t1.location())
						.retrieve()
						.body(String.class);

						
		System.out.println(response);

		
	}
	
	
	public static void main(String[] args)
	{
		
		RESTSetup setup = new RESTSetup(RestClient.create());
		
	}

}
