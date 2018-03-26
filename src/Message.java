
import com.sun.javafx.tools.packager.Main;

import java.util.*;


public class Message {

    public enum ChoiceDestinatary{

        COMMUNITY(1), USER(2);

        private final int choice;

        private ChoiceDestinatary(int choice){
            this.choice = choice;
        }

        public int getChoiceDestinatary(){
            return choice;
        }

    }

    public enum Choice{

        ACCEPT(1), REJECT(2);

        private final int choice;

        private Choice(int choice){
            this.choice = choice;
        }

        public int getChoice(){
            return choice;
        }

    }

	private User sender;
	private String subject;
	private String content;
	private boolean readed = false;

    public Message(User sender, String subject, String content, boolean readed) {
        this.sender = sender;
        this.subject = subject;
        this.content = content;
        this.readed = readed;
    }

    public boolean isReaded() {
        return readed;
    }

    public void setReaded(boolean readed) {
        this.readed = readed;
    }

    public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static void sendMessage(ArrayList<User> users, ArrayList<Community> communities, User logged){
		int choice;
		String name, content, subject;
		boolean exist;
		Scanner input = new Scanner(System.in);

		System.out.println("1. Enviar mensagem para um usuário:");
		System.out.println("2. Enviar mensagem para uma comunidade: ");
		choice = input.nextInt();
		System.out.println("Digite o nome do usuário/Comunidade:");
		name = input.nextLine();

		if(choice == ChoiceDestinatary.COMMUNITY.getChoiceDestinatary())
			exist = Community.checkCommunity(communities, name);
		else  exist = User.checkUser(users, name);

		if(exist){
			System.out.println("Digite o assunto da mensagem:");
			subject = input.nextLine();
			System.out.println("Digite a mensagem:");
			content = input.nextLine();
			addMessage(communities, users, choice, name, subject, content, logged);
			System.out.println("Mensagem enviada com sucesso!");

		} else System.out.println("Destinatário não encontrado!");
	}

    public static void addMessage(ArrayList<Community> communities, ArrayList<User> users,
                                  int choice, String name ,String subject, String content, User logged){

        if(choice == ChoiceDestinatary.COMMUNITY.getChoiceDestinatary()){
            Community current = Community.getCommunity(communities, name);
            Message newMessage = new Message(logged, subject, content, false);
            current.getMessages().add(newMessage);

        } else if(choice == ChoiceDestinatary.USER.getChoiceDestinatary()){
            User current = User.getUser(users, name);
            Message newMessage = new Message(logged, subject, content, false);
            current.getMessages().add(newMessage);
        }

    }

    public static void readMessage(User logged, ArrayList<User> users){
        Scanner input = new Scanner(System.in);
        int choice;

        for(Message current : logged.getMessages()){
            if(!current.isReaded()){
                if(current.getSubject().equals("Convite de amizade")){
                    System.out.println(current.getSender() + " deseja adicionar você a sua lista de amigos\n1. Aceitar         2. Rejeitar");
                    choice = input.nextInt();

                    if(choice == Choice.ACCEPT.getChoice()){
                        logged.getFriends().add(User.getUser(users, current.getSender().getName()));
                        current.getSender().getFriends().add(logged);
                        System.out.println("Pedido de amizade aceito!");
                        Message newMessage = new Message(logged, "Pedido de amizade", "Seu pedido de amizade foi aceito", false);
                        current.getSender().getMessages().add(newMessage);

                    } else System.out.println("Pedido de amizade rejeitado!");


                } else System.out.println(current);
                current.setReaded(true);
            }

        }
    }

    public String toString(User sender, String subject, String content){
        return String.format("Remetente: %s\nAssunto: %s\nMensagem: %s\n", sender.getName(), subject, content);
    }
}
