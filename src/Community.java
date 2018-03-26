
import java.util.*;

public class Community {
	
	private String name;
	private String description;
	private User holder;
	private ArrayList<User> members = new ArrayList<>();
	private ArrayList<Message> messages = new ArrayList<>();
	
	public Community(String name, String description, User holder, ArrayList<User> members, ArrayList<Message> messages) {
		this.name = name;
		this.description = description;
		this.holder = holder;
		this.members = members;
		this.messages = messages;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public User getHolder() {
		return holder;
	}
	
	public void setHolder(User holder) {

		this.holder = holder;
	}
	
	public ArrayList<User> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<User> members) {
		this.members = members;
	}

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public static void createCommunity(ArrayList<Community> communities, User logged){
		
		Scanner input = new Scanner(System.in);
		String name, description;
		
		System.out.println("Digite o nome da nova comunidade:");
		name = input.nextLine();
		//Checar se não há nenhuma outra comunidade com o mesmo nome
		System.out.println("Dê uma descrição para a comunidade:");
		description = input.nextLine();

		Community newCommunity = new Community(name, description, logged, null, null);
		
		communities.add(newCommunity);
		
		System.out.println("Comunidade criada com sucesso!");
	}
	
	public static void addMember(ArrayList<Community> communities, User logged){
		
		Scanner input = new Scanner(System.in);
		String name;
		boolean exist;
		
		System.out.println("Digite o nome da comunidade que deseja participar:");
		name = input.nextLine();

		exist = checkCommunity(communities, name);

		if(exist){
		    Community current = getCommunity(communities, name);
		    current.getMembers().add(logged);
        } else{
			int choice;
			
			System.out.println("Comunidade não encontrada.");
			System.out.println("1. Deseja criar uma nova comunidade ?");
			System.out.println("2. Deseja verificar as comunidades existentes ?");
			choice = input.nextInt();
			
			if(choice == 1){
				createCommunity(communities, logged);
			} else if(choice == 2){
				printCommunity(communities);
			}
		}
	}
	
	public static boolean checkCommunity(ArrayList<Community> communities, String name){
		for(Community current : communities){
			if(current.getName().equals(name))
				return  true;
		}
		
		return false;
	}
	
	public static void printCommunity(ArrayList<Community> communities){
		for(Community current : communities){
			System.out.println(current.getName());
		}
	}

	public static Community getCommunity(ArrayList<Community> communities, String name){
		for(Community current : communities)
			if(current.getName().equals(name))
				return current;

		return null;
	}
}
