package AgendaViral;

public class Main {

	public static void main(String[] args) {
		Agenda agenda = new Agenda();
		Interface gui = new Interface(agenda);
		gui.loginMenu();
	}
}
