import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
        while (continuar){
            System.out.print("Copie Ou Digite o Caminho do Seu Arquivo : "); //testar com o arquivo na msm pasta
            try {
                String path = sc.nextLine();
                System.out.print("Qual o Formato do Seu Arquivo Atualmente(Formatos Compativeis = PDF,TXT,DOCX): ");
                String origin = sc.nextLine().trim().toLowerCase();
                System.out.print("Para Qual Formato Deseja Converter (1 = pdf | 2 = txt | 3 = docx | 4 = pptx ): ");
                String finale = sc.nextLine();

                String baseDir = new File(Main.class.getProtectionDomain()
                        .getCodeSource()
                        .getLocation()
                        .toURI()).getParent();

                String pyExe = baseDir + File.separator + "scripts" + File.separator + "main.exe";

                ProcessBuilder ps = new ProcessBuilder(pyExe, finale, origin, path);
                ps.redirectErrorStream(true);
                Process process = ps.start();
                try (BufferedReader ln = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line = ln.readLine();
                    System.out.println(line);
                }
                System.out.print("Deseja Converter Outro Arquivo? (s/n): ");
                String answer = sc.nextLine().trim().toLowerCase();

                if (!answer.equals("s")) {
                    continuar = false;
                }

            } catch (RuntimeException e) {
                System.out.print("Erro Ao Converter o Arquivo:");
                e.printStackTrace();
            } catch (URISyntaxException e) {
                System.out.print("Erro Ao Converter o Arquivo:");
            }
        }


        sc.close();


    }
}