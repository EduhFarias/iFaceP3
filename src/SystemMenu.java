import java.util.*;

public class SystemMenu {

    public static void initialization(){
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Community> communities = new ArrayList<>();
        User logged = null;

        Scanner input = new Scanner(System.in);
        int choice = 1;

        while(choice != 0){
            System.out.println("1. Fazer login\n2. Criar uma conta\n0. Encerrar\n");
            choice = input.nextInt();

            switch (choice){
                case 1:
                    logged = login(users, logged);
                    break;
                case 2:
                    User newUser = User.createUser();
                    users.add(newUser);
                    break;
                case 0:
                    break;
            }

            if(logged != null){
                int condition = 1;
                while(condition != 0){
                    menu();
                    condition = input.nextInt();

                    switch (condition){
                        case 1:
                            Register.CustomizeData(logged);
                            break;
                        case 2:
                            User.addFriend(users, logged);
                            break;
                        case 3:
                            Message.sendMessage(users, communities, logged);
                            break;
                        case 4:
                            Community.createCommunity(communities, logged);
                            break;
                        case 5:
                            Community.addMember(communities, logged);
                            break;
                        case 6:
                            Register.RecoverData(communities, logged);
                            break;
                        case 7:
                            User.removeUser(users, logged);
                            break;
                        case 0:
                            logged = null;
                            break;
                    }
                }
            }

        }
    }

    public static User login(ArrayList<User> users, User logged){
        Scanner input = new Scanner(System.in);
        String login, password, email;

        System.out.println("Digite seu login: ");
        login = input.nextLine();
        System.out.println("Digite uma senha:");
        password = input.nextLine();

        boolean existLogin = Register.checkLogin(users, login);
        boolean existPassword = Register.checkPassword(users, password);
        if(existLogin && existPassword){
            for(User current : users)
                if(current.getDatas().getLogin().equals(login))
                    return current;
        }
        System.out.println("Login e/ou senha incorretos.");
        return null;

    }

    public static void menu(){
        System.out.println("1. Editar perfil\n2. Adicioanar amigo\n3. Enviar mensagem\n" +
                "4. Criar comunidade\n5. Participar de uma comunidade\n" +
                "6. Recuperar dados\n 7. Deletar conta\n0. Fazer logout");
    }
}
