import java.io.BufferedReader;
import java.io.InputStreamReader;

public class main{
    public static void main(String[] args){
        db.connect();
        Cliente c = new Cliente();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean next;

        System.out.print("\nDigite os dados do cliente a serem inseridos no banco.\nNome: ");
        do{
            next = false;
            try {
                c.nome = br.readLine();
                next = true;
            } catch (Exception e) {
                System.out.print("Você digitou algo inválido, tente de novo.\nNome: ");
            }
        } while(!next);

        System.out.print("Idade: ");
        do{
            next = false;
            try {
                c.idade = Integer.parseInt(br.readLine());
                next = true;
            } catch (Exception e) {
                System.out.print("Você digitou algo inválido, tente de novo.\nIdade: ");
            }
        } while(!next);

        System.out.print("Telefone: ");
        do{
            next = false;
            try {
                c.telefone = br.readLine();
                next = true;
            } catch (Exception e) {
                System.out.print("Você digitou algo inválido, tente de novo.\nTelefone: ");
            }
        } while(!next);

        if(db.insert(c)) System.out.println("[Main] All data has been inserted successfully.");
        else System.out.println("[Main] Failed to insert data on DB.");

        db.show();

        System.out.print("Agora digite o nome de um cliente para ser alterado.\nNome: ");
        do{
            next = false;
            try {
                c.nome = br.readLine();
                next = true;
            } catch (Exception e) {
                System.out.print("Você digitou algo inválido, tente de novo.\nNome: ");
            }
        } while(!next);

        System.out.print("Idade: ");
        do{
            next = false;
            try {
                c.idade = Integer.parseInt(br.readLine());
                next = true;
            } catch (Exception e) {
                System.out.print("Você digitou algo inválido, tente de novo.\nIdade: ");
            }
        } while(!next);

        System.out.print("Telefone: ");
        do{
            next = false;
            try {
                c.telefone = br.readLine();
                next = true;
            } catch (Exception e) {
                System.out.print("Você digitou algo inválido, tente de novo.\nTelefone: ");
            }
        } while(!next);

        if(db.update(c)) System.out.println("[Main] All data has been updated successfully.");
        else System.out.println("[Main] Failed to update data on DB.");;
        db.show();

        String nome=null;
        System.out.print("Por fim, digite um nome para ser deletado.\nNome: ");
        do{
            next = false;
            try {
                nome = br.readLine();
                next = true;
            } catch (Exception e) {
                System.out.print("Você digitou algo inválido, tente de novo.\nNome: ");
            }
        } while(!next);

        if(db.delete(nome)) System.out.println("[Main] Data has been successfully deleted.");
        else System.out.println("[Main] Failed to delete data on DB.");
        db.show();
    }
}
