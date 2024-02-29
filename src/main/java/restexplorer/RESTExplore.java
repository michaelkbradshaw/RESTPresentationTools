package restexplorer;


import org.springframework.web.client.RestClient;

public class RESTExplore
{

	public static void main(String[] args)
	{
		RestClient client = RestClient.create();
		
		String uriBase = "http://localhost:9000/v1";
		
		
		String response = client.get()
		.uri(uriBase)
		.retrieve()
		.body(String.class);

		
		System.out.println(response);
		
		
	}

}
