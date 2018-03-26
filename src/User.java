
import java.util.*;

public class User {

	private String name;
	private Register datas;
	private ArrayList<User> friends = new ArrayList<>();
	private ArrayList<Message> messages = new ArrayList<>();
	
	public User(String name, Register datas, ArrayList<User> users, ArrayList<Message> messages) {
		this.name = name;
		this.datas = datas;
		this.friends = friends;
		this.messages = messages;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

    public Register getDatas() {
        return datas;
    }

    public void setDatas(Register datas) {
        this.datas = datas;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public static User createUser(){
		
		Scanner input = new Scanner(System.in);
		String name, login, password, email;
		
		System.out.println("Digite o nome do novo usuário:");
		name = input.nextLine();
		System.out.println("Digite um login:");
		login = input.nextLine();
		//Fazer uma função pra checar se esse login já existe
		System.out.println("Digite uma senha:");
		password = input.nextLine();
		System.out.println("Digite um email:");
		email = input.nextLine();
		
		Register datas = new Register(login, password, email);
		
		User newUser = new User(name, datas, null, null);
		
		System.out.println("Usuário cadastrado com sucesso!");
		
		return newUser;
	}
	
	public static boolean checkUser(ArrayList<User> users, String name){
		for(User current : users){
			if(current.getName().equals(name))
				return true;
		}
		return false;
	}

	public static void removeUser(ArrayList<User> users, User logged){
		
		Scanner input = new Scanner(System.in);
		String password;

		System.out.println("Por favor, confirme a sua senha:");
		password = input.nextLine();

        if(Register.checkPassword(users, password)) {
            users.remove(logged);
            System.out.println("Conta deletada com sucesso!");
        } else System.out.println("Senha incorreta");
	}

    public static void addFriend(ArrayList<User> users, User logged){
        Scanner input = new Scanner(System.in);
        String name;
        boolean exist = false;

        System.out.println("Digite o nome do usuário que deseja adicionar a lista de amigos:");
        name = input.nextLine();

        exist = checkUser(users, name);

        if(exist){
            User current = getUser(users, name);
            Message newMessage = new Message(logged, "Convite de amizade", "null", false);
            current.getMessages().add(newMessage);
        } else System.out.println("Usuário não encontrado!");
    }

    public static User getUser(ArrayList<User> users, String name){
        for(User current : users)
            if(current.getName().equals(name))
                return current;

        return null;
    }

}