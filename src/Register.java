
import java.util.*;

public class Register {
	
	private String login;
	private String password;
	private String email;
	
	public Register(String login, String password, String email) {
		this.login = login;
		this.password = password;
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public static boolean checkLogin(ArrayList<User> users, String login){
	    for(User current : users)
	        if(current.getDatas().getLogin().equals(login))
	            return true;

	    return false;
    }

    public static boolean checkPassword(ArrayList<User> users, String password) {
        for(User current : users)
            if(current.getDatas().getPassword().equals(password))
                return true;

        return false;
    }

	public static void RecoverData(ArrayList<Community> communities, User logged){
		Scanner input = new Scanner(System.in);
		int choice;
		String condition;

		System.out.println("Que tipo de informação deseja recuperar ?\n1. Dados do perfil\n2. Comunidades\n3. Amigos\n4. Mensagens\n5. Voltar");
		choice = input.nextInt();

		switch (choice){
			case 1:
				System.out.println(logged.getDatas());
				break;
			case 2:
				for(Community current : communities){
					System.out.println(current);
					System.out.println("Deseja ver os membros dessa comunidade ? Yes       No");
					condition = input.nextLine();
					if(condition.equals("Yes"))
						for(User members : current.getMembers())
                            System.out.println(members.getName());
				}
				break;
			case 3:
				for(User current : logged.getFriends())
					System.out.println(current.getName());
				break;
			case 4:
				for(Message current : logged.getMessages())
					System.out.println(current);
				//Deseja responder a mensagem ? YES    Nome
				//sendMessage()
				break;
			case 5:
				break;
		}
	}

	public static void CustomizeData(User logged){
		int choice;
		Scanner input = new Scanner(System.in);
		String option;

		System.out.println("1. Criar novos dados para o perfil\n2. Editar dados");
		choice = input.nextInt();

		if(choice == 1){
			System.out.println("Digite o novo do novo atributo:");
			option = input.nextLine();
		} else{
			System.out.println("Qual atributo deseja alterar ?");
			option = input.nextLine();
		}
	}

	public String toString(String name, String email, String password){
		return String.format("Nome: %s\nEmail: %s\nSenha: %s", name, email, password);
	}
	
}
