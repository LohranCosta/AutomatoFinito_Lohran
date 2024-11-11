import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		VerificadorSentenca arquivoAutomato = new VerificadorSentenca();
		arquivoAutomato.carregaCsv("D:\\LFA\\AutomatoFinito_Lohran\\Arquivo.csv");

		String sentenca = "abbba";

		boolean status = arquivoAutomato.validarAutomato(sentenca);
		System.out.println("\nA sentença '" + sentenca + "' foi " + (status ? "aceita." : "rejeitada."));
	}
}